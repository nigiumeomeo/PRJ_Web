/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.implement.OrderDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;



@WebServlet(name = "UpdateOrderStatusServlet", urlPatterns = {"/UpdateOrderStatusServlet"})
public class UpdateOrderStatusServlet  extends HttpServlet {
      protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        int orderStatus = Integer.parseInt(request.getParameter("orderStatus"));

          OrderDao orderDao = new OrderDao();
        boolean updateSuccess = orderDao.updateOrderStatus(orderId, orderStatus);

        if (updateSuccess) {
            response.sendRedirect(request.getContextPath() + "/GetOrderById?id=" + orderId);
        } else {
            response.sendRedirect(request.getContextPath() + "/GetOrderById?id=" + orderId);
        }
    }
}
