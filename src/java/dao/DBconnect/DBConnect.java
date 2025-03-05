/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.DBconnect;

import java.sql.*;

public abstract class DBConnect {

    public static String DRIVERNAME = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    public static String DBURL = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=MyNewDatabase;encrypt=false;trustServerCertificate=false;loginTimeout=30;";
    public static String USERDB = "sa";
    public static String PASSDB = "123";

    public Connection getConnect() {
        try {
            Class.forName(DRIVERNAME);
        } catch (ClassNotFoundException e) {
            System.out.println("Error loading driver" + e);
        }
        try {
            Connection con = DriverManager.getConnection(DBURL, USERDB, PASSDB);
            return con;
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return null;
    }
    
      public static void main(String[] args) {
        // Tạo một đối tượng DBConnect để kiểm tra kết nối
        DBConnect dbConnect = new DBConnect() {}; // Tạo instance từ lớp abstract
        
        // Gọi phương thức getConnect() để kiểm tra kết nối
        Connection conn = dbConnect.getConnect();
        
        // Kiểm tra nếu kết nối thành công hay không
        if (conn != null) {
            System.out.println("Kết nối thành công!");
        } else {
            System.out.println("Kết nối thất bại!");
        }
        
        // Đóng kết nối (nếu cần)
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Đã đóng kết nối.");
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi đóng kết nối: " + e.getMessage());
        }
    }
}
