<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/views/client/includes/header.jsp"/>

<div class="container-fluid page-header py-5">
    <h1 class="text-center text-white display-6">Cart</h1>
</div>

<!-- Cart Page Start -->
<div class="container-fluid py-5">
    <div class="container py-5">
        <div class="table-responsive">
            <form action="${pageContext.request.contextPath}/CreateOrder" method="post">

                <c:choose>
                    <c:when test="${not empty cartItems}">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th scope="col">Products</th>
                                    <th scope="col">Name</th>
                                    <th scope="col">Price</th>
                                    <th scope="col">Quantity</th>
                                    <th scope="col">Total</th>
                                    <th scope="col">Handle</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="cartItem" items="${cartItems}">
                                    <tr>
                                        <th scope="row">
                                            <div class="d-flex align-items-center">
                                                <img src="${pageContext.request.contextPath}/${cartItem.product.imageURL}" class="img-fluid me-5 rounded-circle" style="width: 80px; height: 80px;" alt="">
                                            </div>
                                        </th>
                                        <td>
                                            <p class="mb-0 mt-4">${cartItem.product.fullName}</p>
                                        </td>
                                        <td>
                                            <p class="mb-0 mt-4"><fmt:formatNumber value="${cartItem.product.price}" type="currency" currencySymbol="$" minFractionDigits="2" maxFractionDigits="2"/></p>
                                        </td>
                                        <td>
                                            <p class="mb-0 mt-4"><fmt:formatNumber value="${cartItem.product.productID}" type="number"  minFractionDigits="2" maxFractionDigits="2"/></p>
                                           <!--<input type="number" id="quantityInput_${cartItem.product.productID}" 
                                                  name="quantity_${cartItem.product.productID}" 
                                                  value="${cartItem.quantity}" min="1" max="" readonly>-->
                                        </td>
                                        <td>
                                            <p class="mb-0 mt-4"><fmt:formatNumber value="${cartItem.product.price * cartItem.quantity}" type="currency" currencySymbol="$" minFractionDigits="2" maxFractionDigits="2"/></p>
                                        </td>
                                        <td>
                                            <a href="Cart?action=removeCart&&cartItem=${cartItem.id}">
                                                <button type="button" class="btn btn-md rounded-circle bg-light border mt-4 delete-btn">
                                                    <i class="fa fa-times text-danger"></i>
                                                </button>
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <div class="mt-5">
                            <button type="submit" class="btn btn-primary rounded-pill px-4 py-3 text-white ms-3">Proceed to Checkout</button>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <p class="text-center mt-5">No products in your cart.</p>
                    </c:otherwise>
                </c:choose>
            </form>
        </div>
        <script>
            // Hàm lấy số lượng sản phẩm từ servlet bằng AJAX bất đồng bộ
            function getAmountProductById(productId) {
                return new Promise(function (resolve, reject) {
                    var xhr = new XMLHttpRequest();
                    xhr.open("GET", "GetAmountProductById?productId=" + encodeURIComponent(productId), true);

                    xhr.onreadystatechange = function () {
                        if (xhr.readyState === 4) {
                            if (xhr.status === 200) {
                                resolve(JSON.parse(xhr.responseText));
                            } else {
                                reject("Không thể lấy dữ liệu từ server");
                            }
                        }
                    };

                    xhr.send();
                });
            }

            // Hàm thiết lập giá trị max cho trường input số lượng
            async function setMaxQuantity(productId, inputId) {
                try {
                    var data = await getAmountProductById(productId);
                    if (data && data.amount) {
                        document.getElementById(inputId).max = data.amount;
                    }
                } catch (error) {
                    console.error(error);
                }
            }

            // Thiết lập max cho từng sản phẩm trong giỏ hàng
            window.onload = function () {
            <c:forEach var="cartItem" items="${cartItems}">
                setMaxQuantity(${cartItem.product.productID}, 'quantityInput_${cartItem.product.productID}');
            </c:forEach>
            };
        </script>
    </div>
</div>
<!-- Cart Page End -->
<jsp:include page="/views/client/includes/footer.jsp" />
<script>
    document.addEventListener("DOMContentLoaded", function () {
        var cartItems = document.querySelector("tbody tr");
        if (!cartItems) {
            document.querySelector(".table-responsive").innerHTML = '<p class="text-center mt-5">No products in your cart.</p>';
        }
    });
</script>
