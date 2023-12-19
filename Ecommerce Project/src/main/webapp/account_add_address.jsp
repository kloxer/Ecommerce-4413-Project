<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Address to User</title>
    <link rel="stylesheet" href="./css/updateform.css">
    <script src="./js/formValidators.js"></script>
</head>
<body>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ include file="./includes/header.jsp" %>

     <div class="account-sections">
        <ul>
            <li><a href="./account">General Info</a></li>
            <li><a href="./account?section=addresses">Addresses</a></li>
            <li class="is-active"><a href="./account?section=addressadd">Add Address</a></li>
            <li><a href="./account?section=paymeth">Payment Method</a></li>
            <li><a href="./account?section=orders">My Orders</a></li>
        </ul>
    </div>

    <form action="UpdateInfoServlet?type=addressadd" method="post" onsubmit="return validateAddress()">
        <h2>Add Address</h2>

        <c:if test="${not empty msg}">
            <p style="color: 007B7D;">${msg}</p>
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
    <br><br>
</body>
</html>
