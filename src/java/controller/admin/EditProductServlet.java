package controller.admin;

import dao.implement.ProductDao;
import model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.IOException;
import utils.ImageUploadUtil;

@WebServlet(name = "EditProductServlet", urlPatterns = {"/EditProductServlet"})
@MultipartConfig
public class EditProductServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int productID = Integer.parseInt(request.getParameter("productID"));
        boolean bestSeller = request.getParameter("bestSeller") != null; 
        String fullName = request.getParameter("fullName");
        String description = request.getParameter("description");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int quantitySold = Integer.parseInt(request.getParameter("quantitySold"));
        int categoryID = Integer.parseInt(request.getParameter("categoryID"));
        double price = Double.parseDouble(request.getParameter("price"));
        int discount = Integer.parseInt(request.getParameter("discount"));

        // Lấy URL của hình ảnh hiện tại
        String imageURL = request.getParameter("imageURL");

        // Xử lý tệp hình ảnh được tải lên bằng tiện ích ImageUploadUtil
        Part imgFilePart = request.getPart("imgFile");
        String uploadedImageUrl = ImageUploadUtil.uploadImage(imgFilePart, getServletContext());
        if (uploadedImageUrl != null) {
            imageURL = uploadedImageUrl;
        }

        Product updatedProduct = new Product(bestSeller, fullName, description, quantity, quantitySold,
                imageURL, categoryID, price, discount);
        updatedProduct.setProductID(productID);

        ProductDao productDao = new ProductDao();
        boolean success = productDao.update(updatedProduct);

        if (success) {
            response.sendRedirect(request.getContextPath() + "/GetProductByIdServlet?id=" + productID);
        } else {
            response.sendRedirect(request.getContextPath() + "/${pageContext.request.contextPath}/views/admin/others/error-500.jsp");
        }
    }
}
