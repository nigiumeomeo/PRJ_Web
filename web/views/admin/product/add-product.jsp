

<jsp:include page="../includes/navbar.jsp" />

<main id="content" role="main" class="main">

    <div class="content container-fluid">

        <div class="page-header">
            <div class="row align-items-center">
                <div class="col-sm mb-2 mb-sm-0">
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb breadcrumb-no-gutter">
                            <li class="breadcrumb-item"><a class="breadcrumb-link" href="ecommerce-products.html">Products</a></li>
                            <li class="breadcrumb-item active" aria-current="page">Add product</li>
                        </ol>
                    </nav>

                    <h1 class="page-header-title">Create new product</h1>
                </div>
            </div>
            <!-- End Row -->
        </div>

        <form method="POST" action="${pageContext.request.contextPath}/AddProductServlet" enctype="multipart/form-data">
            <div class="form-group">
                <label>Best Seller:</label>
                <div class="custom-control custom-checkbox">
                    <input type="checkbox" class="custom-control-input" id="bestSeller" name="bestSeller">
                    <label class="custom-control-label" for="bestSeller">Is Best Seller</label>
                </div>
            </div>
            <div class="form-group">
                <label>Full Name:</label>
                <input type="text" class="form-control" name="fullName" required>
                <div class="invalid-feedback">
                    Please provide a valid full name.
                </div>
            </div>
            <div class="form-group">
                <label>Description:</label>
                <textarea class="form-control" name="description" rows="3"></textarea>
            </div>
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label>Quantity:</label>
                    <input type="number" class="form-control" name="quantity" required>
                    <div class="invalid-feedback">
                        Please provide a valid quantity.
                    </div>
                </div>
            </div>

            <div class="form-row">
                <div class="form-group">
                    <label for="categoryID">Category:</label>
                    <select class="form-control" id="categoryID" name="categoryID" required>
                        <option value="">Choose a category</option>
                        <option value="1">HP</option>
                        <option value="2">Dell</option>
                        <option value="3">Mac</option>
                    </select>
                </div>
                <div class="form-group col-md-6">
                    <label>Price:</label>
                    <input type="number" step="0.01" class="form-control" name="price" required>
                    <div class="invalid-feedback">
                        Please provide a valid price.
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label>Discount:</label>
                <input type="number" class="form-control" name="discount" required>
                <div class="invalid-feedback">
                    Please provide a valid discount.
                </div>
            </div>

            <div class="form-group">
                <label for="imgFile">Image File:</label>
                <input type="file" class="form-control-file" id="imgFile" name="imgFile" accept="image/*" required onchange="previewImage(event)">
            </div>
            <div class="form-group text-center">
                <img id="imgPreview" src="" alt="Image Preview" class=" mx-auto" style="max-width: 50%; height: 50%; display: none" >
            </div>
            <button type="submit" class="btn btn-primary">Create Product</button>
        </form>

</main>

<jsp:include page="../includes/footer.jsp" />

