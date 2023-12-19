<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Required Info</title>
<link rel="stylesheet" href="./css/updateform.css">
<script src="./js/formValidators.js"></script>
</head>
<body>

	<%@ include file="./includes/header.jsp"%>

  	<form action="UpdateInfoServlet?type=new_account" method="post" onsubmit="return AddressAndPayValidate()">
    	<h2>New Account Additional Required Info</h2>
    	<h5>*Please fill out this form. Ensure all fields are completed and valid before ordering!*</h5>
    	
   	    <h3>Billing/Shipping Address Info</h3>
   	    <input type="hidden" name="AddId" value="${latestAddress.id}" readonly>
   	    Unit: <input type="text" name="unitNumber" value="${latestAddress.unitNumber}"><br>
   	    *Address Line 1: <input type="text" name="addressLine1" value="${latestAddress.addressLine1}"><br>
        *City: <input type="text" name="city" value="${latestAddress.city}"><br>
        *Region: <input type="text" name="region" value="${latestAddress.region}"><br>
        *Postal Code: <input type="text" name="postalCode" value="${latestAddress.postalCode}"><br>
        *Country: <input type="text" name="country" value="${latestAddress.country}"><br>
    	
    	<h3>Payment Info</h3>
    	<input type="hidden" name="upmId" value="${latestPayMethod.UPM_ID}" readonly>
    	<input type="hidden" name="userId" value="${latestPayMethod.user_id}" readonly>
    	*Card Provider: 
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
   	     
	<input type="submit" value="Submit">
    </form>


    </ul>
	<br><br>
 
</body>
</html>
