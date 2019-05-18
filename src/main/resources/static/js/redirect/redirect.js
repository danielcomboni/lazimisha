function redirectToLogin() {

	alert('tttt');

	var url = "non";

	var redirect = {
		"url" : url
	}

	$.ajax({
		url : "/toLogin",
		// cache : false,
		type : 'POST',
		data : JSON.stringify(redirect),
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

			}

		}

	});

}