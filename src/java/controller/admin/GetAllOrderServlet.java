/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.implement.OrderDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import model.Order;



@WebServlet(name = "GetAllOrderServlet", urlPatterns = {"/GetAllOrder"})
public class GetAllOrderServlet extends HttpServlet {

    private static final int PAGE_SIZE_ADMIN = 10;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderDao orderDao = new OrderDao();

        int currentPage = 1;
        try {
            currentPage = Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException e) {
            currentPage = 1;
        }

        int offset = (currentPage - 1) * PAGE_SIZE_ADMIN;
        List<Order> orders = orderDao.getPaginatedorders(offset, PAGE_SIZE_ADMIN);
        int totalorders = orderDao.getTotalOrderCount();
        int totalPages = (int) Math.ceil((double) totalorders / PAGE_SIZE_ADMIN);

        request.setAttribute("orders", orders);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);

        RequestDispatcher dispatcher = request.getRequestDispatcher("views/admin/order/list-order.jsp");
        dispatcher.forward(request, response);
    }


}
