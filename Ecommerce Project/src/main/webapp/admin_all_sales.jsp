<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>All User Sales</title>
    <link rel="stylesheet" href="./css/updateform.css">
    <script src="./js/formValidators.js"></script>

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
 	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ include file="./includes/header.jsp"%>


    <div class="account-sections">
        <ul>
            <li ><a href="./admin">Account Maintenance</a></li>
            <li><a href="./admin?section=inventory">Inventory Maintenance</a></li>
            <li class="is-active"> <a href="./admin?section=sales">Sales History</a></li>
        </ul>
    </div>
    
    <h1 style="text-align: center;">All orders in the system</h1>
	    <c:if test="${empty allPurchases}">
            <h2 style="text-align: center;">There are no orders in the system!</h2>
        </c:if>

    <!-- Show all orders for current user-->
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
