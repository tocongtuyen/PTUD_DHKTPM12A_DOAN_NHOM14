package dao;

import entity.ChiTietDonHang;
import entity.ChiTietKhuyenMai;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ChiTietDonHangDao {

    private ObservableList<ChiTietDonHang> list;
    private Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    public ChiTietDonHangDao() {

    }

    public boolean create(String maSanPham, String maDonHang, int soLuong, Double giaBan)
    {
        int n = 0;
        String sql = "INSERT INTO ChiTietDonHang(maSanPham,maDonHang,soLuong,giaBan) VALUES (?,?,?,?)";
        try {
            con = DBConnection.getConnection();
            pst = con.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(maSanPham));
            pst.setInt(2, Integer.parseInt(maDonHang));
            pst.setInt(3,soLuong);
            pst.setDouble(4,giaBan);
            n = pst.executeUpdate();

            pst = con.prepareStatement("SELECT soLuong FROM SANPHAM WHERE maSanPham = ?");
            pst.setInt(1, Integer.parseInt(maSanPham));
            ResultSet rs = pst.executeQuery();

            int sLTon = 0;
            if(rs.next())
            {
                sLTon=rs.getInt(1)-soLuong;
            }
            pst = con.prepareStatement("UPDATE SANPHAM SET soLuong=? WHERE maSanPham = ?");
            pst.setInt(1,sLTon);
            pst.setInt(2, Integer.parseInt(maSanPham));
            pst.execute();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return n>0;
    }

    public ObservableList<ChiTietDonHang> loadData(String maDonHang){

        String sql = "select sp.maSanPham, tenSanPham, ctdh.soLuong, giaBan, mucKhuyenMai, (ctdh.soLuong*giaBan)*(1 - mucKhuyenMai*0.01) as [Thanh tien]  \n" +
                "from sanpham sp join ChiTietKhuyenMai km \n" +
                "on sp.maSanPham = km.maSanPham join ChiTietDonHang ctdh \n" +
                "on ctdh.maSanPham = sp.maSanPham join DonHang dh \n" +
                "on dh.maDonHang = ctdh.maDonHang \n" +
                "where dh.maDonHang = "+maDonHang;
        try {
            con = DBConnection.getConnection();
            list = FXCollections.observableArrayList();
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                list.add(new ChiTietDonHang(""+rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getDouble(4),rs.getInt(5),rs.getDouble(6)));
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
}
