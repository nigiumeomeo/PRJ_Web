/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;

public class Product {

    private int productID;
    private boolean bestSeller;
    private String fullName;
    private String description;
    private int quantity;
    private int quantitySold;
    private String imageURL;
    private int categoryID;
    private double price;
    private int discount;
    private List<Review> reviews;

    public Product() {
    }

    public Product(boolean bestSeller, String fullName, String description, int quantity, int quantitySold,
            String imageURL, int categoryID, double price, int discount) {
        this.bestSeller = bestSeller;
        this.fullName = fullName;
        this.description = description;
        this.quantity = quantity;
        this.quantitySold = quantitySold;
        this.imageURL = imageURL;
        this.categoryID = categoryID;
        this.price = price;
        this.discount = discount;
    }

    // Getters and setters
    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public boolean isBestSeller() {
        return bestSeller;
    }

    public void setBestSeller(boolean bestSeller) {
        this.bestSeller = bestSeller;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    // toString method
    @Override
    public String toString() {
        return "Product{"
                + "productID=" + productID
                + ", bestSeller=" + bestSeller
                + ", fullName='" + fullName + '\''
                + ", description='" + description + '\''
                + ", quantity=" + quantity
                + ", quantitySold=" + quantitySold
                + ", imageURL='" + imageURL + '\''
                + ", categoryID=" + categoryID
                + ", price=" + price
                + ", discount=" + discount
                + '}';
    }
}
