<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/views/client/includes/header.jsp"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- Single Page Header start -->
<div class="container-fluid page-header py-5">
    <h1 class="text-center text-white display-6">Product Detail</h1>

</div>
<!-- Single Page Header End -->
<c:if test="${not empty sessionScope.message}">
    <div class="alert alert-info alert-dismissible fade show" role="alert">
        ${sessionScope.message}
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
    <c:remove var="message" scope="session"/>
</c:if>
<!-- Single Product Start -->
<div class="container-fluid py-5 mt-5">
    <div class="container py-5">
        <div class="row g-4 mb-5">
            <div class="col-lg-8 col-xl-9">
                <div class="row g-4">
                    <div class="col-lg-6">
                        <div class="border rounded">
                            <a href="#">
                                <img src="${pageContext.request.contextPath}${product.imageURL}" class="img-fluid rounded" alt="Image">
                            </a>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <form action="${pageContext.request.contextPath}/AddToCart" method="POST">
                            <h4 class="fw-bold mb-3">${product.fullName}</h4>
                            <c:if test="${p.categoryID == 1}">HP</c:if>
                            <c:if test="${p.categoryID == 2}">Dell</c:if>
                            <c:if test="${p.categoryID == 3}">Mac</c:if>
                            <h5 class="fw-bold mb-3">${product.price} $</h5>
                            <div class="d-flex mb-4">
                                <i class="fa fa-star text-secondary"></i>
                                <i class="fa fa-star text-secondary"></i>
                                <i class="fa fa-star text-secondary"></i>
                                <i class="fa fa-star text-secondary"></i>
                                <i class="fa fa-star"></i>
                            </div>
                            <p class="mb-4">${product.description}</p>
                            <div class="input-group quantity mb-5" style="width: 100px;">
                          
                                   
                                  
                          
                                <c:set var="maxQuantity" value="${product.quantity}" />
                                <input type="number" class="" name="productQuantity" value="1" min="1" max="${maxQuantity}"/> 
                                
                            </div>
                            <p>${product.quantity} (s) remaining</p>
                            <input type="hidden" name="productId" value="${product.productID}">
                            <button type="submit" class="btn border border-seacondary text-primary rounded-pill px-4 py-3">Add to cart</button>
                        </form>
                    </div>

                    <div class="col-lg-12">
                        <nav>
                            <div class="nav nav-tabs mb-3">
                                <button class="nav-link active border-white border-bottom-0" type="button" role="tab"
                                        id="nav-about-tab" data-bs-toggle="tab" data-bs-target="#nav-about"
                                        aria-controls="nav-about" aria-selected="true">Description</button>
                                <button class="nav-link border-white border-bottom-0" type="button" role="tab"
                                        id="nav-mission-tab" data-bs-toggle="tab" data-bs-target="#nav-mission"
                                        aria-controls="nav-mission" aria-selected="false">Reviews</button>
                            </div>
                        </nav>
                        <div class="tab-content mb-5">
                            <div class="tab-pane active" id="nav-about" role="tabpanel" aria-labelledby="nav-about-tab">
                                <p>${product.description}</p>
                                <!--   <div class="px-2">
                                       <div class="row g-4">
                                           <div class="col-6">
                                               <div class="row bg-light align-items-center text-center justify-content-center py-2">
                                                   <div class="col-6">
                                                       <p class="mb-0">Weight</p>
                                                   </div>
                                                   <div class="col-6">
                                                       <p class="mb-0">1 kg</p>
                                                   </div>
                                               </div>
                                               <div class="row text-center align-items-center justify-content-center py-2">
                                                   <div class="col-6">
                                                       <p class="mb-0">Country of Origin</p>
                                                   </div>
                                                   <div class="col-6">
                                                       <p class="mb-0">Agro Farm</p>
                                                   </div>
                                               </div>
                                               <div class="row bg-light text-center align-items-center justify-content-center py-2">
                                                   <div class="col-6">
                                                       <p class="mb-0">Quality</p>
                                                   </div>
                                                   <div class="col-6">
                                                       <p class="mb-0">Organic</p>
                                                   </div>
                                               </div>
                                               <div class="row text-center align-items-center justify-content-center py-2">
                                                   <div class="col-6">
                                                       <p class="mb-0">Сheck</p>
                                                   </div>
                                                   <div class="col-6">
                                                       <p class="mb-0">Healthy</p>
                                                   </div>
                                               </div>
                                               <div class="row bg-light text-center align-items-center justify-content-center py-2">
                                                   <div class="col-6">
                                                       <p class="mb-0">Min Weight</p>
                                                   </div>
                                                   <div class="col-6">
                                                       <p class="mb-0">250 Kg</p>
                                                   </div>
                                               </div>
                                           </div>
                                       </div>
                                   </div>-->
                            </div>
                            <div class="tab-pane" id="nav-mission" role="tabpanel" aria-labelledby="nav-mission-tab">


                                <c:forEach var="r" items="${reviews}">

                                    <div class="d-flex">
                                        <img src="${pageContext.request.contextPath}${r.user.avatarURL}" class="img-fluid rounded-circle p-3" style="width: 100px; height: 100px;" alt="">
                                        <div class="">
                                            <div class="d-flex justify-content-between">
                                                <h5>${r.user.firstName} ${r.user.lastName}</h5>

                                            </div>
                                            <p class="text-dark">${r.description}</p>
                                        </div>
                                    </div>
                                </c:forEach>

                            </div>

                        </div>
                    </div>
                    <form action="${pageContext.request.contextPath}/ReviewServlet" method="POST">
                        <h4 class="mb-5 fw-bold">Leave a Reply</h4>
                        <div class="row g-4">

                            <div class="col-lg-12">
                                <div class="border-bottom rounded my-4">
                                    <textarea name="cmt" class="form-control border-0" cols="30" rows="8" placeholder="Your Review *" spellcheck="false"></textarea>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="d-flex justify-content-between py-3 mb-5">
                                    <div class="d-flex align-items-center">
                                        <input type="hidden" name="productId" value="${product.productID}">
                                    </div>
                                    <button type="submit" class="btn border border-seacondary text-primary rounded-pill px-4 py-3">Post Comment</button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="col-lg-4 col-xl-3">
                <div class="row g-4 fruite">
                    <div class="col-lg-12">
                        <form action="Shop?action=search" method="get" class="search-form">
                            <div class="input-group w-100 mx-auto d-flex">
                                <input type="search" name="keyword" class="form-control p-3" placeholder="keywords" aria-describedby="search-icon-1" required 
                                       value="<%= request.getAttribute("keyword") != null ? request.getAttribute("keyword") : "" %>">
                                <input type="hidden" name="priceRange" value="${priceRange}">
                                <button type="submit" id="search-icon-1" class="input-group-text p-3"><i class="fa fa-search"></i></button>
                            </div>
                        </form>
                        <div class="mb-4">
                            <h4>Recommend products</h4>
                            <ul class="list-unstyled fruite-categorie">
                                <c:forEach var="p" items="${products}" varStatus="loop">
                                    <c:if test="${loop.index < 5}">
                                        <li>
                                            <div class="d-flex justify-content-between fruite-name">
                                                <a href="${pageContext.request.contextPath}/ProductController?id=${p.productID}&action=loadProduct">
                                                    <span class="fa-stack fa-1x">
                                                        <i class="fas fa-circle "></i>
                                                    
                                                    </span>

                                                    ${p.fullName}
                                                </a>
                                                <span>${p.quantity}</span>
                                            </div>
                                        </li>
                                    </c:if>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                    <div class="col-lg-12">
                        <h4 class="mb-4">Featured products</h4>
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
                <div class="col-lg-12">
                    <div class="position-relative">
                        <img src="${pageContext.request.contextPath}/views/client/asset/img/" class="img-fluid w-100 rounded" alt="">
                        <div class="position-absolute" style="top: 50%; right: 10px; transform: translateY(-50%);">

                        </div>
                    </div>
                </div>
            </div>
        </div>
        <h1 class="fw-bold mb-0">Related products</h1>
        <div class="vesitable">

            <div class="owl-carousel vegetable-carousel justify-content-center">
                <c:forEach var="p" items="${products}">
                    <div class="border border-primary rounded position-relative vesitable-item">
                        <div class="fruite-img">
                            <a href="${pageContext.request.contextPath}/ProductController?id=${p.productID}&action=loadProduct">
                                <img src="${pageContext.request.contextPath}${p.imageURL}" class="img-fluid w-100 rounded-top" alt="">
                            </a>
                        </div>
                        <div class="text-white bg-primary px-3 py-1 rounded position-absolute" style="top: 10px; right: 10px;">
                            <c:if test="${p.categoryID == 1}">HP</c:if>
                            <c:if test="${p.categoryID == 2}">Dell</c:if>
                            <c:if test="${p.categoryID == 3}">Mac</c:if>
                            </div>
                            <div class="p-4 rounded-bottom">
                                <h4>${p.fullName}</h4>
                            <p>${p.description}</p>
                            <div class="d-flex justify-content-between flex-lg-wrap">
                                <p class="text-dark fs-5 fw-bold mb-0">${p.price}$</p>
                                <a href="${pageContext.request.contextPath}/ProductController?id=${p.productID}&action=loadProduct" class="btn border border-secondary rounded-pill px-3 text-primary"><i class="fa fa-shopping-bag me-2 text-primary"></i> Add to cart</a>
                            </div>
                        </div>
                    </div> 
                </c:forEach>
            </div>
        </div>
    </div> 
</div>
<!-- Single Product End -->
<jsp:include page="/views/client/includes/footer.jsp" />
