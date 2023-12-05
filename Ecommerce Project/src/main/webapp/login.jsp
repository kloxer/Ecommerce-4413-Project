<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css">
    <title>Sign In</title>
    <style>
    .is-color {
        background-color: forestgreen;
        color: white;
    }
</style>
</head>

<body>

    <section class="section">
        <div class="container">
            <div class="columns is-centered">
                <div class="column is-half">
                    <div class="box has-background-white-ter">
                        <h1 class="title has-text-centered">Shelfless Shop</h1>                                    	
                       		<h2 class="title has-text-centered">Sign In</h2>

                        <!-- Sign In Form -->
                        <form>
                            <!-- Username or Email Input -->
                            <div class="field">
                                <label class="label">Username or Email</label>
                                <div class="control">
                                    <input class="input" type="text" placeholder="Enter your username or email">
                                </div>
                            </div>

                            <!-- Password Input -->
                            <div class="field">
                                <label class="label">Password</label>
                                <div class="control">
                                    <input class="input" type="password" placeholder="Enter your password">
                                </div>
                            </div>

                            <!-- Sign In Button -->
                            <div class="field">
                                <div class="control">
                                    <button class="button is-color is-fullwidth">Sign In</button>
                                </div>
                            </div>
                            <!-- Register Button -->
                            <div class="field">
                                <div class="control">
                                    <button class="button is-color is-fullwidth">New Customer? Sign up!</button>
                                </div>
                            </div>
                        </form>
                        <!-- End Sign In Form -->

                        <p class="has-text-centered">
                            <a href="#">Forgot your password?</a>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <script defer src="https://use.fontawesome.com/releases/v5.3.1/js/all.js"></script>
</body>

</html>
