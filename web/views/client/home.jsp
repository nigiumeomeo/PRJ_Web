<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="./includes/header.jsp"/>
<!-- Hero Start -->
<div class="container-fluid py-5 mb-5 hero-headertext-white" style="background: #124D87">
    <div class="container py-5">
        <div class="row g-5 align-items-center">
            <div class="col-md-12 col-lg-7">
              <h4 style="color: white; text-shadow: 1px 1px 2px ;" class="mb-3">Laptop PROVIP</h4>
<h1 style="color: white; text-shadow: 1px 1px 2px " class="mb-5 display-3 ">Chơi fifai cực cháy</h1>



                <div class="position-relative mx-auto">
                    <form action="Shop" >
                        <input type="hidden" name="action" value="searchName">
                        <input class="form-control border-2 y w-75 py-3 px-4 rounded-pill" type="search" placeholder="Search" name="keyword" required>
                        <button type="submit" class="btn btn-primary border-2py-3 px-4 position-absolute rounded-pill text-white h-100" style="top: 0; right: 25%;">Submit Now</button>
                    </form>
                </div>
            </div>
            <div class="col-md-12 col-lg-5">
                <div id="carouselId" class="carousel slide position-relative" data-bs-ride="carousel">
                    <div class="carousel-inner" role="listbox">
                        <div class="carousel-item active rounded">
                            <img src="${pageContext.request.contextPath}/views/admin/assets/img/w.jpg" class="img-fluid w-100 h-100 bg-secondary rounded" alt="First slide">
                            <a href="#tab" class="btn px-4 py-2 text-white rounded">HP</a>
                        </div> 
                        <div class="carousel-item rounded">
                            <img src="${pageContext.request.contextPath}/views/admin/assets/img/462540516_1212383806655403_4855133294704345333_n.png" class="img-fluid w-100 h-100 rounded" alt="Second slide">
                            <a href="#tab" class="btn px-4 py-2 text-white rounded">Dell</a>
                        </div>
                        <div class="carousel-item rounded">
                            <img src="${pageContext.request.contextPath}/views/admin/assets/img/mac.png" class="img-fluid w-100 h-100 rounded" alt="Second slide">
                            <a href="#tab" class="btn px-4 py-2 text-white rounded">Mac</a>
                        </div>
                    </div>
                    <button class="carousel-control-prev" type="button" data-bs-target="#carouselId" data-bs-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Previous</span>
                    </button>
                    <button class="carousel-control-next" type="button" data-bs-target="#carouselId" data-bs-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Next</span>
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Hero End -->


<!-- Featurs Section Start -->
<div class="container-fluid featurs py-5">
    <div class="container py-5">
        <div class="row g-4">
            <div class="col-md-6 col-lg-3">
                <div class="featurs-item text-center rounded bg-light p-4">
                    <div class="featurs-icon btn-square rounded-circle bg-secondary mb-5 mx-auto">
                        <i class="fas fa-car-side fa-3x text-white"></i>
                    </div>
                    <div class="featurs-content text-center">
                        <h5>Free Shipping</h5>
                        <p class="mb-0">Free on order over $300</p>
                    </div>
                </div>
            </div>
            <div class="col-md-6 col-lg-3">
                <div class="featurs-item text-center rounded bg-light p-4">
                    <div class="featurs-icon btn-square rounded-circle bg-secondary mb-5 mx-auto">
                        <i class="fas fa-user-shield fa-3x text-white"></i>
                    </div>
                    <div class="featurs-content text-center">
                        <h5>Security Payment</h5>
                        <p class="mb-0">100% security payment</p>
                    </div>
                </div>
            </div>
            <div class="col-md-6 col-lg-3">
                <div class="featurs-item text-center rounded bg-light p-4">
                    <div class="featurs-icon btn-square rounded-circle bg-secondary mb-5 mx-auto">
                        <i class="fas fa-exchange-alt fa-3x text-white"></i>
                    </div>
                    <div class="featurs-content text-center">
                        <h5>30 Day Return</h5>
                        <p class="mb-0">30 day money guarantee</p>
                    </div>
                </div>
            </div>
            <div class="col-md-6 col-lg-3">
                <div class="featurs-item text-center rounded bg-light p-4">
                    <div class="featurs-icon btn-square rounded-circle bg-secondary mb-5 mx-auto">
                        <i class="fa fa-phone-alt fa-3x text-white"></i>
                    </div>
                    <div class="featurs-content text-center">
                        <h5>24/7 Support</h5>
                        <p class="mb-0">Support every time fast</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Featurs Section End -->


<div class="container-fluid fruite py-5">
    <div class="container py-5">
        <div class="tab-class text-center">
            <div class="row g-4">
                <div class="col-lg-4 text-start">
                    <h1>Our Products</h1>
                </div>
                <div class="col-lg-8 text-end">
                    <ul class="nav nav-pills d-inline-flex text-center mb-5">
                        <li class="nav-item">
                            <a class="d-flex m-2 py-2 bg-light rounded-pill active" data-bs-toggle="pill" href="#tab-1">
                                <span class="text-dark" style="width: 130px;">Hp</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="d-flex py-2 m-2 bg-light rounded-pill" data-bs-toggle="pill" href="#tab-2">
                                <span class="text-dark" style="width: 130px;">Dell</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="d-flex m-2 py-2 bg-light rounded-pill" data-bs-toggle="pill" href="#tab-3">
                                <span class="text-dark" style="width: 130px;">Mac</span>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
            <div id="tab" class="tab-content">
                <div id="tab-1" class="tab-pane fade show p-0 active">
                    <div class="row g-4">
                        <div class="col-lg-12">
                            <div class="row g-4">
                                <c:forEach var="p" items="${laptop1}">
                                    <div class="col-md-6 col-lg-4 col-xl-3">          
                                        <div class="rounded position-relative fruite-item">        
                                            <div class="fruite-img">
                                                <a href="${pageContext.request.contextPath}/ProductController?id=${p.productID}&action=loadProduct">
                                                    <img src="${pageContext.request.contextPath}${p.imageURL}" class="img-fluid w-100 rounded-top" alt="">
                                                </a>
                                            </div>
                                            <div class="text-white bg-secondary px-3 py-1 rounded position-absolute" style="top: 10px; left: 10px;">Hp</div>
                                            <div class="p-4 ">
                                                <h4 class="limited-text">${p.fullName}</h4>
                                                <p class="limited-text">${p.description}</p>
                                                <div class="d-flex justify-content-between align-items-center">                                              
                                                    <p class="text-dark fs-5 fw-bold mb-0">$ ${p.price} </p>                            
                                                    <a href="${pageContext.request.contextPath}/ProductController?id=${p.productID}&action=loadProduct" class="btn border border-secondary rounded-pill px-3 text-primary"><i class="fa fa-shopping-bag me-2 text-primary"></i> Add to cart</a>
                                                </div>  
                                            </div>
                                        </div>
                                    </div>                               
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="tab-2" class="tab-pane fade show p-0">
                    <div class="row g-4">
                        <div class="col-lg-12">
                            <div class="row g-4">
                                <c:forEach var="p" items="${laptop2}">
                                    <div class="col-md-6 col-lg-4 col-xl-3">
                                        <div class="rounded position-relative fruite-item">
                                            <div class="fruite-img">
                                                <a href="${pageContext.request.contextPath}/ProductController?id=${p.productID}&action=loadProduct">
                                                    <img src="${pageContext.request.contextPath}${p.imageURL}" class="img-fluid w-100 rounded-top" alt="">
                                                </a>
                                            </div>
                                            <div class="text-white bg-secondary px-3 py-1 rounded position-absolute" style="top: 10px; left: 10px;">Dell</div>
                                            <div class="p-4 ">
                                                <h4 class="limited-text">${p.fullName}</h4>
                                                <p>${p.description}</p>
                                                <div class="d-flex justify-content-between  align-items-center">
                                                    <p class="text-dark fs-5 fw-bold mb-0">$ ${p.price} </p>
                                                    <a href="${pageContext.request.contextPath}/ProductController?id=${p.productID}&action=loadProduct" class="btn border border-secondary rounded-pill px-3 text-primary"><i class="fa fa-shopping-bag me-2 text-primary"></i> Add to cart</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="tab-3" class="tab-pane fade show p-0">
                    <div class="row g-4">
                        <div class="col-lg-12">
                            <div class="row g-4">
                                <c:forEach var="p" items="${laptop3}">
                                    <div class="col-md-6 col-lg-4 col-xl-3">
                                        <div class="rounded position-relative fruite-item">
                                            <div class="fruite-img">
                                                <a href="${pageContext.request.contextPath}/ProductController?id=${p.productID}&action=loadProduct">
                                                    <img src="${pageContext.request.contextPath}${p.imageURL}" class="img-fluid w-100 rounded-top" alt="">
                                                </a>
                                            </div>
                                     <div class="text-white bg-secondary px-3 py-1 rounded position-absolute" style="top: 10px; left: 10px;">Mac</div>
                                            <div class="p-4 ">
                                                <h4 class="limited-text">${p.fullName}</h4>
                                                <p>${p.description}</p>
                                                <div class="d-flex justify-content-between  align-items-center">
                                                    <p class="text-dark fs-5 fw-bold mb-0">$ ${p.price} </p>
                                                    <a href="${pageContext.request.contextPath}/ProductController?id=${p.productID}&action=loadProduct" class="btn border border-secondary rounded-pill px-3 text-primary"><i class="fa fa-shopping-bag me-2 text-primary"></i> Add to cart</a>
                                                </div>
                                            </div>           
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>      
    </div>
</div>
<div class="container-fluid vesitable py-5">
    <div class="container py-5">
        <h1 class="mb-0">List top Laptop</h1>
        <div class="owl-carousel vegetable-carousel justify-content-center">
            <c:forEach var="p" items="${listLaptop}">
                <div class="border border-primary rounded position-relative vesitable-item">
                    <div class="fruite-img">
                        <a href="${pageContext.request.contextPath}/ProductController?id=${p.productID}&action=loadProduct">
                            <img src="${pageContext.request.contextPath}${p.imageURL}" class="img-fluid w-100 rounded-top" alt="">
                        </a>
                    </div>
                  
                    <div class="p-4 border border-secondary border-top-0 rounded-bottom">
                        <h4>${p.fullName}</h4>
                        <p>${p.description}</p>
                        <div class="d-flex justify-content-between align-items-center">
                            <p class="text-dark fs-5 fw-bold mb-0">$ ${p.price} </p>
                            <a href="${pageContext.request.contextPath}/ProductController?id=${p.productID}&action=loadProduct" class="btn border border-secondary rounded-pill px-3 text-primary"><i class="fa fa-shopping-bag me-2 text-primary"></i> Add to cart</a>
                        </div>
                    </div>
                </div> 
            </c:forEach>
        </div>
    </div>
</div>
<!-- Vesitable Shop End -->





<!-- Bestsaler Product Start -->
<div class="container-fluid py-5">
    <div class="container py-5">
        <div class="text-center mx-auto mb-5" style="max-width: 700px;">
            <h1 class="display-4">Bestseller Products</h1>
            <p>Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable.</p>
        </div>
        <div class="row g-4">
            <c:forEach var="p" items="${bestSellers}">
                <div class="col-lg-6 col-xl-4">
                    <div class="p-4 rounded bg-light">
                        <div class="row align-items-center">
                            <div class="fruite-img">
                                <a href="${pageContext.request.contextPath}/ProductController?id=${p.productID}&action=loadProduct">
                                    <img src="${pageContext.request.contextPath}${p.imageURL}" class="img-fluid w-100 rounded-top" alt="">
                                </a>
                            </div>
                            <div class="text-white bg-secondary px-3 py-1 rounded position-absolute" style="top: 10px; left: 10px;"></div>
                            <div class="p-4 rounded-bottom">
                                <h4>${p.fullName}</h4>
                                <div class="d-flex my-3">
                                    <i class="fas fa-star text-primary"></i>
                                    <i class="fas fa-star text-primary"></i>
                                    <i class="fas fa-star text-primary"></i>
                                    <i class="fas fa-star text-primary"></i>
                                    <i class="fas fa-star"></i>
                                </div>
                                <h4 class="mb-3">$ ${p.price}</h4>
                                <a href="${pageContext.request.contextPath}/ProductController?id=${p.productID}&action=loadProduct" class="btn border border-secondary rounded-pill px-3 text-primary"><i class="fa fa-shopping-bag me-2 text-primary"></i> Add to cart</a>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<!-- Bestsaler Product End -->



<jsp:include page="includes/footer.jsp" />