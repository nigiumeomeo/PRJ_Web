/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.implement.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.io.PrintWriter;
import model.User;
import utils.ImageUploadUtil;


@WebServlet(name = "UploadImage", urlPatterns = {"/UploadImage"})
@MultipartConfig
public class UploadImage extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("id"));

        Part imgFilePart = request.getPart("imgFile");
        String uploadedImageUrl = ImageUploadUtil.uploadImage(imgFilePart, getServletContext());

        User user = new User();
        user.setId(userId);
        user.setAvatarURL(uploadedImageUrl);

        UserDao userDao = new UserDao();
        boolean success = userDao.updateImageUrl(user);

        if (success) {
            response.sendRedirect(request.getContextPath() + "/UserServlet?action=detail");
        } else {
            response.sendRedirect(request.getContextPath() + "/views/client/404.jsp");
        }
    }

}
