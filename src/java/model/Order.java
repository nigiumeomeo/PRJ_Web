package model;

import java.util.Date;

public class Order {
    private int id;
    private int userID;
    private Date orderDate;
    private Date expectedDate;
    private int orderStatusID;
    private double totalCost;

    public Order() {
    }

    public Order(int id, int userID, Date orderDate, Date expectedDate, int orderStatusID, double totalCost) {
        this.id = id;
        this.userID = userID;
        this.orderDate = orderDate;
        this.expectedDate = expectedDate;
        this.orderStatusID = orderStatusID;
        this.totalCost = totalCost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getExpectedDate() {
        return expectedDate;
    }

    public void setExpectedDate(Date expectedDate) {
        this.expectedDate = expectedDate;
    }

    public int getOrderStatusID() {
        return orderStatusID;
    }

    public void setOrderStatusID(int orderStatusID) {
        this.orderStatusID = orderStatusID;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userID=" + userID +
                ", orderDate=" + orderDate +
                ", expectedDate=" + expectedDate +
                ", orderStatusID=" + orderStatusID +
                ", totalCost=" + totalCost +
                '}';
    }

}
