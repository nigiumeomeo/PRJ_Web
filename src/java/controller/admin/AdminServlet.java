/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.implement.CategoryDao;
import dao.implement.OrderDao;
import dao.implement.OrderDetailDao;
import dao.implement.ProductDao;
import dao.implement.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import model.Product;
import model.User;



@WebServlet(name = "AdminServlet", urlPatterns = {"/AdminServlet"})
public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        CategoryDao categoryDao = new CategoryDao();
        OrderDao orderDao = new OrderDao();
        ProductDao productDao = new ProductDao();
        UserDao userDao = new UserDao();

        // Lấy các thông tin từ các hàm đã viết trong CategoryDao
        double totalOrderCost = orderDao.getTotalOrderCost();
        int totalProducts = productDao.getTotalProducts();
        int totalOrders = orderDao.getTotalOrders();
        int totalCustomers = userDao.getTotalCustomers();
        List<Product> bestSellers = productDao.getBestSeller();
        
        request.setAttribute("bestSellers", bestSellers);
        request.setAttribute("totalOrderCost", totalOrderCost);
        request.setAttribute("totalProducts", totalProducts);
        request.setAttribute("totalOrders", totalOrders);
        request.setAttribute("totalCustomers", totalCustomers);

        // Forward request đến trang JSP hoặc thực hiện các hành động khác
        request.getRequestDispatcher("/views/admin/index.jsp").forward(request, response);
        HttpSession session = request.getSession();
            User user = (User) session.getAttribute("account");
             if ("Admin".equals(user.getRole()) == false) {
            response.sendRedirect(request.getContextPath() + "/Home");

            return;
        }
    }


}
