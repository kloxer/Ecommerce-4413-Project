<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css">
    <script src="./js/formValidators.js"></script>

    <title>Register</title>
        <style>
    .is-color {
        background-color: #007B7D;
        color: white;
    }
</style>
</head>

<body>

<!-- <<<<<<< HEAD  -->
    <section class="section">
        <div class="container">
            <div class="columns is-centered">
                <div class="column is-half">
                    <div class="box has-background-white-ter">
                        <h1 class="title has-text-centered">Register</h1>
<!-- ======= -->
	<%-- -- <%@ include file="./includes/header.jsp"%><br> -->--%>
	<br>
	<!--  <form >
		<c:if test="${not empty error}">
			<p style="color: red;">${error}</p>
		</c:if>
		<h2>Register</h2>
    	First name: <input type="text" name="firstname" value="${empty firstname ? '' : firstname}"><br> 
    	Last name: <input type="text" name="lastname" value="${empty lastname ? '' : lastname}"><br> 
    	Phone number: <input type="text" name="phone" value="${empty phone ? '' : phone}"><br> 
    	Username: <input type="text" name="username" value="${empty username ? '' : username}"><br>
		Password: <input type="password" name="password"><br> 
		<input type="submit" value="Register"> <br>
		
	</form><br>-->
<!-- >>>>>>> branch 'main' of https://github.com/kloxer/Ecommerce-4413-Project -->

                        <!-- Register Form -->
                        <form action="registerServlet" method="post" onsubmit="return validateRegister()">
                            <!-- First Name Input -->
                            <div class="field">
                                <label class="label">First Name</label>
                                <div class="control">
                                    <input class="input" type="text" id=firstname name=firstname placeholder="Enter your first name">
                                </div>
                            </div>

                            <!-- Last Name Input -->
                            <div class="field">
                                <label class="label">Last Name</label>
                                <div class="control">
                                    <input class="input" type="text" id=lastname name=lastname placeholder="Enter your last name">
                                </div>
                            </div>
                            
                            <!-- Phone Input -->
                            <div class="field">
                                <label class="label">Phone Number</label>
                                <div class="control">
                                    <input class="input" type="text" id=phone name=phone placeholder="Enter your phone number">
                                </div>
                            </div>
                            
                            <!-- Username Input -->
                            <div class="field">
                                <label class="label">Username</label>
                                <div class="control">
                                    <input class="input" type="text" id=username name=username placeholder="Enter your username">
                                </div>
                            </div>
                            
                            <!-- Password Input -->
                            <div class="field">
                                <label class="label">Password</label>
                                <div class="control">
                                    <input class="input" type="password" id=password name=password placeholder="Enter your last name">
                                </div>
                            </div>


                            <!-- Register Button -->
                            <div class="field">
                                <div class="control">
                                    <button class="button is-color is-fullwidth">Register</button>
                                </div>
                            </div>
                        </form>
                        <!-- End Register Form -->
                    </div>
                </div>
            </div>
        </div>
    </section>

    <script defer src="https://use.fontawesome.com/releases/v5.3.1/js/all.js"></script>
</body>

</html>
