/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Filter.java to edit this template
 */
package filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;



@WebFilter(filterName = "AuthenticationFilter", urlPatterns = {"/views/client/pages/order/order.jsp", "/views/admin/", "/views/client/pages/cart.jsp", "/products", "/Cart","/GetAllOrder","/Order","/users","/OrderServlet","/OrderDetail","/ReviewServlet"})

public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code, if needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        String loginURI = httpRequest.getContextPath() + "/views/client/pages/login.jsp";

        boolean loggedIn = (session != null && session.getAttribute("account") != null);
        boolean loginRequest = httpRequest.getRequestURI().equals(loginURI);

        if (loggedIn || loginRequest) {
            chain.doFilter(request, response); // User is logged in, continue to the requested page
        } else {
            httpResponse.sendRedirect(loginURI); // User is not logged in, redirect to the login page
        }
    }

    @Override
    public void destroy() {
        // Cleanup code, if needed
    }
}