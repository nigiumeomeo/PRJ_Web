/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.implement.ProductDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "DeleteProductServlet", urlPatterns = {"/DeleteProductServlet"})
public class DeleteProductServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy productID từ form
        String productIdParam = request.getParameter("id");

        if (productIdParam != null) {
            try {
                int productId = Integer.parseInt(productIdParam);

                ProductDao productDao = new ProductDao();

                boolean success = productDao.delete(productId);

                if (success) {
                    response.sendRedirect("products");

                } else {
                    response.sendRedirect("products");
                }
            } catch (NumberFormatException e) {
                response.sendRedirect(request.getContextPath() + "/${pageContext.request.contextPath}/views/admin/others/error-500.jsp");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/${pageContext.request.contextPath}/views/admin/others/error-404.jsp");
        }
    }

}
