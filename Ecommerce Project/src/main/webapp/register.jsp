<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css">
    <title>Register</title>
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
                        <h1 class="title has-text-centered">Register</h1>

                        <!-- Register Form -->
                        <form>
                            <!-- First Name Input -->
                            <div class="field">
                                <label class="label">First Name</label>
                                <div class="control">
                                    <input class="input" type="text" placeholder="Enter your first name">
                                </div>
                            </div>

                            <!-- Last Name Input -->
                            <div class="field">
                                <label class="label">Last Name</label>
                                <div class="control">
                                    <input class="input" type="text" placeholder="Enter your last name">
                                </div>
                            </div>

                            <!-- Shipping Address Input -->
                            <div class="field">
                                <label class="label">Shipping Address</label>
                                <div class="control">
                                    <input class="input" type="text" placeholder="Enter your shipping address">
                                </div>
                            </div>

                            <!-- Postal Code Input -->
                            <div class="field">
                                <label class="label">Postal Code</label>
                                <div class="control">
                                    <input class="input" type="text" placeholder="Enter your postal code">
                                </div>
                            </div>

                            <!-- Card Number Input -->
                            <div class="field">
                                <label class="label">Card Number</label>
                                <div class="control">
                                    <input class="input" type="text" placeholder="Enter your card number">
                                </div>
                            </div>

                            <!-- Expiry Date Input -->
                            <div class="field">
                                <label class="label">Expiry Date</label>
                                <div class="control">
                                    <input class="input" type="text" placeholder="Enter expiry date (MM/YYYY)">
                                </div>
                            </div>

                            <!-- Security Code Input -->
                            <div class="field">
                                <label class="label">Security Code</label>
                                <div class="control">
                                    <input class="input" type="text" placeholder="Enter security code">
                                </div>
                            </div>

                            <!-- Register Button -->
                            <div class="field">
                                <div class="control">
                                    <button class="button is-color is-fullwidth">Register</button>
                                </div>
                            </div>
                        </form>
                        <!-- End Register Form -->
                    </div>
                </div>
            </div>
        </div>
    </section>

    <script defer src="https://use.fontawesome.com/releases/v5.3.1/js/all.js"></script>
</body>

</html>
