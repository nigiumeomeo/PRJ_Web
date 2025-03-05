
package model;

public class Category {
    private int id;
    private String fullName, descript;

    public Category() {
    }

    public Category(int id, String name, String descript) {
        this.id = id;
        this.fullName = name;
        this.descript = descript;
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

    public void setFullName(String name) {
        this.fullName = name;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    @Override
    public String toString() {
        return "Category{" + "id=" + id + ", name=" + fullName + ", descript=" + descript + '}';
    }
    
}
