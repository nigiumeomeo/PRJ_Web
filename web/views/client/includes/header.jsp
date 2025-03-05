
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>Laptop Website</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Raleway:wght@600;800&display=swap" rel="stylesheet"> 

        <!-- Icon Font Stylesheet -->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link type="text/css" href="${pageContext.request.contextPath}/views/client/asset/lib/lightbox/css/lightbox.min.css" rel="stylesheet">
        <link type="text/css" href="${pageContext.request.contextPath}/views/client/asset/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">


        <!-- Customized Bootstrap Stylesheet -->
        <link type="text/css" href="${pageContext.request.contextPath}/views/client/asset/css/bootstrap.min.css" rel="stylesheet">

        <!-- Template Stylesheet -->

        <link type="text/css" href="${pageContext.request.contextPath}/views/client/asset/css/style.css" rel="stylesheet">
        <style>
            .custom-form {
                padding: 20px;
                background-color: #f9f9f9;
                border: 1px solid #ddd;
                border-radius: 5px;
                max-width: 220px;
                font-family: 'Arial', sans-serif;
            }
            .custom-form div.mb-2 {
                padding: 10px 0;
            }
            .custom-form input[type="radio"] {
                accent-color: #81c408;
            }
            .custom-form label {
                margin-left: 10px;
                color: #555;
                font-size: 18px;
                font-weight: 500;
            }
            .custom-form button {
                background-color: #81c408;
                color: #fff;
                font-size: 18px;
                font-weight: 500;
                padding: 10px 20px;
                border: none;
                border-radius: 5px;
                margin-top: 20px;
            }
            .custom-form button:hover {
                background-color: #6da306;
            }
            i {
                margin-right: 5px;
            }
        </style>
    </head>

    <body>

        <!-- Spinner Start -->
        <div id="spinner" class="show w-100 vh-100 bg-white position-fixed translate-middle top-50 start-50  d-flex align-items-center justify-content-center">
            <div class="spinner-grow text-primary" role="status"></div>
        </div>
        <!-- Spinner End -->


        <!-- Navbar start -->
        <div class="container-fluid fixed-top">
            <div class="container topbar bg-primary d-none d-lg-block">
                <div class="d-flex justify-content-between">
                    <div class="top-info ps-2">
                        <small class="me-3"><i class="fas fa-map-marker-alt me-2 text-secondary"></i> <a class="text-white">Hanoi</a></small>
                        <small class="me-3"><i class="fas fa-envelope me-2 text-secondary"></i><a  class="text-white">nhanhhe181669@fpt.edu.vn</a></small>
                    </div>
                    <div class="top-link pe-2">
                        <a class="text-white"><small class="text-white mx-2">Privacy Policy</small>/</a>
                        <a  class="text-white"><small class="text-white mx-2">Terms of Use</small>/</a>
                        <a class="text-white"><small class="text-white ms-2">Sales and Refunds</small></a>
                    </div>
                </div>
            </div>
            <div class="container px-0">
                <nav class="navbar navbar-light bg-white navbar-expand-xl">
                    <a href="${pageContext.request.contextPath}/Home" class="navbar-brand"><h1 class="text-primary display-6">Laptop Shop</h1></a>
                    <button class="navbar-toggler py-2 px-3" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                        <span class="fa fa-bars text-primary"></span>
                    </button>
                    <div class="collapse navbar-collapse bg-white" id="navbarCollapse">
                        <div class="navbar-nav mx-auto">
                            <a href="${pageContext.request.contextPath}/Home" class="nav-item nav-link">Home</a>
                            <a href="${pageContext.request.contextPath}/Shop" class="nav-item nav-link">Shop</a>
                            <div class="nav-item dropdown">
                                <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">Pages</a>
                                <div class="dropdown-menu m-0 bg-secondary rounded-0">
                                    <a href="${pageContext.request.contextPath}/Order" class="dropdown-item">Order</a>
                                    <a href="${pageContext.request.contextPath}/Cart" class="dropdown-item">Cart</a>
                                    <a href="${pageContext.request.contextPath}/ReviewServlet" class="dropdown-item">Testimonial</a>
                                </div>
                            </div>

                            <script>
                                // Lấy tất cả các mục điều hướng
                                const navLinks = document.querySelectorAll('.navbar-nav .nav-link');

                                // Lấy URL hiện tại
                                const currentURL = window.location.href;

                                navLinks.forEach(link => {
                                    // Kiểm tra nếu URL của mục điều hướng khớp với URL hiện tại
                                    if (link.href === currentURL) {
                                        link.classList.add('active');
                                    }
                                });
                            </script>

                            <a href="${pageContext.request.contextPath}/views/client/pages/contact.jsp" class="nav-item nav-link">Contact</a>
                        </div>
                        <div class="d-flex m-3 me-0 align-items-center">
                            <button class="btn-search btn border border-secondary btn-md-square rounded-circle bg-white me-4" data-bs-toggle="modal" data-bs-target="#searchModal"><i class="fas fa-search text-primary"></i></button>

                            <a href="${pageContext.request.contextPath}/Cart" class="position-relative me-4 my-auto">
                                <i class="fa fa-shopping-bag fa-2x"></i>
                          </a>
                            <c:if test="${not empty sessionScope.account}">
                                <div class="nav-item dropdown">
                                    <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown"><i class="fas fa-user fa-1x"></i>${sessionScope.account.firstName}</a>
                                    <div class="dropdown-menu m-0 bg-secondary rounded-0">
                                        <c:if test="${sessionScope.account.role == 'Admin'}">
                                            <a href="${pageContext.request.contextPath}/AdminServlet" class="dropdown-item">
                                                <i class="fa fa-lock fa-1x"></i>Admin
                                            </a>
                                        </c:if>
                                        <a href="${pageContext.request.contextPath}/UserServlet?action=detail" class="dropdown-item">
                                            <i class="fa fa-user-circle fa-1x"></i>Account
                                        </a> 
                                        <a href="${pageContext.request.contextPath}/LogoutServlet" class="dropdown-item">
                                            <i class="fas fa-sign-out-alt"></i>Log out
                                        </a> 


                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${empty sessionScope.account}">
                                <a href="${pageContext.request.contextPath}/views/client/pages/login.jsp" class="my-auto">
                                    <i class="fas fa-user fa-1x"></i> Login
                                </a>
                            </c:if>
                        </div>
                    </div>
                </nav>
            </div>
        </div>

        <!-- Modal Search Start -->
        <div class="modal fade" id="searchModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-fullscreen">
                <div class="modal-content rounded-0">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Search by keyword</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>


                    <form action="Shop" class="modal-body d-flex align-items-center">
                        <div class="input-group w-75 mx-auto d-flex">

                            <input type="search" class="form-control p-3" placeholder="Keywords" aria-describedby="search-icon-1" name="keyword" required>
                            <input type="hidden" name="action" value="searchName">
                            <button type="submit" id="search-icon-1" class="input-group-text p-3"><i class="fa fa-search"></i></button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- Modal Search End -->

