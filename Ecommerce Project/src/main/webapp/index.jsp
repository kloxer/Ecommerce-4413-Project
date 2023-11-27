<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Display Items--Jon</title>
</head>
<body>
  <%@ include file="./includes/header.jsp" %>
  <h1>Catalogue of Our Items</h1>
  <ul>
    <c:forEach var="item" items="${items}">
      <li>${item.name} - $ ${item.price}</li>
    </c:forEach>
  </ul>
</body>
</html>
