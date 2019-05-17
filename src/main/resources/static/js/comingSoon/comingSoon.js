// similar behavior as an HTTP redirect
//window.location.replace("http://stackoverflow.com");

// similar behavior as clicking on a link
//window.location.href = "http://stackoverflow.com";

(function() {

	var currentLocation = window.location;
	alert("url = " + currentLocation);

})();

/**
 * note:
 * 
 * using replace clears the session history of the previous
 * 
 * using href mimicks clicking a button link
 * 
 * @returns
 */

function goToLogin() {

	var currentLocation = window.location;

	// currentLocation.replace(window.location + "login");

	window.location.href = currentLocation + "login";

}