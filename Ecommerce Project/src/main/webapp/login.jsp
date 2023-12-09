<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" href="./css/form.css">
<script src="./js/formValidators.js"></script>
</head>
<body>

	<%@ include file="./includes/header.jsp"%><br><br>
	<form action="loginServlet" method="post" onsubmit="return validateLogin()">
		<c:if test="${not empty error}">
			<p style="color: red;">${error}</p>
		</c:if>
		<h2>Login</h2>
		Username: <input type="text" name="username" value="${empty username ? '' : username}"><br>
		Password: <input type="password" name="password"><br> <input
			type="submit" value="Login">
	</form>

</body>
</html>