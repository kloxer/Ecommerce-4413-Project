<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>User Payment Info</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css">
<link rel="stylesheet" href="./css/form.css">
</head>
<body>

	<%@ include file="./includes/header.jsp"%>

	<div class="tabs is-centered">
		<ul>
			<li class="is-active"><a href="./account">Payment Info</a></li>
			<li><a href="#">Address Info</a></li>
			<li><a href="#">Account Info</a></li>
			<li><a href="#">Orders</a></li>
		</ul>
	</div>

	<form action="updatePaymentServlet" method="post" onsubmit="return validatePaymentUpdate()">
    	<h2>Update Payment Info Below</h2>
    	<h4>*Filling this and Address Info is mandatory before placing order.*</h4><br>
    	<input type="hidden" name="upmId" value="${userPaymentMethod.UPM_ID}" readonly>
    	<input type="hidden" name="userId" value="${userPaymentMethod.user_id}" readonly>
    	Card Provider: <input type="text" name="cardProvider" value="${userPaymentMethod.cardProvider}"><br> 
    	Card Number: <input type="number" maxlength="20" name="cardNumber" value="${userPaymentMethod.cardNumber}"><br>
    	CVV: <input type="number" maxlength="3" name="cvv" value="${userPaymentMethod.CVV}"><br> 
    	Expiry Year: <input type="number" maxlength="2" name="expYear" value="${userPaymentMethod.exp_year}"><br>
   	    Expiry Month: <input type="number" maxlength="2" name="expMonth" value="${userPaymentMethod.exp_month}"><br>
    <input type="submit" value="Update">
    </form>
	<br><br>
 
</body>
</html>
