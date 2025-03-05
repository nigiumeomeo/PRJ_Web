<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <!-- Required Meta Tags Always Come First -->
        <meta charset="utf-8" />
        <meta
            name="viewport"
            content="width=device-width, initial-scale=1, shrink-to-fit=no"
            />

        <!-- Title -->
        <title>FRUIT SHOP | Dashboard Admin</title>

        <!-- Favicon -->
        <link rel="shortcut icon" href="favicon.ico" />

        <!-- Font -->
        <link
            href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&display=swap"
            rel="stylesheet"
            />

        <!-- CSS Implementing Plugins -->
        <link rel="stylesheet" href="assets\css\vendor.min.css" />
        <link rel="stylesheet" href="assets\vendor\icon-set\style.css" />

        <!-- CSS Front Template -->
        <link rel="stylesheet" href="assets\css\theme.min.css?v=1.0" />

        <style>
            body {
                background: linear-gradient(to right, #c5fdf2, #bdaef5);
                font-family: Arial, sans-serif;
            }
            .profile-card {
                max-width: 600px;
                margin: 50px auto;
                background-color: #ffffff;
                border: 1px solid #ddd;
                border-radius: 8px;
                box-shadow: 0 0 20px rgba(0,0,0,0.1);
                padding: 30px;
            }
            .profile-img {
                width: 150px;
                height: 150px;
                border-radius: 50%;
                object-fit: cover;
                margin-bottom: 20px;
                border: 5px solid #fff;
                box-shadow: 0 0 10px rgba(0,0,0,0.1);
            }
            .profile-name {
                font-size: 28px;
                font-weight: bold;
                color: #333;
                margin-bottom: 10px;
            }
            .profile-email {
                color: #6c757d;
                margin-bottom: 20px;
            }
            .profile-details {
                border-top: 1px solid #ddd;
                padding-top: 20px;
            }
            .profile-details h4 {
                margin-bottom: 15px;
                font-size: 22px;
                color: #333;
            }
            .profile-details ul {
                list-style-type: none;
                padding-left: 0;
            }
            .profile-details ul li {
                padding: 5px 0;
                color: #555;
            }
            .btn-edit-profile {
                background-color: #007bff;
                color: #ffffff;
                border: none;
                padding: 8px 20px;
                border-radius: 5px;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }
            .btn-edit-profile:hover {
                background-color: #0056b3;
            }
            .edit-profile-form {
                display: none;
                margin-top: 20px;
            }
        </style>
    </head>

    <body class="footer-offset">
        <script src="assets\vendor\hs-navbar-vertical-aside\hs-navbar-vertical-aside-mini-cache.js"></script>

        <jsp:include page="../includes/navbar.jsp" />

        <main id="content" role="main" class="main">
            <div class="content container-fluid">

                <div class="profile-card">
                    <div class="text-center">
                        <img src="${pageContext.request.contextPath}/${user.avatarURL}" alt="Profile Picture" class="profile-img">
                        <div class="profile-name">${user.firstName} ${user.lastName}</div>
                   
                    </div>
                    <div class="edit-profile-form" id="editProfileForm">
                        <form method="post" action="EditUserServlet">
                            <input hidden="true" id="userId" name="id" value="${user.id}" />

                            <div class="form-group">
                                <label for="firstName">First Name:</label>
                                <input type="text" class="form-control" id="firstName" name="firstName" value="${user.firstName}">
                            </div>
                            <div class="form-group">
                                <label for="lastName">Last Name:</label>
                                <input type="text" class="form-control" id="lastName" name="lastName" value="${user.lastName}">
                            </div>
                            <div class="form-group">
                                <label for="address">Address:</label>
                                <input type="text" class="form-control" id="address" name="address" value="${user.address}">
                            </div>
                            <div class="form-group">
                                <label for="role">Role:</label>
                                <input type="text" class="form-control" id="role" name="role" value="${user.role}">
                            </div>
                            <div class="form-group">
                                <label for="avatarURL">Avatar URL:</label>
                                <input type="text" class="form-control" id="avatarURL" name="avatarURL" value="${pageContext.request.contextPath}/${user.avatarURL}">
                            </div>
                            <button type="submit" class="btn btn-primary">Save Changes</button>
                        </form>
                    </div>
                    <div class="profile-details">
                        <h4>Profile Details</h4>
                        <ul>
                            <li><strong>First Name:</strong> ${user.firstName}</li>
                            <li><strong>Last Name:</strong> ${user.lastName}</li>
                            <li><strong>Address:</strong> ${user.address}</li>
                            <li><strong>Role:</strong> ${user.role}</li>
                        </ul>
                    </div>
                </div>
            </div>
        </main>

        <jsp:include page="../includes/footer.jsp" />
        <script>
            document.getElementById('btnEditProfile').addEventListener('click', function () {
                // Toggle display of edit profile form
                var editForm = document.getElementById('editProfileForm');
                editForm.style.display = (editForm.style.display === 'block') ? 'none' : 'block';
                // Update form fields with current profile details (if needed)
                var fullName = document.getElementById('fullName');
                var age = document.getElementById('age');
                var location = document.getElementById('location');
                var interests = document.getElementById('interests');
                // Replace default values with actual profile data here
                fullName.value = "User Full Name";
                age.value = "30";
                location.value = "City, Country";
                interests.value = "Reading, Traveling";
            });
        </script>
