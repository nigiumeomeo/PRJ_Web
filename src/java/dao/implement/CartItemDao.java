package dao.implement;

import dao.DBconnect.DBConnect;
import dao.intefaces.GenericDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.CartItem;
import model.Product;

public class CartItemDao extends DBConnect implements GenericDao<CartItem> {

    @Override
    public List<CartItem> getAll() {
        List<CartItem> cartItems = new ArrayList<>();
        String query = "SELECT * FROM CartItem";

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                CartItem cartItem = new CartItem();
                cartItem.setId(rs.getInt("CartItemID"));
                cartItem.setQuantity(rs.getInt("Quantity"));
                cartItem.setTotalCost(rs.getDouble("TotalCost"));
                cartItem.setCartID(rs.getInt("CartID"));
                cartItem.setProductID(rs.getInt("ProductID"));

                cartItems.add(cartItem);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching cart items: " + e.getMessage());
        }

        return cartItems;
    }

    @Override
    public CartItem getById(int id) {
        CartItem cartItem = null;
        String query = "SELECT * FROM CartItem WHERE CartItemID = ?";

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                cartItem = new CartItem();
                cartItem.setId(rs.getInt("CartItemID"));
                cartItem.setQuantity(rs.getInt("Quantity"));
                cartItem.setTotalCost(rs.getDouble("TotalCost"));
                cartItem.setCartID(rs.getInt("CartID"));
                cartItem.setProductID(rs.getInt("ProductID"));
            }

        } catch (SQLException e) {
            System.out.println("Error fetching cart item with ID " + id + ": " + e.getMessage());
        }

        return cartItem;
    }

    @Override
    public boolean insert(CartItem cartItem) {
        String query = "INSERT INTO CartItem (Quantity, TotalCost, CartID, ProductID) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, cartItem.getQuantity());
            stmt.setDouble(2, cartItem.getTotalCost());
            stmt.setInt(3, cartItem.getCartID());
            stmt.setInt(4, cartItem.getProductID());
            stmt.executeUpdate();

            System.out.println("Cart item inserted successfully.");
            return true;
        } catch (SQLException e) {
            System.out.println("Error inserting cart item: " + e.getMessage());
        }
        return false;
    }

    public boolean insertCartItem(int userID, int quantity, int productID) {
        double price = 0.0;
        int cartID = 0;
        String getPriceQuery = "SELECT Price FROM Product WHERE ProductID = ?";
        String getCartIDQuery = "SELECT CartID FROM Cart WHERE UserID = ?";
        String insertCartItemQuery = "INSERT INTO CartItem (Quantity, TotalCost, CartID, ProductID) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnect(); PreparedStatement getPriceStmt = conn.prepareStatement(getPriceQuery); PreparedStatement getCartIDStmt = conn.prepareStatement(getCartIDQuery); PreparedStatement insertStmt = conn.prepareStatement(insertCartItemQuery)) {

            // Get product price
            getPriceStmt.setInt(1, productID);
            try (ResultSet rs = getPriceStmt.executeQuery()) {
                if (rs.next()) {
                    price = rs.getDouble("Price");
                } else {
                    System.out.println("Product not found with ID: " + productID);
                    return false;
                }
            }

            // Get cart ID
            getCartIDStmt.setInt(1, userID);
            try (ResultSet rs = getCartIDStmt.executeQuery()) {
                if (rs.next()) {
                    cartID = rs.getInt("CartID");
                } else {
                    System.out.println("Cart not found for user with ID: " + userID);
                    return false;
                }
            }

            // Calculate total cost
            double totalCost = price * quantity;

            // Insert cart item
            insertStmt.setInt(1, quantity);
            insertStmt.setDouble(2, totalCost);
            insertStmt.setInt(3, cartID);
            insertStmt.setInt(4, productID);
            insertStmt.executeUpdate();

            System.out.println("Cart item inserted successfully.");
            return true;

        } catch (SQLException e) {
            System.out.println("Error inserting cart item: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(CartItem cartItem) {
        String query = "UPDATE CartItem SET Quantity = ?, TotalCost = ?, CartID = ?, ProductID = ? WHERE CartItemID = ?";

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, cartItem.getQuantity());
            stmt.setDouble(2, cartItem.getTotalCost());
            stmt.setInt(3, cartItem.getCartID());
            stmt.setInt(4, cartItem.getProductID());
            stmt.setInt(5, cartItem.getId());
            stmt.executeUpdate();

            System.out.println("Cart item updated successfully.");
            return true;
        } catch (SQLException e) {
            System.out.println("Error updating cart item with ID " + cartItem.getId() + ": " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String query = "DELETE FROM CartItem WHERE CartItemID = ?";

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

            System.out.println("Cart item deleted successfully.");
            return true;
        } catch (SQLException e) {
            System.out.println("Error deleting cart item with ID " + id + ": " + e.getMessage());
        }
        return false;
    }

    public List<CartItem> getCartItemByCartId(int cartId) {
        List<CartItem> cartItems = new ArrayList<>();
        String query = "SELECT ci.CartItemID, ci.Quantity, ci.TotalCost, ci.CartID, ci.ProductID, "
                + "p.ProductID, p.FullName, p.Description, p.Price, p.ImageURL "
                + "FROM CartItem ci "
                + "JOIN Product p ON ci.ProductID = p.ProductID "
                + "WHERE ci.CartID = ?";

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, cartId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    CartItem cartItem = new CartItem();
                    cartItem.setId(rs.getInt("CartItemID"));
                    cartItem.setQuantity(rs.getInt("Quantity"));
                    cartItem.setTotalCost(rs.getDouble("TotalCost"));
                    cartItem.setCartID(rs.getInt("CartID"));
                    cartItem.setProductID(rs.getInt("ProductID"));

                    Product product = new Product();
                    product.setProductID(rs.getInt("ProductID"));
                    product.setFullName(rs.getString("FullName"));
                    product.setDescription(rs.getString("Description"));
                    product.setPrice(rs.getDouble("Price"));
                    product.setImageURL(rs.getString("ImageURL"));
                    
                    cartItem.setProduct(product);

                    cartItems.add(cartItem);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching cart items: " + e.getMessage());
        }

        return cartItems;
    }
    
    public boolean deleteCartItemByCartId(int id) {
        String query = "DELETE FROM CartItem WHERE CartID = ?";

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

            System.out.println("Cart item deleted successfully.");
            return true;
        } catch (SQLException e) {
            System.out.println("Error deleting cart item with ID " + id + ": " + e.getMessage());
        }
        return false;
    }
  
}
