<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="model.*" %>
    <%@ page import="java.util.Map" %>
    <!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Shopping Cart</title>
    <link rel="stylesheet" href="./css/shopexp.css">
</head>
<body>

    <%@ include file="./includes/header.jsp"%>

	<h1>Your Shopping Cart</h1>
	<div class="container">
	<div class="cart-items">
    <%
        // Get the cart from the session
        Cart cart = (Cart) session.getAttribute("cart");

        // Check if the cart is empty
        if (cart == null || cart.getItems().isEmpty()) {
    %>
        <p>Your cart is empty.</p>
        <% } else { %>

            <!-- Need for checkout -->

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
                        <td>
                            <!-- //servlet updateQuantity Should be easy to implement, get cart, update quantity, return
                            //and the same for deleteItem -->
                            <form action="updateQuantity" method="post"> 
                                <input type="hidden" name="prodID" value="<%= entry.getKey().getProdID() %>">
                                <input type="number" name="quantity" value="<%= entry.getValue() %>" min="1">
                                <input type="submit" value="Update">
                            </form>

                        </td>

                        <td>
                            <form action="deleteItem" method="post">
                                <input type="hidden" name="prodID" value="<%= entry.getKey().getProdID() %>">
                                <input type="submit" value="Delete">
                            </form>
                        </td>
                    </tr>
                <% } %>
        
            </table><br>
		</div>
		</div>
            <div class="cart-summary">
            <p>Total: <%= cart.getTotalPrice() %></p>

            <!-- Checkout button -->
            <form action="checkout" method="post">

                <input type="submit" value="Checkout">
            
            </form> <br>
            
        <% } %>



        <a href="products">
            <button>Shop for more products</button>
        </a>
        </div>
      



</body>
</html>
