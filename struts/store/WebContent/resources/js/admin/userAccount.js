function displayManageAccount() {
	MEDICINE.disableForm('manageAccountDisplayForm');
	jQuery('#manageAccountDisplayDiv').form('load', '');
	jQuery('#manageAccountDisplayDiv').css('display', 'block');
	jQuery('#manageAccountDisplayWindow').window('open');
	jQuery('.validatebox-tip').css('display', 'none');
	jQuery('#manageAccountDisplayWindow').dialog('setTitle','Manage Account'); 
	jQuery('.deleteManageAccount').css('display', 'none');
	MEDICINE.enableForm('manageAccountDisplayForm');
	
}


jQuery().ready(function(){
	MEDICINE.XHR('fetchUserAccount.action', 'json', '');
	var jsonString = jQuery("#ajaxJsonResponse").html();
	var json = JSON.parse(jsonString);
	if(json.errMsg != null) {
		MEDICINE.errMessage(json.errMsg);
	}
	jQuery('#userAccountDisplayForm').form('load',json);
});

jQuery(document).delegate(".submitUserAccount", "click", function(event) {
	if(jQuery('#userAccountDisplayForm').form('validate') == false) {
		MEDICINE.enableForm('userAccountDisplayForm');
		return;
	}
	// Check if current password is correct or not
	var userName = jQuery('input[name=userName]').val();
	var password = jQuery('input[name=password]').val();
	var newPassword = jQuery('input[name=newPassword]').val();
	var confirmPassword = jQuery('input[name=confirmPassword]').val();
	if(password == '' && (newPassword !='' || confirmPassword != '')) {
		MEDICINE.errMessage("Please enter current password");
		return;
	}
	
	if(password != null && password != '') {
		var nameVal = 'userName=' + userName + '&password=' + password + '&newPassword=' + newPassword + '&confirmPassword=' + confirmPassword;
		MEDICINE.XHR('validateUserCredential.action?'+nameVal, 'json', '');
		var jsonString = jQuery("#ajaxJsonResponse").html();
		var json = JSON.parse(jsonString);
		if(json.errMsg != null) {
			MEDICINE.errMessage(json.errMsg);
			jQuery('input[name=newPassword]').val('');
			jQuery('input[name=confirmPassword]').val('');
			return;
		}
		if(password != '' && (newPassword =='' || confirmPassword == '')) {
			MEDICINE.errMessage("New password and Confirm Password must not be empty");
			return;
		}
		if(newPassword == password) {
			MEDICINE.errMessage("New password should be different from old password");
			return;
		}
		
		if(newPassword != confirmPassword) {
			MEDICINE.errMessage("New password and Confirm Password must be same");
			jQuery('input[name=newPassword]').val('');
			jQuery('input[name=confirmPassword]').val('');
			return;
		}
	}
	MEDICINE.XHR('updateUserAccount.action?', 'json', 'userAccountDisplayForm');
	var jsonString = jQuery("#ajaxJsonResponse").html();
	var json = JSON.parse(jsonString);
	if(json.errMsg != null) {
		MEDICINE.errMessage(json.errMsg);
	} else if(json.msg != null) {
		MEDICINE.operationMessage("Account updated succesfully.");
	}
	MEDICINE.clear();
});

function cleardata(){
	jQuery('#userAccountDisplayForm').form('clear');
}
