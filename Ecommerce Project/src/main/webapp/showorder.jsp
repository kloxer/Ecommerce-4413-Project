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
        .container {
            width: 80%; /* Or any width you prefer */
            max-width: 1000px; /* Maximum width of the container */
            margin: 0 auto; /* Centers the container */
            padding: 20px; /* Optional: adds some padding inside the container */
        }
        
        .purchase table {
            width: 100%; /* Table fills the container */
            border-collapse: collapse;
            margin-bottom: 20px; /* Space between tables */
        }
        
        .purchase th,
        .purchase td {
            border: 1px solid #ddd;
            padding: 8px; /* Padding inside cells */
        }
        
        .purchase th {
            background-color: #f2f2f2;
            text-align: left;
        }
        
        /* Optional: Adds a background color to the table header row */
        .purchase thead tr {
            background-color: #e9e9e9;
        }
        
    </style>
</head>
<body>
    <%@ include file="./includes/header.jsp"%>

    <h1>Your Order</h1>
    <p>Your oder has been submitted, view details below</p>


    <div class="container">
    <c:forEach var="purchase" items="${allPurchases}">
        <div class="purchase">
            <table border="1">
                <caption><strong>Purchase ID: ${purchase.getPurchaseId()}</strong></caption>
                <tr>
                    <th>Date</th>
                    <td>${purchase.getDate()}</td>
                </tr>
                <tr>
                    <th>Total</th>
                    <td>$${purchase.getTotal()}</td>
                </tr>
                <tr>
                    <th>Status</th>
                    <td>${purchase.isFilled() ? 'Filled' : 'Pending'}</td>
                </tr>
                <tr>
                    <th colspan="2">Items</th>
                </tr>
                <c:forEach var="item" items="${purchase.getItems()}">
                    <tr>
                        <td>QTY: ${item.getQuantity()}</td>
                        <td>Item ID: ${item.getItemID()} at $${item.getPrice()}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <br/> <!-- Spacing between purchases -->
    </c:forEach>
</div>

</body>
</html>