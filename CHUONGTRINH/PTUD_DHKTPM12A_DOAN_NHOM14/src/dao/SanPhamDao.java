package dao;

import entity.ChiTietDonHang;
import entity.SanPham;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class SanPhamDao {
    private ObservableList<SanPham> list;
    private Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private ArrayList<String> dSMaSP;
    public SanPhamDao() {
        con = DBConnection.getConnection();
        dSMaSP = new ArrayList<String>();
    }

    public ObservableList<SanPham> loadMaSP(){
        try {
            list = FXCollections.observableArrayList();
            con = DBConnection.getConnection();
            pst = con.prepareStatement("SELECT maSanPham FROM SanPham");
            rs = pst.executeQuery();
            while (rs.next()){
                list.add(new SanPham("" + rs.getInt(1)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int loadMaSanPham(){
        int n=0;
        try {
            list = FXCollections.observableArrayList();
            con = DBConnection.getConnection();
            pst = con.prepareStatement("SELECT max(maSanPham) FROM SanPham ");
            rs = pst.executeQuery();
            while (rs.next()){
                n=rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    public ArrayList<String> loadDSMaSP(){
        try {
            con = DBConnection.getConnection();
            pst = con.prepareStatement("SELECT maSanPham FROM SanPham");
            rs = pst.executeQuery();
            while (rs.next()){
                dSMaSP.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dSMaSP;
    }

    public ArrayList<String> loadTenSP(){
        try {
            con = DBConnection.getConnection();
            pst = con.prepareStatement("SELECT tenSanPham FROM SanPham");
            rs = pst.executeQuery();
            while (rs.next()){
                dSMaSP.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dSMaSP;
    }

    public ObservableList<SanPham> loadSPTheoMa(String maSP){
        try {
            list = FXCollections.observableArrayList();
            con = DBConnection.getConnection();
            pst = con.prepareStatement("SELECT * FROM SanPham Where maSanPham ="+maSP);
            rs = pst.executeQuery();
            while (rs.next()){
                list.add(new SanPham(""+rs.getInt(1),rs.getString(3),rs.getDouble(6)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ObservableList<SanPham> loadSPTheoTen(String tenSanPham){
        try {
            list = FXCollections.observableArrayList();
            con = DBConnection.getConnection();
            pst = con.prepareStatement("SELECT * FROM SanPham Where tenSanPham = '"+tenSanPham + "'");
            rs = pst.executeQuery();
            while (rs.next()){
                list.add(new SanPham(""+rs.getInt(1),rs.getString(3),rs.getDouble(6)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ObservableList<SanPham> TKSP(){
        try {
            list = FXCollections.observableArrayList();
            con = DBConnection.getConnection();
            pst = con.prepareStatement("SELECT top 10 tenSanPham,soLuong FROM SanPham order by soLuong Desc");
            rs = pst.executeQuery();
            while (rs.next()){
                list.add(new SanPham(""+rs.getString(1),rs.getInt(2)));
                System.out.println(rs.getInt(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public Double TKDoanhSo(int thang, int nam){
        Double  doanhSo= 0.0;
        try {
            list = FXCollections.observableArrayList();
            con = DBConnection.getConnection();
            pst = con.prepareStatement("SELECT doanhso=sum(soLuong*giaBan) FROM [dbo].[ChiTietDonHang]  CTHD JOIN [dbo].[DonHang] DHANG ON CTHD.maDonHang=DHANG.maDonHang Where Month(ngayDatHang)='"+thang+"' and year(ngayDatHang)='"+nam+"'  and trangThai=N'Đã thanh toán'");

            rs = pst.executeQuery();
            while (rs.next()){
//                if(rs.getDouble(1)==0.0)
//
//                System.out.println(rs.getDouble(1));

                doanhSo=rs.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doanhSo;
    }

    public ObservableList<SanPham> loadData(){
        try {
            list = FXCollections.observableArrayList();
            pst = con.prepareStatement("SELECT * FROM SANPHAM");
            rs = pst.executeQuery();
            while (rs.next()){
                list.add(new SanPham(""+rs.getInt(1),""+rs.getInt(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getDouble(6),rs.getString(7)));
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

    public boolean create(String maNhaCungCap, String tenSanPham, int tGBH, String sLT, String donGia, String trangThai){
        int n = 0;
        String sql = "INSERT INTO SANPHAM(maNhaCungCap,tenSanPham,thoiGianBaoHanh,soLuong,donGia,trangThai) VALUES(?,?,?,?,?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1,maNhaCungCap);
            pst.setString(2,tenSanPham);
            pst.setInt(3, tGBH);
            pst.setInt(4, Integer.parseInt(sLT));
            pst.setDouble(5, Double.parseDouble(donGia));
            pst.setString(6,trangThai);
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

    public boolean update(String tenSanPham, int thoiGianBaoHanh, String soLuongTon, String donGia, String trangThai, String maSanPham){
        int n = 0;
        String sql = "UPDATE SANPHAM SET tenSanPham = ?, thoiGianBaoHanh = ?, soLuong = ?, donGia = ?, trangThai = ? WHERE maSanPham = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1,tenSanPham);
            pst.setInt(2,thoiGianBaoHanh);
            pst.setInt(3, Integer.parseInt(soLuongTon));
            pst.setDouble(4, Double.parseDouble(donGia));
            pst.setString(5,trangThai);
            pst.setInt(6, Integer.parseInt(maSanPham));
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

    public boolean delete(String maSanPham) {
        int n = 0;
        String sql = "DELETE FROM SANPHAM WHERE maSanPham = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1,maSanPham);
            pst.executeUpdate();
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
            alert.setHeaderText("Không thể xóa thông tin được, vì sản phẩm đã được bán ra");
            //alert.setContentText("");
            Optional<ButtonType> BT=alert.showAndWait();
        }
        return n > 0;
    }

    public ObservableList<SanPham> search(String tenSanPham){
        String sql = "SELECT * FROM SANPHAM WHERE tenSanPham LIKE '%" + tenSanPham + "%'";
        try{
            list = FXCollections.observableArrayList();
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                list.add(new SanPham(""+rs.getInt(1),""+rs.getInt(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getDouble(6),rs.getString(7)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
