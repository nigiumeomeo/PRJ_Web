Price range ($ / kg)Price range ($ / kg)<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/views/client/includes/header.jsp"/>

<div class="container-fluid page-header py-5">
    <h1 class="text-center text-white display-6">Shop</h1>

</div>
<!-- Single Page Header End -->

<!-- Fruits Shop Start-->
<div class="container-fluid fruite py-5">
    <div class="container py-5">
        <h1 class="mb-4">Laptop shop</h1>
        <div class="row g-4">
            <div class="col-lg-12">
                <div class="row g-4">
                    <div class="col-xl-3">
                        <form action="Shop?action=search" method="get" class="search-form">
                            <div class="input-group w-100 mx-auto d-flex">
                                <input type="search" name="keyword" class="form-control p-3" placeholder="keywords" aria-describedby="search-icon-1" required 
                                       value="<%= request.getAttribute("keyword") != null ? request.getAttribute("keyword") : "" %>">
                                <button type="submit" id="search-icon-1" class="input-group-text p-3"><i class="fa fa-search"></i></button>
                            </div>
                        </form>
                    </div>
                    <div class="col-6"></div>
                    <div class="col-xl-3">
                        <form id="fruitform" action="Shop" method="get">
                            <div class="bg-light ps-3 py-3 rounded d-flex align-items-center mb-4 row">
                                <label class="col-xl-4" for="fruits">Order By</label>
                                <select id="fruits" name="order" class="border-0 form-select-sm bg-light me-3 col-xl-4">
                                    <option value="">Nothing</option>
                                    <option value="asc">Price ↑</option>
                                    <option value="desc">Price ↓</option>
                                </select>
                                <c:if test="${not empty categoryId}">
                                    <input type="hidden" name="categoryId" value="${categoryId}">
                                </c:if>                                
                                <c:if test="${not empty keyword}">
                                    <input type="hidden" name="keyword" value="${keyword}">
                                </c:if>  
                                <input type="hidden" name="priceRange" value="${priceRange}">
                                <button type="submit" class="btn btn-primary col-xl-3" style="color: #FFF">Sort</button>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="row g-4">
                    <div class="col-lg-3">
                        <div class="row g-4">
                            <div class="col-lg-12">
                                <div class="mb-3">
                                    <h4>Categories</h4>
                                    <ul class="list-unstyled fruite-categorie">
                                        <li>
                                            <div class="d-flex justify-content-between fruite-name">
                                                <a href="${pageContext.request.contextPath}/Shop?action=loadHome&categoryId=0">
                                                 <i class="fas fa-circle me-2"></i>

                                                    All
                                                </a>
<!--                                                        <span>${p.quantity}</span>-->
                                            </div>
                                        </li>
                                        <c:forEach var="c" items="${cList}" varStatus="loop">
                                            <c:if test="${loop.index < 3}">
                                                <li>
                                                    <div class="d-flex justify-content-between fruite-name">
                                                        <a href="${pageContext.request.contextPath}/Shop?action=loadHome&categoryId=${c.id}">
                                                            <i class="fas fa-circle me-2"></i>

                                                            ${c.fullName}
                                                        </a>
<!--                                                        <span>${p.quantity}</span>-->
                                                    </div>
                                                </li>
                                            </c:if>
                                        </c:forEach>
                                    </ul>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="mb-3">
                                    <h4>Price range ($)</h4>
                                    <form action="Shop" method="get" class="custom-form">    
                                        <c:if test="${not empty categoryId}">
                                            <input type="hidden" name="categoryId" value="${categoryId}">
                                        </c:if>                                
                                        <c:if test="${not empty keyword}">
                                            <input type="hidden" name="keyword" value="${keyword}">
                                        </c:if>   
                                        <div class="mb-2">
                                            <input type="radio" class="me-2" id="Categories-2" name="priceRange" value="2"
                                                   <%= request.getAttribute("priceRange") != null && request.getAttribute("priceRange").equals("1") ? "checked" : "" %>>
                                            <label for="Categories-1">Under 2 USD</label>
                                        </div>
                                        <div class="mb-2">
                                            <input type="radio" class="me-2" id="Categories-2" name="priceRange" value="2"
                                                   <%= request.getAttribute("priceRange") != null && request.getAttribute("priceRange").equals("2") ? "checked" : "" %>>
                                            <label for="Categories-2">2 - 4 USD</label>
                                        </div>
                                        <div class="mb-2">
                                            <input type="radio" class="me-2" id="Categories-3" name="priceRange" value="3"
                                                   <%= request.getAttribute("priceRange") != null && request.getAttribute("priceRange").equals("3") ? "checked" : "" %>>  
                                            <label for="Categories-3">4 - 6 USD</label>
                                        </div>
                                        <div class="mb-2">
                                            <input type="radio" class="me-2" id="Categories-4" name="priceRange" value="4"
                                                   <%= request.getAttribute("priceRange") != null && request.getAttribute("priceRange").equals("4") ? "checked" : "" %>>  
                                            <label for="Categories-4">6 - 8 USD</label>
                                        </div>
                                        <div class="mb-2">
                                            <input type="radio" class="me-2" id="Categories-5" name="priceRange" value="5"
                                                   <%= request.getAttribute("priceRange") != null && request.getAttribute("priceRange").equals("5") ? "checked" : "" %>>  
                                            <label for="Categories-5">Over 8 USD</label>
                                        </div>
                                        <button type="submit" class="btn btn-light col-md-4 border border-primary" style="width: 180px;">Choose</button>
                                    </form>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <ul class="list-unstyled fruite-categorie">
                                    <c:forEach var="p" items="${features}" varStatus="loop">
                                        <c:if test="${loop.index < 3}">
                                            <li>
                                                <a href="${pageContext.request.contextPath}/ProductController?id=${p.productID}&action=loadProduct">
                                                    <div class="d-flex align-items-center justify-content-start">
                                                        <div class="rounded me-4" style="width: 100px; height: 100px;">                                                 
                                                            <img src="${pageContext.request.contextPath}${p.imageURL}" class="img-fluid rounded" alt="">
                                                        </div>
                                                        <div>
                                                            <h6 class="mb-2">${p.fullName}</h6>
                                                            <div class="d-flex mb-2">
                                                                <i class="fa fa-star text-secondary"></i>
                                                                <i class="fa fa-star text-secondary"></i>
                                                                <i class="fa fa-star text-secondary"></i>
                                                                <i class="fa fa-star text-secondary"></i>
                                                                <i class="fa fa-star"></i>
                                                            </div>
                                                            <div class="d-flex mb-2">
                                                                <h5 class="fw-bold me-2">${p.price}</h5>
                                                                <h5 class="text-danger text-decoration-line-through">${Math.round(p.price / (100 - p.discount) * 100 * 100) / 100} $</h5>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </a>
                                            </li>
                                        </c:if>
                                    </c:forEach>
                                </ul>
                            </div>
                           
                        </div>
                    </div>
                    <div class="col-lg-9">
                        <div class="row g-4 justify-content-center">
                            <c:forEach var="p" items="${result}">
                                <div class="col-md-6 col-lg-6 col-xl-4">
                                    <div class="rounded position-relative fruite-item">
                                        <a href="${pageContext.request.contextPath}/ProductController?id=${p.productID}&action=loadProduct">
                                            <img src="${pageContext.request.contextPath}${p.imageURL}" class="img-fluid w-100 rounded-top" alt="">
                                        </a>
                                        <div class="text-white bg-secondary px-3 py-1 rounded position-absolute" style="top: 10px; left: 10px;">
                                            <c:if test="${p.categoryID == 1}">HP</c:if>
                                            <c:if test="${p.categoryID == 2}">Dell</c:if>
                                            <c:if test="${p.categoryID == 3}">Mac</c:if>
                                            </div>
                                            <div class="p-4">
                                                <h4>${p.fullName}</h4>
                                            <p class="limited-text">${p.description}</p>
                                            <div class="d-flex justify-content-between flex-lg-wrap">
                                                <% String priceRange = request.getParameter("priceRange"); %>

                                                <p class="text-dark fs-5 fw-bold mb-0">$ ${p.price} </p>
                                                <a href="${pageContext.request.contextPath}/ProductController?id=${p.productID}&action=loadProduct" class="btn border border-secondary rounded-pill px-3 text-primary"><i class="fa fa-shopping-bag me-2 text-primary"></i> Add to cart</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>

                        <c:url var="firstPageUrl" value="Shop">
                            <c:param name="action" value="loadHome"/>
                            <c:param name="page" value="1"/>
                            <c:param name="order" value="${param.order}"/>
                        </c:url>
                        <c:url var="lastPageUrl" value="Shop">
                            <c:param name="action" value="loadHome"/>
                            <c:param name="page" value="${numberOfPages}"/>
                            <c:param name="order" value="${param.order}"/>
                        </c:url>

                        <div class="col-12">
                            <div class="pagination d-flex justify-content-center mt-5">
                                <c:if test="${currentPage > 1}">
                                    <c:url var="prevPageUrl" value="Shop">
                                        <c:param name="action" value="loadHome"/>
                                        <c:param name="page" value="${currentPage - 1}"/>
                                        <c:param name="order" value="${param.order}"/>
                                    </c:url>
                                    <a href="${firstPageUrl}" class="rounded">&laquo;</a>
                                </c:if>

                                <c:forEach begin="1" end="${numberOfPages}" varStatus="loop">
                                    <c:url var="pageUrl" value="Shop">
                                        <c:param name="page" value="${loop.index}"/>
                                        <c:if test="${not empty param.order}">
                                            <c:param name="order" value="${param.order}"/>
                                        </c:if>   
                                        <c:if test="${not empty keyword}">
                                            <c:param name="keyword" value="${keyword}"/>                                   
                                        </c:if>   
                                        <c:if test="${not empty categoryId}">
                                            <c:param name="categoryId" value="${categoryId}"/>
                                        </c:if>          
                                        <c:param name="priceRange" value="${priceRange}"/>
                                    </c:url>
                                    <a href="${pageUrl}" class="rounded ${loop.index == currentPage ? 'active' : ''}">${loop.index}</a>
                                </c:forEach>

                                <c:if test="${currentPage < numberOfPages}">
                                    <a href="${lastPageUrl}" class="rounded">&raquo;</a>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>



<!-- Fruits Shop End-->
<jsp:include page="/views/client/includes/footer.jsp" />