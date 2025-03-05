package dao.implement;

import dao.DBconnect.DBConnect;
import dao.intefaces.GenericDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Order;

public class OrderDao extends DBConnect implements GenericDao<Order> {

    @Override
    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM [Order]";

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("OrderID"));
                order.setUserID(rs.getInt("UserID"));
                order.setOrderDate(rs.getDate("OrderDate"));
                order.setExpectedDate(rs.getDate("ExpectedDate"));
                order.setOrderStatusID(rs.getInt("OrderStatusID"));
                order.setTotalCost(rs.getDouble("TotalCost"));

                orders.add(order);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching orders: " + e.getMessage());
        }

        return orders;
    }
    
    
      public double getTotalOrderCost() {
        double totalCost = 0;
        String query = "SELECT SUM(OD.Quantity * P.Price) AS TotalCost "
                + "FROM OrderDetail OD "
                + "JOIN Product P ON OD.ProductId = P.ProductId";

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                totalCost = rs.getDouble("TotalCost");
            }

        } catch (SQLException e) {
            System.out.println("Error calculating total order cost: " + e.getMessage());
        }

        return totalCost;
    }


     public int getTotalOrders() {
        int totalOrders = 0;
        String query = "SELECT COUNT(*) AS TotalOrders FROM [Order]";

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                totalOrders = rs.getInt("TotalOrders");
            }

        } catch (SQLException e) {
            System.out.println("Error fetching total orders: " + e.getMessage());
        }

        return totalOrders;
    }
    
    @Override
    public Order getById(int id) {
        Order order = null;
        String query = "SELECT * FROM [Order] WHERE OrderID = ?";

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                order = new Order();
                order.setId(rs.getInt("OrderID"));
                order.setUserID(rs.getInt("UserID"));
                order.setOrderDate(rs.getDate("OrderDate"));
                order.setExpectedDate(rs.getDate("ExpectedDate"));
                order.setOrderStatusID(rs.getInt("OrderStatusID"));
                order.setTotalCost(rs.getDouble("TotalCost"));
            }

        } catch (SQLException e) {
            System.out.println("Error fetching order with ID " + id + ": " + e.getMessage());
        }

        return order;
    }

    @Override
    public boolean insert(Order order) {
        String query = "INSERT INTO [Order] (UserID, OrderDate, ExpectedDate, OrderStatusID, TotalCost) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, order.getUserID());
            stmt.setDate(2, new java.sql.Date(order.getOrderDate().getTime()));
            stmt.setDate(3, new java.sql.Date(order.getExpectedDate().getTime()));
            stmt.setInt(4, order.getOrderStatusID());
            stmt.setDouble(5, order.getTotalCost());
            stmt.executeUpdate();

            System.out.println("Order inserted successfully.");
            return true;

        } catch (SQLException e) {
            System.out.println("Error inserting order: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(Order order) {
        String query = "UPDATE [Order] SET UserID = ?, OrderDate = ?, ExpectedDate = ?, OrderStatusID = ?, TotalCost = ? WHERE OrderID = ?";

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, order.getUserID());
            stmt.setDate(2, new java.sql.Date(order.getOrderDate().getTime()));
            stmt.setDate(3, new java.sql.Date(order.getExpectedDate().getTime()));
            stmt.setInt(4, order.getOrderStatusID());
            stmt.setDouble(5, order.getTotalCost());
            stmt.setInt(6, order.getId());
            stmt.executeUpdate();

            System.out.println("Order updated successfully.");
            return true;

        } catch (SQLException e) {
            System.out.println("Error updating order with ID " + order.getId() + ": " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String query = "DELETE FROM [Order] WHERE OrderID = ?";

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

            System.out.println("Order deleted successfully.");
            return true;

        } catch (SQLException e) {
            System.out.println("Error deleting order with ID " + id + ": " + e.getMessage());
        }
        return false;
    }

    public List<Order> getOrderByUserID(int id) {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM [Order] WHERE UserID = ?";

        try {
            PreparedStatement ps = getConnect().prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("OrderID"));
                order.setUserID(rs.getInt("UserID"));
                order.setOrderDate(rs.getDate("OrderDate"));
                order.setExpectedDate(rs.getDate("ExpectedDate"));
                order.setOrderStatusID(rs.getInt("OrderStatusID"));
                order.setTotalCost(rs.getDouble("TotalCost"));

                orders.add(order);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching orders: " + e.getMessage());
        }

        return orders;
    }

    public double getTotalCostByID(int id) {
        double cost = 0;
        try {
            String query = "Select TotalCost from [Order]"
                    + "where OrderID = ?";
            PreparedStatement ps = getConnect().prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cost = rs.getDouble(1);
            }
        } catch (SQLException e) {
            System.out.println("Error get quantity by category!!");
        }
        return cost;
    }

    public List<Order> getPaginatedorders(int offset, int limit) {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM [Order] ORDER BY orderID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, offset);
            stmt.setInt(2, limit);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("OrderID"));
                order.setUserID(rs.getInt("UserID"));
                order.setOrderDate(rs.getTimestamp("OrderDate"));
                order.setOrderStatusID(rs.getInt("OrderStatusID"));
                order.setTotalCost(rs.getDouble("TotalCost"));

                orders.add(order);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching paginated orders: " + e.getMessage());
        }

        return orders;
    }

    public int getTotalOrderCount() {
        int totalCount = 0;
        String query = "SELECT COUNT(*) FROM [Order]";

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                totalCount = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching total Order count: " + e.getMessage());
        }

        return totalCount;
    }

    public boolean updateOrderStatus(int orderId, int orderStatus) {
        String query = "UPDATE [Order] SET orderStatusID = ? WHERE OrderID = ?";

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, orderStatus);
            stmt.setInt(2, orderId);
            stmt.executeUpdate();

            System.out.println("Order updated successfully.");
            return true;

        } catch (SQLException e) {
            System.out.println(e);
        }

        return false;
    }
}
