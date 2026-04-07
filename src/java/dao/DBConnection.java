/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ASUS
 */
public class DBConnection {
    // Thong tin ket noi database 
//    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/qlbanhang";
//    private static final String JDBC_USER = "root";
//    private static final String JDBC_PASSWORD = "NekPhung7122006";
      // 1. Thay URL (Dùng External Database URL từ Render) (chạy local) 
      // private static final String JDBC_URL = "jdbc:postgresql://dpg-d79teipr0fns73ep0l90-a.oregon-postgres.render.com:5432/nps_1xj5?sslmode=require";
      
      // chạy trên render (internet) 
      private static final String JDBC_URL = "jdbc:postgresql://dpg-d79teipr0fns73ep0l90-a/nps_1xj5?sslmode=require";
      // 2. Thay User và Password
      private static final String JDBC_USER = "nps_1xj5_user";
      private static final String JDBC_PASSWORD = "15JddfuDQR875Ww4UcieIXYojrFa7Fj8";
    
    public static Connection getConnection() throws SQLException {
        try {
            //Class.forName("com.mysql.cj.jdbc.Driver");
            // 3. Thay Driver Class cho PostgreSQL
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Khong the tim thay MySQL JDBC Driver. Vui long kiem tra thu vien.");
        }
    }
}
