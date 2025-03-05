/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import static com.oracle.wls.shaded.org.apache.xalan.xsltc.compiler.util.Type.Int;
import dao.implement.CartItemDao;
import dao.implement.ProductDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import model.User;

/**
 *
 * @author LAPTOP ACER
 */
@WebServlet(name = "AddToCartServlet", urlPatterns = {"/AddToCart"})
public class AddToCartServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
        HttpSession session = request.getSession();
        User account = (User) session.getAttribute("account");

        if (account == null) {
            response.sendRedirect(request.getContextPath() + "/views/client/pages/login.jsp"); // Redirect to login page if user is not logged in
            return;
        }

        ProductDao pDao = new ProductDao();
        CartItemDao ciDao = new CartItemDao();
        int productId = Integer.parseInt(request.getParameter("productId"));
        int productQuantity = Integer.parseInt(request.getParameter("productQuantity"));

        // Check product quantity
           int totalProductCount = pDao.getTotalProductCount();
        if (productQuantity > totalProductCount) {
            session.setAttribute("message", "Số lượng sản phẩm vượt quá giới hạn cho phép.");
              return;
        }
        

        // Insert product into cart
        int userId = account.getId();
        boolean isInserted = ciDao.insertCartItem(userId, productQuantity, productId);

        if (isInserted) {
            session.setAttribute("message", "Thêm vào giỏ hàng thành công!");
        } else {
            session.setAttribute("message", "Có lỗi xảy ra khi thêm sản phẩm vào giỏ hàng.");
        }

        response.sendRedirect(request.getContextPath() + "/ProductController?id=" + productId + "&action=loadProduct"); // Redirect back to the product detail page
    }
}
