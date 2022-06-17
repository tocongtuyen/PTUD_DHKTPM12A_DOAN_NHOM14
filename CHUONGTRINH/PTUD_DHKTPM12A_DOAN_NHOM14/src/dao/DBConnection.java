package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static String DB_URL = "jdbc:sqlserver://localhost:1433;"
            + "databaseName=PTUD_DHKTPM12A_NHOM14;"
            + "integratedSecurity=false";
    private static String USER_NAME = "sa";
    private static String PASSWORD = "123";

//    public static void main(String[] args) {
//        Connection con=getConnection();
//    }
    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return con;
    }

}