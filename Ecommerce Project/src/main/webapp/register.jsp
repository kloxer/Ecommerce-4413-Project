<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
<link rel="stylesheet" href="./css/form.css">
<script src="./js/formValidators.js"></script>
</head>
<body>

	<%@ include file="./includes/header.jsp"%><br>
	<br>
	<form action="registerServlet" method="post"
		onsubmit="return validateRegister()">
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
		
	</form><br>

</body>
</html>