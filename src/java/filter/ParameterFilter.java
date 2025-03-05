package filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

@WebFilter("/Shop")
public class ParameterFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String categoryId = request.getParameter("categoryId");
        String keyword = request.getParameter("keyword");

        if (categoryId != null && categoryId.isEmpty()) {
            request.removeAttribute("categoryId");
        }
        if (keyword != null && keyword.isEmpty()) {
            request.removeAttribute("keyword");
        }

        chain.doFilter(request, response);
    }

    // Các phương thức khác của Filter không cần thiết phải được triển khai
}
