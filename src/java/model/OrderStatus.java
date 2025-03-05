
package model;
public class OrderStatus {
    private int id;
    private String fullName;

    
    
    public OrderStatus(int id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public OrderStatus() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "OrderStatus{" + "id=" + id + ", fullName=" + fullName + '}';
    }
    
}
