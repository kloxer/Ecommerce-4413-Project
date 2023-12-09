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

    <div class="account-sections">
        <ul>
            <li class="is-active"><a href="./account">Account Info</a></li>
            <li><a href="./account?section=paymentmeth">Payment Methods</a></li>
            <li><a href="./account?section=addresses">Addresses</a></li>
            <li><a href="./account?section=orders">My Orders</a></li>
        </ul>
    </div>

    <form>
        <h2>Update Account Information (Not Impl yet Just View)</h2>
        <h5>*Ensure all fields are completed and valid before ordering*</h5>

        <h3>General Info</h3>
        *First Name: <input type="text" name="firstName" value="${user.getFirstName()}"><br>
        *Last Name: <input type="text" name="lastName" value="${user.getLastName()}"><br>
        *Phone Number: <input type="text" name="phoneNumber" value="${user.getPhoneNumber()}"><br>

        <h3>Billing/Shipping Address Info</h3>
   	    <input type="hidden" name="AddId" value="${latestAddress.id}" readonly>
   	    Unit Number: <input type="text" name="unitNumber" value="${latestAddress.unitNumber}"><br>
   	    *Address Line 1: <input type="text" name="addressLine1" value="${latestAddress.streetNumber} ${latestAddress.addressLine1}"><br>
        *City: <input type="text" name="city" value="${latestAddress.city}"><br>
        *Region: <input type="text" name="region" value="${latestAddress.region}"><br>
        *Postal Code: <input type="text" name="postalCode" value="${latestAddress.postalCode}"><br>
        *Country: <input type="text" name="country" value="${latestAddress.country}"><br>

        <h3>Payment Info</h3>
        <input type="hidden" name="upmId" value="${latestPayMethod.UPM_ID}" readonly>
        <input type="hidden" name="userId" value="${latestPayMethod.user_id}" readonly>
        *Card Provider (Enter MasterCard, Visa, Amex, Discover or Other): <input type="text" name="cardProvider" value="${latestPayMethod.cardProvider}"><br> 
        *Card Number: <input type="number" maxlength="20" name="cardNumber" value="${latestPayMethod.cardNumber}"><br>
        *CVV: <input type="number" maxlength="3" name="cvv" value="${latestPayMethod.CVV}"><br> 
        *Expiry Year: <input type="number" maxlength="2" name="expYear" value="${latestPayMethod.exp_year}"><br>
        *Expiry Month: <input type="number" maxlength="2" name="expMonth" value="${latestPayMethod.exp_month}"><br>
         
    </form>
    <br><br>
 
</body>
</html>
