package dao;

import entity.KhachHang;
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

public class KhachHangDao {
    private ObservableList<KhachHang> list;
    private Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private ArrayList<String> dSSDT;
    private ObservableList<TKKH>listTKKH;
    public KhachHangDao() {
        con = DBConnection.getConnection();
        dSSDT = new ArrayList<String>();
    }
    public class TKKH{
        private String tenKH;
        private double tongTienDaMua;

        public TKKH(String tenKH, double tongTienDaMua) {
            this.tenKH = tenKH;
            this.tongTienDaMua = tongTienDaMua;
        }

        public String getTenKH() {
            return tenKH;
        }

        public void setTenKH(String tenKH) {
            this.tenKH = tenKH;
        }

        public double getTongTienDaMua() {
            return tongTienDaMua;
        }

        public void setTongTienDaMua(double tongTienDaMua) {
            this.tongTienDaMua = tongTienDaMua;
        }
    }

    public int loadMaKhachHang(){
        int n=0;
        try {
            list = FXCollections.observableArrayList();
            con = DBConnection.getConnection();
            pst = con.prepareStatement("SELECT max(maKhachHang) FROM KhachHang ");
            rs = pst.executeQuery();
            while (rs.next()){
                n=rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    public ObservableList<KhachHang> searchSDT(String SDT){
        try {
            list = FXCollections.observableArrayList();
            con = DBConnection.getConnection();
            pst = con.prepareStatement("SELECT * FROM KhachHang WHERE soDienThoai ="+SDT);
            rs = pst.executeQuery();
            while (rs.next()){
                list.add(new KhachHang(""+rs.getInt(1),rs.getString(2)));
            }
        } catch (SQLException e) {
//            e.printStackTrace();
        }
//        System.out.println(list);
        return list;
    }

    public ArrayList<String> loadSDT(){
        try {
            con = DBConnection.getConnection();
            pst = con.prepareStatement("SELECT soDienThoai FROM KhachHang");
            rs = pst.executeQuery();
            while (rs.next()){
                dSSDT.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dSSDT;
    }

    public ObservableList<TKKH> thongKeKH(){
        // System.out.println("  thành công" );
        try {
            listTKKH = FXCollections.observableArrayList();// tao 1 list rong
            con = DBConnection.getConnection();// ket noi database
//            System.out.println("  thành công" );
            String sql = "select top 10 KH.tenKhachHang,tongTienDaMua=sum(soluong*giaBan)\n" +
                    "\t\t\t\t\tfrom [dbo].[DonHang] DH join [dbo].[ChiTietDonHang] CTDH \n" +
                    "                    on DH.maDonHang=CTDH.maDonHang join [dbo].[KhachHang] KH on KH.maKhachHang=DH.maKhachHang\n" +
                    "                    where trangThai=N'Đã thanh toán'\n" +
                    "                    group by KH.tenKhachHang\n" +
                    "                    order by tongTienDaMua DESC";

            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
//            System.out.println("sdgsgs"+rs);

            while (rs.next()){

                System.out.println(rs.getString(1) );
                System.out.println(rs.getString(2) );
                listTKKH.add(new TKKH(rs.getString(1),rs.getDouble(2)));
                System.out.println(listTKKH.size());
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // sai ở đây
//        if(listTKKH.isEmpty())
//            System.out.println("rong roi nek cu");
        return listTKKH;
    }

    public ObservableList<KhachHang> loadData(){
        try {
            list = FXCollections.observableArrayList();
            pst = con.prepareStatement("SELECT * FROM KHACHHANG");
            rs = pst.executeQuery();
            while (rs.next()){
                list.add(new KhachHang(""+rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5)));
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

    public boolean create(String tenKhachHang, String soDienThoai, String email, String diaChi){
        int n = 0;
        String sql = "INSERT INTO KHACHHANG(tenKhachHang,soDienThoai,email,diaChi) VALUES(?,?,?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1,tenKhachHang);
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

    public boolean update(String maKhachHang, String tenKhachHang, String soDienThoai, String email, String diaChi){
        int n = 0;
        String sql = "UPDATE KHACHHANG SET  tenKhachHang = ?, soDienThoai = ?, email = ?, diaChi = ? WHERE maKhachHang = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1,tenKhachHang);
            pst.setString(2,soDienThoai);
            pst.setString(3,email);
            pst.setString(4,diaChi);
            pst.setString(5,maKhachHang);
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

    public boolean delete(String maKhachHang) {
        int n = 0;
        String sql = "DELETE FROM KHACHHANG WHERE maKhachHang = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1,maKhachHang);
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
            alert.setHeaderText("Không thể xóa thông tin được, vì khách hàng đã có hóa đơn");
            //alert.setContentText("");
            Optional<ButtonType> BT=alert.showAndWait();
        }
        return n > 0;
    }

    public ObservableList<KhachHang> search(String tenKhachHang){
        String sql = "SELECT * FROM KHACHHANG WHERE tenKhachHang LIKE '%" + tenKhachHang + "%'";
        try{
            list = FXCollections.observableArrayList();
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                list.add(new KhachHang(""+rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
