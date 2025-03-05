/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.implement.OrderDao;
import dao.implement.OrderDetailDao;
import dao.implement.ProductDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.OrderDetail;
import model.Product;


@WebServlet(name = "OrderDetailController", urlPatterns = {"/OrderDetail"})
public class OrderDetailController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        List<OrderDetail> odList = new OrderDetailDao().getOrderDetailsByOrderID(id);
        Map<Integer, Product> pMap = new HashMap<>();
        for (OrderDetail orderDetail : odList) {
            pMap.put(orderDetail.getProductID(), new ProductDao().getProductByID(orderDetail.getProductID()));
        }
        request.setAttribute("totalCost", new OrderDao().getTotalCostByID(id));
        request.setAttribute("odList", odList);
        request.setAttribute("pMap", pMap);
        request.getRequestDispatcher("views/client/pages/order/orderDetail.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
