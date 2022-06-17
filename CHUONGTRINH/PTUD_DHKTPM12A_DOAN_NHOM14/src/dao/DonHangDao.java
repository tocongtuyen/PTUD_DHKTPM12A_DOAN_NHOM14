package dao;

import entity.ChiTietDonHang;
import entity.DonHang;
import entity.NhaCungCap;
import entity.SanPham;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.*;
import java.util.Optional;


public class DonHangDao {
    private ObservableList<DonHang> list;
    private Connection con;// =  DBConnection.getConnection();;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    public DonHangDao() {
        con =  DBConnection.getConnection();
    }


    public boolean create(String maKhachHang, String maNhanVien, String diaChiGiaoHang, String ngayDatHang, String trangThai){
        int n = 0;
        String sql = "INSERT INTO DonHang(maKhachHang,maNhanVien,diaChiGiaoHang,ngayDatHang,trangThai) VALUES(?,?,?,?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt((maKhachHang)));
            pst.setInt(2, Integer.parseInt(maNhanVien));
            pst.setString(3,diaChiGiaoHang);
            pst.setDate(4, Date.valueOf((ngayDatHang)));
            pst.setString(5,trangThai);
//            System.out.println("a" + pst.toString());
            n = pst.executeUpdate();
        } catch (SQLException e) {
//            e.printStackTrace();
        }
        return n > 0;
    }

    public int loadMaDonHang(){
        int n=0;
        try {
            list = FXCollections.observableArrayList();
            con = DBConnection.getConnection();
            pst = con.prepareStatement("SELECT max(maDonHang) FROM DonHang ");
            rs = pst.executeQuery();
            while (rs.next()){
                n=rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }
    public ObservableList<DonHang> loadData(String SDT){
        String sql = "select maDonHang, k.maKhachHang, maNhanVien, diaChiGiaoHang, ngayDatHang, trangThai from KhachHang k join DonHang d on k.maKhachHang = d.maKhachHang where k.soDienThoai ="+SDT;
        try {

            list = FXCollections.observableArrayList();
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                list.add(new DonHang(""+rs.getInt(1),""+rs.getInt(2),""+rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6)));
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

    public ObservableList<DonHang> search(String tenNhaCungCap){
        String sql = "SELECT * FROM NHACUNGCAP WHERE tenNhaCungCap LIKE '%" + tenNhaCungCap + "%'";
        try{
            list = FXCollections.observableArrayList();
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                list.add(new DonHang(""+rs.getInt(1),""+rs.getInt(2),""+rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
