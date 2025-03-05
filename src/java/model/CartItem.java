
package model;

public class CartItem {
    private int id, quantity;
    private double totalCost;
    private int cartID;
    private int productID;
    private Product product;
    
    public CartItem() {
    }

    public CartItem(int id, int quantity, double totalCost, int cartID, int productID) {
        this.id = id;
        this.quantity = quantity;
        this.totalCost = totalCost;
        this.cartID = cartID;
        this.productID = productID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    
    

    @Override
    public String toString() {
        return "CartItem{" + "id=" + id + ", quantity=" + quantity + ", totalCost=" + totalCost + ", cartID=" + cartID + ", productID=" + productID + '}';
    }
    
}
