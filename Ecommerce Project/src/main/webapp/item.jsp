<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String itemId = request.getParameter("id"); //itemID sent from index.jsp, find details of items
    // Use the itemId to fetch the item details from the server
    // For example, you might have a method like getItemDetails(itemId) that returns an Item object
    // Item item = getItemDetails(itemId);

%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
<!--   <link rel="stylesheet" href="../css/header.css"> -->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css">
  <link rel="stylesheet" href="./css/shopexp.css">
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
	<%@ include file="./includes/header.jsp"%>

    <!DOCTYPE html>
    <html>
    <head>
        <meta charset="ISO-8859-1">
        <title>Item Details</title>
    </head>
    <body>
	<div class="container">
		<div class="columns">
			<div class="image-frame">
				<img class="product-image" src="./images/${item.id}.jpg"
					alt="${item.pName}">
			</div>
			<div class="column">
				<h1 class="title">${item.pName}</h1>
				<p>ID: ${item.prodID}</p>
				<p>Name: ${item.pName}</p>
				<p>Description: ${item.pDescription}</p>
				<p>Quantity Remaining: ${item.quantityRemaining}</p>
				<p class="price">Price: ${item.price}</p>

				<form action="addToCartServlet" method="post">
					<input type="hidden" name="prodID" value="${item.prodID}">
					<input class="addcartbutton" type="submit" value="Add to Cart">
				</form>
			</div>
		</div>
	</div>
</body>
    </html>
