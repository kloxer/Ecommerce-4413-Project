
//This function validates the registration input
function validateRegister() {
	var firstName = document.getElementsByName('firstname')[0].value;
	var lastName = document.getElementsByName('lastname')[0].value;
	var phone = document.getElementsByName('phone')[0].value;
	var username = document.getElementsByName('username')[0].value;
	var password = document.getElementsByName('password')[0].value;

	// Check if any fields are left blank
	if (firstName === '' || lastName === '' || phone === '' || username === '' || password === '') {
		alert('Please fill in all fields.');
		//Disallow registeration
		return false; 
	}

	// Validate phone number
	var phoneRegex = /^\(?\d{3}\)?[-\s]?\d{3}[-\s]?\d{4}$/;
	if (!phoneRegex.test(phone)) {
		alert('Please enter a valid phone number (e.g. 8005550185, 800-555-0185, or (800)-555-0185).');
		return false;
	}

	// Ensure password is decently long for security
	if (password.length < 8) {
		alert('Password should be at least 8 characters long for security.');
		return false;
	}

	//Allow registration as valid input
	return true;

}

//This function validates the login input
function validateLogin() {
	var username = document.getElementsByName('username')[0].value;
	var password = document.getElementsByName('password')[0].value;

	if (username === '' || password === '') {
		alert('Please enter both username and password.');
		return false; // Disallow login attempt
	}

	return true; //Allow login attempt
}

function AddressAndPayValidate() {
    var addressLine1 = document.getElementsByName('addressLine1')[0].value;
    var city = document.getElementsByName('city')[0].value;
    var region = document.getElementsByName('region')[0].value;
    var postalCode = document.getElementsByName('postalCode')[0].value;
    var country = document.getElementsByName('country')[0].value;
    var cardProvider = document.getElementsByName('cardProvider')[0].value;
    var cardNumber = document.getElementsByName('cardNumber')[0].value;
    var cvv = document.getElementsByName('cvv')[0].value;
    var expYear = document.getElementsByName('expYear')[0].value;
    var expMonth = document.getElementsByName('expMonth')[0].value;

    if (
        addressLine1 === '' ||
        city === '' ||
        region === '' ||
        postalCode === '' ||
        country === '' ||
        cardProvider === '' ||
        cardNumber === '' ||
        cvv === '' ||
        expYear === '' ||
        expMonth === ''
    ) {
        alert('Please fill in all required fields.');
        return false; // Disallow submission
    }

    // Validate card number length
	if (cardNumber.length !== 16 && cardNumber.length !== 15) {
		alert('Please enter a valid card number with 15 or 16 digits.');
		return false;
	}

    // Validate CVV length
    if (cvv.length !== 3) {
        alert('Please enter a valid CVV.');
        return false;
    }
    
    // Validate year length
    if (expYear.length !== 4) {
        alert('Please enter a valid expiration year.');
        return false; 
    }

    // Validate month length
    if (expMonth.length !== 2) {
        alert('Please enter a valid expiration month.');
        return false;
    }

	//Passed validation
    return true;
}
