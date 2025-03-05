package model;

import java.util.List;

public class Review {
    private int id;
    private String description;
    private int userID;
    private int productID;
    private User user;
    private Product product;
    
    public Review() {
    }

    public Review(int id, String description, int userID, int productID) {
        this.id = id;
        this.description = description;
        this.userID = userID;
        this.productID = productID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", userID=" + userID +
                ", productID=" + productID +
                '}';
    }
}
