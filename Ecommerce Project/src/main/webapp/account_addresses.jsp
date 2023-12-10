<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Addresses</title>
    <link rel="stylesheet" href="./css/updateform.css">
    <script src="./js/formValidators.js"></script>
</head>
<body>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ include file="./includes/header.jsp" %>

     <div class="account-sections">
        <ul>
            <li><a href="./account">General Info</a></li>
            <li class="is-active"><a href="./account?section=addresses">Addresses</a></li>
            <li><a href="./account?section=addressadd">Add Address</a></li>
            <li><a href="./account?section=paymeth">Payment Method</a></li>
            <li><a href="./account?section=orders">My Orders</a></li>
        </ul>
    </div>

 	<form action="UpdateInfoServlet?type=address" method="post" onsubmit="return validateAddress()">
        <h2>Edit Address</h2>

        <input type="hidden" id="addrList" name="addrList" value='${addrListJson}'> <!-- Assuming addrListJson contains serialized addrList -->

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
        </select>
        <button type="button" id="pickbutton">Select Address</button> <br>
        
        <c:if test="${not empty msg}">
			<p style="color: green;">${msg}</p>
		</c:if>
        
        <br>
        <input type="hidden" name="userId" value="${user.getUserId()}" readonly>
        <input type="hidden"  id="addressId" name="addressId" value="${latestAddress.id}" readonly><br>

        <label for="unitNumber">Unit:</label>
        <input type="text" id="unitNumber" name="unitNumber" value="${latestAddress.unitNumber}"><br>

        <label for="addressLine1">*Address:</label>
        <input type="text" id="addressLine1" name="addressLine1" value="${latestAddress.addressLine1}"><br>

        <label for="city">*City:</label>
        <input type="text" id="city" name="city" value="${latestAddress.city}"><br>

        <label for="region">*Region:</label>
        <input type="text" id="region" name="region" value="${latestAddress.region}"><br>

        <label for="postalCode">*Postal Code:</label>
        <input type="text" id="postalCode" name="postalCode" value="${latestAddress.postalCode}"><br>

        <label for="country">*Country:</label>
        <input type="text" id="country" name="country"  value="${latestAddress.country}"><br>

        <input type="submit" value="Update Address">
    </form>
    <br><br>
    
      <script>
     	 //Change inputs when someone picks an address
        document.getElementById('pickbutton').addEventListener('click', function() {
            var select = document.getElementById('addresses');
            var selectedAddress = select.options[select.selectedIndex];

            // Obtain data attributes from selected address
            var addressId = selectedAddress.value;
            var unitNumber = selectedAddress.getAttribute('data-data');
            var addressLine1 = selectedAddress.getAttribute('data-address');
            var city = selectedAddress.getAttribute('data-city');
            var region = selectedAddress.getAttribute('data-region');
            var postalCode = selectedAddress.getAttribute('data-postal');
            var country = selectedAddress.getAttribute('data-country');

            // Fill input fields
            document.getElementById('addressId').value = addressId;
            document.getElementById('unitNumber').value = unitNumber;
            document.getElementById('addressLine1').value = addressLine1;
            document.getElementById('city').value = city;
            document.getElementById('region').value = region;
            document.getElementById('postalCode').value = postalCode;
            document.getElementById('country').value = country;
        });
    </script>

</body>
</html>
