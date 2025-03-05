package dao.implement;

import dao.DBconnect.DBConnect;
import dao.intefaces.ICategoryDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Category;

public class CategoryDao extends DBConnect implements ICategoryDao {

    @Override
    public List<Category> getAll() {
        List<Category> categories = new ArrayList<>();
        String query = "SELECT * FROM Category";

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("CategoryID"));
                category.setFullName(rs.getString("FullName"));
                category.setDescript(rs.getString("Description"));

                categories.add(category);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching categories: " + e.getMessage());
        }

        return categories;
    }

    @Override
    public Category getById(int id) {
        Category category = null;
        String query = "SELECT * FROM Category WHERE CategoryID = ?";

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                category = new Category();
                category.setId(rs.getInt("CategoryID"));
                category.setFullName(rs.getString("FullName"));
                category.setDescript(rs.getString("Description"));
            }

        } catch (SQLException e) {
            System.out.println("Error fetching category with ID " + id + ": " + e.getMessage());
        }

        return category;
    }

    @Override
    public boolean insert(Category category) {
        String query = "INSERT INTO Category (FullName, Description) VALUES (?, ?)";

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, category.getFullName());
            stmt.setString(2, category.getDescript());
            stmt.executeUpdate();

            System.out.println("Category inserted successfully.");
            return true;
        } catch (SQLException e) {
            System.out.println("Error inserting category: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(Category category) {
        String query = "UPDATE Category SET FullName = ?, Description = ? WHERE CategoryID = ?";

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, category.getFullName());
            stmt.setString(2, category.getDescript());
            stmt.setInt(3, category.getId());
            stmt.executeUpdate();

            System.out.println("Category updated successfully.");
            return true;
        } catch (SQLException e) {
            System.out.println("Error updating category with ID " + category.getId() + ": " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String query = "DELETE FROM Category WHERE CategoryID = ?";

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

            System.out.println("Category deleted successfully.");
            return true;
        } catch (SQLException e) {
            System.out.println("Error deleting category with ID " + id + ": " + e.getMessage());
        }
        return false;
    }

    @Override
    public Map<Integer, Integer> getQuantityOfCategory(List<Category> cList) {
        Map<Integer, Integer> cateMap = new HashMap<>();
        for (Category category : cList) {
            cateMap.put(category.getId(), new ProductDao().getQuantityByCateID(category.getId(), 0, Double.MAX_VALUE));
        }
        return cateMap;
    }



}
