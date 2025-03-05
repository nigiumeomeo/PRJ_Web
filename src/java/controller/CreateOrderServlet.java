package controller;

import dao.implement.ProductDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import model.Order;
import model.OrderDetail;
import model.Product;

@WebServlet(name = "CreateOrderServlet", urlPatterns = {"/CreateOrder"})
public class CreateOrderServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<OrderDetail> orderDetails = new ArrayList<>();
        Enumeration<String> parameterNames = request.getParameterNames();

        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();

            if (paramName.startsWith("quantity_")) {
                String productId = paramName.substring("quantity_".length());
                int quantity = Integer.parseInt(request.getParameter(paramName));

                // Tạo đối tượng OrderDetail từ thông tin sản phẩm và số lượng
                Product product = new ProductDao().getById(Integer.parseInt(productId)); // Hàm này phải thay đổi để lấy sản phẩm từ cơ sở dữ liệu
                if (product != null) {
                    OrderDetail orderDetail = new OrderDetail(0, 0, product.getProductID(), quantity, product.getPrice() * quantity);
                    orderDetail.setProduct(product);
                    orderDetails.add(orderDetail);
                }
            }
        }

        // Lưu thông tin đơn hàng vào session hoặc database
        HttpSession session = request.getSession();
        session.setAttribute("orderDetails", orderDetails);
        request.getRequestDispatcher("/views/client/pages/order/checkout.jsp").forward(request, response);

        // Chuyển hướng đến trang checkout.jsp để xác nhận đơn hàng
    }
}
