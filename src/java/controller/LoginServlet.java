/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.implement.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import model.User;


@WebServlet(name = "LoginServlet", urlPatterns = {"/Login"})
public class LoginServlet extends HttpServlet {

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");

        String password = req.getParameter("password");

        String alertMsg = "";

        if (username == null || password == null) {
            alertMsg = "Username and password can't be empty!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/client/pages/login.jsp").forward(req, resp);
            return;
        }

        UserDao userDao = new UserDao();

        User user = userDao.getUserByEmail(username);

        if (user != null && password.equals(user.getPassword())) {
            HttpSession session = req.getSession(true);
            session.setAttribute("account", user);
            if ("Admin".equals(user.getRole())) {
                resp.sendRedirect(req.getContextPath()+"/products");
            } else {
                resp.sendRedirect(req.getContextPath() + "/Home");
            }
        } else {
            alertMsg = "Username or password isn't correct";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/client/pages/login.jsp").forward(req, resp);
        }
    }
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
}
