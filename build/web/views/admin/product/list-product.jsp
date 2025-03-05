<%@ page import="java.util.List" %>
<%@ page import="model.Product" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<jsp:include page="../includes/navbar.jsp" />

<main id="content" role="main" class="main">
    <!-- Content -->
    <div class="content container-fluid">
        <!-- Page Header -->
        <div class="page-header">
            <div class="row align-items-center mb-3">
                <div class="col-sm mb-2 mb-sm-0">
                    <h1 class="page-header-title">Products <span class="badge badge-soft-dark ml-2">${totalProducts}</span></h1>
                </div>
                <div class="col-sm-auto">
                    <a class="btn btn-primary" href="${pageContext.request.contextPath}/views/admin/product/add-product.jsp">Add Product</a>
                </div>
            </div>
            <!-- End Row -->

            <!-- Column Control -->
          
            <!-- End Column Control -->

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
                        <a class="nav-link active" href="#">All Products</a>
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
                            <th>ID</th>
                            <th>Product Name</th>
                            <th>Description</th>
                            <th>Quantity</th>
                            <th>Quantity Sold</th>
                            <th>Image</th>
                            <th>Category</th>
                            <th>Price</th>
                            <th>Discount</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="product" items="${products}">
                            <tr>
                                <td class="table-column-pr-0"></td>
                                <td>${product.productID}</td>
                                <td>${product.fullName}</td>
                                <td>${product.description}</td>
                                <td>${product.quantity}</td>
                                <td>${product.quantitySold}</td>
                                <td>
                                    <img class="avatar avatar-lg" src="${pageContext.request.contextPath}/${product.imageURL}" alt="${product.fullName}">
                                </td>
                                <td>${product.categoryID}</td> <!-- Có th? c?n l?y tên danh m?c t? c? s? d? li?u -->
                                <td>$${product.price}</td>
                                <td>${product.discount}%</td>
                                <td>
                                    <div class="btn-group" role="group">

                                        <form action="${pageContext.request.contextPath}/GetProductByIdServlet" method="GET" style="display: inline;">
                                            <input type="hidden" name="id" value="${product.productID}">
                                            <!--      <button type="submit" class="btn btn-sm btn-dark" style="margin-right: 5px;">
                                                      <i class="tio-edit"></i> View
                                                  </button>
                                              </form> -->
                                            <form action="${pageContext.request.contextPath}/GetProductByIdServlet" method="GET" style="display: inline;">
                                                <input type="hidden" name="id" value="${product.productID}">
                                                <button type="submit" class="btn btn-sm btn-warning" style="margin-right: 5px;">
                                                    <i class="tio-edit"></i> Edit
                                                </button>
                                            </form>

                                            <form action="${pageContext.request.contextPath}/DeleteProductServlet" method="POST" style="display: inline;">
                                                <input type="hidden" name="id" value="${product.productID}">
                                                <button type="submit" class="btn btn-sm btn-danger">
                                                    <i class="tio-delete"></i> Delete
                                                </button>
                                            </form>
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
                                            <a class="page-link" href="products?page=${currentPage - 1}" aria-label="Previous">
                                                <span aria-hidden="true">&laquo;</span>
                                            </a>
                                        </li>
                                    </c:if>

                                    <!-- Page Numbers -->
                                    <c:forEach var="page" begin="1" end="${totalPages}">
                                        <li class="page-item <c:if test="${page == currentPage}">active</c:if>">
                                            <a class="page-link" href="products?page=${page}">${page}</a>
                                        </li>
                                    </c:forEach>
                                    <!-- Next Page -->
                                    <c:if test="${currentPage < totalPages}">
                                        <li class="page-item">
                                            <a class="page-link" href="products?page=${currentPage + 1}" aria-label="Next">
                                                <span aria-hidden="true">&raquo;</span>
                                            </a>
                                        </li>
                                    </c:if>
                                </ul>
                            </nav>
                            <!-- End Pagination Controls -->
                        </div>
                    </div>
                </div>
            </div>
            <!-- End Footer -->
        </div>
        <!-- End Card -->
    </div>
    <!-- End Content -->

    <jsp:include page="../includes/footer.jsp" />
</main>

<script>
    document.querySelectorAll('.column-control').forEach(checkbox => {
        checkbox.addEventListener('change', function () {
            const column = this.getAttribute('data-column');
            const isChecked = this.checked;
            document.querySelectorAll('#datatable th:nth-child(' + column + '), #datatable td:nth-child(' + column + ')')
                    .forEach(cell => {
                        cell.style.display = isChecked ? '' : 'none';
                    });
        });
    });
</script>