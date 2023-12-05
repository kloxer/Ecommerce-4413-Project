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
		UPM ID: <input type="text" name="upmId" value="${userPaymentMethod.upmID}" readonly><br> 
		User ID: <input type="text" name="userId" value="${userPaymentMethod.userID}" readonly><br>
		Payment Type ID: <input type="text" name="paymentTypeId" value="${userPaymentMethod.paymentTypeID}"><br> 
		Card Provider: <input type="text" name="cardProvider" value="${userPaymentMethod.cardProvider}"><br> 
		Account Number: <input type="text" name="accountNumber" value="${userPaymentMethod.accountNumber}"><br>
		Expiry Date: <input type="text" name="expiryDate" value="${userPaymentMethod.expiryDate}"><br> 
		<input type="submit" value="Update">
	</form>
	<br><br>
 
</body>
</html>