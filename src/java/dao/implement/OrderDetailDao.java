package dao.implement;

import dao.DBconnect.DBConnect;
import dao.intefaces.GenericDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.OrderDetail;

public class OrderDetailDao extends DBConnect implements GenericDao<OrderDetail> {

    @Override
    public List<OrderDetail> getAll() {
        List<OrderDetail> orderDetails = new ArrayList<>();
        String query = "SELECT * FROM OrderDetail";

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                OrderDetail orderDetail = new OrderDetail(
                        rs.getInt("OrderDetailID"),
                        rs.getInt("OrderID"),
                        rs.getInt("ProductID"),
                        rs.getInt("Quantity"),
                        rs.getDouble("Cost")
                );
                orderDetails.add(orderDetail);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching order details: " + e.getMessage());
        }

        return orderDetails;
    }

    @Override
    public OrderDetail getById(int id) {
        OrderDetail orderDetail = null;
        String query = "SELECT * FROM OrderDetail WHERE OrderDetailID = ?";

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                orderDetail = new OrderDetail(
                        rs.getInt("OrderDetailID"),
                        rs.getInt("OrderID"),
                        rs.getInt("ProductID"),
                        rs.getInt("Quantity"),
                        rs.getDouble("Cost")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error fetching order detail with ID " + id + ": " + e.getMessage());
        }

        return orderDetail;
    }

    @Override
    public boolean insert(OrderDetail orderDetail) {
        String query = "INSERT INTO OrderDetail (OrderID, ProductID, Quantity, Cost) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, orderDetail.getOrderID());
            stmt.setInt(2, orderDetail.getProductID());
            stmt.setInt(3, orderDetail.getQuantity());
            stmt.setDouble(4, orderDetail.getCost());
            stmt.executeUpdate();

            System.out.println("Order detail inserted successfully.");
            return true;

        } catch (SQLException e) {
            System.out.println("Error inserting order detail: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(OrderDetail orderDetail) {
        String query = "UPDATE OrderDetail SET OrderID = ?, ProductID = ?, Quantity = ?, Cost = ? WHERE OrderDetailID = ?";

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, orderDetail.getOrderID());
            stmt.setInt(2, orderDetail.getProductID());
            stmt.setInt(3, orderDetail.getQuantity());
            stmt.setDouble(4, orderDetail.getCost());
            stmt.setInt(5, orderDetail.getId());
            stmt.executeUpdate();

            System.out.println("Order detail updated successfully.");
            return true;

        } catch (SQLException e) {
            System.out.println("Error updating order detail with ID " + orderDetail.getId() + ": " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String query = "DELETE FROM OrderDetail WHERE OrderDetailID = ?";

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

            System.out.println("Order detail deleted successfully.");
            return true;

        } catch (SQLException e) {
            System.out.println("Error deleting order detail with ID " + id + ": " + e.getMessage());
        }
        return false;
    }

    public List<OrderDetail> getOrderDetailsByOrderID(int id) {
       List<OrderDetail> orderDetails = new ArrayList<>();
        try (Connection connection = getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM OrderDetail WHERE OrderID = ?")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setId(resultSet.getInt("OrderDetailID"));
                orderDetail.setOrderID(resultSet.getInt("OrderID"));
                orderDetail.setProductID(resultSet.getInt("ProductID"));
                orderDetail.setQuantity(resultSet.getInt("Quantity"));
//                orderDetail.setCost(resultSet.getDouble("Cost"));

                orderDetails.add(orderDetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderDetails;
    }
}


