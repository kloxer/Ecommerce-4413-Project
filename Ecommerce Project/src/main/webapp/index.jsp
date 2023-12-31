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
  <title>Display Items</title>
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
	<%@ include file="./includes/header.jsp"%>
    <!-- Tabs Container -->
	<div class="tabs is-centered">
		<ul>
			<strong><li>Sort by:</li></strong>

			<!-- Price sorting -->
			<li><a href="#" onclick="addQueryParam('sort', 'priceasc')">Price Ascending</a></li>
			<li><a href="#" onclick="addQueryParam('sort', 'pricedesc')">Price Descending</a></li>

			<!-- Name sorting-->
			<li><a href="#" onclick="addQueryParam('sort', 'nameasc')">Name Ascending</a></li>
			<li><a href="#" onclick="addQueryParam('sort', 'namedesc')">Name Descending</a></li>

			<strong><li>Category/Brand:</li></strong>
			<!-- Category Filtering -->
			<li><a href="#" onclick="addQueryParam('brand', 'Apple')">Apple</a></li>
			<li><a href="#" onclick="addQueryParam('brand', 'Samsung')">Samsung</a></li>
		</ul>

	</div>
	

	<h1>Catalogue of Our Items</h1>

<ul style="list-style-type: none; display: flex; flex-wrap: wrap; padding: 0; margin: 0;">
  <c:forEach var="item" items="${productDisplays}">
    <li style="flex-basis: calc(33.333% - 20px); margin: 10px; border: 1px solid #ccc; border-radius: 4px; box-shadow: 0 2px 4px rgba(0, 0, 0, .1); overflow: hidden;">
      <div style="height: 200px; width: 100%; display: flex; align-items: center; justify-content: center; background: #f9f9f9;">
        <img src="./images/${item.id}.jpg" alt="${item.pName}" style="max-height: 100%; max-width: 100%;">
      </div>
      <div style="padding: 15px; text-align: center;">
        <h3 style="margin-top: 0;"><a href="ItemServlet?id=${item.id}">${item.pName}</a></h3>
        <p>${item.pDescription}</p>
        <p>Quantity: ${item.quantityRemaining}</p>
        <p>Price: $${item.price}</p>
      </div>
    </li>
  </c:forEach>
</ul>

<script>
	//Script to change query parameters for filtering
    function addQueryParam(key, value) {
        const url = new URL(window.location.href);
        url.searchParams.set(key, value);
        window.location.href = url.toString();
    }
</script>



</body>
</html>