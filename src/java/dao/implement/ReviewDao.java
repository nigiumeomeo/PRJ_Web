package dao.implement;

import dao.DBconnect.DBConnect;
import dao.intefaces.GenericDao;
import model.Review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.User;

public class ReviewDao extends DBConnect implements GenericDao<Review> {

    @Override
    public List<Review> getAll() {
        List<Review> reviews = new ArrayList<>();
        String query = "SELECT * FROM Review";

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Review review = new Review(
                        rs.getInt("ReviewID"),
                        rs.getString("Description"),
                        rs.getInt("UserID"),
                        rs.getInt("ProductID")
                );
                reviews.add(review);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching reviews: " + e.getMessage());
        }

        return reviews;
    }

    @Override
    public Review getById(int id) {
        Review review = null;
        String query = "SELECT * FROM Review WHERE ReviewID = ?";

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                review = new Review(
                        rs.getInt("ReviewID"),
                        rs.getString("Description"),
                        rs.getInt("UserID"),
                        rs.getInt("ProductID")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error fetching review with ID " + id + ": " + e.getMessage());
        }

        return review;
    }

    @Override
    public boolean insert(Review review) {
        String query = "INSERT INTO Review (Description, UserID, ProductID) VALUES (?, ?, ?)";

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, review.getDescription());
            stmt.setInt(2, review.getUserID());
            stmt.setInt(3, review.getProductID());
            stmt.executeUpdate();

            System.out.println("Review inserted successfully.");
            return true;

        } catch (SQLException e) {
            System.out.println("Error inserting review: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(Review review) {
        String query = "UPDATE Review SET Description = ?, UserID = ?, ProductID = ? WHERE ReviewID = ?";

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, review.getDescription());
            stmt.setInt(2, review.getUserID());
            stmt.setInt(3, review.getProductID());
            stmt.setInt(4, review.getId());
            stmt.executeUpdate();

            System.out.println("Review updated successfully.");
            return true;

        } catch (SQLException e) {
            System.out.println("Error updating review with ID " + review.getId() + ": " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String query = "DELETE FROM Review WHERE ReviewID = ?";

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

            System.out.println("Review deleted successfully.");
            return true;

        } catch (SQLException e) {
            System.out.println("Error deleting review with ID " + id + ": " + e.getMessage());
        }
        return false;
    }

    public List<Review> getReviewsByProductId(int productId) {
        List<Review> reviews = new ArrayList<>();
        String query = "SELECT r.ReviewID, r.ProductID, r.UserID, r.Description, u.FirstName, u.LastName, u.AvatarURL "
                + "FROM Review r "
                + "JOIN [User] u ON r.UserID = u.UserID "
                + "WHERE r.ProductID = ?";

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Review review = new Review();
                review.setId(rs.getInt("ReviewID"));
                review.setProductID(rs.getInt("ProductID"));
                review.setUserID(rs.getInt("UserID"));
                review.setDescription(rs.getString("Description"));

                User user = new User();
                user.setFirstName(rs.getString("FirstName"));
                user.setLastName(rs.getString("LastName"));
                user.setAvatarURL(rs.getString("AvatarURL"));
                review.setUser(user);

                reviews.add(review);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching reviews for product with ID " + productId + ": " + e.getMessage());
        }

        return reviews;
    }
    public List<Review> getAllReviews() {
    List<Review> reviews = new ArrayList<>();
    String query = "SELECT r.ReviewID, r.ProductID, r.UserID, r.Description, u.FirstName, u.LastName, u.AvatarURL "
            + "FROM Review r "
            + "JOIN [User] u ON r.UserID = u.UserID";

    try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(query)) {
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Review review = new Review();
            review.setId(rs.getInt("ReviewID"));
            review.setProductID(rs.getInt("ProductID"));
            review.setUserID(rs.getInt("UserID"));
            review.setDescription(rs.getString("Description"));

            User user = new User();
            user.setFirstName(rs.getString("FirstName"));
            user.setLastName(rs.getString("LastName"));
            user.setAvatarURL(rs.getString("AvatarURL"));
            review.setUser(user);

            reviews.add(review);
        }
    } catch (SQLException e) {
        System.out.println("Error fetching all reviews: " + e.getMessage());
    }

    return reviews;
}

}
