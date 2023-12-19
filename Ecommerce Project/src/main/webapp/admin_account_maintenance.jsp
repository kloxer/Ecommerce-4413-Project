<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin - Account Maintenance Selection</title>
    <link rel="stylesheet" href="./css/updateform.css">
    <script src="./js/formValidators.js"></script>
</head>
<body>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ include file="./includes/header.jsp" %>

    <div class="account-sections">
        <ul>
            <li class="is-active"><a href="./admin">Account Maintenance</a></li>
            <li><a href="./admin?section=inventory">Inventory Maintenance</a></li>
            <li><a href="./admin?section=sales">Sales History</a></li>
        </ul>
    </div>
    
    <div class="user-dropdown">
        <form action="admin?action=EditUser" method="post">
        <h2>Select a User to Edit for Maintenance</h2>
            <select name="selectedUser">
                <c:forEach var="user" items="${userList}">
                    <option value="${user.userId}">${user.firstName} ${user.lastName}</option>
                </c:forEach>
            </select>
            <input type="submit" value="Select User">
        </form>
    </div>
    
</body>
</html>
