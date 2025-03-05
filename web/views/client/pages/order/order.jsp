<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/views/client/includes/header.jsp"/>

<!-- Single Page Header start -->
<div class="container-fluid page-header py-5">
    <h1 class="text-center text-white display-6">Order</h1>

</div>
<!-- Single Page Header End -->


<!-- Cart Page Start -->
<div class="container-fluid py-5">
    <div class="container py-5">
        <div class="table-responsive">
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">OrderDate</th>
                        <th scope="col">ExpectedDate</th>
                        <th scope="col">OrderStatusID</th>
                        <th scope="col">TotalCost</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="o" items="${oList}">
                        <tr>
                            <td scope="row">
                                <p class="mb-0 mt-4">${o.id}</p>
                            </td>
                            <td>
                                <p class="mb-0 mt-4">${o.orderDate}</p>
                            </td>
                            <td>
                                <p class="mb-0 mt-4">${o.expectedDate}</p>
                            </td>
                            <td>
                                <c:if test="${o.orderStatusID == 1}"><p class="mb-0 mt-4">Pending</p></c:if>
                                <c:if test="${o.orderStatusID == 2}"><p class="mb-0 mt-4">Delivered</p></c:if>
                                </td>
                                <td>
                                    <p class="mb-0 mt-4">${o.totalCost} $</p>
                            </td>
                            <td>
                                <p class="mb-0 mt-4">
                                    <a href="OrderDetail?id=${o.id}">
                                        <i class="fa fa-search text-green"></i>
                                    </a>
                                </p>

                            </td>

                        </tr>
                    </c:forEach>
                </tbody>
            </table>

        </div>
    </div>
    <!-- Cart Page End -->
    <jsp:include page="/views/client/includes/footer.jsp" />
