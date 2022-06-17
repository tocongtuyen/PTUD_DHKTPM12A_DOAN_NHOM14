package dao;

import entity.NhanVien;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

public class NhanVienDao {
    private ObservableList<NhanVien> listNV;
    private ObservableList<TKNhanVien> listTKNV;
    private Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    public NhanVienDao() {
        con = DBConnection.getConnection();
    }

    public static class TKNhanVien{
        private  String tenNhanVien;
        private double doanSo;

        public TKNhanVien(String tenNhanVien, double doanSo) {
            this.tenNhanVien = tenNhanVien;
            this.doanSo = doanSo;
        }

        public String getTenNhanVien() {
            return tenNhanVien;
        }

        public void setTenNhanVien(String tenNhanVien) {
            this.tenNhanVien = tenNhanVien;
        }

        public double getDoanSo() {
            return doanSo;
        }

        public void setDoanSo(double doanSo) {
            this.doanSo = doanSo;
        }
    }

    public ObservableList<NhanVien> loadData(){
        try {
            listNV = FXCollections.observableArrayList();
            pst = con.prepareStatement("SELECT maNhanVien,tenNhanVien,soDT,email,chucVu,ngayVaoLam,trangThai,diaChi FROM NHANVIEN");
            rs = pst.executeQuery();
            while (rs.next()){
                listNV.add(new NhanVien(""+rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)));
//                System.out.println(rs.getInt(1) );
//                System.out.println(rs.getString(2) );
//                System.out.println(rs.getString(3) );
//                System.out.println(rs.getString(4) );
//                System.out.println(rs.getString(5) );
//                System.out.println(rs.getString(6) );
//                System.out.println(rs.getString(7) );
//                System.out.println(rs.getString(8) );
            }
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listNV;
    }

    public int loadMaNV(){
        int n=0;
        try {
            listNV = FXCollections.observableArrayList();
            con = DBConnection.getConnection();
            pst = con.prepareStatement("SELECT max(maNhanVien) FROM NhanVien ");
            rs = pst.executeQuery();
            while (rs.next()){
                n=rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    public ObservableList<NhanVien> loadNhanVien(String taiKhoan, String matKhau){
        try {
            listNV = FXCollections.observableArrayList();
            con = DBConnection.getConnection();
            pst = con.prepareStatement("SELECT * FROM NhanVien WHERE taiKhoan = '"+taiKhoan+"' and matKhau ='"+matKhau+"'");
            rs = pst.executeQuery();
            while (rs.next()){
                listNV.add(new NhanVien(""+rs.getInt(1),rs.getString(2),rs.getString(5),rs.getString(7)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listNV;
    }

    public ObservableList<NhanVien> loadNhanVien(String maNV){
        try {
            listNV = FXCollections.observableArrayList();
            con = DBConnection.getConnection();
            pst = con.prepareStatement("SELECT * FROM NhanVien WHERE maNhanVien ="+maNV);
            rs = pst.executeQuery();
            while (rs.next()){
                listNV.add(new NhanVien(""+rs.getInt(1),rs.getString(2),rs.getString(5),rs.getString(7)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listNV;
    }

    public ObservableList<TKNhanVien> thongKeNV(){
        // System.out.println("  thành công" );
        try {
            listTKNV = FXCollections.observableArrayList();// tao 1 list rong
            con = DBConnection.getConnection();// ket noi database
            System.out.println("  thành công" );
            pst = con.prepareStatement("select top 10 NV.[tenNhanVien],tongDoanhThu=SUM([soLuong]*[giaBan])\n" +
                    "FROM NHANVIEN NV JOIN DONHANG DH ON NV.[maNhanVien]=DH.[maNhanVien] join [dbo].[ChiTietDonHang] CT \n" +
                    "ON CT.maDonHang=DH.maDonHang \n" +
                    "Where DH.trangThai=N'Đã thanh toán'\n" +
                    " GROUP BY NV.tenNhanVien order by tongDoanhThu DESC" );

            rs = pst.executeQuery();
            System.out.println("sdgsgs"+rs);

            while (rs.next()){
                listTKNV.add(new TKNhanVien(""+rs.getString(1), rs.getDouble(2)));
//                System.out.println(rs.getString(1) );
//                System.out.println(rs.getString(2) );
//                System.out.println(listTKNV.size());
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        if(listTKNV.isEmpty())
//            System.out.println("rong roi nek cu");
        return listTKNV;
    }

    public boolean create(String tenNhanVien, String soDT, String email, String chucVu, String ngayVaoLam, String taiKhoan, String matKhau, String diaChi, String trangThai){
        int n = 0;
        String sql = "INSERT INTO NhanVien ([tenNhanVien], [soDT], [email], [chucVu], [ngayVaoLam], [taiKhoan], [matKhau], [diaChi], [trangThai])" +
                "VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1,tenNhanVien);
            pst.setString(2,soDT);
            pst.setString(3,email);
            pst.setString(4,chucVu);
            pst.setDate(5, Date.valueOf(ngayVaoLam));
            pst.setString(6, taiKhoan);
            pst.setString(7, matKhau);
            pst.setString(8, diaChi);
            pst.setString(9,trangThai);
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

    public boolean update(String maNhanVien, String tenNhanVien, String soDT, String email, String chucVu, String ngayVaoLam, String taiKhoan, String matKhau, String diaChi, String trangThai){
        int n = 0;
        String sql = "UPDATE SANPHAM SET [tenNhanVien] = ?, [soDT] = ?, [email] = ?, [chucVu] = ?, [ngayVaoLam] = ?," +
                " [taiKhoan] = ?, [matKhau] = ?, [diaChi] = ?, [trangThai] = ? WHERE maNhanVien = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1,tenNhanVien);
            pst.setString(2,soDT);
            pst.setString(3,email);
            pst.setString(4,chucVu);
            pst.setDate(5, Date.valueOf(ngayVaoLam));
            pst.setString(6, taiKhoan);
            pst.setString(7, matKhau);
            pst.setString(8, diaChi);
            pst.setString(9,trangThai);
            pst.setString(10,maNhanVien);
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

    public boolean delete(String maNhanVien) {
        int n = 0;
        String sql = "DELETE FROM NHANVIEN WHERE maNhanVien = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1,maNhanVien);
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
            alert.setHeaderText("Không thể xóa thông tin được, vì nhân viên đã làm việc");
            //alert.setContentText("");
            Optional<ButtonType> BT=alert.showAndWait();
        }
        return n > 0;
    }

    public ObservableList<NhanVien> search(String tenNhanVien){
        String sql = "SELECT * FROM NHANVIEN WHERE tenNhanVien LIKE '%" + tenNhanVien + "%'";
        try{
            listNV = FXCollections.observableArrayList();
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                listNV.add(new NhanVien(""+rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(10),rs.getString(9)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listNV;
    }

}
