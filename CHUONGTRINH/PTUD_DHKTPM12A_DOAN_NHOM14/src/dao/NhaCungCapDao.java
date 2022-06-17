package dao;

import entity.NhaCungCap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class NhaCungCapDao {
    private ObservableList<NhaCungCap> list;
    private static Connection con = null;
    private static PreparedStatement pst = null;
    private static ResultSet rs = null;
    private ArrayList<String> dSMNCC;

    public NhaCungCapDao() {
        con = DBConnection.getConnection();
        dSMNCC = new ArrayList<String>();
    }

    public ObservableList<NhaCungCap> loadData(){
        try {
            list = FXCollections.observableArrayList();
            pst = con.prepareStatement("SELECT * FROM NHACUNGCAP");
            rs = pst.executeQuery();
            while (rs.next()){
                list.add(new NhaCungCap(""+rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5)));
            }
        } catch (SQLException e) {
//            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Không thể kết nối dữ liệu");
            //alert.setContentText("");
            Optional<ButtonType> BT=alert.showAndWait();
        }
        return list;
    }

    public int loadMaNCC(){
        int n=0;
        try {
            list = FXCollections.observableArrayList();
            con = DBConnection.getConnection();
            pst = con.prepareStatement("SELECT max(maNhaCungCap) FROM NhaCungCap ");
            rs = pst.executeQuery();
            while (rs.next()){
                n=rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    public String loadTenNCC(String maNhaCungCap){
        String n="";
        try {
            list = FXCollections.observableArrayList();
            con = DBConnection.getConnection();
            pst = con.prepareStatement("SELECT tenNhaCungCap FROM NhaCungCap Where maNhaCungCap="+maNhaCungCap);
            rs = pst.executeQuery();
            while (rs.next()){
                n=rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    public ArrayList<String> loadDSMaNCC(){
        try {
            con = DBConnection.getConnection();
            pst = con.prepareStatement("SELECT maNhaCungCap FROM NhaCungCap");
            rs = pst.executeQuery();
            while (rs.next()){
                dSMNCC.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dSMNCC;
    }

    public boolean create(String tenNhaCungCap, String soDienThoai, String email, String diaChi){
        int n = 0;
        String sql = "INSERT INTO NHACUNGCAP(tenNhaCungCap,soDienThoai,email,diaChi) VALUES(?,?,?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1,tenNhaCungCap);
            pst.setString(2,soDienThoai);
            pst.setString(3,email);
            pst.setString(4,diaChi);
            n = pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Lưu thành công");
            //alert.setContentText("");
            Optional<ButtonType> BT=alert.showAndWait();
        } catch (SQLException e) {
//            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText("Vui lòng nhập đầy đủ thông tin");
            //alert.setContentText("");
            Optional<ButtonType> BT=alert.showAndWait();
        }
        return n > 0;
    }

    public boolean update(String maNhaCungCap, String tenNhaCungCap, String soDienThoai, String email, String diaChi){
        int n = 0;
        String sql = "UPDATE NHACUNGCAP SET  tenNhaCungCap = ?, soDienThoai = ?, email = ?, diaChi = ? WHERE maNhaCungCap = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1,tenNhaCungCap);
            pst.setString(2,soDienThoai);
            pst.setString(3,email);
            pst.setString(4,diaChi);
            pst.setString(5,maNhaCungCap);
            n = pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Lưu thành công");
            //alert.setContentText("");
            Optional<ButtonType> BT=alert.showAndWait();
        } catch (SQLException e) {
//            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText("Vui lòng nhập đầy đủ thông tin");
            //alert.setContentText("");
            Optional<ButtonType> BT=alert.showAndWait();
        }
        return n > 0;
    }

    public boolean delete(String maNhaCungCap) {
        int n = 0;
        String sql = "DELETE FROM NHACUNGCAP WHERE maNhaCungCap = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1,maNhaCungCap);
            int i = pst.executeUpdate();
            n = pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Xóa thông tin thành công");
            //alert.setContentText("");
            Optional<ButtonType> BT=alert.showAndWait();
        } catch (SQLException e) {
//            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Không thể xóa thông tin được, vì nhà cung cấp đã cung cấp mặt hàng");
            //alert.setContentText("");
            Optional<ButtonType> BT=alert.showAndWait();
        }
        return n > 0;
    }

    public ObservableList<NhaCungCap> search(String tenNhaCungCap){
        String sql = "SELECT * FROM NHACUNGCAP WHERE tenNhaCungCap LIKE '%" + tenNhaCungCap + "%'";
        try{
            list = FXCollections.observableArrayList();
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                list.add(new NhaCungCap(""+rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    // xuat ra file excel trong ổ đĩa E
    public  static void  exportToExcel() throws IOException {
        con = DBConnection.getConnection();

        try {
            pst = con.prepareStatement("SELECT Top 100 * FROM NHACUNGCAP");
            rs = pst.executeQuery();
            //            // tao workbook
            HSSFWorkbook workbook = new HSSFWorkbook();
            // System.out.println("Xuat thanh cong12");
            // tao ten
            HSSFSheet sheet = workbook.createSheet("THÔNG TIN NHÀ CUNG CẤP");
            HSSFRow hearder = sheet.createRow(0);// tao ô

            // tao hearder
            hearder.createCell(0).setCellValue("Mã nhà cung cấp");
            hearder.createCell(1).setCellValue("Tên nhà cung cấp");
            hearder.createCell(2).setCellValue("Số điện thoại nhà cung cấp");
            hearder.createCell(3).setCellValue("Email nhà cung cấp");
            hearder.createCell(4).setCellValue("Địa chỉ nhà cung cấp");
            // duyet du lieu write ra file excel
            int index = 1;
            while (rs.next()) {
                HSSFRow row = sheet.createRow(index);
                HSSFCell cell;
                cell = row.createCell(0);
                cell.setCellValue(rs.getInt("maNhaCungCap"));
                cell = row.createCell(1);
                cell.setCellValue(rs.getString("tenNhaCungCap"));
                cell = row.createCell(2);
                cell.setCellValue(rs.getString("soDienThoai"));
                cell = row.createCell(3);
                cell.setCellValue(rs.getString("email"));
                cell = row.createCell(4);
                cell.setCellValue(rs.getString("diaChi"));
                index++;
            }
            // noi xuat file
            FileOutputStream fileOut = new FileOutputStream("T:\\PTUD_DHKTPM12A_DOAN_NHOM14\\BaoCao\\BaoCaoQuanLyNhaCungCap.xls");// noi luu file
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();// tắt file
            rs.close();
            pst.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Báo cáo");
            alert.setHeaderText("Xuất báo cáo thành công");
            alert.setContentText("Lưu tại đường dẫn T:\\PTUD_DHKTPM12A_DOAN_NHOM14\\BaoCao");
            Optional<ButtonType> BT=alert.showAndWait();
            // System.out.println("Xuat thanh cong2");
        } catch (SQLException e) {
//            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Không thể kết nối dữ liệu");
            //alert.setContentText("");
            Optional<ButtonType> BT=alert.showAndWait();
        }
    }
}