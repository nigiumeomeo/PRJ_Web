

<%@ include file="../admin/includes/navbar.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="assets\js\demo.js"></script>

<main id="content" role="main" class="main">
    <!-- Content -->
    <div class="content container-fluid">
        <!-- Page Header -->
        <div class="page-header">
            <div class="row align-items-center">
                <div class="col-sm mb-2 mb-sm-0">
                    <h1 class="page-header-title">Good morning</h1>
                    <p class="page-header-text">
                       Xem trang web m con song khong 
                    </p>
                </div>
            </div>
        </div>
        <!-- End Page Header -->

        <!-- Card -->
        <div class="card card-body mb-3 mb-lg-5">
            <div class="row gx-lg-4">
                <div class="col-sm-6 col-lg-3">
                    <div class="media">
                        <div class="media-body">
                            <h6 class="card-subtitle">Total sales</h6>
                            <span class="card-title h3">${totalOrderCost}</span>
                            <div class="d-flex align-items-center">
                                <span class="badge badge-soft-success ml-2">
                                    <i> ></i> 4.3%
                                </span>
                            </div>
                        </div>

                        <span class="icon icon-sm icon-soft-secondary icon-circle ml-3">
                            <i class="tio-shop"></i>
                        </span>
                    </div>

                    <div class="d-lg-none">
                        <hr />
                    </div>
                </div>

                <div class="col-sm-6 col-lg-3 column-divider-sm">
                    <div class="media">
                        <div class="media-body">
                            <h6 class="card-subtitle">Total orders</h6>
                            <span class="card-title h3">${totalOrders}</span>

                            <div class="d-flex align-items-center">
                                <span class="badge badge-soft-success ml-2">
                                    <i ></i> 12.5%
                                </span>
                            </div>
                        </div>

                        <span class="icon icon-sm icon-soft-secondary icon-circle ml-3">
                            <i class="tio-website"></i>
                        </span>
                    </div>

                    <div class="d-lg-none">
                        <hr />
                    </div>
                </div>

                <div class="col-sm-6 col-lg-3 column-divider-lg">
                    <div class="media">
                        <div class="media-body">
                            <h6 class="card-subtitle">Total customers</h6>
                            <span class="card-title h3">${totalCustomers}</span>

                            <div class="d-flex align-items-center">
                                <span class="badge badge-soft-success ml-2">
                                    <i class=""></i> 5.5%
                                </span>
                            </div>
                        </div>

                        <span class="icon icon-sm icon-soft-secondary icon-circle ml-3">
                            <i class="tio-label-off"></i>
                        </span>
                    </div>

                    <div class="d-sm-none">
                        <hr />
                    </div>
                </div>

                <div class="col-sm-6 col-lg-3 column-divider-sm">
                    <div class="media">
                        <div class="media-body">
                            <h6 class="card-subtitle">Total products</h6>
                            <span class="card-title h3">${totalProducts}</span>

                            <div class="d-flex align-items-center">
                                <span class="badge badge-soft-danger ml-2">
                                    <i class=""></i> 4.4%
                                </span>
                            </div>
                        </div>

                        <span class="icon icon-sm icon-soft-secondary icon-circle ml-3">
                            <i class="tio-users-switch"></i>
                        </span>
                    </div>
                </div>
            </div>
        </div>
        <!-- End Card -->

        <!-- Card -->
        <div class="card mb-3 mb-lg-5">
            <!-- Header -->
            <div class="card-header">
                <div class="row align-items-center flex-grow-1">
                    <div class="col-sm mb-2 mb-sm-0">
                        <h4 class="card-header-title">
                            Sales
                            <i
                                class="tio-help-outlined text-body ml-1"
                                data-toggle="tooltip"
                                data-placement="top"
                                title="Net sales (gross sales minus discounts and returns) plus taxes and shipping. Includes orders from all sales channels."
                                ></i>
                        </h4>
                    </div>

                    <div class="col-sm-auto">
                        <!-- Select -->
                        <select
                            class="js-select2-custom custom-select-sm"
                            size="1"
                            style="opacity: 0"
                            data-hs-select2-options='{
                            "minimumResultsForSearch": "Infinity",
                            "customClass": "custom-select custom-select-sm mb-2 mb-sm-0 mr-2",
                            "dropdownAutoWidth": true,
                            "width": true
                            }'
                            >
                            <option value="">Online store</option>
                            <option value="in-store">In-store</option>
                        </select>
                        <!-- End Select -->

                        <!-- Daterangepicker -->
                        <button
                            id="js-daterangepicker-predefined"
                            class="btn btn-sm btn-white dropdown-toggle mb-2 mb-sm-0"
                            >
                            <i class="tio-date-range"></i>
                            <span
                                class="js-daterangepicker-predefined-preview ml-1"
                                ></span>
                        </button>
                        <!-- End Daterangepicker -->
                    </div>
                </div>
                <!-- End Row -->
            </div>
            <!-- End Header -->

            <!-- Body -->
            <div class="card-body">
                <div class="row">
                    <div class="col-md-9 mb-5 mb-md-0">
                        <!-- Bar Chart -->
                        <div class="chartjs-custom mb-4">
                            <canvas
                                class="js-chart"
                                style="height: 18rem"
                                data-hs-chartjs-options='{
                                "type": "bar",
                                "data": {
                                "labels": ["1AM","2AM","3AM","4AM","5AM","6AM","7AM","8AM","9AM","10AM","11AM"],
                                "datasets": [{
                                "data": [200, 300, 290, 350, 150, 350, 300, 100, 125, 220, 200, 300, 290, 350, 150, 350, 300, 100, 125, 220, 225],
                                "backgroundColor": "#377dff",
                                "hoverBackgroundColor": "#377dff",
                                "borderColor": "#377dff"
                                },
                                {
                                "data": [150, 230, 382, 204, 169, 290, 300, 100, 300, 225, 120, 150, 230, 382, 204, 169, 290, 300, 100, 300, 140],
                                "backgroundColor": "#e7eaf3",
                                "borderColor": "#e7eaf3"
                                }]
                                },
                                "options": {
                                "scales": {
                                "yAxes": [{
                                "gridLines": {
                                "color": "#e7eaf3",
                                "drawBorder": false,
                                "zeroLineColor": "#e7eaf3"
                                },
                                "ticks": {
                                "beginAtZero": true,
                                "stepSize": 100,
                                "fontSize": 12,
                                "fontColor": "#97a4af",
                                "fontFamily": "Open Sans, sans-serif",
                                "padding": 10
                                }
                                }],
                                "xAxes": [{
                                "gridLines": {
                                "display": false,
                                "drawBorder": false
                                },
                                "ticks": {
                                "fontSize": 12,
                                "fontColor": "#97a4af",
                                "fontFamily": "Open Sans, sans-serif",
                                "padding": 5
                                },
                                "categoryPercentage": 0.5,
                                "maxBarThickness": "10"
                                }]
                                },
                                "cornerRadius": 2,
                                "tooltips": {
                                "hasIndicator": true,
                                "mode": "index",
                                "intersect": false
                                },
                                "hover": {
                                "mode": "nearest",
                                "intersect": true
                                }
                                }
                                }'
                                ></canvas>
                        </div>
                        <!-- End Bar Chart -->

                        <!-- Legend Indicators -->
                        <div class="row justify-content-center">
                            <div class="col-auto">
                                <span class="legend-indicator"></span> Revenue
                            </div>
                            <div class="col-auto">
                                <span class="legend-indicator bg-primary"></span> Orders
                            </div>
                        </div>
                        <!-- End Legend Indicators -->
                    </div>

                    <div class="col-md-3 column-divider-md">
                        <div class="row">
                            <div class="col-sm-6 col-md-12">
                                <!-- Stats -->
                                <div
                                    class="d-flex justify-content-center flex-column"
                                    style="min-height: 10.5rem"
                                    >
                                    <h6 class="card-subtitle">Revenue</h6>
                                    <span class="d-block display-4 text-dark mb-1 mr-3"
                                          >${totalOrders}</span
                                    >
                                    <span class="d-block text-success">
                                        <i class="tio-trending-up mr-1"></i> +${totalOrders - 1}$
                                    </span>
                                </div>
                                <!-- End Stats -->

                                <div class="d-sm-none">
                                    <hr class="my-0" />
                                </div>

                                <div class="d-none d-md-block">
                                    <hr class="my-0" />
                                </div>
                            </div>

                            <div class="col-sm-6 col-md-12">
                                <!-- Stats -->
                                <div
                                    class="d-flex justify-content-center flex-column"
                                    style="min-height: 10.5rem"
                                    >
                                    <h6 class="card-subtitle">Orders</h6>
                                    <span class="d-block display-4 text-dark mb-1 mr-3"
                                          >${totalOrders}</span
                                    >
                                    <span class="d-block text-danger">
                                        <i class="tio-trending-down mr-1"></i> -${totalOrders} (1.2%)
                                    </span>
                                </div>
                                <!-- End Stats -->
                            </div>
                        </div>
                        <!-- End Row -->
                    </div>
                </div>
                <!-- End Row -->
            </div>
            <!-- End Body -->
        </div>
        <!-- End Card -->

        <div class="row">
            <div class="col-lg-4 mb-3 mb-lg-5">
                <!-- Card -->
                <a class="card card-hover-shadow mb-4" href="${pageContext.request.contextPath}/products">
                    <div class="card-body">
                        <div class="media align-items-center">
                         

                            <div class="media-body">
                                <h3 class="text-hover-primary mb-1">Product</h3>
                                <span class="text-body">Manage products</span>
                            </div>

                            <div class="ml-2 text-right">
                                <i
                                    class="tio-chevron-right tio-lg text-body text-hover-primary"
                                    ></i>
                            </div>
                        </div>
                        <!-- End Row -->
                    </div>
                </a>
                <!-- End Card -->

                <!-- Card -->
                <a class="card card-hover-shadow mb-4" href="${pageContext.request.contextPath}/GetAllOrder">
                    <div class="card-body">
                        <div class="media align-items-center">
                           

                            <div class="media-body">
                                <h3 class="text-hover-primary mb-1">Orders</h3>
                                <span class="text-body">Manage orders</span>
                            </div>

                            <div class="ml-2 text-right">
                                <i
                                    class="tio-chevron-right tio-lg text-body text-hover-primary"
                                    ></i>
                            </div>
                        </div>
                        <!-- End Row -->
                    </div>
                </a>
                <!-- End Card -->

                <!-- Card -->
                <a class="card card-hover-shadow" href="${pageContext.request.contextPath}/products">
                    <div class="card-body">
                        <div class="media align-items-center">
                           

                            <div class="media-body">
                                <h3 class="text-hover-primary mb-1">User</h3>
                                <span class="text-body">Manage customers</span>
                            </div>

                            <div class="ml-2 text-right">
                                <i
                                    class="tio-chevron-right tio-lg text-body text-hover-primary"
                                    ></i>
                            </div>
                        </div>
                        <!-- End Row -->
                    </div>
                </a>
                <!-- End Card -->
            </div>

            <div class="col-lg-8 mb-3 mb-lg-5">
                <!-- Card -->
                <div class="card h-100">
                    <!-- Header -->
                    <div class="card-header">
                        <h4 class="card-header-title">Top products</h4>

                        <a class="btn btn-sm btn-ghost-secondary" href="#">View all</a>
                    </div>
                    <!-- End Header -->

                    <!-- Body -->
                    <div class="card-body-height">
                        <!-- Table -->
                        <div class="table-responsive">
                            <table
                                class="table table-borderless table-thead-bordered table-nowrap table-align-middle card-table"
                                >
                                <thead class="thead-light">
                                    <tr>
                                        <th scope="col">Item</th>
                                        <th scope="col">Discount</th>
                                        <th scope="col">Price</th>
                                        <th scope="col">Sold</th>
                                        <th scope="col">Quantity</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="product" items="${bestSellers}">
                                        <tr>
                                            <td>
                                                <!-- Media -->
                                                <a class="media align-items-center" href="#">
                                                    <img class="avatar mr-3" src="${pageContext.request.contextPath}/${product.imageURL}" alt="Image Description" />
                                                    <div class="media-body">
                                                        <h5 class="text-hover-primary mb-0">${product.fullName}</h5>
                                                    </div>
                                                </a>
                                                <!-- End Media -->
                                            </td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${product.discount > 0}">
                                                        <i class="tio-trending-up text-success mr-1"></i> ${product.discount}%
                                                    </c:when>
                                                    <c:otherwise>
                                                        <i class="tio-trending-down text-danger mr-1"></i> ${product.discount}%
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td>${product.price}</td>
                                            <td>${product.quantitySold}</td>
                                            <td>
                                                <h4 class="mb-0">${product.quantity}</h4>
                                            </td>
                                        </tr>
                                    </c:forEach>

                                </tbody>
                            </table>
                        </div>
                        <!-- End Table -->
                    </div>
                    <!-- End Body -->
                </div>
                <!-- End Card -->
            </div>
        </div>
        <!-- End Row -->

        <!-- Card -->

        <!-- End Card -->
    </div>
    <!-- End Content -->

    <!-- Footer -->

    <jsp:include page="./includes/footer.jsp" />

