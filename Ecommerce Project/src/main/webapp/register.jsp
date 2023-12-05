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
		<h2>Register</h2>
		First name: <input type="text" name="firstname"><br> 
		Last name: <input type="text" name="lastname"><br> 
		Phone: <input type="number" name="phone"><br> 
		Username: <input type="text" name="username"><br> 
		Password: <input type="password" name="password"><br> 
		<input type="submit" value="Register">
	</form>

</body>
</html>