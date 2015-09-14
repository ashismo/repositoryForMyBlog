function searchUser(value,name) {
	var isDeleted = false;
	var searchByCriteria = jQuery('.searchbox-text').attr('name');
	jQuery('#searchCriteria').children('div').each(function(){
		if(jQuery(this).attr('name') == searchByCriteria) {
			jQuery(this).attr('iconCls', 'icon-ok');
		}
	});
	// Create a panel to list companies
	jQuery('#userDiv').css('display', 'block');
	
	jQuery('#userListTable').datagrid({
		title:'Search Result',
		iconCls:'icon-save',
		width:900,
		height:400,
		nowrap: false,
		striped: true,
		collapsible:true,
		url:'searchUser.action?' + name + '=' + value,
		sortName: 'code',
		sortOrder: 'desc',
		remoteSort: false,
		idField:'code',
		frozenColumns:[[
            {title:'userId',field:'userId',width:80,sortable:true,hidden:true},
            {title:'role',field:'role',width:80,sortable:true,hidden:true}
		]],
		columns:[[
	        {title:'Base Information',colspan:7},
			{field:'opt',title:'Delete',width:60,align:'center', rowspan:2,
	        	formatter:function(value,row,index){  
		        	var d = '<a href="#">Delete</a>';  
	                return d;
            	}
			}
			
		],[
			{field:'userName',title:'User Name',width:100, sortable:true,
				formatter:function(value,row,index){  
					var d = '<a href="#">' + value +'</a>';  
	                return d; 
	        	}
			},
			{field:'name',title:'Name',width:130, sortable:true},
			{field:'address',title:'Address',width:150,rowspan:2,sortable:true},
			{field:'mobile',title:'Mobile',width:80,rowspan:2},
			{field:'phone',title:'Phone',width:80,rowspan:2},
			{field:'role',title:'Role',width:80,rowspan:2},
			{field:'emailId',title:'Email',width:150,rowspan:2}
			
		]],
		pagination:true,
		rownumbers:true,
		onClickCell:function(rowIndex, colIndex){
			if(colIndex == 'opt') {
				var selected = jQuery('#userListTable').datagrid('getRows');
				if (selected != null || selected != undefined){
					jQuery('#hiddenFieldForm').children('input').each(function(){
						if(jQuery(this).attr('name') == 'userId') {
							userId = selected[rowIndex].userId;
							jQuery(this).val(userId);
						}
					});
					
					deleteUser('hiddenFieldForm');
					isDeleted = true;
				}
			}
		},
		onClickRow:function(rowIndex, rowData){
			jQuery('.datagrid-row-selected').each(function(){
				if(jQuery(this).attr('datagrid-row-index') != rowIndex ) {
					jQuery(this).removeClass('datagrid-row-selected');
				}
			});
			if(isDeleted == false) {
				displayUser(rowData.userId, rowData.role, 'viewUser.action?userId=' + rowData.userId);
			}
		}
	});
	var p = jQuery('#userListTable').datagrid('getPager');
	jQuery(p).pagination({
		onBeforeRefresh:function(){
		}
	});
	function getSelected(){
		var selected = jQuery('#userListTable').datagrid('getSelected');
		if (selected){
			alert(selected.userId+":"+selected.userName+":"+selected.userAddr1+":"+selected.phone1);
		}
	}
}

function displayUser(userId, role, url) {
//	MEDICINE.disableForm('userDisplayForm');
	jQuery('#userDisplayDiv').form('load', url);
	jQuery('#userDisplayDiv').css('display', 'block');
	jQuery('#userDisplayWindow').window('open');
	jQuery('.validatebox-tip').css('display', 'none');
	jQuery('#userDisplayForm input[name=confirmPassword]').val('');
	if(userId == 0){
		jQuery('#userDisplayForm input[name=userName]').removeAttr('readonly');
		jQuery('#userDisplayWindow').dialog('setTitle','Create User'); 
		jQuery('.deleteUser').css('display', 'none');
	} else {
		jQuery('#userDisplayWindow').dialog('setTitle','Update User'); 
		jQuery('.deleteUser').css('display','');
		if(role == 'SuperAdmin') {
			jQuery('#userDisplayForm input[name=userName]').attr('readonly','readonly');
		}
	}
	MEDICINE.enableForm('userDisplayForm');
	
}

jQuery(document).delegate(".submitCreateEditUser", "click", function(event) {
	if(jQuery('#userDisplayForm').form('validate') == false) {
		MEDICINE.enableForm('userDisplayForm');
		return;
	}
	var userId = jQuery('#userDisplayForm input[name=userId]').val();
	var userName = jQuery('#userDisplayForm input[name=userName]').val();
	var password = jQuery('input[name=password]').val();
	var confirmPassword = jQuery('input[name=confirmPassword]').val();
	
	if((password !='' && confirmPassword == '') || (password =='' && confirmPassword != '')) {
		MEDICINE.errMessage("Password or Confirm Password can not be empty");
		return;
	}
	
	if(password != confirmPassword) {
		MEDICINE.errMessage("New password and Confirm Password must be same");
		jQuery('input[name=newPassword]').val('');
		jQuery('input[name=confirmPassword]').val('');
		return;
	}
	
	if(userId == 0){
		if(password =='' || confirmPassword == '') {
			MEDICINE.errMessage("Please enter password");
			return;
		}
	}
	MEDICINE.XHR('createUser.action', 'json', 'userDisplayForm');
	var operationMsg = jQuery("#ajaxResponse").html();
	MEDICINE.operationMessage(operationMsg);
	if(operationMsg != null && operationMsg.indexOf('success') != -1) {
		jQuery('#userDisplayWindow').dialog('close');
	}
	MEDICINE.clear();
	var param = getAttrNameOfSelectedCriteria();
	var value = jQuery('.searchbox-text').val();
	if(value == 'Search User') {
		value = '';
	}
	searchUser(value,param);
});

jQuery(document).delegate(".deleteUser", "click", function(event) {
	deleteUser('userDisplayForm');
});

function deleteUser(formName) {
	var role = jQuery('#userDisplayForm input[name=role]').val();
	if(role == 'SuperAdmin') {
		MEDICINE.errMessage("Default user can not be deleted");
		return;
	}
	jQuery.messager.confirm('Confirm','Delete this User?',function(r){  
        if (r){  
        	MEDICINE.XHR('deleteUser.action', 'json', formName);
        	var operationMsg = jQuery("#ajaxResponse").html();
        	MEDICINE.operationMessage(operationMsg);
        	jQuery('#userDisplayWindow').dialog('close');
        	var param = getAttrNameOfSelectedCriteria();
        	var value = jQuery('.searchbox-text').val();
        	if(value == 'Search User') {
        		value = '';
        	}
        	searchUser(value,param);
        }  
    });
}

function cleardata(){
	jQuery('#userDisplayForm').form('clear');
}

jQuery(document).delegate(".addNewUser", "click", function(event) {
	displayUser('0', '', 'addUserForm.action');
	cleardata('#userDisplayForm');
	jQuery('#statex').attr('selectedIndex', 0); 
});


jQuery(document).delegate("div[name=userName]", "click", function(event) {
	MEDICINE.selectSearchCriteria(this);
});
jQuery(document).delegate("div[name=role]", "click", function(event) {
	MEDICINE.selectSearchCriteria(this);
});
