<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>General Info</title>
    <link rel="stylesheet" href="./css/updateform.css">
    <script src="./js/formValidators.js"></script>
</head>
<body>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ include file="./includes/header.jsp"%>

     <div class="account-sections">
        <ul>
            <li class="is-active"><a href="./account">General Info</a></li>
            <li><a href="./account?section=addresses">Addresses</a></li>
            <li><a href="./account?section=addressadd">Add Address</a></li>
            <li><a href="./account?section=paymeth">Payment Method</a></li>
            <li><a href="./account?section=orders">My Orders</a></li>
        </ul>
    </div>

	<form action="UpdateInfoServlet?type=general" method="post" onsubmit="return GeneralInfoVal()">
		<c:if test="${not empty msg}">
			<p style="color: 007B7D;">${msg}</p>
		</c:if>

		<h2>General Info</h2>
		<input type="hidden" name="userId" value="${user.getUserId()}" readonly>
        *First Name: <input type="text" name="firstName" value="${user.getFirstName()}"><br>
        *Last Name: <input type="text" name="lastName" value="${user.getLastName()}"><br>
        *Phone Number: <input type="text" name="phoneNumber" value="${user.getPhoneNumber()}"><br>
	
	<input type="submit" value="Update General Info">
         
    </form>
    <br><br>
 
</body>
</html>
