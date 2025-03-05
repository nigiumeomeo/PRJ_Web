
package model;

public class Cart {
    private int id, userID;

    public Cart() {
    }

   
    public Cart(int id, int userID) {
        this.id = id;
        this.userID = userID;
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

    @Override
    public String toString() {
        return "Cart{" + "id=" + id + ", userID=" + userID + '}';
    }
    
    
}
