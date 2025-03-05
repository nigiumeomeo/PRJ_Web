/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.implement.ProductDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.IOException;
import model.Product;
import utils.ImageUploadUtil;


@WebServlet(name = "AddProductServlet", urlPatterns = {"/AddProductServlet"})
@MultipartConfig

public class AddProductServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        boolean bestSeller = request.getParameter("bestSeller") != null;
        String fullName = request.getParameter("fullName");
        String description = request.getParameter("description");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int quantitySold = 0;
        int categoryID = Integer.parseInt(request.getParameter("categoryID"));
        double price = Double.parseDouble(request.getParameter("price"));
        int discount = Integer.parseInt(request.getParameter("discount"));
        Part imgFilePart = request.getPart("imgFile");
        String uploadedImageUrl = ImageUploadUtil.uploadImage(imgFilePart, getServletContext());

        Product product = new Product(bestSeller, fullName, description, quantity, quantitySold,
                uploadedImageUrl, categoryID, price, discount);

        ProductDao productDao = new ProductDao();
        boolean inserted = productDao.insert(product);

        if (inserted) {
            response.sendRedirect(request.getContextPath() + "/products");
        } else {
            response.sendRedirect(request.getContextPath() + "/${pageContext.request.contextPath}/views/admin/others/error-500.jsp");
        }

    }

}
