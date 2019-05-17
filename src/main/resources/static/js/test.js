



function test() {

	var user = {
		"userName" : $("#userNameId").val(),
		"password": $("#passwordId").val()
	}

	/*var formData = {
		firstName : $("#userNameId").val(),
		lastName : $("#passwordId").val()
	}*/

	alert('fn: ' + user.password)	

	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "/authenticateTheUser",
		data : JSON.stringify(user),
		dataType : 'application/json',
		success : function(data, textStatus, xhr) {
			console.log(xhr.status);
			alert(xhr.status);
			// refreshTable();
		},
		complete : function(xhr, textStatus) {
			console.log(xhr.status);
			if (xhr.status !== 200) {
				alert(xhr.status);
				console.log(xhr.status);
			} else if (xhr.status === 200) {
				alert('you have successfully saved');
				console.log(xhr.status);
				// refreshTable();
				// clearFields();
				// window.location.href =
				// '/keepCount/business/entities/'
				// + businessName + '/' + emailOfUser;
			}

		}

	});

}