package dao;

import entity.SanPham;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class KhuyenMaiDao {
    private static ObservableList<SanPham> list;
    private static Connection con = null;
    private static PreparedStatement pst = null;
    private static ResultSet rs = null;

    public KhuyenMaiDao() {
    }


}
