<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>User Payment Info</title>
<link rel="stylesheet" href="./css/updateform.css">
<script src="./js/formValidators.js"></script>
</head>
<body>

	<%@ include file="./includes/header.jsp"%>

	<form action="UpdateInfoServlet" method="post" onsubmit="return AddressAndPayValidate()">
    	<h2>New Account Information Form</h2>
    	<h5>*Please fill out this form. Ensure all fields are completed and valid before Ordering*</h5>
    	
   	    <h3>Billing/Shipping Address Info</h3>
   	    <input type="hidden" name="AddId" value="${latestAddress.id}" readonly>
   	    Unit Number: <input type="text" name="unitNumber" value="${latestAddress.unitNumber}"><br>
   	    *Address Line 1: <input type="text" name="addressLine1" value="${latestAddress.addressLine1}"><br>
        *City: <input type="text" name="city" value="${latestAddress.city}"><br>
        *Region: <input type="text" name="region" value="${latestAddress.region}"><br>
        *Postal Code: <input type="text" name="postalCode" value="${latestAddress.postalCode}"><br>
        *Country: <input type="text" name="country" value="${latestAddress.country}"><br>
    	
    	<h3>Payment Info</h3>
    	<input type="hidden" name="upmId" value="${latestPayMethod.UPM_ID}" readonly>
    	<input type="hidden" name="userId" value="${latestPayMethod.user_id}" readonly>
    	*Card Provider (Enter MasterCard, Visa, Amex, Discover or Other) <input type="text" name="cardProvider" value="${userPaymentMethod.cardProvider}"><br> 
    	*Card Number: <input type="number" maxlength="20" name="cardNumber" value="${latestPayMethod.cardNumber}"><br>
    	*CVV: <input type="number" maxlength="3" name="cvv" value="${latestPayMethod.CVV}"><br> 
    	*Expiry Year (ex. "2024"): <input type="number" maxlength="2" name="expYear" value="${latestPayMethod.exp_year}"><br>
   	    *Expiry Month (ex. "05"): <input type="number" maxlength="2" name="expMonth" value="${latestPayMethod.exp_month}"><br>
   	     
	<input type="submit" value="Update">
    </form>
	<br><br>
 
</body>
</html>
