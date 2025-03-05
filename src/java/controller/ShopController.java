package controller;

import dao.implement.CategoryDao;
import dao.implement.ProductDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import model.Category;
import model.Product;

@WebServlet(name = "ShopController", urlPatterns = {"/Shop"})
public class ShopController extends HttpServlet {

    private static final int PAGE_SIZE_CLIENT = 9;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action;
        action = request.getParameter("action");
        if (action == null) {
            action = "loadHome";
        }

        if (request.getParameter("keyword") != null && !request.getParameter("keyword").isEmpty()) {
            loadPaginatedProductsSearching(request, response);
        } else {
            loadPaginatedProducts(request, response);
        }

    }

    private void loadPaginatedProducts(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String order = "ASC";
        String categoryId;
        String priceRange = "default";

        int currentPage = 1;
        try {
            String sortOrder = request.getParameter("order");
            if (sortOrder != null && !sortOrder.isEmpty()) {
                order = sortOrder.toUpperCase();
            }
            currentPage = Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException e) {
            currentPage = 1;
        }

        try {
            categoryId = request.getParameter("categoryId");
            if (categoryId == null || categoryId.isEmpty()) {
                categoryId = "0";
            }
        } catch (NumberFormatException e) {
            categoryId = "0";
        }

        ProductDao productDao = new ProductDao();
        int offset = (currentPage - 1) * PAGE_SIZE_CLIENT;
        List<Product> products;

        try {
            priceRange = request.getParameter("priceRange");
            if (priceRange == null) {
                priceRange = "default";
            }
        } catch (NumberFormatException e) {
            priceRange = "";
        }

        double low = 0, high = Double.MAX_VALUE;
        switch (priceRange) {
            case "1":
                low = 0;
                high = 2;
                break;
            case "2":
                low = 2;
                high = 4;
                break;
            case "3":
                low = 4;
                high = 6;
                break;
            case "4":
                low = 6;
                high = 8;
                break;
            case "5":
                low = 8;
                high = Double.MAX_VALUE;
                break;
            default:
                low = 0;
                high = Double.MAX_VALUE;
                break;
        }

        products = productDao.getPaginatedProductsDynamic(low, high, Integer.parseInt(categoryId), order, "Price", offset, PAGE_SIZE_CLIENT);

        int totalProducts = 0;

        if ("0".equals(categoryId)) {
            totalProducts = productDao.getTotalProductCount();
        } else {
            totalProducts = productDao.getQuantityByCateID(Integer.parseInt(categoryId), low, high);
        }

        int totalPages = (int) Math.ceil((double) totalProducts / PAGE_SIZE_CLIENT);
        List<Category> cList = new CategoryDao().getAll();
        Map<Integer, Integer> cMap = new CategoryDao().getQuantityOfCategory(cList);

        request.setAttribute("features", productDao.getFeatureProduct());
        request.setAttribute("result", products);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("numberOfPages", totalPages);
        request.setAttribute("cMap", cMap);
        request.setAttribute("cList", cList);
        request.setAttribute("categoryId", categoryId);
        request.setAttribute("priceRange", priceRange);
        request.getRequestDispatcher("/views/client/pages/product/list.jsp").forward(request, response);
    }

    private void loadPaginatedProductsSearching(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String order = "ASC";
        String keyword;
        String priceRange = "default";

        int currentPage = 1;
        try {
            String sortOrder = request.getParameter("order");
            if (sortOrder != null && !sortOrder.isEmpty()) {
                order = sortOrder.toUpperCase();
            }
            currentPage = Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException e) {
            currentPage = 1;
        }

        keyword = request.getParameter("keyword");

        ProductDao productDao = new ProductDao();
        int offset = (currentPage - 1) * PAGE_SIZE_CLIENT;
        List<Product> products;

        try {
            priceRange = request.getParameter("priceRange");
            if (priceRange == null) {
                priceRange = "default";
            }
        } catch (NumberFormatException e) {
            priceRange = "";
        }

        double low = 0, high = Double.MAX_VALUE;
        switch (priceRange) {
            case "1":
                low = 0;
                high = 2;
                break;
            case "2":
                low = 2;
                high = 4;
                break;
            case "3":
                low = 4;
                high = 6;
                break;
            case "4":
                low = 6;
                high = 8;
                break;
            case "5":
                low = 8;
                high = Double.MAX_VALUE;
                break;
            default:
                low = 0;
                high = Double.MAX_VALUE;
                break;
        }

        products = productDao.getPaginatedProductsDynamicByKeyword(keyword, low, high, order, "Price", offset, PAGE_SIZE_CLIENT);

        int totalProducts = 0;

        if ("0".equals(keyword)) {
            totalProducts = productDao.getTotalProductCount();
        } else {
            totalProducts = productDao.getQuantityByKeyword(keyword, low, high);
        }

        int totalPages = (int) Math.ceil((double) totalProducts / PAGE_SIZE_CLIENT);
        List<Category> cList = new CategoryDao().getAll();
        Map<Integer, Integer> cMap = new CategoryDao().getQuantityOfCategory(cList);

        request.setAttribute("features", productDao.getFeatureProduct());
        request.setAttribute("result", products);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("numberOfPages", totalPages);
        request.setAttribute("cMap", cMap);
        request.setAttribute("cList", cList);
        request.setAttribute("keyword", keyword);
        request.setAttribute("priceRange", priceRange);
        request.getRequestDispatcher("/views/client/pages/product/list.jsp").forward(request, response);
    }
}
