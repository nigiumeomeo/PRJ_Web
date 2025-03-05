/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.implement.OrderDao;
import dao.implement.OrderDetailDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import model.Order;
import model.OrderDetail;


@WebServlet(name = "GetOrderById", urlPatterns = {"/GetOrderById"})
public class GetOrderById extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("id");

        if (orderId != null && !orderId.isEmpty()) {
            // Call your DAO or service method to get the order details by ID
            OrderDao orderDao = new OrderDao();
            OrderDetailDao orderDetailDao = new OrderDetailDao();

            Order order = orderDao.getById(Integer.parseInt(orderId));
            List<OrderDetail> orderDetails = orderDetailDao.getOrderDetailsByOrderID(order.getId());
            
            if (order != null) {
                request.setAttribute("order", order);
                request.setAttribute("orderDetails", orderDetails);
                request.getRequestDispatcher("views/admin/order/detail-order.jsp").forward(request, response);

            } else {
                response.sendRedirect(request.getContextPath() + "/${pageContext.request.contextPath}/views/admin/others/error-500.jsp");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/${pageContext.request.contextPath}/views/admin/others/error-404.jsp");
        }
    }

}
