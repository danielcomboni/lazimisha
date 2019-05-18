function startProcess() {
	$('#pleaseWaitDialog').modal();
	$('.progress').show();
}

function stopProcess() {
	$('#pleaseWaitDialog').modal('hide');
	$('.progress').hide();
}
