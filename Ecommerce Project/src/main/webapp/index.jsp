<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css">
  <title>Display Items</title>
      <style>
        /* Customize the active tab color */
        .tabs ul li.is-active a {
            color: forestgreen; /* Change to your desired color */
            border-bottom-color: #007B7D; /* Change to your desired color */    
        }

        /* Customize the underline color on hover */
        .tabs ul li:hover a {
            border-bottom-color: #007B7D; /* Change to your desired color */
        }
            h1 {
  font-size: 20px;
}
    </style>
</head>
<body>
<!--  <<<<<<< HEAD-->

  <!-- Grid Container -->
  <div class="column is-full">

<!-- ======= -->
	<%@ include file="./includes/header.jsp"%>
<!--  <<<<<<< HEAD-->
<!-- >>>>>>> branch 'main' of https://github.com/kloxer/Ecommerce-4413-Project -->
    <!-- Tabs Container
    <div class="column is-three-fifths is-offset-one-fifth">
        <div class="tabs is-centered">  -->
        <!-- Added is-centered class 
            <ul>-->
                <!-- Home Tab 
                <li class="is-active"><a href="#">Home</a></li>-->
<!--  =======-->
    <!-- Tabs Container -->
	<div class="tabs is-centered">
		<ul>
			<strong><li>Sort by:</li></strong>
<!-- >>>>>>> branch 'main' of https://github.com/kloxer/Ecommerce-4413-Project

<<<<<<< HEAD -->
                <!-- Products Tab 
                <li><a href="#">Products</a></li>-->
<!-- ======= -->
			<!-- Price sorting -->
			<li><a href="#" onclick="addQueryParam('sort', 'priceasc')">Price Ascending</a></li>
			<li><a href="#" onclick="addQueryParam('sort', 'pricedesc')">Price Descending</a></li>
<!--  >>>>>>> branch 'main' of https://github.com/kloxer/Ecommerce-4413-Project

<<<<<<< HEAD-->
                <!-- About Tab 
                <li><a href="#">About</a></li>-->
<!--  =======-->
			<!-- Name sorting-->
			<li><a href="#" onclick="addQueryParam('sort', 'nameasc')">Name Ascending</a></li>
			<li><a href="#" onclick="addQueryParam('sort', 'namedesc')">Name Descending</a></li>
<!-- >>>>>>> branch 'main' of https://github.com/kloxer/Ecommerce-4413-Project

<<<<<<< HEAD -->
                <!-- Blog Tab
                <li><a href="#">Blog</a></li>
                
                
            </ul>
        </div>
    </div>    -->
<!-- <<<<<<< HEAD -->

    <!-- Search Input Container 
    <div class="column is-one-fifth ">
        <div class="tabs is-right">
            <ul>
                <li class="is-right">
                    <div class="control">
                        <input class="input" type="text" placeholder="Search">
                    </div>
                </li>
                <li><a href="#">Cart</a></li>
            </ul>
        </div>
    </div>-->

  </div>

  <h1>Catalogue of Our Phones</h1>
  <ul>
    <c:forEach var="item" items="${items}">
      <li>${item.name} - $ ${item.price}</li>
    </c:forEach>
  </ul>
<!-- ======= -->
    
<!--  =======-->
			<strong><li>Category/Brand:</li></strong>
			<!-- Category Filtering -->
			<li><a href="#" onclick="addQueryParam('brand', 'Apple')">Apple</a></li>
			<li><a href="#" onclick="addQueryParam('brand', 'Samsung')">Samsung</a></li>
		</ul>

	</div>
	

	<h1>Catalogue of Our Items</h1>

<!-- >>>>>>> branch 'main' of https://github.com/kloxer/Ecommerce-4413-Project -->
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



<!--  >>>>>>> branch 'main' of https://github.com/kloxer/Ecommerce-4413-Project-->
</body>
</html>
