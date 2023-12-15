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

</head>
<body>
    <%@ include file="./includes/header.jsp"%>
    

<h1>Your Shopping Cart</h1>



<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1>Checkout</h1>

<!-- Display the user info -->
<c:if test="${not empty user}">
    <p>User Name: ${user.firstName}</p>
    <p>Last Name: ${user.lastName}</p>

    <!-- Display other user info... -->
</c:if>

<!-- Display the payment method info or input boxes to enter card information -->
<c:choose>
    <c:when test="${not empty latestPayMethod and latestPayMethod.cardNumber != ''}">
        <p id='cardNumber'>Card Number: ${latestPayMethod.cardNumber}</p>
        <!-- Display other payment method info... -->

        <select id="paymentOption" onchange="displayCardForm()">
            <option value="currentCard">Use current card</option>
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
                *Card Number: <input type="number" maxlength="20" name="cardNumber"><br>
                *CVV: <input type="number" maxlength="3" name="cvv" ><br> 
                *Expiry Year (ex. "2024"): <input type="number" maxlength="2" name="expYear" ><br>
                *Expiry Month (ex. "05"): <input type="number" maxlength="2" name="expMonth" ><br>
                <input type="submit" value="Update Payment Method">

            </form>
            <p id="paymentUpdateMessage"></p>
        </div>

  

        <script>
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
                            
                            $('#cardNumber').text("Card Number: " + $('#paymentForm input[name="cardNumber"]').val());
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
    </c:when>
    <c:otherwise>
        <form action="yourActionURL" method="post">
            <label for="cardNumber">Card Number:</label><br>
            <input type="text" id="cardNumber" name="cardNumber"><br>
            <label for="expiryDate">Expiry Date:</label><br>
            <input type="text" id="expiryDate" name="expiryDate"><br>
            <label for="cvv">CVV:</label><br>
            <input type="text" id="cvv" name="cvv"><br>
            <input type="submit" value="Submit">
        </form>
    </c:otherwise>
</c:choose>




</body>
</html>