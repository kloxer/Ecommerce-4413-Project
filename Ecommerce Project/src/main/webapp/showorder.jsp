<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="model.*" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
<head>
    <title>Checkout</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="./js/formValidators.js"></script>
    <link rel="stylesheet" href="./css/checkout.css">

    <style>
        body {
            font-family: Arial, sans-serif; /* Use a modern font */
            background: #f9f9f9; /* Light grey background */
            color: #333; /* Dark grey text for better readability */
            line-height: 1.6; /* More space between lines */
        }
        
        .header {
            text-align: center; /* Center align the header text */
            margin-top: 20px; /* Add top margin */
        }
        
        .container {
            background: #fff; /* White background for the content */
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* Subtle shadow around the container */
            border-radius: 5px; /* Rounded corners */
            padding: 20px; /* Padding inside the container */
        }
        
        .table-container {
            overflow-x: auto; /* Allows table to scroll on small screens */
        }
        
        table {
            width: 100%; /* Full-width table */
            margin: 20px 0; /* Margin above and below the table */
            border-collapse: collapse; /* Collapse borders */
        }
        
        th,
        td {
            text-align: left; /* Align text to the left */
            padding: 12px 20px; /* Padding within cells */
            border-bottom: 1px solid #ddd; /* Light grey border below each row */
        }
        
        th {
            background-color: #007bff; /* Use a color that matches your site theme */
            color: #fff; /* White text on a colored background */
        }
        
        tr:nth-child(even) {
            background-color: #f2f2f2; /* Zebra-striping for rows */
        }
        
        tr:hover {
            background-color: #ddd; /* Highlight row on hover */
        }
        
    </style>
</head>
<body>
    <%@ include file="./includes/header.jsp"%>

    <h1>Your Order</h1>
    <p>Your oder has been submitted, view details below</p>

    <c:if test="${not empty user}">
        <p>First Name: ${user.firstName}</p>
        <p>Last Name: ${user.lastName}</p>
        <p>Payment used: ${paymentUsedForPurchase}</P>
        <p>Address used: ${addressUsedForPurchase.addressLine1} , ${addressUsedForPurchase.city}, ${addressUsedForPurchase.region}, ${addressUsedForPurchase.postalCode}, ${addressUsedForPurchase.country}</P>

        <!-- Display other user info... -->
    </c:if>




    <%
    // Get the cart from the session
    Cart cart = (Cart) session.getAttribute("cart");
    %>
<!-- Display the shopping cart items -->
<h2 class="header">Items purchased: </h2>

<table>
    <tr>
        <th>Product ID</th>
        <th>Name</th>
        <th>Price</th>
        <th>Quantity</th>
    </tr>


    <% for (Map.Entry<ProductDisplay, Integer> entry : cart.getItems().entrySet()) { %>
        <tr>
            <td><%= entry.getKey().getProdID() %></td>
            <td><%= entry.getKey().getpName() %></td>
            <td><%= entry.getKey().getPrice() %></td>
            <td><%= entry.getValue() %></td>
                <!-- //servlet updateQuantity Should be easy to implement, get cart, update quantity, return
                //and the same for deleteItem -->

        
        </tr>
    <% } %>

</table>
   
<!-- Clear the cart-->
    <%
cart.clearCart();
%>

</body>
</html>