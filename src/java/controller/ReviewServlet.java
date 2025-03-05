/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.implement.ReviewDao;
import dao.implement.UserDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Review;
import model.User;


@WebServlet(name = "ReviewServlet", urlPatterns = {"/ReviewServlet"})
public class ReviewServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String productId = "7";
        try {
            productId = request.getParameter("productId");
            if (productId == null) {
                productId = "7";
            }
        } catch (NumberFormatException e) {
            productId = "7";
        }

        List<Review> reviews = new ReviewDao().getReviewsByProductId(Integer.parseInt(productId));

        request.setAttribute("reviews", reviews);
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/client/pages/testimonial.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String cmt = request.getParameter("cmt");
        String productId = request.getParameter("productId");

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("account");
        request.setAttribute("name", user.getFirstName() + user.getLastName());
     
        new ReviewDao().insert(new Review(0, cmt, user.getId(), Integer.parseInt(productId)));
        response.sendRedirect("ProductController?id=" + productId + "&action=loadProduct");
    }

}
