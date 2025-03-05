package dao.implement;

import dao.DBconnect.DBConnect;
import dao.intefaces.GenericDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Cart;

public class CartDao extends DBConnect implements GenericDao<Cart> {

    @Override
    public List<Cart> getAll() {
        List<Cart> carts = new ArrayList<>();
        String query = "SELECT * FROM Cart";

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cart cart = new Cart();
                cart.setId(rs.getInt("CartID"));
                cart.setUserID(rs.getInt("UserID"));

                carts.add(cart);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching carts: " + e.getMessage());
        }

        return carts;
    }

    @Override
    public Cart getById(int id) {
        Cart cart = null;
        String query = "SELECT * FROM Cart WHERE CartID = ?";

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                cart = new Cart();
                cart.setId(rs.getInt("CartID"));
                cart.setUserID(rs.getInt("UserID"));
            }

        } catch (SQLException e) {
            System.out.println("Error fetching cart with ID " + id + ": " + e.getMessage());
        }

        return cart;
    }

    @Override
    public boolean insert(Cart cart) {
        String query = "INSERT INTO Cart (UserID) VALUES (?)";

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, cart.getUserID());
            stmt.executeUpdate();

            System.out.println("Cart inserted successfully.");
            return true;

        } catch (SQLException e) {
            System.out.println("Error inserting cart: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(Cart cart) {
        String query = "UPDATE Cart SET UserID = ? WHERE CartID = ?";

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, cart.getUserID());
            stmt.setInt(2, cart.getId());
            stmt.executeUpdate();

            System.out.println("Cart updated successfully.");
            return true;

        } catch (SQLException e) {
            System.out.println("Error updating cart with ID " + cart.getId() + ": " + e.getMessage());
        }
        return true;

    }

    @Override
    public boolean delete(int id) {
        String deleteCartItemsQuery = "DELETE FROM Cart WHERE CartID = ?";
        String deleteCartQuery = "DELETE FROM Cart WHERE CartID = ?";

        try (Connection conn = getConnect()) {
            // Xóa các bản ghi liên quan trong bảng CartItem trước
            try (PreparedStatement stmt = conn.prepareStatement(deleteCartItemsQuery)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }

            // Xóa các bản ghi liên quan trong bảng Cart
            try (PreparedStatement stmt = conn.prepareStatement(deleteCartQuery)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println("Error deleting cart with ID " + id + ": " + e.getMessage());
        }
        return false;
    }
    public boolean deleteCartItemByUserIdAndProductId(int userId, int productId) {
        String query = "DELETE CI FROM CartItem CI " +
                       "JOIN Cart C ON CI.CartID = C.CartID " +
                       "WHERE C.UserID = ? AND CI.ProductID = ?";

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, productId);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("CartItem deleted successfully.");
                return true;
            } else {
                System.out.println("No CartItem found with the given UserID and ProductID.");
            }

        } catch (SQLException e) {
            System.out.println("Error deleting CartItem with UserID " + userId + " and ProductID " + productId + ": " + e.getMessage());
        }
        return false;
    }
    
    public Cart getCartByUserId(int id) {
        Cart cart = null;
        String query = "SELECT * FROM Cart WHERE UserId = ?";

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                cart = new Cart();
                cart.setId(rs.getInt("CartID"));
                cart.setUserID(rs.getInt("UserID"));
            }

        } catch (SQLException e) {
            System.out.println("Error fetching cart with ID " + id + ": " + e.getMessage());
        }

        return cart;
    }
     public boolean removeCartItem(int cartItemId) {
    String query = "DELETE FROM CartItem WHERE CartItemID = ?";
    
    try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setInt(1, cartItemId);
        int rowsAffected = stmt.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("CartItem removed successfully.");
            return true;
        } else {
            System.out.println("No CartItem found with the given CartItemID.");
        }
    } catch (SQLException e) {
        System.out.println("Error removing CartItem with CartItemID " + cartItemId + ": " + e.getMessage());
    }
    return false;
}
}
