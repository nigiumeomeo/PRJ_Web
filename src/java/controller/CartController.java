package controller;

import dao.implement.CartDao;
import dao.implement.CartItemDao;
import dao.implement.ProductDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import model.Cart;
import model.CartItem;
import model.Product;
import model.User;

@WebServlet(name = "CartController", urlPatterns = {"/Cart"})
public class CartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("account");
        if (user != null) {
            CartDao pDAO = new CartDao(); 
//remove cacrt 
            if (request.getParameter("action") != null) {
                int cartItem = Integer.parseInt(request.getParameter("cartItem"));
                pDAO.removeCartItem(cartItem);
            }
            Cart cart = pDAO.getCartByUserId(user.getId());
            

            CartItemDao cartItemDao = new CartItemDao();

            List<CartItem> cartItems = cartItemDao.getCartItemByCartId(cart.getId());

            request.setAttribute("cartItems", cartItems);

            request.getRequestDispatcher("/views/client/pages/cart.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String action = request.getParameter("action");

        switch (action) {

            case "delete" -> {
                int cartId = Integer.parseInt(request.getParameter("id"));

                CartItemDao cartDao = new CartItemDao();
                boolean result = cartDao.delete(cartId);

                if (result) {
                response.sendRedirect(request.getContextPath() + "/Cart");
                } else {
                    // Handle error
                    response.getWriter().write("Failed to delete the item.");
                }
            }
            default -> {
            }
        }

    }
}
