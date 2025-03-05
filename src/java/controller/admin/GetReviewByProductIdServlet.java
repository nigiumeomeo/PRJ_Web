/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.implement.ProductDao;
import dao.implement.ReviewDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import model.Product;
import model.Review;


@WebServlet(name = "GetReviewByProductIdServlet", urlPatterns = {"/GetReviewByProductIdServlet"})
public class GetReviewByProductIdServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("id");
        ReviewDao reviewDao = new ReviewDao();
        List<Review> review = null;
        if (productId.equals("0")) {
            review = reviewDao.getAllReviews();

        } else {
            review = reviewDao.getReviewsByProductId(Integer.parseInt(productId));

        }

        Product product = new ProductDao().getById(Integer.parseInt(productId));

        if (review != null) {
            request.setAttribute("reviews", review);
            request.setAttribute("product", product);
            request.getRequestDispatcher("views/admin/review/manage-review.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/views/admin/others/error-500.jsp");

        }
    }

}
