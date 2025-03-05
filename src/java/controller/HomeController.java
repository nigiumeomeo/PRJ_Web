/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.implement.ProductDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import model.Product;


@WebServlet(name = "HomeController", urlPatterns = {"/Home"})
public class HomeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Product> laptop1 = new ProductDao().laptopCategory1();
        List<Product> listLaptop = new ProductDao().getFreshVegetables();
        List<Product> bestSellers = new ProductDao().getBestSeller();
        List<Product> laptop2 = new ProductDao().laptopCategory2();
        List<Product> laptop3 = new ProductDao().laptopCategory3();
        request.setAttribute("laptop1", laptop1);
        request.setAttribute("listLaptop", listLaptop);
        request.setAttribute("bestSellers", bestSellers);
        request.setAttribute("laptop2", laptop2);
        request.setAttribute("laptop3", laptop3);
        request.getRequestDispatcher("views/client/home.jsp").forward(request, response);
    }
}
