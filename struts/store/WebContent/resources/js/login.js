jQuery(document).delegate("#submitLogin", "click", function(event) {	
//	window.location.href = "admin/home.action";
	if ($('.forgotPassword').is(":checked")) {
		var userName = jQuery("input[name='userName']").val();
		if(userName == '') {
			alert("Please enter username to retrive password");
		} else {
			document.forms["loginform"].action = 'admin/forgotPassword.action';
			document.forms["loginform"].submit();
		}
	} else {
		document.forms["loginform"].submit();
	}
});