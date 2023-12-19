<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin - Inventory Maintenance</title>
    <link rel="stylesheet" href="./css/updateform.css">
    <script src="./js/formValidators.js"></script>
</head>
<body>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ include file="./includes/header.jsp" %>

    <div class="account-sections">
        <ul>
            <li><a href="./admin">Account Maintenance</a></li>
            <li class="is-active"><a href="./admin?section=inventory">Inventory Maintenance</a></li>
            <li><a href="./admin?section=sales">Sales History</a></li>
        </ul>
    </div>

    <form id="productForm" action="AdminUpdateServlet?type=productqty" method="post" onsubmit="return validateInventoryUpdate()">
        <h2> Inventory Maintenance</h2>
        
        <c:if test="${not empty msg}">
			<p style="color: 007B7D;">${msg}</p>
		</c:if>

        <label for="productList">Choose a product:</label> 
        <select id="productList" name="productId">
            <c:forEach items="${sessionScope.productList}" var="product">
                <option value="${product.prodID}"
                    data-description="${product.pDescription}"
                    data-quantity="${product.quantityRemaining}"
                    data-price="${product.price}" data-sku="${product.sku}">
                    ${product.pName}</option>
            </c:forEach>
        </select>
        <button type="button" id="pickProductButton">Select Product</button> <br>
        
        <!-- Input fields for product information -->
        
        <input type="hidden" id="prodId" name="prodId" readonly>
        
        <label for="productName">Product Name:</label>
        <input type="text" id="productName" name="productName" readonly><br>

        <label for="productDescription">Product Description:</label>
        <input type="text" id="productDescription" name="productDescription" readonly><br>

        <label for="setPrice">Price:</label>
        <input type="text" id="setPrice" name="setPrice" readonly><br>

        <label for="sku">SKU:</label>
        <input type="text" id="sku" name="sku" readonly><br>
        
        <br>
        <br>
        
        <label for="quantityRemaining"><strong>Quantity Remaining (Editable):</strong></label>
        <input type="text" id="quantityRemaining" name="quantityRemaining"><br>

        <table class="button-table">
            <tr>
                <td style="border: none;">
                    <input type="submit" value="Update Quantity">
                </td>
            </tr>
        </table>
    </form>
     <br><br>

    <script>
        // Change inputs when a product is selected
        document.getElementById('pickProductButton').addEventListener('click', function() {
            var select = document.getElementById('productList');
            var selectedProduct = select.options[select.selectedIndex];

            // Obtain product information
            var productName = selectedProduct.text;
            var productId = selectedProduct.value;
            var productDescription = selectedProduct.getAttribute('data-description');
            var quantityRemaining = selectedProduct.getAttribute('data-quantity');
            var price = selectedProduct.getAttribute('data-price');
            var sku = selectedProduct.getAttribute('data-sku');

            // Fill input fields
            document.getElementById('prodId').value = productId;
            document.getElementById('productName').value = productName;
            document.getElementById('productDescription').value = productDescription;
            document.getElementById('quantityRemaining').value = quantityRemaining;
            document.getElementById('setPrice').value = price;
            document.getElementById('sku').value = sku;
        });
    </script>
</body>
</html>
