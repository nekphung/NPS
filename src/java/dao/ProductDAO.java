/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.Category;
import models.Product;
/**
 *
 * @author ASUS
 */
public class ProductDAO {
    
    public static ArrayList<Product> getProducts(String keyword, String sort, int page, int pageSize) {
        try {
            ArrayList<Product> products = new ArrayList<>();
            StringBuilder sql = new StringBuilder(
                "SELECT P.id, P.product_name, P.description, P.category_id, P.unit_price, P.units_in_stock, P.date_added, " + 
                "string_agg(I.url_path, ',') as images " + 
                "FROM products P " +
                "LEFT JOIN product_images I ON P.id = I.product_id "
            );
            // where 
            if (keyword != null && !keyword.trim().isEmpty()) {
                sql.append("where P.product_name ILIKE ? OR P.description ILIKE ? ");   
            }
            
            sql.append("GROUP BY P.id, P.product_name, P.description, P.category_id, P.unit_price, P.units_in_stock, P.date_added ");
            
            // sort 
            if (sort != null) {
                switch (sort) {
                    case "price-asc":
                        sql.append("order by P.unit_price ASC ");
                        break;
                    case "price-desc":
                        sql.append("order by P.unit_price DESC ");
                        break;
                    case "name-asc":
                        sql.append("order by P.product_name ASC ");
                        break;
                    case "name-desc":
                        sql.append("order by P.product_name DESC ");
                        break;
                    case "newest":
                        sql.append("ORDER BY P.date_added DESC ");
                        break;  
                    case "default":
                    default:
                        // Không làm gì cả, trả về danh sách với thứ tự ban đầu 
                        break;
                }
            }
            sql.append("LIMIT ? OFFSET ?");
            
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql.toString());
            
            int index = 1;
            
            if (keyword != null && !keyword.trim().isEmpty()) {
                ps.setString(index++, "%" + keyword + "%");
                ps.setString(index++, "%" + keyword + "%");
            }
            
            int offset = (page - 1)* pageSize;
            ps.setInt(index++, pageSize);
            ps.setInt(index++, offset);
            
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Category category = new Category(resultSet.getInt("category_id"));
                String imageStr = resultSet.getString("images");
                ArrayList<String> listImages = new ArrayList<>();
                if(imageStr != null){
                    String[] arrImages = imageStr.split(",");
                    for(String img : arrImages){
                        System.out.println(img);
                        listImages.add(img);
                    }
                }
                Product product = new Product(resultSet.getInt("id"), resultSet.getString("product_name"), resultSet.getString("description"), category, resultSet.getDouble("unit_price"), resultSet.getInt("units_in_stock"), listImages, resultSet.getString("date_added"));
                products.add(product);
            }
            System.out.println("Successfully!");
            return products;
        } catch (Exception e) {
             e.printStackTrace();
             return null;
        }
        
    }
    
    public static Product getProductDetail(int id) {
        try {
            String query = "SELECT P.id, P.product_name, P.description, P.category_id, P.unit_price, P.units_in_stock, P.date_added, "
            + "string_agg(I.url_path, ',') as images\n"
            + "FROM products as P\n"
            + "JOIN product_images as I\n"
            + "ON P.id = I.product_id\n"
            + "WHERE P.id = ? \n"
            + "GROUP BY P.id, P.product_name, P.description, P.category_id, P.unit_price, P.units_in_stock, P.date_added";
            
            System.out.println("query: " + query);
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            Product product = null;
            if (resultSet.next()) {
                Category category = new Category(resultSet.getInt("category_id"));
                String imageStr = resultSet.getString("images");
                ArrayList<String> listImages = new ArrayList<>();
                if(imageStr != null){
                    String[] arrImages = imageStr.split(",");
                    for(String img : arrImages){
                        System.out.println(img);
                        listImages.add(img);
                    }
                }
                product = new Product(resultSet.getInt("id"), resultSet.getString("product_name"), resultSet.getString("description"), category, resultSet.getDouble("unit_price"), resultSet.getInt("units_in_stock"), listImages, resultSet.getString("date_added"));
            }
            return product;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static ArrayList<Product> getProductsPaging(int page, int pageSize) {
        try {
            String query = "SELECT P.id, P.product_name, P.description, P.category_id, P.unit_price, P.units_in_stock, P.date_added, "
            + "string_agg(I.url_path, ',') as images\n"
            + "FROM products as P\n"
            + "LEFT JOIN product_images as I\n"
            + "ON P.id = I.product_id\n"
            + "GROUP BY P.id, P.product_name, P.description, P.category_id, P.unit_price, P.units_in_stock, P.date_added\n"
            + "LIMIT ? OFFSET ?";
            
            System.out.println("query: " + query);
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            int offset = (page - 1)*pageSize;
            
            statement.setInt(1, pageSize);
            statement.setInt(2, offset);
            
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                Category category = new Category(resultSet.getInt("category_id"));
                String imageStr = resultSet.getString("images");
                ArrayList<String> listImages = new ArrayList<>();
                if(imageStr != null){
                    String[] arrImages = imageStr.split(",");
                    for(String img : arrImages){
                        System.out.println(img);
                        listImages.add(img);
                    }
                }
                Product product = new Product(resultSet.getInt("id"), resultSet.getString("product_name"), resultSet.getString("description"), category, resultSet.getDouble("unit_price"), resultSet.getInt("units_in_stock"), listImages, resultSet.getString("date_added"));
                products.add(product);
            }
            return products;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static ArrayList<Product> searchProducts(String keyword) {
        ArrayList<Product> list = new ArrayList<>();
        String sql = "SELECT P.id, P.product_name, P.description, P.category_id, P.unit_price, P.units_in_stock, P.date_added, " +
             "string_agg(PI.url_path, ',') as images\n" +
             "FROM products as P\n" +
             "JOIN product_images as PI \n" +
             "ON P.id = PI.product_id \n" +
             "WHERE P.product_name ILIKE ? OR P.description ILIKE ?\n" +
             "GROUP BY P.id, P.product_name, P.description, P.category_id, P.unit_price, P.units_in_stock, P.date_added";
        
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category category = new Category(rs.getInt("category_id"));
                String imageStr = rs.getString("images");
                ArrayList<String> listImages = new ArrayList<>();
                if(imageStr != null){
                    String[] arrImages = imageStr.split(",");
                    for(String img : arrImages){
                        System.out.println(img);
                        listImages.add(img);
                    }
                }
                System.out.println(rs.getInt("id"));
                Product product = new Product(rs.getInt("id"), rs.getString("product_name"), rs.getString("description"), category, rs.getDouble("unit_price"), rs.getInt("units_in_stock"), listImages, rs.getString("date_added"));
                list.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public static int countProducts(String keyword) {
        try{ 
            String sql = "SELECT COUNT(*) FROM products ";
            
            if (keyword != null && !keyword.trim().isEmpty()) {
                sql += "where product_name ILIKE ? OR description ILIKE ?";
            }
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            if (keyword != null && !keyword.trim().isEmpty()) {
                ps.setString(1, "%" + keyword + "%");
                ps.setString(2, "%" + keyword + "%");
            }
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();    
        }
        return 0;
    }
    
//        public static ArrayList<Product> getProducts(String keyword, String sort, int page, int pageSize) {
//            try {
//                ArrayList<Product> products = new ArrayList<>();
//                StringBuilder sql = new StringBuilder(
//                    "Select P.*, group_concat(I.url_path) as images " + 
//                    "from products P " +
//                    "Left join product_images I on P.id = I.product_id "
//                );
//                // where 
//                if (keyword != null && !keyword.trim().isEmpty()) {
//                    sql.append("where P.product_name LIKE ? OR P.description LIKE ? ");   
//                }
//                
//                sql.append("group by P.id ");
//                
//                // sort 
//                if (sort != null) {
//                    switch (sort) {
//                        case "price-asc":
//                            sql.append("order by P.unit_price ASC ");
//                            break;
//                        case "price-desc":
//                            sql.append("order by P.unit_price DESC ");
//                            break;
//                        case "name-asc":
//                            sql.append("order by P.product_name ASC ");
//                            break;
//                        case "name-desc":
//                            sql.append("order by P.product_name DESC ");
//                            break;
//                        case "newest":
//                            sql.append("order by P.DateAdded DESC ");
//                            break;  
//                        case "default":
//                        default:
//                            // Không làm gì cả, trả về danh sách với thứ tự ban đầu 
//                            break;
//                    }
//                }
//                sql.append("LIMIT ? OFFSET ?");
//                
//                Connection conn = DBConnection.getConnection();
//                PreparedStatement ps = conn.prepareStatement(sql.toString());
//                
//                int index = 1;
//                
//                if (keyword != null && !keyword.trim().isEmpty()) {
//                    ps.setString(index++, "%" + keyword + "%");
//                    ps.setString(index++, "%" + keyword + "%");
//                }
//                
//                int offset = (page - 1)* pageSize;
//                ps.setInt(index++, pageSize);
//                ps.setInt(index++, offset);
//                
//                ResultSet resultSet = ps.executeQuery();
//                while (resultSet.next()) {
//                    Category category = new Category(resultSet.getInt("category_id"));
//                    String imageStr = resultSet.getString("images");
//                    ArrayList<String> listImages = new ArrayList<>();
//                    if(imageStr != null){
//                        String[] arrImages = imageStr.split(",");
//                        for(String img : arrImages){
//                            System.out.println(img);
//                            listImages.add(img);
//                        }
//                    }
//                    Product product = new Product(resultSet.getInt("id"), resultSet.getString("product_name"), resultSet.getString("description"), category, resultSet.getDouble("unit_price"), resultSet.getInt("units_in_stock"), listImages, resultSet.getString("DateAdded"));
//                    products.add(product);
//                }
//                return products;
//            } catch (Exception e) {
//                 e.printStackTrace();
//                 return null;
//            }
//        }
//        
//        public static Product getProductDetail(int id) {
//            try {
//                String query = "select P.*, group_concat(I.url_path) as images\n"
//                        + "from products as P\n"
//                        + "join product_images as I\n"
//                        + "on P.id = I.product_id\n"
//                        + "where P.id = ? \n"
//                        + "group by P.id";
//                System.out.println("query: " + query);
//                Connection connection = DBConnection.getConnection();
//                PreparedStatement statement = connection.prepareStatement(query);
//                statement.setInt(1, id);
//                ResultSet resultSet = statement.executeQuery();
//                Product product = null;
//                if (resultSet.next()) {
//                    Category category = new Category(resultSet.getInt("category_id"));
//                    String imageStr = resultSet.getString("images");
//                    ArrayList<String> listImages = new ArrayList<>();
//                    if(imageStr != null){
//                        String[] arrImages = imageStr.split(",");
//                        for(String img : arrImages){
//                            System.out.println(img);
//                            listImages.add(img);
//                        }
//                    }
//                    product = new Product(resultSet.getInt("id"), resultSet.getString("product_name"), resultSet.getString("description"), category, resultSet.getDouble("unit_price"), resultSet.getInt("units_in_stock"), listImages, resultSet.getString("DateAdded"));
//                }
//                return product;
//            } catch(Exception e) {
//                e.printStackTrace();
//                return null;
//            }
//        }
//        
//        public static ArrayList<Product> getProductsPaging(int page, int pageSize) {
//            try {
//                String query = "select P.*, group_concat(I.url_path) as images\n"
//                        + "from products as P\n"
//                        + "left join product_images as I\n"
//                        + "on P.id = I.product_id\n"
//                        + "group by P.id\n"
//                        + "limit ? offset ?";
//                
//                System.out.println("query: " + query);
//                Connection connection = DBConnection.getConnection();
//                PreparedStatement statement = connection.prepareStatement(query);
//                int offset = (page - 1)*pageSize;
//                
//                statement.setInt(1, pageSize);
//                statement.setInt(2, offset);
//                
//                ResultSet resultSet = statement.executeQuery();
//                ArrayList<Product> products = new ArrayList<>();
//                while (resultSet.next()) {
//                    Category category = new Category(resultSet.getInt("category_id"));
//                    String imageStr = resultSet.getString("images");
//                    ArrayList<String> listImages = new ArrayList<>();
//                    if(imageStr != null){
//                        String[] arrImages = imageStr.split(",");
//                        for(String img : arrImages){
//                            System.out.println(img);
//                            listImages.add(img);
//                        }
//                    }
//                    Product product = new Product(resultSet.getInt("id"), resultSet.getString("product_name"), resultSet.getString("description"), category, resultSet.getDouble("unit_price"), resultSet.getInt("units_in_stock"), listImages, resultSet.getString("DateAdded"));
//                    products.add(product);
//                }
//                return products;
//            } catch (Exception e) {
//                e.printStackTrace();
//                return null;
//            }
//        }
//        
//        public static ArrayList<Product> searchProducts(String keyword) {
//            ArrayList<Product> list = new ArrayList<>();
//            String sql = "SELECT P.*,  group_concat(PI.url_path) as images\n" +
//                         "FROM products as P\n" +
//                         "join product_images as PI \n" +
//                         "on P.id = PI.product_id \n" +
//                         "where P.product_name LIKE ? OR description LIKE ?\n" +
//                         "group by P.id ";
//            try (Connection conn = DBConnection.getConnection();
//                PreparedStatement ps = conn.prepareStatement(sql)) {
//                ps.setString(1, "%" + keyword + "%");
//                ps.setString(2, "%" + keyword + "%");
//                ResultSet rs = ps.executeQuery();
//                while (rs.next()) {
//                    Category category = new Category(rs.getInt("category_id"));
//                    String imageStr = rs.getString("images");
//                    ArrayList<String> listImages = new ArrayList<>();
//                    if(imageStr != null){
//                        String[] arrImages = imageStr.split(",");
//                        for(String img : arrImages){
//                            System.out.println(img);
//                            listImages.add(img);
//                        }
//                    }
//                    System.out.println(rs.getInt("id"));
//                    Product product = new Product(rs.getInt("id"), rs.getString("product_name"), rs.getString("description"), category, rs.getDouble("unit_price"), rs.getInt("units_in_stock"), listImages, rs.getString("DateAdded"));
//                    list.add(product);
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            return list;
//        }
//        
//        public static int countProducts(String keyword) {
//            try{ 
//                String sql = "SELECT COUNT(*) FROM products ";
//                
//                if (keyword != null && !keyword.trim().isEmpty()) {
//                    sql += "where product_name LIKE ? OR description LIKE ?";
//                }
//                Connection conn = DBConnection.getConnection();
//                PreparedStatement ps = conn.prepareStatement(sql);
//                if (keyword != null && !keyword.trim().isEmpty()) {
//                    ps.setString(1, "%" + keyword + "%");
//                    ps.setString(2, "%" + keyword + "%");
//                }
//                
//                ResultSet rs = ps.executeQuery();
//                
//                if (rs.next()) {
//                    return rs.getInt(1);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();    
//            }
//            return 0;
//        }
//    }
}

