package dao;

import entity.ChiTietKhuyenMai;
import entity.SanPham;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChiTietKhuyenMaiDao {
    private ObservableList<ChiTietKhuyenMai> list;
    private Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    public ChiTietKhuyenMaiDao() {
    }

    public ObservableList<ChiTietKhuyenMai> loadSPTheoMa(String maSP){
        try {
            list = FXCollections.observableArrayList();
            con = DBConnection.getConnection();
            pst = con.prepareStatement("SELECT * FROM ChiTietKhuyenMai Where maSanPham ="+maSP);
            rs = pst.executeQuery();
            while (rs.next()){
                list.add(new ChiTietKhuyenMai(rs.getInt(5)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


//        try {

//            pst = con.prepareStatement(sql);
//            pst.setInt(1,);
//            pst.setString(2,soDienThoai);
//            pst.setString(3,email);
//            pst.setString(4,diaChi);
//            n = pst.executeUpdate();
//            stat = conn.prepareStatement("SELECT SoLuong FROM SANPHAM WHERE MaSP = ?");
//            stat.setInt(1,maSP);
//            n = pst.executeUpdate();
//            int sLTon = 0;
//            if(rs.next())
//            {
//                sLTon=rs.getInt(1)-sLuong;
//            }
//            stat = conn.prepareStatement("UPDATE SANPHAM SET SoLuong=? WHERE MaSP = ?");
//            stat.setInt(1,sLTon);
//            stat.setInt(2,maSP);
//            stat.execute();

//        } catch (SQLException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }

}
