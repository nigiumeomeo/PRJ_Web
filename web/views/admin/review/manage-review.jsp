<%@ page import="java.util.List" %>
<%@ page import="model.Review" %>
<%@ page import="model.Product" %>
<%@ page import="dao.implement.ReviewDao" %>
<%@ page import="dao.implement.ProductDao" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="../includes/navbar.jsp" />

<main id="content" role="main" class="main">
    <div class="content container-fluid">
        <div class="row">
            <div class="col-sm-5 col-md-6 col-12 pb-4">
                <nav class="navbar navbar-light bg-light mb-4">
                 
                </nav>
                <h1>Comments</h1>


                <c:forEach var="review" items="${reviews}">
                    <div class="row comment mt-4 text-justify d-flex align-items-start">
                        
                        <div class="col-2">
                             ProductID:   ${review.productID}
                        </div>
                        <div class="col-8">
                           <p style="word-wrap: break-word;">${review.description}</p>
                        </div>
                        <div class="col-2 text-right">
                            <form action="DeleteReviewServlet" method="post">
                                <input type="hidden" name="id" value="${review.id}">               
                                <input type="hidden" name="productId" value="${review.productID}">
                                <button type="submit" class="btn btn-danger btn-sm">Delete</button>
                            </form>
                        </div>
                    </div>
                </c:forEach>

            </div>
            <div class="col-lg-4 col-md-5 col-sm-4 offset-md-1 offset-sm-1 col-12 mt-4">

                <c:if test="${product != null}">
                    <div class="form-group text-center">
                        <label for="imgUrl">Image:</label><br>
                        <img src="${pageContext.request.contextPath}/${product.imageURL}" alt="Current Image" style="
                             width: 200px;
                             height: 200px;
                             object-fit: cover;
                             " class="img-preview">
                    </div>
                    <h4>Product Details</h4>
                    <div class="form-group">
                        <label>Best Seller:</label>
                        <p>${product.bestSeller ? 'Yes' : 'No'}</p>
                    </div>
                    <div class="form-group">
                        <label>Full Name:</label>
                        <p>${product.fullName}</p>
                    </div>
                    <div class="form-group">
                        <label>Description:</label>
                        <p>${product.description}</p>
                    </div>
                    <div class="form-group">
                        <label>Quantity:</label>
                        <p>${product.quantity}</p>
                    </div>
                    <div class="form-group">
                        <label>Quantity Sold:</label>
                        <p>${product.quantitySold}</p>
                    </div>
                    <div class="form-group">
                        <label>Category ID:</label>
                        <p>${product.categoryID}</p>
                    </div>
                    <div class="form-group">
                        <label>Price:</label>
                        <p>${product.price}</p>
                    </div>
                    <div class="form-group">
                        <label>Discount:</label>
                        <p>${product.discount}</p>
                    </div>

                </c:if>
            </div>
        </div>
    </div>
</main>

<jsp:include page="../includes/footer.jsp" />

