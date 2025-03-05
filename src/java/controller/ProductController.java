package controller;

import dao.implement.ProductDao;
import dao.implement.ReviewDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import model.Product;
import model.Review;

@WebServlet(name = "ProductController", urlPatterns = {"/ProductController"})
public class ProductController extends HttpServlet {

    private static final int PAGE_SIZE_ADMIN = 10;
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String action;
        action = request.getParameter("action");
        if (action == null) {
            action = "loadHome";
        }
        switch (action) {
            
            case "loadProduct":
                getProductById(request, response);
            default:
                getProductById(request, response);
        }
    }




    private void getProductById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        
        List<Product> products = new ProductDao().getAll();
        List<Product> features = new ProductDao().getFeatureProduct();
        String id = request.getParameter("id");
        Product product = new ProductDao().getById(Integer.parseInt(id));
        
        List<Review> reviews = new ReviewDao().getReviewsByProductId(Integer.parseInt(id));
        request.setAttribute("reviews", reviews );
        request.setAttribute("product", product);
        request.setAttribute("products", products);
        request.setAttribute("features", features);
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/client/pages/product/product-detail.jsp");
        dispatcher.forward(request, response);
    }

}
