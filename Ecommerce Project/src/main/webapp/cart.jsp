<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.*" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Shopping Cart</title>
</head>
<body>
    <%@ include file="./includes/header.jsp"%>

    <h1>Your Shopping Cart</h1>
    <%
        // Get the cart from the session
        Cart cart = (Cart) session.getAttribute("cart");

        // Check if the cart is empty
        if (cart == null || cart.getItems().isEmpty()) {
    %>
        <p>Your cart is empty.</p>
        <% } else { %>
            <table>
                <tr>
                    <th>Product ID</th>
                    <th>Name</th>
                    <th>Price</th>
                </tr>
                <% for (ProductDisplay item : cart.getItems()) { %>
                    <tr>
                        <td><%= item.getProdID() %></td>
                        <td><%= item.getpName() %></td>
                        <td><%= item.getPrice() %></td>
                    </tr>
                <% } %>
            </table>
            <p>Total: <%= cart.getTotalPrice() %></p>
        <% } %>

        <a href="products">
            <button>Shop for more products</button>
        </a>

        </body>
        </html>