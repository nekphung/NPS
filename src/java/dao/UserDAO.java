/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import models.User;
/**
 *
 * @author ASUS
 */
public class UserDAO {
    public static User getUser(String username, String password) {
        try {
            String query = String.format("SELECT* FROM users where username = '%s' and password = '%s' LIMIT 1", username, password);
            System.out.println("query: " + query);
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                User user = new User(resultSet.getInt("id"),
                                    resultSet.getString("username"),
                                    resultSet.getString("password"),
                                    resultSet.getString("first_name"),
                                    resultSet.getString("last_name"),
                                    resultSet.getString("date_of_birth"),
                                         resultSet.getString("email"));
                return user;
            }
            else {
                return null;
            }
        }catch (Exception e) {
            return null; 
        }
    }
    
    public static void addUser(String username, String password, String firstName, String lastName, String dateOfBirth, String email) {
        String query = "INSERT INTO users (email, username, password, first_name, last_name, date_of_birth) VALUES (?, ?, ?, ?, ?, ?)";
        
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, email);
            statement.setString(2, username);
            statement.setString(3, password);
            statement.setString(4, firstName);
            statement.setString(5, lastName);
            statement.setString(6, dateOfBirth);

            int rows = statement.executeUpdate();

            System.out.println("Inserted rows: " + rows);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static boolean checkUserExists(String identifier) {
        String query = "SELECT * FROM users WHERE username = ? OR email = ?";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, identifier);
            ps.setString(2, identifier);

            ResultSet rs = ps.executeQuery();

            return rs.next(); // có dữ liệu → true

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static void updatePassword(String identifier, String newPassword) {
        String query = "UPDATE users SET password = ? WHERE username = ? OR email = ?";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, newPassword);
            ps.setString(2, identifier);
            ps.setString(3, identifier);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
