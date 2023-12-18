<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.*" %>


<%@ page import="java.util.Map" %>



<!DOCTYPE html>
<html>
<head>
    <title>Log out</title>
        <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css">
    <title>Sign In</title>
    <style>
    .is-color {
        background-color: forestgreen;
        color: white;
    }
    h1 {
  font-size: 20px;
}
</style>
</head>
<body>
   <!-- <%@ include file="./includes/header.jsp"%> --> 
    
    <h1>You are now logged out.</h1>
    <h1>Click the button below to log in or register.</h1>
    
    <form action="login.jsp" method="get">
                              <div class="field">
                                <div class="control">
                                    <button class="button is-color is-fullwidth">Sign In</button>
                                </div>
                            </div>
    </form>
    
<!-- <form action="register.jsp" method="get">
        <input type="submit" value="Register">
    </form> -->    
</body>
</html>