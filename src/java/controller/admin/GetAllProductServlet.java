/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.implement.ProductDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import model.Product;


@WebServlet(name = "GetAllProductServlet", urlPatterns = {"/products"})
public class GetAllProductServlet extends HttpServlet {

    private static final int PAGE_SIZE_ADMIN = 10;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDao productDao = new ProductDao();

        int currentPage = 1;
        try {
            currentPage = Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException e) {
            currentPage = 1;
        }

        int offset = (currentPage - 1) * PAGE_SIZE_ADMIN;
        List<Product> products = productDao.getPaginatedProducts(offset, PAGE_SIZE_ADMIN);
        int totalProducts = productDao.getTotalProductCount();
        int totalPages = (int) Math.ceil((double) totalProducts / PAGE_SIZE_ADMIN);

        request.setAttribute("products", products);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);

        RequestDispatcher dispatcher = request.getRequestDispatcher("views/admin/product/list-product.jsp");
        dispatcher.forward(request, response);
    }

}
