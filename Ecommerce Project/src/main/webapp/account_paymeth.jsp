<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Payment Method</title>
    <link rel="stylesheet" href="./css/updateform.css">
    <script src="./js/formValidators.js"></script>
</head>
<body>
 	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ include file="./includes/header.jsp"%>

     <div class="account-sections">
        <ul>
            <li><a href="./account">General Info</a></li>
            <li><a href="./account?section=addresses">Addresses</a></li>
            <li><a href="./account?section=addressadd">Add Address</a></li>
            <li class="is-active"><a href="./account?section=paymeth">Payment Method</a></li>
            <li><a href="./account?section=orders">My Orders</a></li>
        </ul>
    </div>

	<form action="UpdateInfoServlet?type=paymeth" method="post" onsubmit="return validatePaymentMeth()">
    <h2>Your Payment Method</h2>
    <c:if test="${not empty msg}">
        <p style="color: green;">${msg}</p>
    </c:if>
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
    	*Card Number: <input type="number" maxlength="20" name="cardNumber" value="${latestPayMethod.cardNumber}"><br>
    	*CVV: <input type="number" maxlength="3" name="cvv" value="${latestPayMethod.CVV}"><br> 
    	*Expiry Year (ex. "2024"): <input type="number" maxlength="2" name="expYear" value="${latestPayMethod.exp_year}"><br>
   	    *Expiry Month (ex. "05"): <input type="number" maxlength="2" name="expMonth" value="${latestPayMethod.exp_month}"><br>
    <input type="submit" value="Update Payment Method">
    </form>
    
	<br><br>
 
</body>
</html>
