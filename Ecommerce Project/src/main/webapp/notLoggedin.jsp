<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.*" %>


<%@ page import="java.util.Map" %>



<!DOCTYPE html>
<html>
<head>
    <title>Checkout</title>
</head>
<body>
    <%@ include file="./includes/header.jsp"%>
    
    <p>You are not logged in.</p>
    <p>Click the button below to log in or register.</p>
    
    <form action="login.jsp" method="get">
        <input type="submit" value="Log In">
    </form>
    
    <form action="register.jsp" method="get">
        <input type="submit" value="Register">
    </form>
</body>
</html>