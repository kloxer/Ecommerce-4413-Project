<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="model.*" %>


<!DOCTYPE html>
<html>
<head>
    <title>Checkout</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="./js/formValidators.js"></script>
    <link rel="stylesheet" href="./css/checkout.css">
    <style>
/* checkout-styles.css */

body {
    background-color: #f2f2f2;
    font-family: 'Arial', sans-serif;
    margin: 0;
    padding: 0;
}

#checkoutContainer {
    display: flex;
    justify-content: space-between;
    padding: 20px;
}

#leftSide {
    width: 60%;
    padding: 20px;
    background-color: #ffffff;
    border-radius: 5px;
    box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
}

.header {
    color: #007B7D;
}

/* Customize other styles as needed */
#rightSide {
    width: 35%;
    padding: 20px;
    background-color: #ffffff;
    border-radius: 5px;
    box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
}

table {
    width: 100%;
    border-collapse: collapse;
    margin-top: 20px;
}

th, td {
    border: 1px solid #ddd;
    padding: 8px;
    text-align: left;
}

th {
    background-color: #007B7D;
    color: #ffffff;
}

#orderForm {
    margin-top: 20px;
}

#checkoutButton {
    background-color: #007B7D;
    color: #ffffff;
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}

#checkoutButton:hover {
    background-color: #1d5bbf;
}

/* Additional styles for the form-like box */
.form-like-box {
    background-color: #ffffff;
    padding: 20px;
    border-radius: 5px;
    box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
    margin-bottom: 20px;
}

.form-like-box p {
    color: #3273dc;
}

.form-like-box label {
    color: #007B7D;
    display: block;
    margin-bottom: 8px;
}

.form-like-box select,
.form-like-box input {
    width: 100%;
    padding: 8px;
    margin-bottom: 10px;
    box-sizing: border-box;
}

    </style>
</head>
<body>
   <%--  <%@ include file="./includes/header.jsp"%>--%>
    




<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="checkoutContainer">
<div id="leftSide">
<h2 class="header">Checkout</h2>


<!-- Display the user info -->
<c:if test="${not empty user}">
    <p>First Name: ${user.firstName}</p>
    <p>Last Name: ${user.lastName}</p>

    <!-- Display other user info... -->
</c:if>

<!-- Display the payment method info or input boxes to enter card information -->


        <!-- Display other payment method info... -->
        <c:choose>
            <c:when test="${empty latestPayMethod or empty addrList or latestPayMethod.cardNumber== '' or addrList[0].addressLine1 == ''}">
                <p>Either payment method or address is not on file. </p>
                <p>Please go to <a href="account">Account</a> and add the information required.</p>            
            </c:when>
            <c:otherwise>

        <label for="cards">Choose a card:</label>
        <select id="paymentOption" name ='cardNum' onchange="displayCardForm()">
            <option id ="currentCard" value="${latestPayMethod.cardNumber}">${latestPayMethod.cardNumber}</option>
            <option value="newCard">Enter new card</option>
        </select>

        <div id="cardForm" style="display: none;">
            <form id="paymentForm" action="ajaxUpdatePayment" method="post" onsubmit="submitForm(event)">
                <input type="hidden" name="upmId" value="${latestPayMethod.UPM_ID}" readonly>
                <input type="hidden" name="userId" value="${latestPayMethod.user_id}" readonly>
                *Card Provider
                <select name="cardProvider">
                <option value="MasterCard" ${latestPayMethod.cardProvider == 'MasterCard' ? 'selected' : ''}>MasterCard</option>
                <option value="Visa" ${latestPayMethod.cardProvider == 'Visa' ? 'selected' : ''}>Visa</option>
                <option value="Amex" ${latestPayMethod.cardProvider == 'Amex' ? 'selected' : ''}>Amex</option>
                <option value="Discover" ${latestPayMethod.cardProvider == 'Discover' ? 'selected' : ''}>Discover</option>
                <option value="Other" ${latestPayMethod.cardProvider == 'Other' ? 'selected' : ''}>Other</option>
                </select> <br> 
                *Card Number: <input id="cN" type="number" maxlength="20" name="cardNumber"><br>
                *CVV: <input type="number" maxlength="3" name="cvv" ><br> 
                *Expiry Year (ex. "2024"): <input type="number" maxlength="2" name="expYear" ><br>
                *Expiry Month (ex. "05"): <input type="number" maxlength="2" name="expMonth" ><br>
                <input type="submit" value="Update Payment Method">

            </form>
            <p id="paymentUpdateMessage"></p>
        </div>
            <br>
            <!-- Display the user's addresses -->
            <input type="hidden" id="addrList" name="addrList" value='${addrListJson}'>

            <label for="addresses">Choose an address:</label>
            <select id="addresses" name="addressId">
                <c:forEach items="${addrList}" var="address">
                    <option value="${address.id}" 
                        data-unit="${address.unitNumber}"
                        data-address="${address.addressLine1}"
                        data-city="${address.city}"
                        data-region="${address.region}"
                        data-postal="${address.postalCode}"
                        data-country="${address.country}">
                        ${address.addressLine1}, ${address.city}, ${address.region}, ${address.postalCode}, ${address.country}
                    </option>
                </c:forEach>
                <option value="new">Add New Address</option>
            </select>
            <br>

        <!-- Add the address form here -->
        <div id="cardAddressForm">
            <form id="addAddressForm" action="ajaxAddAddress" method="post" onsubmit="submitAddress(event)">
                <!-- Old form validation validateAddress() -->
        
                <c:if test="${not empty msg}">
                    <p style="color: green;">${msg}</p>
                </c:if>
        
                <input type="hidden" name="userId" value="${user.getUserId()}" readonly><br>
        
                <label for="unitNumber">Unit:</label>
                <input type="text" id="unitNumber" name="unitNumber"><br>
        
                <label for="addressLine1">*Address:</label>
                <input type="text" id="addressLine1" name="addressLine1"><br>
        
                <label for="city">*City:</label>
                <input type="text" id="city" name="city"><br>
        
                <label for="region">*Region:</label>
                <input type="text" id="region" name="region"><br>
        
                <label for="postalCode">*Postal Code:</label>
                <input type="text" id="postalCode" name="postalCode"><br>
        
                <label for="country">*Country:</label>
                <input type="text" id="country" name="country"><br>
        
                <input type="submit" value="Add Address">
            </form>

            <p id="addressUpdate"></p>
            <!-- Your form goes here -->
        </div>

  

      
   
</div>

<div id="rightSide">

    <%
    // Get the cart from the session
    Cart cart = (Cart) session.getAttribute("cart");
    %>
<!-- Display the shopping cart items -->
<h2 class="header">Your Shopping Cart</h2>

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




</div>

<form id="orderForm" action="checkoutServlet" method="post">

    <button id="checkoutButton" type="submit" onclick="submitOrder(event)">Submit Order</button>
</form>


</div>
</c:otherwise>
</c:choose>

  <script>

    // Function to submit the form normally for order
    document.getElementById('orderForm').addEventListener('submit', function(e) {
        e.preventDefault(); // Prevent the form from submitting normally

        // Get the selected card and address
        var card = document.querySelector('select[name="cardNum"]').value;
        var address = document.querySelector('select[name="addressId"]').value;
    
        // Create hidden input fields for the card and address
        var cardInput = document.createElement('input');
        cardInput.type = 'hidden';
        cardInput.name = 'paymentInfo';
        cardInput.value = card;
    
        var addressInput = document.createElement('input');
        addressInput.type = 'hidden';
        addressInput.name = 'addressInfo';
        addressInput.value = address;
    
        // Append the hidden input fields to the form
        this.appendChild(cardInput);
        this.appendChild(addressInput);

        // Submit the form programmatically
        this.submit();
    });











                // Get the select element and the form
            var selectElement = document.getElementById('addresses');
            var formElement = document.getElementById('cardAddressForm');
            
                // Add an event listener to the select element
                selectElement.addEventListener('change', function() {
                    // Check if the "Add New Address" option is selected
                    if (selectElement.value === 'new') {
                        // If it is, show the form
                        formElement.style.display = 'block';
                    } else {
                        // If it's not, hide the form
                        formElement.style.display = 'none';
                    }
                });
                    
                

            // Function to submit the form via AJAX
            function submitForm(event) {
                event.preventDefault();  // Prevent the default form submission
                
                var isValid = validatePaymentMeth();

                if (isValid == true){
                    var formData = $('#paymentForm').serialize();  // Serialize the form data

                    $.ajax({
                        type: 'POST',
                        url: 'ajaxUpdatePayment',
                        data: formData,
                        success: function(response) {
                            // Update a part of the checkout.jsp page with the response
                            $('#paymentUpdateMessage').text(response);
                            
                            var cardnum = $('#cN').val();
                            // Update the value and text of the #currentCard option
                            $('#currentCard').val(cardnum);
                            $('#currentCard').text(cardnum);

                            // Set the new card as the selected option
                            $('#paymentOption').val(cardnum);




                        }
                    });
                }
                
            }



            // Function to submit the address via AJAX
            function submitAddress(event) {
                event.preventDefault();  // Prevent the default form submission
                
                var isValid = validateAddress();

                if (isValid == true){
                    var formData = $('#addAddressForm').serialize();  // Serialize the form data

                    $.ajax({
                        
                        type: 'POST',
                        url: 'ajaxAddAddress',
                        data: formData,
                        success: function(response) {
                            // Update a part of the checkout.jsp page with the response
                            $('#addressUpdate').text(response);
                            var addr = $('#addressLine1').val();
                            var city = $('#city').val();
                            var region = $('#region').val();
                            var postalCode = $('#postalCode').val();
                            var country = $('#country').val();
                            var newAddress = addr + ', ' + city + ', ' + region + ', ' + postalCode + ', ' + country;

                            var newOption = new Option(newAddress, newAddress);

                            $('#addresses').append(newOption);
                            $(newOption).prop('selected', true);
                        }
                    });
                }
                
            }



            function displayCardForm() {
                var paymentOption = document.getElementById("paymentOption").value;
                if (paymentOption == "newCard") {
                    document.getElementById("cardForm").style.display = "block";
                } else {
                    document.getElementById("cardForm").style.display = "none";
                }
            }


        </script>


</body>
</html>