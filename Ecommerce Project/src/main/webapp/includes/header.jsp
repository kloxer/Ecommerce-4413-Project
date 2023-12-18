<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.Map" %>
<%@ page import="model.*" %>



<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	  <link rel="stylesheet" href="./css/header.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css">
	
</head>
<body>
    <header>
        <div class="site-title">
            <a href="./" > Shelfless Shop </a>
        </div>
        <div class="search-bar">
            <input type="text" placeholder="What are you looking for?">
            <button type="button">Search</button>
        </div>
        <div class="user-actions">

            <span class="cart">
                <a href="cart.jsp">Cart &#x1F6D2; </a>
            </span>    

          
        <%    Object userObject = session.getAttribute("user");
        if (userObject != null) {
            // For when user is logged in
            out.println("<a href=\"./account\">Account</a>");
            out.println("<a href=\"./logoutServlet\">Log Out</a>");
        } else {
            // For when user is logged out
            out.println("<a href=\"./login.jsp\">Login</a>");
            //out.println("<a href=\"./register.jsp\">Register</a>");
        }  %>
   
    
        </div> 
  <!--       <div class="column is-three-fifths is-offset-one-fifth"> -->
   <!--       <div class="tabs is-centered"> <!-- Added is-centered class -->
       <!--      <ul>-->  
               <!-- Home Tab 
                <li class="is-active"><a href="#">Home</a></li>-->

                <!-- Products Tab
                <li><a href="#">Products</a></li> -->

                <!-- About Tab 
                <li><a href="#">About</a></li>-->

                <!-- Blog Tab 
                <li><a href="#">Blog</a></li>-->
                
                
     <!--        </ul>
        </div>
    </div> -->
    </header>
</body>
</html>
