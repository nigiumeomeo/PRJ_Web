<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.Map" %>
<%@ page import="model.*" %>
<%@ page import="dao.implement.*" %>

<jsp:include page="/views/client/includes/header.jsp"/>

<div class="container-fluid page-header py-5">
    <h1 class="text-center text-white display-6">Order Details</h1>
    <ol class="breadcrumb justify-content-center mb-0">

</div>
<!-- Single Page Header End -->
<div class="container py-5">

    <div class="row">
        <div class="col">
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">Product Name</th>
                        <th scope="col">Quantity</th>
                        <th scope="col">Unit Price</th>
                        <th scope="col">Total Price</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="orderDetail" items="${orderDetails}">
                        <tr>
                            <td>${orderDetail.product.fullName}</td>
                            <td>${orderDetail.quantity}</td>
                            <td><fmt:formatNumber value="${orderDetail.product.price}" type="currency" currencySymbol="$" minFractionDigits="2" maxFractionDigits="2"/></td>
                            <td><fmt:formatNumber value="${orderDetail.cost}" type="currency" currencySymbol="$" minFractionDigits="2" maxFractionDigits="2"/></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div class="row">
        <div class="col">
            <form action="${pageContext.request.contextPath}/ConfirmOrderServlet" method="post">
                <c:forEach var="cartItem" items="${orderDetails}">
                    <input type="hidden" name="quantity_${cartItem.productID}" value="${cartItem.quantity}" />
                </c:forEach>
                <button type="submit" class="btn btn-primary rounded-pill px-4 py-3 text-white ms-3">Confirm Order</button>
            </form>
        </div>
    </div>
</div>
<jsp:include page="/views/client/includes/footer.jsp" />
