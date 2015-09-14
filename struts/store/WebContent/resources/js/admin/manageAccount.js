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
	jQuery('#cc').combobox({
		filter: function(q, row){
			return row['text'].toLowerCase().indexOf(q.toLowerCase()) != -1;
		}
	});
	MEDICINE.XHR('myAccount.action', 'json', '');
	var jsonString = jQuery("#ajaxJsonResponse").html();
	var json = JSON.parse(jsonString);
	jQuery('#manageAccountDisplayForm').form('load',json);
});

jQuery(document).delegate(".submitCreateEditManageAccount", "click", function(event) {
	if(jQuery('#manageAccountDisplayForm').form('validate') == false) {
		MEDICINE.enableForm('manageAccountDisplayForm');
		return;
	}
	MEDICINE.XHR('updateAccount.action', 'json', 'manageAccountDisplayForm');
	var jsonString = jQuery("#ajaxJsonResponse").html();
	var json = JSON.parse(jsonString);
	if(json.errMsg != null) {
		MEDICINE.errMessage(json.errMsg);
	} else if(json.msg != null) {
		MEDICINE.operationMessage("Store Details updated succesfully.");
	}
	MEDICINE.clear();
});

function cleardata(){
	jQuery('#manageAccountDisplayForm').form('clear');
}
