

<jsp:include page="../includes/navbar.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<main id="content" role="main" class="main">
    <!-- Content -->
    <div class="content container-fluid">
        <!-- Page Header -->
        <div class="page-header">
            
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
                        <a class="nav-link active" href="#">All products</a>
                    </li>
                </ul>
                <!-- End Nav -->
            </div>
            <!-- End Nav Scroller -->
        </div>
        <!-- End Page Header -->


        <!-- End Row -->

        <!-- Card -->
        <div class="card">
            <!-- Header -->
            <!-- End Header -->

            <!-- Table -->
            <div class="table-responsive datatable-custom">
                <table id="datatable" class="table table-borderless table-thead-bordered table-nowrap table-align-middle card-table">
                    <thead>
                        <tr>
                            <th scope="col" class="table-column-pr-0"></th>
                            <th>ID</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Address</th>
                            <th>Role</th>
                            <th>Avatar</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="user" items="${users}">
                            <tr>
                                <td class="table-column-pr-0"></td>
                                <td>${user.id}</td>
                                <td>${user.firstName}</td>
                                <td>${user.lastName}</td>
                                <td>${user.address}</td>
                                <td>${user.role}</td>
                                <td>
                                    <img class="avatar avatar-lg" src="${pageContext.request.contextPath}/${user.avatarURL}" alt="Avatar">
                                </td>
                                <td>
                                    <div class="btn-group" role="group">
                                        
                                         <form action="${pageContext.request.contextPath}/GetUserByIdServlet" method="GET" style="display: inline;">
                                            <input type="hidden" name="id" value="${user.id}">
                                            <button type="submit" class="btn btn-sm btn-primary" style="margin-right: 5px;">
                                                <i class="tio-edit"></i> View
                                            </button>
                                        </form>
                                        
                                        <form action="${pageContext.request.contextPath}/GetUserByIdServlet" method="GET" style="display: inline;">
                                            <input type="hidden" name="id" value="${user.id}">
                                        
                                        </form>

                                        <form action="${pageContext.request.contextPath}/DeleteUserServlet" method="POST" style="display: inline;">
                                            <input type="hidden" name="id" value="${user.id}">
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
                                            <a class="page-link" href="users?page=${currentPage - 1}" aria-label="Previous">
                                                <span aria-hidden="true">&laquo;</span>
                                            </a>
                                        </li>
                                    </c:if>

                                    <!-- Page Numbers -->
                                    <c:forEach var="page" begin="1" end="${totalPages}">
                                        <li class="page-item <c:if test="${page == currentPage}">active</c:if>">
                                            <a class="page-link" href="users?page=${page}">${page}</a>
                                        </li>
                                    </c:forEach>

                                    <!-- Next Page -->
                                    <c:if test="${currentPage < totalPages}">
                                        <li class="page-item">
                                            <a class="page-link" href="users?page=${currentPage - 1}" aria-label="Next">
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

    <!-- Footer -->




    <!-- End Footer -->

    <jsp:include page="../includes/footer.jsp" />

</main>

