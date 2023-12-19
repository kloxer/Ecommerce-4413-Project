<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css">
    <title>Sign In</title>
    <style>
    .is-color {
        background-color: #007B7D;
        color: white;
    }
</style>

<script src="./js/formValidators.js"></script>

</head>

<body>

<!-- <<<<<<< HEAD  -->
    <section class="section">
        <div class="container">
            <div class="columns is-centered">
                <div class="column is-half">
                    <div class="box">
                        <h1 class="title has-text-centered">Shelfless Shop</h1>                                  	
                       		<h2 class="title has-text-centered">Sign In</h2>

                       		<c:if test="${not empty error}">
					<p style="color: red;">${error}</p>
				</c:if>

                       		<form action="loginServlet" method="post" onsubmit="return validateLogin()">
                       		<!-- Sign In Form -->
			                 <!-- Username or Email Input -->
                            <div class="field">
                                <label class="label">Username</label>
                                <div class="control">
                                    <input class="input" id=username name="username" type="text" placeholder="Enter your username">
                                </div>
                            </div>

                            <!-- Password Input -->
                            <div class="field">
                                <label class="label">Password</label>
                                <div class="control">
                                    <input class="input" id=password name="password" type="password" placeholder="Enter your password">
                                </div>
                            </div>

                            <!-- Sign In Button -->
                            <div class="field">
                                <div class="control">
                                    <button class="button is-color is-fullwidth">Sign In</button>
                                </div>
                            </div>
                            <!-- Register Button -->
                            <div class="field">
                                <div class="control">
                                    <button id="registerButton" class="button is-color is-fullwidth">New Customer? Sign up!</button>
                                </div>
                            </div>
                                                    <!-- End Sign In Form -->
                           </form>
                                                   <p class="has-text-centered">
                            <a href="#">Forgot your password?</a>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </section>
<!-- ======= -->
	<%--  <%@ include file="./includes/header.jsp"%><br><br> -->--%> 
	
<!--  		<c:if test="${not empty error}">
			<p style="color: red;">${error}</p>
		</c:if>-->


	
<!-- >>>>>>> branch 'main' of https://github.com/kloxer/Ecommerce-4413-Project -->


       <!-- JavaScript to handle button click and redirect to register.jsp -->
    <script>
        document.getElementById("registerButton").addEventListener("click", function (event) {
                    // Prevent the form from being submitted
            event.preventDefault();
            // Redirect to register.jsp
            window.location.href = "register.jsp";
        });
    </script>

    <script defer src="https://use.fontawesome.com/releases/v5.3.1/js/all.js"></script>
</body>

</html>
