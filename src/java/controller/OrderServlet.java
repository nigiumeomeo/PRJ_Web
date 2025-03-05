package controller;

import dao.implement.OrderDao;
import model.Order;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "OrderServlet", urlPatterns = {"/OrderServlet"})
public class OrderServlet extends HttpServlet {

    private static final int PAGE_SIZE = 10; // Số lượng đơn hàng mỗi trang
    private OrderDao orderDao = new OrderDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "listOrderAdmin":
                loadPaginationListOrders(request, response);
                break;
            case "detail":
                getOrderById(request, response);
                break;
            default:
                // Redirect to a default action or error page
                response.sendRedirect("error.jsp");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "add":
                createNewOrder(request, response);
                break;
            case "delete":
                deleteOrderById(request, response);
                break;
            case "update":
                updateOrderById(request, response);
                break;
            
            default:
                // Redirect to a default action or error page
                response.sendRedirect("error.jsp");
                break;
        }
    }

    private void createNewOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int userID = Integer.parseInt(request.getParameter("userID"));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date orderDate = sdf.parse(request.getParameter("orderDate"));
            Date expectedDate = sdf.parse(request.getParameter("expectedDate"));
            int orderStatusID = Integer.parseInt(request.getParameter("orderStatusID"));
            double totalCost = Double.parseDouble(request.getParameter("totalCost"));

            Order order = new Order(0, userID, orderDate, expectedDate, orderStatusID, totalCost);
            boolean success = orderDao.insert(order);

            if (success) {
                response.sendRedirect("OrderServlet?action=listOrderAdmin");
            } else {
                response.sendRedirect("error.jsp");
            }
        } catch (ParseException e) {
            throw new ServletException("Invalid date format", e);
        }
    }

    private void deleteOrderById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("id"));

        // Delete the order
        boolean success = orderDao.delete(orderId);

        if (success) {
            response.sendRedirect("OrderServlet?action=listOrderAdmin");
        } else {
            response.sendRedirect("error.jsp");
        }
    }

    private void updateOrderById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int orderId = Integer.parseInt(request.getParameter("id"));
            int userID = Integer.parseInt(request.getParameter("userID"));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date orderDate = sdf.parse(request.getParameter("orderDate"));
            Date expectedDate = sdf.parse(request.getParameter("expectedDate"));
            int orderStatusID = Integer.parseInt(request.getParameter("orderStatusID"));
            double totalCost = Double.parseDouble(request.getParameter("totalCost"));

            Order order = new Order(orderId, userID, orderDate, expectedDate, orderStatusID, totalCost);
            boolean success = orderDao.update(order);

            if (success) {
                response.sendRedirect("OrderServlet?action=listOrderAdmin");
            } else {
                response.sendRedirect("error.jsp");
            }
        } catch (ParseException e) {
            throw new ServletException("Invalid date format", e);
        }
    }

    private void loadPaginationListOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        List<Order> orders = orderDao.getAll();
        int totalOrders = orders.size();
        int totalPages = (int) Math.ceil((double) totalOrders / PAGE_SIZE);
        int start = (page - 1) * PAGE_SIZE;
        int end = Math.min(start + PAGE_SIZE, totalOrders);

        request.setAttribute("orders", orders.subList(start, end));
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        RequestDispatcher dispatcher = request.getRequestDispatcher("views/admin/list-order.jsp");
        dispatcher.forward(request, response);
    }

    private void getOrderById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("id"));

        Order order = orderDao.getById(orderId);

        if (order != null) {
            request.setAttribute("order", order);
            RequestDispatcher dispatcher = request.getRequestDispatcher("order-detail.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("error.jsp");
        }
    }
}
