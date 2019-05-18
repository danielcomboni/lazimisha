/**
 * triggers/initializes the confirmation of signup
 * 
 * @returns
 */

function postEmailForValidification() {

	startProcess();

	var email = $("#emailFld").val();
	var password = $("#passwordFld").val();
	var randomNumber = $("#codeNumberFld").val();

	// var txt = $('#txtName');
	// if (txt.val() != null && txt.val() != '') {
	// alert('you entered text ' + txt.val())
	// } else {
	// alert('Please enter text')
	// }

	if (email == null || email == '') {
		alert('email must be specified')
		stopProcess();
		return;
	}

	if (password === '') {
		alert('password must be specified')
		stopProcess();
		return;
	}

	if (randomNumber === '') {
		alert('code number must be specified')
		stopProcess();
		return;
	}

	var signup = {
		"email" : email,
		"password" : password,
		"randomNumber" : randomNumber
	}

	$.ajax({
		url : "/confirmation/checkEmail",
		// cache : false,
		type : 'POST',
		data : JSON.stringify(signup),
		contentType : 'application/json',
		dataType : 'json',
		success : function(data, textStatus, xhr) {
			// console.log(xhr.status);
			// alert(xhr.status);
		},
		complete : function(xhr, textStatus) {
			console.log(xhr.status);
			if (xhr.status !== 200) {
				// alert(xhr.status);
				// console.log(xhr.status);
			} else if (xhr.status === 200) {

				// alert('you have successfully saved');

				returnEmailValidResult();
				stopProcess();

			}

		}

	});

}

/**
 * 
 * @returns a string message as to whether the email exists or not
 */
function returnEmailValidResult() {

	startProcess()

	var emailResult = "";

	$.ajax({
		type : "GET",
		url : "/confirmation/checkEmailResult",

		contentType : 'application/json',
		dataType : 'json',

		success : function(result) {

			emailResult = result.message;

			// alert("message: " + emailResult);

			if (emailResult === 'valid email') {
				isEmailSignedAlready();
				stopProcess();
			} else {
				alert('please check your email')
				stopProcess();
			}

		}
	});
}

/**
 * 
 * @returns a string message as to whether the email is already signed up or not
 */

function isEmailSignedAlready() {

	startProcess();

	var emailResult = "";

	$.ajax({
		type : "GET",
		url : "/confirmation/isEmailSignedAlready",
		contentType : 'application/json',
		dataType : 'json',

		success : function(result) {

			emailResult = result.message;

			if (emailResult === 'user exists not') {

				alert('this email is not yet signed up. Please sign up');

				// goToSignupConfirmation();
				stopProcess();

			} else {

				isPasswordMatching();
				stopProcess();

			}

		}
	});
}

/**
 * 
 * @returns a string message as to whether the password passed matches the user
 *          password
 */
function isPasswordMatching() {

	startProcess();

	var emailResult = "";

	$.ajax({
		type : "GET",
		url : "/confirmation/isPasswordMatching",
		contentType : 'application/json',
		dataType : 'json',

		success : function(result) {

			emailResult = result.message;

			if (emailResult === 'password matched') {
				// alert('matched');
				isRandomNumberMatching();

				stopProcess();

			} else {

				alert('please check your password');

				stopProcess();

			}

		}
	});
}

/**
 * 
 * @returns a string message as to whether the random verification number passed
 *          matches the user random number
 */
function isRandomNumberMatching() {

	stopProcess();

	var emailResult = "";

	$.ajax({
		type : "GET",
		url : "/confirmation/isRandomNumberMatching",
		contentType : 'application/json',
		dataType : 'json',
		success : function(result) {

			emailResult = result.message;

			if (emailResult === 'number matched') {

				confirmSignup();

				stopProcess();

			} else {

				alert('please check your verification number code');

				stopProcess();

			}

		}
	});
}

/**
 * 
 * @returns finally confirms sign up
 */
function confirmSignup() {

	startProcess();

	var email = $("#emailFld").val();
	var password = $("#passwordFld").val();
	var randomNumber = $("#codeNumberFld").val();

	var signup = {
		"email" : email,
		"password" : password,
		"randomNumber" : randomNumber
	}

	$.ajax({
		url : "/confirmSignup",
		// cache : false,
		type : 'POST',
		data : JSON.stringify(signup),
		contentType : 'application/json',
		dataType : 'json',
		success : function(data, textStatus, xhr) {
			console.log(xhr.status);
		},
		complete : function(xhr, textStatus) {
			console.log(xhr.status);
			if (xhr.status !== 200) {
				console.log(xhr.status);
			} else if (xhr.status === 200) {

				stopProcess();

			}

		}

	});

}
