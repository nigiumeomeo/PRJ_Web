<%@ page import="java.util.List" %>
<%@ page import="model.Order" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../includes/navbar.jsp" />

<main id="content" role="main" class="main">
    <!-- Content -->
    <div class="content container-fluid">
        <!-- Page Header -->
        <div class="page-header">
            <div class="row align-items-center mb-3">
                <div class="col-sm mb-2 mb-sm-0">
                    <h1 class="page-header-title">Orders <span class="badge badge-soft-dark ml-2">${totalOrders}</span></h1>
                </div>
                <div class="col-sm-auto">
                    <a class="btn btn-primary" href="order-add.jsp">Add Order</a>
                </div>
            </div>
            <!-- End Row -->

            <!-- Nav Scroller -->
            <div class="js-nav-scroller hs-nav-scroller-horizontal">
                <span class="hs-nav-scroller-arrow-prev" style="display: none;">
                    <a class="hs-nav-scroller-arrow-link" href="javascript:;">
                        <i class="tio-chevron-left"></i>
                    </a>
                </span>

                <span class="hs-nav-scroller-arrow-next" style="display: none;">
                    <a class="hs-nav-scroller-arrow-link" href="javascript:;">
                        <i class="tio-chevron-right"></i>
                    </a>
                </span>

                <!-- Nav -->
                <ul class="nav nav-tabs page-header-tabs" id="pageHeaderTab" role="tablist">
                    <li class="nav-item">
                        <a class="nav-link active" href="#">All Orders</a>
                    </li>
                </ul>
                <!-- End Nav -->
            </div>
            <!-- End Nav Scroller -->
        </div>
        <!-- End Page Header -->

        <!-- Card -->
        <div class="card">
            <!-- Table -->
            <div class="table-responsive datatable-custom">
                <table id="datatable" class="table table-borderless table-thead-bordered table-nowrap table-align-middle card-table">
                    <thead>
                        <tr>
                            <th scope="col" class="table-column-pr-0"></th>
                            <th>ORDER ID</th>
                            <th>User ID</th>
                            <th>Order Date</th>
                            <th>Order Status</th>
                            <th>Total Cost</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="order" items="${orders}">
                            <tr>
                                <td class="table-column-pr-0"></td>
                                <td>${order.id}</td>
                                <td>${order.userID}</td>
                                <td>${order.orderDate}</td>
                                <td><c:choose>
                                        <c:when test="${order.orderStatusID == 1}">
                                            <span class="badge badge-soft-warning">
                                                <span class="legend-indicator bg-warning"></span>Pending
                                            </span>
                                        </c:when>
                                        <c:when test="${order.orderStatusID == 2}">
                                            <span class="badge badge-soft-success">
                                                <span class="legend-indicator bg-success"></span>Paid
                                            </span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge badge-soft-secondary">
                                                <span class="legend-indicator bg-secondary"></span>Cancel
                                            </span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>

                                <td>${order.totalCost}</td>
                                <td>
                                    <div class="btn-group" role="group">
                                        <a class="btn btn-sm btn-status-success" href="${pageContext.request.contextPath}/GetOrderById?id=${order.id}" style="margin-right: 5px;">
                                            <i class="tio-edit"></i> Detail Order
                                        </a>
                                       
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <!-- End Table -->

            <div class="card-footer">
                <!-- Pagination -->
                <div class="row justify-content-center align-items-center">
                    <div class="col-auto">
                        <div class="d-flex align-items-center">
                            <nav aria-label="Pagination">
                                <ul class="pagination mb-0">
                                    <!-- Previous Page -->
                                    <c:if test="${currentPage > 1}">
                                        <li class="page-item">
                                            <a class="page-link" href="?action=listOrderAdmin&page=${currentPage - 1}" aria-label="Previous">
                                                <span aria-hidden="true">&laquo;</span>
                                            </a>
                                        </li>
                                    </c:if>

                                    <!-- Page Numbers -->
                                    <c:forEach var="i" begin="1" end="${totalPages}">
                                        <li class="page-item ${i == currentPage ? 'active' : ''}">
                                            <a class="page-link" href="?action=listOrderAdmin&page=${i}">${i}</a>
                                        </li>
                                    </c:forEach>

                                    <!-- Next Page -->
                                    <c:if test="${currentPage < totalPages}">
                                        <li class="page-item">
                                            <a class="page-link" href="?action=listOrderAdmin&page=${currentPage + 1}" aria-label="Next">
                                                <span aria-hidden="true">&raquo;</span>
                                            </a>
                                        </li>
                                    </c:if>
                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>
                <!-- End Pagination -->
            </div>
        </div>
        <!-- End Card -->
    </div>
    <!-- End Content -->
    <jsp:include page="../includes/footer.jsp" />

</main>
