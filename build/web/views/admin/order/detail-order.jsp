<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="model.Order, model.OrderDetail" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <!-- Required Meta Tags Always Come First -->
        <meta charset="utf-8" />
        <meta
            name="viewport"
            content="width=device-width, initial-scale=1, shrink-to-fit=no"
            />

        <!-- Title -->
        <title>FRUIT SHOP | Dashboard Admin</title>

        <!-- Favicon -->
        <link rel="shortcut icon" href="favicon.ico" />

        <!-- Font -->
        <link
            href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&display=swap"
            rel="stylesheet"
            />

        <!-- CSS Implementing Plugins -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/vendor.min.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendor/icon-set/style.css" />

        <!-- CSS Front Template -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/theme.min.css?v=1.0" />
        <style>
            body {
                background-color: #f8f9fa;
                padding-top: 20px;
            }
            .container {
                max-width: 800px;
                margin: 0 auto;
            }
            .order-card {
                margin-bottom: 20px;
                padding: 15px;
                background-color: #ffffff;
                border: 1px solid #ddd;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0,0,0,0.1);
            }
            .order-details {
                margin-top: 10px;
                padding-top: 10px;
                border-top: 1px solid #ddd;
            }
            .order-details ul {
                list-style-type: none;
                padding-left: 0;
            }
            .order-details ul li {
                padding: 5px 0;
                font-size: 14px;
            }
            .order-header {
                background-color: #00CC99;
                color: #ffffff;
                padding: 10px;
                border-top-left-radius: 8px;
                border-top-right-radius: 8px;
                margin-bottom: 10px;
            }
            .order-header h4 {
                margin: 0;
            }
        </style>
    </head>

    <body class="footer-offset">
        <script src="${pageContext.request.contextPath}/assets/vendor/hs-navbar-vertical-aside/hs-navbar-vertical-aside-mini-cache.js"></script>

        <jsp:include page="../includes/navbar.jsp" />

        <main id="content" role="main" class="main">
            <div class="content container-fluid">
                <div class="order-header text-center">
                    <h4>Order Details</h4>
                </div>
                <c:if test="${not empty order}">
                    <div class="order-card">
                        <div class="row">
                            <div class="col-md-8">
                                <h5>Order ID: <c:out value="${order.id}"/></h5>
                                <p>Order Date: <c:out value="${order.orderDate}"/></p>
                                <p>Total Cost: $<c:out value="${order.totalCost}"/></p>
                            </div>
                            <form action="${pageContext.request.contextPath}/UpdateOrderStatusServlet" method="post">
                                <input type="hidden" name="orderId" value="${order.id}"/>
                                <div class="form-group">
                                    <label for="orderStatus">Order Status:</label>
                                    <select name="orderStatus" id="orderStatus" class="form-control">
                                        <option value="1" ${order.orderStatusID == 1 ? 'selected' : ''}>Pending</option>
                                        <option value="2" ${order.orderStatusID == 2 ? 'selected' : ''}>Paid</option>
                                        <option value="3" ${order.orderStatusID == 3 ? 'selected' : ''}>Cancelled</option>
                                    </select>
                                </div>
                                <button type="submit" class="btn btn-primary">Update Status</button>
                            </form>
                        </div>
                        <div class="order-details">
                            <h6>Order Details</h6>
                            <table>
                                <thead>
                                    <tr>
                                        <th>Product ID</th>
                                        <th>Quantity</th>
                                        <th>Cost</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="detail" items="${orderDetails}">
                                        <tr>
                                            <td><c:out value="${detail.id}"/></td>
                                            <td><c:out value="${detail.quantity}"/></td>
                                            <td>$<c:out value="${detail.cost}"/> USD</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </c:if>
            </div>
        </main>

        <jsp:include page="../includes/footer.jsp" />

