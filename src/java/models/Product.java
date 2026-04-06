/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class Product {
    private int id;
    private String productName, description;
    private Category category;
    private double unitPrice;
    private int unitsInStock;
    private ArrayList<String> imageUrls;
    private String dateAdded;
    
    public Product() {}
    
    public Product(int id, String productName, String description, Category category, double unitPrice, int unitsInStock, ArrayList<String> imageUrls, String dateAdded) {
        this.id = id;
        this.productName = productName;
        this.description = description;
        this.category = category;
        this.unitPrice = unitPrice;
        this.unitsInStock = unitsInStock;
        this.imageUrls = imageUrls;
        this.dateAdded = dateAdded;
    }
    
    public int getId() {
        return id;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getProductName() {
        return productName;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setUnitsInStock(int unitsInStock) {
        this.unitsInStock = unitsInStock;
    }

    public void setImageUrls(ArrayList<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public int getUnitsInStock() {
        return unitsInStock;
    }

    public ArrayList<String> getImageUrls() {
        return imageUrls;
    }
}
