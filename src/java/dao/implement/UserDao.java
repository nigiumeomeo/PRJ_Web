package dao.implement;

import dao.DBconnect.DBConnect;
import dao.intefaces.GenericDao;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends DBConnect implements GenericDao<User> {

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM [User]";

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                User user = new User(
                        rs.getInt("UserID"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("Address"),
                        rs.getString("Role"),
                        rs.getString("AvatarURL"),
                        rs.getString("Email"),
                        rs.getString("Password")
                );
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching users: " + e.getMessage());
        }

        return users;
    }

    public int getTotalCustomers() {
        int totalCustomers = 0;
        String query = "SELECT COUNT(*) AS TotalCustomers FROM Customer";

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                totalCustomers = rs.getInt("TotalCustomers");
            }

        } catch (SQLException e) {
            System.out.println("Error fetching total customers: " + e.getMessage());
        }

        return totalCustomers;
    }

    @Override
    public User getById(int id) {
        User user = null;
        String query = "SELECT * FROM [User] WHERE UserID = ?";

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user = new User(
                        rs.getInt("UserID"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("Address"),
                        rs.getString("Role"),
                        rs.getString("AvatarURL"),
                        rs.getString("Password")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error fetching user with ID " + id + ": " + e.getMessage());
        }

        return user;
    }

    @Override
    public boolean insert(User user) {
        String query = "INSERT INTO [User] (FirstName, LastName, Address, Role, AvatarURL, Email, Password) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getAddress());
            stmt.setString(4, user.getRole());
            stmt.setString(5, user.getAvatarURL());
            stmt.setString(6, user.getEmail());
            stmt.setString(7, user.getPassword());
            stmt.executeUpdate();

            System.out.println("User inserted successfully.");
            return true;

        } catch (SQLException e) {
            System.out.println("Error inserting user: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(User user) {
        String query = "UPDATE [User] SET FirstName = ?, LastName = ?, Address = ?, Role = ?, AvatarURL = ? WHERE UserID = ?";

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getAddress());
            stmt.setString(4, user.getRole());
            stmt.setString(5, user.getAvatarURL());
            stmt.setInt(6, user.getId());
            stmt.executeUpdate();

            System.out.println("User updated successfully.");
            return true;

        } catch (SQLException e) {
            System.out.println("Error updating user with ID " + user.getId() + ": " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String deleteCartItemsQuery = "DELETE FROM CartItem WHERE CartID IN (SELECT CartID FROM Cart WHERE UserID = ?)";
        String deleteCartQuery = "DELETE FROM Cart WHERE UserID = ?";
        String deleteOrderDetailsQuery = "DELETE FROM OrderDetail WHERE OrderID IN (SELECT OrderID FROM [Order] WHERE UserID = ?)";
        String deleteOrdersQuery = "DELETE FROM [Order] WHERE UserID = ?";
        String deleteReviewsQuery = "DELETE FROM Review WHERE UserID = ?";
        String deleteUserQuery = "DELETE FROM [User] WHERE UserID = ?";

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

            // Xóa các bản ghi liên quan trong bảng OrderDetail
            try (PreparedStatement stmt = conn.prepareStatement(deleteOrderDetailsQuery)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }

            // Xóa các bản ghi liên quan trong bảng Order
            try (PreparedStatement stmt = conn.prepareStatement(deleteOrdersQuery)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }

            // Xóa các bản ghi liên quan trong bảng Review
            try (PreparedStatement stmt = conn.prepareStatement(deleteReviewsQuery)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }

            // Xóa người dùng
            try (PreparedStatement stmt = conn.prepareStatement(deleteUserQuery)) {
                stmt.setInt(1, id);
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("User deleted successfully.");
                    return true;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error deleting user with ID " + id + ": " + e.getMessage());
        }
        return false;
    }

    public List<User> getPaginatedUsers(int offset, int limit) {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM [User] ORDER BY UserID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try (Connection connection = getConnect(); PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, offset);
            statement.setInt(2, limit);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("UserID");
                    String firstName = resultSet.getString("FirstName");
                    String lastName = resultSet.getString("LastName");
                    String address = resultSet.getString("Address");
                    String role = resultSet.getString("Role");
                    String avatarURL = resultSet.getString("AvatarURL");

                    User user = new User(id, firstName, lastName, address, role, avatarURL);
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public int getTotalUserCount() {
        String sql = "SELECT COUNT(*) AS total FROM [User]";
        int total = 0;

        try (Connection connection = getConnect(); PreparedStatement statement = connection.prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                total = resultSet.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return total;
    }

    public User getUserByEmail(String email) {
        User user = null;
        String query = "SELECT * FROM [User] WHERE Email = ?";

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user = new User(
                        rs.getInt("UserID"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("Address"),
                        rs.getString("Role"),
                        rs.getString("AvatarURL"),
                        rs.getString("Email"),
                        rs.getString("Password")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error fetching user with username " + email + ": " + e.getMessage());
        }

        return user;
    }

    public void changeInfor(User u) {
        String query = "update [User] \n"
                + "set FirstName = ?, \n"
                + "	 LastName = ?,\n"
                + "	 Password = ?\n"
                + "where UserID = ?";
        try {
            PreparedStatement ps = getConnect().prepareStatement(query);
            ps.setString(1, u.getFirstName());
            ps.setString(2, u.getLastName());
            ps.setString(3, u.getPassword());
            ps.setInt(4, u.getId());
            ps.execute();
        } catch (Exception e) {
        }
    }

    public boolean updateImageUrl(User user) {
        String query = "UPDATE [User] set AvatarURL = ? WHERE UserID = ?";

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, user.getAvatarURL());
            stmt.setInt(2, user.getId());
            stmt.executeUpdate();

            System.out.println("User updated successfully.");
            return true;

        } catch (SQLException e) {
            System.out.println("Error updating user with ID " + user.getId() + ": " + e.getMessage());
        }
        return false;
    }

    public int getUserIdByFirstName(String firstName) {
        int userId = -1; // Default value if no user is found
        String query = "SELECT UserID FROM [User] WHERE FirstName = ?";

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, firstName);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                userId = rs.getInt("UserID");
            }

        } catch (SQLException e) {
            System.out.println("Error fetching user ID with first name " + firstName + ": " + e.getMessage());
        }

        return userId;
    }

    public int register(User user) {
        String query = "INSERT INTO [User] (FirstName, LastName, Address, Role, AvatarURL, Email, Password) VALUES (?, ?, ?, ?, ?, ?, ?)";
        int userId = -1;

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getAddress());
            stmt.setString(4, user.getRole());
            stmt.setString(5, user.getAvatarURL());
            stmt.setString(6, user.getEmail());
            stmt.setString(7, user.getPassword());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    userId = generatedKeys.getInt(1);
                    System.out.println("User inserted successfully with ID: " + userId);
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Error inserting user: " + e.getMessage());
        }

        return userId;
    }

    public static void main(String[] args) {
        // Khởi tạo các biến
        String firstName = "LOI";
        String lastName = "Doe";
        String email = "john.doe@example.com";
        String password = "securepassword";

        // Tạo một đối tượng UserDao
        UserDao userDao = new UserDao();

        // Tạo một đối tượng User mới để đăng ký
        User newUser = new User(0, firstName, lastName, "", "", "", email, password);

        int userId = userDao.register(newUser);
        System.out.println("User registered successfully with ID: " + userId);
    }
}
