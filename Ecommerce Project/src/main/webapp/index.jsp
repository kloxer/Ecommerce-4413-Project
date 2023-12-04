<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
<!--   <link rel="stylesheet" href="../css/header.css"> -->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css">
  <title>Display Items--Jon Doe</title>
      <style>
        /* Customize the active tab color */
        .tabs ul li.is-active a {
            color: forestgreen; /* Change to your desired color */
            border-bottom-color: forestgreen; /* Change to your desired color */    
        }

        /* Customize the underline color on hover */
        .tabs ul li:hover a {
            border-bottom-color: forestgreen; /* Change to your desired color */
        }
    </style>
</head>
<body>
    <!-- Tabs Container -->
    <div class="tabs is-centered">
        <ul>
            <!-- Home Tab -->
            <li class="is-active"><a href="#">Home</a></li>

            <!-- Products Tab -->
            <li><a href="#">Products</a></li>

            <!-- About Tab -->
            <li><a href="#">About</a></li>

            <!-- Blog Tab -->
            <li><a href="#">Blog</a></li>
        </ul>
    </div>
  <h1>Catalogue of Our Items</h1>
  <ul>
    <c:forEach var="item" items="${items}">
      <li>${item.name} - $ ${item.price}</li>
    </c:forEach>
  </ul>
</body>
</html>
