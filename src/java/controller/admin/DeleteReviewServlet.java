/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.implement.ReviewDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteReviewServlet", urlPatterns = {"/DeleteReviewServlet"})
public class DeleteReviewServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy reviewID từ form
        String reviewIdParam = request.getParameter("id");
        String productIdParam = request.getParameter("productId");
        if (reviewIdParam != null) {
            try {
                int reviewId = Integer.parseInt(reviewIdParam);

                ReviewDao reviewDao = new ReviewDao();

                boolean success = reviewDao.delete(reviewId);

                if (success) {
                    response.sendRedirect("GetReviewByProductIdServlet?id=" + productIdParam);
                    
                } else {
                    response.sendRedirect("GetReviewByProductIdServlet");
                }
            } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/${pageContext.request.contextPath}/views/admin/others/error-500.jsp");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/${pageContext.request.contextPath}/views/admin/others/error-404.jsp");
        }
    }


}
