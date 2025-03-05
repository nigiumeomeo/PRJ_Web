<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Footer Start -->
<div class="container-fluid text-white-50 footer pt-5 mt-5" style="background-color: #595959">
    <div class="container py-5">
        <div class="pb-4 mb-4" style="border-bottom: 1px solid rgba(226, 175, 24, 0.5) ;">
            <div class="row g-4">
                <div class="col-lg-3">
                    <a href="#">
                        <h1 class=" mb-0" style="color: white" >Laptop</h1>
                        <p class="mb-0" style="color: white">Shop</p>
                    </a>
                </div>
                <div class="col-lg-6">
                    <div class="position-relative mx-auto">
                        <form action="${pageContext.request.contextPath}/views/client/pages/contact.jsp" method="GET">
                            <input class="form-control border-0 w-100 py-3 px-4 rounded-pill" name="email" type="email" placeholder="Your Email">
                            <button type="submit" class="btn btn-primary border-0 border-secondary py-3 px-4 position-absolute rounded-pill " style="top: 0; right: 0;">Subscribe Now</button>
                        </form>

                    </div>
                </div>
                
            </div>
        </div>
        <div class="row g-5">
            <div class="col-lg-3 col-md-6">
                <div class="footer-item">
                    <h4 class="text-light mb-3">Why People Like us!</h4>
                    <a href="${pageContext.request.contextPath}/views/client/pages/404.jsp" class="btn border-secondary py-2 px-4 rounded-pill "style="color: white">Read More</a>
                </div>
            </div>
            <div class="col-lg-3 col-md-6">
                <div class="d-flex flex-column text-start footer-item">
                    <h4 class="text-light mb-3">Shop Info</h4>
                    <a href="${pageContext.request.contextPath}/views/client/pages/contact.jsp" class="btn-link" href="">About Us</a>
                    <a href="${pageContext.request.contextPath}/views/client/pages/contact.jsp" class="btn-link" href="">Contact Us</a>
                </div>
            </div>
            <div class="col-lg-3 col-md-6">
                <div class="d-flex flex-column text-start footer-item">
                    <h4 class="text-light mb-3">Account</h4>
                    <a class="btn-link" href="">My Account</a>
                    <a class="btn-link" href="${pageContext.request.contextPath}/views/client/pages/cart.jsp">Shopping Cart</a>
                </div>
            </div>
            <div class="col-lg-3 col-md-6">
                <div class="footer-item">
                    <h4 class="text-light mb-3">Privacy Policy</h4>
                    <!--                    <p>Address: 1429 Netus Rd, NY 48247</p>
                                        <p>Email: Example@gmail.com</p>
                                        <p>Phone: +0123 4567 8910</p>-->
                    <p>Payment Accepted</p>
                    <img src="${pageContext.request.contextPath}/views/client/asset/img/payment.png" class="img-fluid" alt="">
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Footer End -->

<!-- Copyright Start -->
<div class="container-fluid copyright py-4" style="background-color: #595959">
    <div class="container">
        <div class="row">
            <div class="col-md-6 text-center text-md-start mb-3 mb-md-0">
                <span class="text-light"><a href="#"><i class="fas fa-copyright text-light me-2"></i>Laptop</a>, All right reserved.</span>
            </div>
            <div class="col-md-6 my-auto text-center text-md-end text-white">
                <!--/*** This template is free as long as you keep the below author’s credit link/attribution link/backlink. ***/-->
                <!--/*** If you'd like to use the template without the below author’s credit link/attribution link/backlink, ***/-->
                <!--/*** you can purchase the Credit Removal License from "https://htmlcodex.com/credit-removal". ***/-->
                <!--                Designed By <a class="border-bottom" href="https://htmlcodex.com">HTML Codex</a> Distributed By <a class="border-bottom" href="https://themewagon.com">ThemeWagon</a>-->
            </div>
        </div>
    </div>
</div>
<!-- Copyright End -->



<!-- Back to Top -->
<a href="#" class="btn btn-primary border-3 border-primary rounded-circle back-to-top"><i class="fa fa-arrow-up"></i></a>   

<!-- JavaScript Libraries -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/views/client/asset/lib/easing/easing.min.js"></script>
<script src="${pageContext.request.contextPath}/views/client/asset/lib/waypoints/waypoints.min.js"></script>
<script src="${pageContext.request.contextPath}/views/client/asset/lib/lightbox/js/lightbox.min.js"></script>
<script src="${pageContext.request.contextPath}/views/client/asset/lib/owlcarousel/owl.carousel.min.js"></script>

<!-- Template Javascript -->    
<script src="${pageContext.request.contextPath}/views/client/asset/js/main.js"></script>
</body>

</html>