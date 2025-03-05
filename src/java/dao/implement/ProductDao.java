/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.implement;

import java.sql.*;
import dao.DBconnect.DBConnect;
import dao.intefaces.IProductDao;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Product;

/**
 *
 * @author admin
 */
public class ProductDao extends DBConnect implements IProductDao {

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM Product";

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Product product = new Product();
                product.setProductID(rs.getInt("ProductID"));
                product.setBestSeller(rs.getBoolean("isBestSeller"));
                product.setFullName(rs.getString("FullName"));
                product.setDescription(rs.getString("Description"));
                product.setQuantity(rs.getInt("Quantity"));
                product.setQuantitySold(rs.getInt("QuantitySold"));
                product.setImageURL(rs.getString("ImageURL"));
                product.setCategoryID(rs.getInt("CategoryID"));
                product.setPrice(rs.getDouble("Price"));
                product.setDiscount(rs.getInt("discount"));

                products.add(product);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching products: " + e.getMessage());
        }

        return products;
    }

     // Hàm tính tổng số sản phẩm
    public int getTotalProducts() {
        int totalProducts = 0;
        String query = "SELECT COUNT(*) AS TotalProducts FROM Product";

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                totalProducts = rs.getInt("TotalProducts");
            }

        } catch (SQLException e) {
            System.out.println("Error fetching total products: " + e.getMessage());
        }

        return totalProducts;
    }
    
    @Override
    public Product getById(int id) {
        Product product = null;
        String query = "SELECT * FROM Product WHERE ProductID = ?";

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                product = new Product();
                product.setProductID(rs.getInt("ProductID"));
                product.setBestSeller(rs.getBoolean("isBestSeller"));
                product.setFullName(rs.getString("FullName"));
                product.setDescription(rs.getString("Description"));
                product.setQuantity(rs.getInt("Quantity"));
                product.setQuantitySold(rs.getInt("QuantitySold"));
                product.setImageURL(rs.getString("ImageURL"));
                product.setCategoryID(rs.getInt("CategoryID"));
                product.setPrice(rs.getDouble("Price"));
                product.setDiscount(rs.getInt("discount"));
            }

        } catch (SQLException e) {
            System.out.println("Error fetching product with ID " + id + ": " + e.getMessage());
        }

        return product;
    }

    @Override
    public boolean insert(Product product) {
        String query = "INSERT INTO Product (isBestSeller, FullName, Description, Quantity, QuantitySold, ImageURL, CategoryID, Price, discount) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setBoolean(1, product.isBestSeller());
            stmt.setString(2, product.getFullName());
            stmt.setString(3, product.getDescription());
            stmt.setInt(4, product.getQuantity());
            stmt.setInt(5, product.getQuantitySold());
            stmt.setString(6, product.getImageURL());
            stmt.setInt(7, product.getCategoryID());
            stmt.setDouble(8, product.getPrice());
            stmt.setInt(9, product.getDiscount());

            stmt.executeUpdate();
            System.out.println("Product inserted successfully.");
            return true;

        } catch (SQLException e) {
            System.out.println("Error inserting product: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(Product product) {
        String query = "UPDATE Product SET isBestSeller=?, FullName=?, Description=?, Quantity=?, QuantitySold=?, "
                + "ImageURL=?, CategoryID=?, Price=?, discount=? WHERE ProductID=?";

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setBoolean(1, product.isBestSeller());
            stmt.setString(2, product.getFullName());
            stmt.setString(3, product.getDescription());
            stmt.setInt(4, product.getQuantity());
            stmt.setInt(5, product.getQuantitySold());
            stmt.setString(6, product.getImageURL());
            stmt.setInt(7, product.getCategoryID());
            stmt.setDouble(8, product.getPrice());
            stmt.setInt(9, product.getDiscount());
            stmt.setInt(10, product.getProductID());

            stmt.executeUpdate();
            System.out.println("Product updated successfully.");
            return true;

        } catch (SQLException e) {
            System.out.println("Error updating product: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
            String deleteCartItemQuery = "DELETE FROM CartItem WHERE ProductID = ?";
            String deleteProductQuery = "DELETE FROM Product WHERE ProductID = ?";

        try (Connection conn = getConnect()) {
            // Xóa các bản ghi liên quan trong bảng CartItem trước
            try (PreparedStatement stmt = conn.prepareStatement(deleteCartItemQuery)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }

            // Xóa các bản ghi liên quan trong bảng Cart
            try (PreparedStatement stmt = conn.prepareStatement(deleteProductQuery)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println("Error deleting product with ID " + id + ": " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<Product> laptopCategory1() {
        List<Product> result = new ArrayList<>();
        try {
            String query = "select  top 8 * from Product"
                    + "	where CategoryID = 1";
            PreparedStatement ps = getConnect().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProductID(rs.getInt("ProductID"));
                product.setBestSeller(rs.getBoolean("isBestSeller"));
                product.setFullName(rs.getString("FullName"));
                product.setDescription(rs.getString("Description"));
                product.setQuantity(rs.getInt("Quantity"));
                product.setQuantitySold(rs.getInt("QuantitySold"));
                product.setImageURL(rs.getString("ImageURL"));
                product.setCategoryID(rs.getInt("CategoryID"));
                product.setPrice(rs.getDouble("Price"));
                product.setDiscount(rs.getInt("discount"));

                result.add(product);
            }
        } catch (SQLException e) {
            System.out.println("Error get organic products!!");
        }
        return result;
    }

    @Override
    public List<Product> getFreshVegetables() {
        List<Product> result = new ArrayList<>();
        try {
            String query = "select  top 10 * from Product\n"
                    + "	where CategoryID = 2";
            PreparedStatement ps = getConnect().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProductID(rs.getInt("ProductID"));
                product.setBestSeller(rs.getBoolean("isBestSeller"));
                product.setFullName(rs.getString("FullName"));
                product.setDescription(rs.getString("Description"));
                product.setQuantity(rs.getInt("Quantity"));
                product.setQuantitySold(rs.getInt("QuantitySold"));
                product.setImageURL(rs.getString("ImageURL"));
                product.setCategoryID(rs.getInt("CategoryID"));
                product.setPrice(rs.getDouble("Price"));
                product.setDiscount(rs.getInt("discount"));

                result.add(product);
            }
        } catch (SQLException e) {
            System.out.println("Error get fresh vegetables!!");
        }
        return result;
    }

    @Override
    public List<Product> getBestSeller() {
        List<Product> result = new ArrayList<>();
        try {
            String query = "select top 9 * from Product\n"
                    + "	where isBestSeller = 1";
            PreparedStatement ps = getConnect().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProductID(rs.getInt("ProductID"));
                product.setBestSeller(rs.getBoolean("isBestSeller"));
                product.setFullName(rs.getString("FullName"));
                product.setDescription(rs.getString("Description"));
                product.setQuantity(rs.getInt("Quantity"));
                product.setQuantitySold(rs.getInt("QuantitySold"));
                product.setImageURL(rs.getString("ImageURL"));
                product.setCategoryID(rs.getInt("CategoryID"));
                product.setPrice(rs.getDouble("Price"));
                product.setDiscount(rs.getInt("discount"));

                result.add(product);
            }
        } catch (SQLException e) {
            System.out.println("Error get bestseller!!");
        }
        return result;
    }

    public int getTotalProductCount() {
        int totalCount = 0;
        String query = "SELECT COUNT(*) FROM Product";

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                totalCount = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching total product count: " + e.getMessage());
        }

        return totalCount;
    }

    @Override
    public List<Product> getPaginatedProducts(int offset, int limit) {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM [Product] ORDER BY ProductID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, offset);
            stmt.setInt(2, limit);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setProductID(rs.getInt("ProductID"));
                product.setBestSeller(rs.getBoolean("isBestSeller"));
                product.setFullName(rs.getString("FullName"));
                product.setDescription(rs.getString("Description"));
                product.setQuantity(rs.getInt("Quantity"));
                product.setQuantitySold(rs.getInt("QuantitySold"));
                product.setImageURL(rs.getString("ImageURL"));
                product.setCategoryID(rs.getInt("CategoryID"));
                product.setPrice(rs.getDouble("Price"));
                product.setDiscount(rs.getInt("discount"));

                products.add(product);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching paginated products: " + e.getMessage());
        }

        return products;
    }

    @Override
    public List<Product> laptopCategory2() {
        List<Product> result = new ArrayList<>();
        try {
            String query = "select  top 8 * from Product"
                    + "	where CategoryID = 2";
            PreparedStatement ps = getConnect().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProductID(rs.getInt("ProductID"));
                product.setBestSeller(rs.getBoolean("isBestSeller"));
                product.setFullName(rs.getString("FullName"));
                product.setDescription(rs.getString("Description"));
                product.setQuantity(rs.getInt("Quantity"));
                product.setQuantitySold(rs.getInt("QuantitySold"));
                product.setImageURL(rs.getString("ImageURL"));
                product.setCategoryID(rs.getInt("CategoryID"));
                product.setPrice(rs.getDouble("Price"));
                product.setDiscount(rs.getInt("discount"));

                result.add(product);
            }
        } catch (SQLException e) {
            System.out.println("Error get organic vegetable!!");
        }
        return result;
    }

    @Override
    public List<Product> laptopCategory3() {
        List<Product> result = new ArrayList<>();
        try {
            String query = "select  top 8 * from Product"
                    + "	where CategoryID = 3";
            PreparedStatement ps = getConnect().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProductID(rs.getInt("ProductID"));
                product.setBestSeller(rs.getBoolean("isBestSeller"));
                product.setFullName(rs.getString("FullName"));
                product.setDescription(rs.getString("Description"));
                product.setQuantity(rs.getInt("Quantity"));
                product.setQuantitySold(rs.getInt("QuantitySold"));
                product.setImageURL(rs.getString("ImageURL"));
                product.setCategoryID(rs.getInt("CategoryID"));
                product.setPrice(rs.getDouble("Price"));
                product.setDiscount(rs.getInt("discount"));

                result.add(product);
            }
        } catch (SQLException e) {
            System.out.println("Error get organic Nuts!!");
        }
        return result;
    }

    public List<Product> getFeatureProduct() {
        List<Product> result = new ArrayList<>();
        String query = "SELECT * FROM Product WHERE discount != 0";

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Product product = new Product();
                product.setProductID(rs.getInt("ProductID"));
                product.setBestSeller(rs.getBoolean("isBestSeller"));
                product.setFullName(rs.getString("FullName"));
                product.setDescription(rs.getString("Description"));
                product.setQuantity(rs.getInt("Quantity"));
                product.setQuantitySold(rs.getInt("QuantitySold"));
                product.setImageURL(rs.getString("ImageURL"));
                product.setCategoryID(rs.getInt("CategoryID"));
                product.setPrice(rs.getDouble("Price"));
                product.setDiscount(rs.getInt("discount"));

                result.add(product);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching featured products: " + e.getMessage());
        }

        return result;
    }

    @Override
    public List<Product> searchByName(String keyword) {
        List<Product> result = new ArrayList<>();
        try {
            String query = "select * from Product\n"
                    + "	where CHARINDEX(?, FullName) > 0";
            PreparedStatement ps = getConnect().prepareStatement(query);
            ps.setString(1, keyword);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setProductID(rs.getInt("ProductID"));
                product.setBestSeller(rs.getBoolean("isBestSeller"));
                product.setFullName(rs.getString("FullName"));
                product.setDescription(rs.getString("Description"));
                product.setQuantity(rs.getInt("Quantity"));
                product.setQuantitySold(rs.getInt("QuantitySold"));
                product.setImageURL(rs.getString("ImageURL"));
                product.setCategoryID(rs.getInt("CategoryID"));
                product.setPrice(rs.getDouble("Price"));
                product.setDiscount(rs.getInt("discount"));

                result.add(product);
            }
        } catch (SQLException e) {

            System.out.println("Error fetching featured products: " + e.getMessage());
        }

        return result;
    }

    public List<Product> getPaginatedProductsDynamic(double low, double high, int categoryID, String orderBy, String sortColumn, int offset, int limit) {
        List<Product> products = new ArrayList<>();

        String query;

        if (categoryID == 0) {
            query = "SELECT * FROM [Product] WHERE Price >= ? AND Price < ? ORDER BY " + sortColumn + " " + orderBy + " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        } else {
            query = "SELECT * FROM [Product] WHERE CategoryID = ? AND Price >= ? AND Price < ? ORDER BY " + sortColumn + " " + orderBy + " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        }

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(query)) {
            int paramIndex = 1;
            if (categoryID != 0) {
                stmt.setInt(paramIndex++, categoryID);
            }
            stmt.setDouble(paramIndex++, low);
            stmt.setDouble(paramIndex++, high);
            stmt.setInt(paramIndex++, offset);
            stmt.setInt(paramIndex++, limit);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setProductID(rs.getInt("ProductID"));
                product.setBestSeller(rs.getBoolean("isBestSeller"));
                product.setFullName(rs.getString("FullName"));
                product.setDescription(rs.getString("Description"));
                product.setQuantity(rs.getInt("Quantity"));
                product.setQuantitySold(rs.getInt("QuantitySold"));
                product.setImageURL(rs.getString("ImageURL"));
                product.setCategoryID(rs.getInt("CategoryID"));
                product.setPrice(rs.getDouble("Price"));
                product.setDiscount(rs.getInt("discount"));

                products.add(product);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching paginated products: " + e.getMessage());
        }

        return products;
    }

    public List<Product> getPaginatedProductsDynamicByKeyword(String keyword, double low, double high, String orderBy, String sortColumn, int offset, int limit) {
        List<Product> products = new ArrayList<>();

        String query;

        query = "SELECT * FROM Product WHERE (FullName LIKE ? OR description LIKE ?) AND Price >= ? AND Price < ? ORDER BY " + sortColumn + " " + orderBy + " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(query)) {
            int paramIndex = 1;
            stmt.setString(paramIndex++, "%" + keyword + "%");
            stmt.setString(paramIndex++, "%" + keyword + "%");
            stmt.setDouble(paramIndex++, low);
            stmt.setDouble(paramIndex++, high);
            stmt.setInt(paramIndex++, offset);
            stmt.setInt(paramIndex++, limit);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setProductID(rs.getInt("ProductID"));
                product.setBestSeller(rs.getBoolean("isBestSeller"));
                product.setFullName(rs.getString("FullName"));
                product.setDescription(rs.getString("Description"));
                product.setQuantity(rs.getInt("Quantity"));
                product.setQuantitySold(rs.getInt("QuantitySold"));
                product.setImageURL(rs.getString("ImageURL"));
                product.setCategoryID(rs.getInt("CategoryID"));
                product.setPrice(rs.getDouble("Price"));
                product.setDiscount(rs.getInt("discount"));

                products.add(product);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching paginated products: " + e.getMessage());
        }

        return products;
    }

    @Override
    public List<Product> searchByCategory(int categoryID) {
        List<Product> products = new ArrayList<>();
        try {
            String query = "select * from Product\n"
                    + "where CategoryID = ?";
            PreparedStatement ps = getConnect().prepareStatement(query);
            ps.setInt(1, categoryID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setProductID(rs.getInt("ProductID"));
                product.setBestSeller(rs.getBoolean("isBestSeller"));
                product.setFullName(rs.getString("FullName"));
                product.setDescription(rs.getString("Description"));
                product.setQuantity(rs.getInt("Quantity"));
                product.setQuantitySold(rs.getInt("QuantitySold"));
                product.setImageURL(rs.getString("ImageURL"));
                product.setCategoryID(rs.getInt("CategoryID"));
                product.setPrice(rs.getDouble("Price"));
                product.setDiscount(rs.getInt("discount"));

                products.add(product);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching paginated products: " + e.getMessage());
        }

        return products;
    }

    @Override
    public int getQuantityByCateID(int categoryID, double low, double high) {
        int n = 0;
        try {

            String query = "SELECT COUNT(*) AS number\n"
                    + "FROM Product\n"
                    + "WHERE CategoryID = ? AND Price >= ? AND Price < ?";
            PreparedStatement ps = getConnect().prepareStatement(query);
            ps.setInt(1, categoryID);
            ps.setDouble(2, low);
            ps.setDouble(3, high);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                n = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Error get quantity by category!!");
        }
        return n;
    }

    public int getQuantityByKeyword(String keyword, double low, double high) {
        int n = 0;
        try {

            String query = "SELECT COUNT(*) AS number\n"
                    + "FROM Product\n"
                    + "WHERE (fullname LIKE ? OR description LIKE ?) AND Price >= ? AND Price < ?";
            PreparedStatement ps = getConnect().prepareStatement(query);
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ps.setDouble(3, low);
            ps.setDouble(4, high);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                n = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Error get quantity by category!!");
        }
        return n;
    }

    public List<Product> getProductsByUserID(int userID) {
        List<Product> products = new ArrayList<>();
        String query = "SELECT p.ImageURL, p.FullName, ci.Quantity, p.Price ,ci.productID" +
                       "FROM CartItem ci " +
                       "JOIN Cart c ON ci.CartID = c.CartID " +
                       "JOIN Product p ON ci.ProductID = p.ProductID " +
                       "WHERE c.UserID = ?";

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setImageURL(rs.getString("ImageURL"));
                product.setFullName(rs.getString("FullName"));
                product.setQuantity(rs.getInt("Quantity"));
                product.setPrice(rs.getDouble("Price"));
                product.setProductID(rs.getInt("productID"));
                products.add(product);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching products for user with ID " + userID + ": " + e.getMessage());
        }

        return products;
    }
    
    public Product getProductByID(int productID) {
        Product product = new Product();
        try {
            String query = "SELECT * from Product \n"
                    + "where ProductID = ?;";
            PreparedStatement ps = getConnect().prepareStatement(query);
            ps.setInt(1, productID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                product.setProductID(rs.getInt("ProductID"));
                product.setBestSeller(rs.getBoolean("isBestSeller"));
                product.setFullName(rs.getString("FullName"));
                product.setDescription(rs.getString("Description"));
                product.setQuantity(rs.getInt("Quantity"));
                product.setQuantitySold(rs.getInt("QuantitySold"));
                product.setImageURL(rs.getString("ImageURL"));
                product.setCategoryID(rs.getInt("CategoryID"));
                product.setPrice(rs.getDouble("Price"));
                product.setDiscount(rs.getInt("discount"));
            }
        } catch (SQLException e) {
            System.out.println("Error get Product by ProductID!!");
        }
        return product;
        
    }

}
