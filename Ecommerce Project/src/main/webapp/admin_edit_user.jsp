<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin - Edit User Info </title>
    <link rel="stylesheet" href="./css/updateform.css">
    <script src="./js/formValidators.js"></script>
</head>
<body>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ include file="./includes/header.jsp"%>

    <div class="account-sections">
        <ul>
            <li class="is-active"><a href="./admin">Account Maintenance</a></li>
            <li><a href="./admin?section=inventory">Inventory Maintenance</a></li>
            <li><a href="./admin?section=sales">Sales History</a></li>
        </ul>
    </div>
    
    <c:if test="${not empty msg}">
        <center> <h3 style="color: green;">${msg}</h3></center>
    </c:if>

	<!-- Form to Update General Info of Selected User -->
    <form action="AdminUpdateServlet?type=general" method="post" onsubmit="return GeneralInfoVal()">

        <h2>Edit General Info (For User ID: ${selectedUser.getUserId()})</h2>
        <input type="hidden" name="userId" value="${selectedUser.getUserId()}" readonly>
        *First Name: <input type="text" name="firstName" value="${selectedUser.getFirstName()}"><br>
        *Last Name: <input type="text" name="lastName" value="${selectedUser.getLastName()}"><br>
        *Phone Number: <input type="text" name="phoneNumber" value="${selectedUser.getPhoneNumber()}"><br>
    
        <input type="submit" value="Update General Info">
    </form><br>
    
    <!-- Form to Update Payment Method of Selected User -->
    	<form action="AdminUpdateServlet?type=paymeth" method="post" onsubmit="return validatePaymentMeth()">
    <h2>User's Payment Method</h2>
    	<input type="hidden" name="upmId" value="${latestPaymentMethod.UPM_ID}" readonly>
    	<input type="hidden" name="userId" value="${latestPaymentMethod.user_id}" readonly>
    	*Card Provider
    	<select name="cardProvider">
        <option value="MasterCard" ${latestPaymentMethod.cardProvider == 'MasterCard' ? 'selected' : ''}>MasterCard</option>
        <option value="Visa" ${latestPaymentMethod.cardProvider == 'Visa' ? 'selected' : ''}>Visa</option>
        <option value="Amex" ${latestPaymentMethod.cardProvider == 'Amex' ? 'selected' : ''}>Amex</option>
        <option value="Discover" ${latestPaymentMethod.cardProvider == 'Discover' ? 'selected' : ''}>Discover</option>
        <option value="Other" ${latestPaymentMethod.cardProvider == 'Other' ? 'selected' : ''}>Other</option>
    	</select> <br> 
    	*Card Number: <input type="number" maxlength="20" name="cardNumber" value="${latestPaymentMethod.cardNumber}"><br>
    	*CVV: <input type="number" maxlength="3" name="cvv" value="${latestPaymentMethod.CVV}"><br> 
    	*Expiry Year (ex. "2024"): <input type="number" maxlength="2" name="expYear" value="${latestPaymentMethod.exp_year}"><br>
   	    *Expiry Month (ex. "05"): <input type="number" maxlength="2" name="expMonth" value="${latestPaymentMethod.exp_month}"><br>
    <input type="submit" value="Update Payment Method">
    </form><br>
    
    <!-- Form to Edit Addresses of Selected User -->
    <form id="addressForm" action="AdminUpdateServlet?type=address" method="post" onsubmit="return validateAddress()">
        <h2>Edit Addresses of User</h2>

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
        </select>
        <button type="button" id="pickbutton">Select Address</button> <br>
        
        <br>
        <input type="hidden" name="userId" value="${selectedUser.getUserId()}" readonly>
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


		<table class="button-table">

	        <tr>
    	        <td style="border: none;">
        	        <input type="submit" value="Update Address">
            	</td>
    	        <td style="width: 20px;"></td>
    	        <td style="border: none;">
     	           <button type="button" id="removeAddressButton">Remove Address</button>
    	        </td>
   		   </tr>
    	</table>

	</form>
    <br>
    
      <!-- Form to Add Address to Selected User -->
       <form action="AdminUpdateServlet?type=addressadd" method="post" onsubmit="return validateAddress()">
        <h2>Add Address to User</h2>

        <input type="hidden" name="userId" value="${selectedUser.getUserId()}" readonly><br>

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
    
    <br>
    
      <script>
     	 //Change inputs when someone picks an address
        document.getElementById('pickbutton').addEventListener('click', function() {
            var select = document.getElementById('addresses');
            var selectedAddress = select.options[select.selectedIndex];

            // Obtain data attributes from selected address
            var addressId = selectedAddress.value;
            var unitNumber = selectedAddress.getAttribute('data-unit');
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
     	 
     	//Listener for when remove button is pressed
        document.getElementById('removeAddressButton').addEventListener('click', function() {
            if (confirm("Are you sure you want to delete this address?")) {
                // Update form action
                document.getElementById('addressForm').action = "AdminUpdateServlet?type=addressrem";
                // Submit form
                document.getElementById('addressForm').submit();
            }
        });
     	
    </script>
    
    
</body>
</html>
