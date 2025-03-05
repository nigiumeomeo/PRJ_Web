/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.implement.CartDao;
import dao.implement.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.Cart;
import model.User;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Lấy các tham số từ request
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        String firstName = "";
        String lastName = "";

        // Tìm vị trí của dấu cách đầu tiên trong chuỗi name
        int spaceIndex = name.indexOf(' ');

        if (spaceIndex != -1) {
            // Nếu tìm thấy dấu cách, chia chuỗi thành firstName và lastName
            firstName = name.substring(0, spaceIndex).trim(); // firstName từ đầu đến dấu cách (không bao gồm dấu cách)
            lastName = name.substring(spaceIndex + 1).trim(); // lastName từ dấu cách đến hết chuỗi (loại bỏ dấu cách đầu tiên)
        } else {
            // Nếu không tìm thấy dấu cách, chỉ lấy toàn bộ chuỗi làm firstName
            firstName = name.trim();
        }

        // Tạo đối tượng User
        User user = new User(0, firstName, lastName, " ", "client", " ", email, password);

        // Đăng ký user và lấy userId
        UserDao userDao = new UserDao();
        int userId = userDao.register(user);

        if (userId != -1) {
            // Nếu đăng ký thành công, tạo giỏ hàng (cart) cho user
            Cart cart = new Cart(0, userId);
            CartDao cartDao = new CartDao();
            cartDao.insert(cart);

            // Chuyển hướng tới trang login.jsp
            response.sendRedirect(request.getContextPath() + "/views/client/pages/login.jsp");
        } else {
            // Nếu đăng ký thất bại, chuyển hướng tới trang lỗi hoặc hiển thị thông báo lỗi
            request.setAttribute("errorMessage", "Registration failed. Please try again.");
            request.getRequestDispatcher("/views/client/pages/login.jsp").forward(request, response);
        }
    }

}
