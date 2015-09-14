var isTxDetailsDisplayed = false;
var currentTransactionDetailsId = 0;

function searchTransactionDetails(value,name) {
	var isDeleted = false;
	var searchByCriteria = jQuery('.searchbox-text').attr('name');
	jQuery('#searchCriteria').children('div').each(function(){
		if(jQuery(this).attr('name') == searchByCriteria) {
			jQuery(this).attr('iconCls', 'icon-ok');
		}
	});
	jQuery('#transactionDetailsDiv').css('display', 'block');
}

function displayTransactionDetails(transactionDetailsId, url) {
//	MEDICINE.disableForm('transactionDetailsDisplayForm');
	jQuery('#transactionDetailsDisplayDiv').form('load', url);
	jQuery('#transactionDetailsDisplayDiv').css('display', 'block');
	jQuery('#transactionDetailsDisplayWindow').window('open');
	jQuery('.validatebox-tip').css('display', 'none');
	if(transactionDetailsId == 0){
		jQuery('#transactionDetailsDisplayWindow').dialog('setTitle','Create Transaction Details'); 
		jQuery('.deleteTransactionDetails').css('display', 'none');
		jQuery('#medRepListAdd').css('display', 'block');
//		jQuery('transactionDetailsDisplayForm #medRepList2').removeAttr('required');
		jQuery('#medRepListModify').css('display', 'none');
	} else {
		jQuery('#transactionDetailsDisplayWindow').dialog('setTitle','Update Transaction Details'); 
		jQuery('.deleteTransactionDetails').css('display','');
		jQuery('#medRepListAdd').css('display', 'none');
//		jQuery('#transactionDetailsDisplayForm #medRepList').removeAttr('required');
		jQuery('#medRepListModify').css('display', 'block');
	}
	MEDICINE.enableForm('transactionDetailsDisplayForm');
	
}



jQuery(document).delegate(".submitCreateEditTransactionDetails", "click", function(event) {
	if(jQuery('#transactionDetailsDisplayForm').form('validate') == false) {
		MEDICINE.enableForm('transactionDetailsDisplayForm');
		return;
	}
	var medRepId = jQuery("input[name=medRepId]").val();
	var medRepName = jQuery("input[name=medRepName]").val();
	var transactionDetailsId = jQuery("input[name=transactionDetailsId]").val();
	if(transactionDetailsId != '' && medRepId == '' && medRepName == '') {
		MEDICINE.errMessage("STOP: Please enter Medical Representative Details");
		return false;
	}
	if(transactionDetailsId == '') {
		jQuery("input[name=transactionDetailsId]").val(0);
	}
	MEDICINE.XHR('addOrUpdateTransactionDetails.action', 'json', 'transactionDetailsDisplayForm');
	var operationMsg = jQuery("#ajaxResponse").html();
	MEDICINE.operationMessage(operationMsg);
	var jsonString = jQuery("#ajaxJsonResponse").html();
	var json = JSON.parse(jsonString);
	if(json.transactionDetailsBean.transactionDetailsId) {
		currentTransactionDetailsId = json.transactionDetailsBean.transactionDetailsId;
		jQuery('#attachedBillDetailsDiv input[name=transactionDetailsId]').val(json.transactionDetailsBean.transactionDetailsId);
	}
//	jQuery('#transactionDetailsDisplayWindow').dialog('close');
	MEDICINE.clear();
	var param = getAttrNameOfSelectedCriteria();
	var value = jQuery('.searchbox-text').val();
	if(value == 'Search Transactions') {
		value = '';
	}
	searchTransactionDetails(value,param);
});

jQuery(document).delegate(".deleteTransactionDetails", "click", function(event) {
	deleteTransactionDetails('transactionDetailsDisplayForm');
});

function getWholesellerDetails(index) {
	isTxDetailsDisplayed = false;
	var rows = jQuery('#transactionDetailsListTable').datagrid('getRows');
	alert(rows[index].wholesellerId);
}
function deleteTransactionDetails(formName) {
	jQuery.messager.confirm('Confirm','Delete this Transaction Details?',function(r){  
        if (r){  
        	MEDICINE.XHR('deleteTransactionDetails.action', 'json', formName);
        	var operationMsg = jQuery("#ajaxResponse").html();
        	MEDICINE.operationMessage(operationMsg);
        	jQuery('#transactionDetailsDisplayWindow').dialog('close');
        	var param = getAttrNameOfSelectedCriteria();
        	var value = jQuery('.searchbox-text').val();
        	if(value == 'Search Transactions') {
        		value = '';
        	}
        	searchTransactionDetails(value,param);
        }  
    });
}

function searchTransactionDetailsFinal() {
	jQuery('#transactionDetailsSearch').searchbox({	  
	    searcher:function(value,name){
			// Prepare Name Value Pair
			var startDate = jQuery('input[name=startDate]').val();
			if(startDate == null || startDate == undefined) startDate = '';
			var endDate = jQuery('input[name=endDate]').val();
			if(endDate == null || endDate == undefined) endDate = '';
			if(startDate != '' || endDate != '') {
				if(compareDates(startDate, endDate) == 1) {
					MEDICINE.errMessage("End Date must be greater than Start date");
					return false;
				}
			}
			
			// Check if the input is number
			if(value == '') {
				value = 30;
			}
			var nameValue = name + "=" + value + "&startDate=" + startDate + "&endDate=" + endDate;
			searchTransactionDetails(nameValue);
			if(name == 'expenditure') {
				var wholesellerId = jQuery('input[name=wholesellerId]').val();
				var medRepId = jQuery('input[name=medRepId]').val();
				if(wholesellerId == '') wholesellerId = 0;
				if(medRepId == '') medRepId = 0;
				var paymentMode = jQuery('#transactionDistributorCriteria input[name=paymentMode]').val();
				nameValue = nameValue + '&wholesellerId=' + wholesellerId + '&medRepId=' + medRepId + '&paymentMode=' + paymentMode;;
				jQuery('#transactionDetailsListTable').datagrid({
					showFooter:true,
					title:'Search Result',
					iconCls:'icon-edit',
					width:900,
					height:400,
					singleSelect:true,
					nowrap: false,
					striped: true,
					collapsible:true,
					url:'searchTransactionDetails.action?'+nameValue,
					sortName: 'code',
					sortOrder: 'desc',
					remoteSort: false,
					idField:'code',
					frozenColumns:[[
			            {title:'transactionDetailsId',field:'transactionDetailsId',width:80,sortable:true,hidden:true}
					]],
					columns:[[
						        {title:'Base Information',colspan:9}
							],[
								{field:'paymentDate',title:'Transaction Date',width:100,rowspan:2,
									editor:{type:'datebox',
									options:{
										formatter:function(date) {
													return formatDate(date);
												}
										}
									}
								},
								{field:'wholesellerName',title:'Whole Seller Name',width:150
								},
								{field:'medRepName',title:'MR Name',width:170},
								{field:'paymentMode',title:'Payment By',width:100,rowspan:2},
								{field:'cardNumber',title:'Card/Cheque No',width:100,rowspan:2},
								{field:'totalAmt',title:'Total',width:70,rowspan:2},
								{field:'totalPaid',title:'Paid',width:70,rowspan:2},
								{field:'totalDueAmount',title:'Due',width:70,rowspan:2,
									formatter:function(value,row,index){  
				                    	return value.toFixed(2); 
			                		}
								},
								{field:'transactionDesc',title:'Description',width:200,rowspan:2}
							]],
					onBeforeEdit:function(index,row){  
				        row.editing = true;  
				        updateActions();  
				    },  
				    onAfterEdit:function(index,row){  
				        row.editing = false;  
				        updateActions();  
				    },  
				    onCancelEdit:function(index,row){  
				        row.editing = false;  
				        updateActions();  
				    },
	//			    onClickCell:function(rowIndex, rowData) {
	//			    	isTxDetailsDisplayed = true;
	//			    },
				    onClickRow:function(rowIndex, rowData){
						jQuery('.datagrid-row-selected').each(function(){
							if(jQuery(this).attr('datagrid-row-index') != rowIndex ) {
								jQuery(this).removeClass('datagrid-row-selected');
							}
						});
						currentTransactionDetailsId = rowData.transactionDetailsId;
	//					if(isTxDetailsDisplayed == true) {
							jQuery('#attachedBillDetailsDiv input[name=transactionDetailsId]').val(currentTransactionDetailsId);
							displayTransactionDetails(rowData.transactionDetailsId, 'viewTransactionDetails.action?transactionDetailsId=' + rowData.transactionDetailsId);
	//					}
	
						isTxDetailsDisplayed = true;
					},
					pagination:true,
					rownumbers:true
				});
			} else if(name == 'income') {
				var customerName = jQuery('input[name=customerName]').val();
				var mob1 = jQuery('input[name=mob1]').val();
				var paymentMode = jQuery('#transactionCustomerCriteria input[name=paymentMode]').val();
				nameValue = nameValue + '&customerName=' + customerName + '&mob1=' + mob1 + '&paymentMode=' + paymentMode;
				jQuery('#transactionDetailsListTable').datagrid({
					showFooter:true,
					title:'Search Income',
					iconCls:'icon-edit',
					width:900,
					height:400,
					singleSelect:true,
					nowrap: false,
					striped: true,
					collapsible:true,
					url:'searchTransactionDetails.action?' + nameValue,
					sortName: 'code',
					sortOrder: 'desc',
					remoteSort: false,
					idField:'code',
					frozenColumns:[[
			            {title:'transactionDetailsId',field:'transactionDetailsId',width:80,sortable:true,hidden:true}
					]],
					columns:[[
						        {title:'Base Information',colspan:9}
							],[
							   	{field:'invoiceId',title:'Invoice#',width:50},
								{field:'paymentDate',title:'Transaction<br/>Date',width:80,rowspan:2,
									editor:{type:'datebox',
									options:{
										formatter:function(date) {
													return formatDate(date);
												}
										}
									}
								},
								{field:'customerName',title:'Customer Name',width:150
	//								,formatter:function(value,row,index){  
	//									if(value == null) return '';
	//				                    return '<a href="#" onclick="getWholesellerDetails('+index+')">'+value+'</a>'; 
	//				                }  
								},
								{field:'mob1',title:'Customer Mobile',width:100},
								{field:'paymentMode',title:'Payment By',width:70,rowspan:2},
								{field:'cardNumber',title:'Card/Cheque No',width:100,rowspan:2},
								{field:'totalAmt',title:'Total',width:70,rowspan:2,
									formatter:function(value,row,index){  
					                    return value.toFixed(2); 
				                	}
								},
								{field:'totalPaid',title:'Paid',width:70,rowspan:2,
									formatter:function(value,row,index){  
					                    return value.toFixed(2); 
				                	}
								},
								{field:'totalDueAmount',title:'Due',width:70,rowspan:2,
									formatter:function(value,row,index){  
					                    return value.toFixed(2); 
				                	}
								},
								{field:'transactionDesc',title:'Description',width:200,rowspan:2}
							]],
					onClickRow:function(rowIndex, rowData){
					},
					onBeforeEdit:function(index,row){  
				        row.editing = true;  
				        updateActions();  
				    },  
				    onAfterEdit:function(index,row){  
				        row.editing = false;  
				        updateActions();  
				    },  
				    onCancelEdit:function(index,row){  
				        row.editing = false;  
				        updateActions();  
				    },
					pagination:true,
					rownumbers:true
				});
			} else if(name == 'revenue') {
				var userId = jQuery('input[name=usersId]').val();
				var paymentMode = jQuery('#revenueCriteria input[name=paymentMode]').val();
				nameValue = nameValue + '&userId=' + userId + '&paymentMode=' + paymentMode;
				jQuery('#transactionDetailsListTable').datagrid({
					showFooter:true,
					title:'Total Revenue',
					iconCls:'icon-edit',
					width:900,
					height:400,
					singleSelect:true,
					nowrap: false,
					striped: true,
					collapsible:true,
					url:'searchRevenueDetails.action?' + nameValue,
					sortName: 'code',
					sortOrder: 'desc',
					remoteSort: false,
					idField:'code',
					
					columns:[[
						        {title:'Base Information',colspan:7}
							],[
								{field:'startDate',title:'Start Date',width:80,rowspan:2,
									editor:{type:'datebox',
									options:{
										formatter:function(date) {
													return formatDate(date);
												}
										}
									}
								},
								{field:'endDate',title:'End Date',width:80,rowspan:2,
									editor:{type:'datebox',
									options:{
										formatter:function(date) {
													return formatDate(date);
												}
										}
									}
								},
								{field:'transactionDesc',title:'Revenue Type',width:100},
								{field:'paymentMode',title:'Paid By',width:140},
								{field:'totalAmt',title:'Total Amount/<br/>Total Income',width:100,rowspan:2,
									formatter:function(value,row,index){  
					                    return value.toFixed(2); 
				                	}
								},
								{field:'totalPaid',title:'Total Paid',width:100,rowspan:2,
									formatter:function(value,row,index){  
					                    return value.toFixed(2); 
				                	}
								},
								{field:'totalDueAmount',title:'Total Due',width:100,rowspan:2,
									formatter:function(value,row,index){  
					                    return value.toFixed(2); 
				                	}
								}
							]],
					onClickRow:function(rowIndex, rowData){
					},
					onBeforeEdit:function(index,row){  
				        row.editing = true;  
				        updateActions();  
				    },  
				    onAfterEdit:function(index,row){  
				        row.editing = false;  
				        updateActions();  
				    },  
				    onCancelEdit:function(index,row){  
				        row.editing = false;  
				        updateActions();  
				    },
					pagination:true,
					rownumbers:true
				});
			}
	    },  
	    menu:'#searchCriteria',  
	    prompt:'Search Transactions'  
	});
}
$().ready(function(){
	searchTransactionDetailsFinal();
	jQuery('.datebox').datebox({   
	    formatter:function(date) {
			return formatDate(date);
		}
	});
	jQuery('input[name=totalAmt]').numberbox({  
	    min:0,  
	    precision:2  
	});
	jQuery('input[name=totalPaid]').numberbox({  
	    min:0,  
	    precision:2  
	});
	jQuery('input[name=totalDueAmount]').numberbox({  
	    min:0,  
	    precision:2  
	});
	jQuery('#wholesellerList').combobox({
	    onChange:function(newValue,oldValue){
			if(isNaN(newValue)) newValue = 0;
			loadMedRepList(newValue,'medRepList');
	    },
	    filter: function(q, row){
	    	return row['text'].toLowerCase().indexOf(q.toLowerCase()) != -1;
		}
	});
	
	jQuery('#wholesellerList1').combobox({
	    onChange:function(newValue,oldValue){
			if(isNaN(newValue)) newValue = 0;
			loadMedRepList(newValue,'medRepList');
	    },
	    filter: function(q, row){
	    	return row['text'].toLowerCase().indexOf(q.toLowerCase()) != -1;
		}
	});
	
	jQuery('#revenueCriteria #paymentMode').combobox({
		filter: function(q, row){
			return row['text'].toLowerCase().indexOf(q.toLowerCase()) != -1;
		}
	});
	jQuery('#transactionDistributorCriteria #paymentMode').combobox({
		filter: function(q, row){
			return row['text'].toLowerCase().indexOf(q.toLowerCase()) != -1;
		}
	});
	jQuery('#transactionCustomerCriteria #paymentMode').combobox({
		filter: function(q, row){
			return row['text'].toLowerCase().indexOf(q.toLowerCase()) != -1;
		}
	});
	
	jQuery('#transactionDetailsDisplayForm #paymentMode').combobox({
		filter: function(q, row){
			return row['text'].toLowerCase().indexOf(q.toLowerCase()) != -1;
		}
	});
});

function cleardata(){
	jQuery('#transactionDetailsDisplayForm').form('clear');
}

function calculateDueForPurchase() {
	
}

function calculateDueForPurchase(totalAmt,totalPaid) {
	if(totalAmt == '') totalAmt=0;
	if(totalPaid == '') totalPaid=0;
	var due = totalAmt - totalPaid;
	if(due != null && due != undefined) {
		due = due.toFixed(2);
	}
	jQuery('input[name=totalDueAmount]').val(due);
};

jQuery(document).delegate(".addNewTransactionPurchase", "click", function(event) {
	currentTransactionDetailsId = 0;
	displayTransactionDetails('0', 'addTransactionDetailsForm.action');
	cleardata('#transactionDetailsDisplayForm');
	jQuery('#statex').attr('selectedIndex', 0); 
});

jQuery(document).delegate(".attachBillDetails", "click", function(event) {
	if(currentTransactionDetailsId == 0) {
		MEDICINE.errMessage("Save the transaction then upload the file");
		return;
	}
	jQuery('#attachedBillDetailsDisplayWindow').dialog('open');
	jQuery('#attachedBillDetailsDisplayWindow').dialog('setTitle','Attachment details'); 

	var lastIndex;
	jQuery('#attachedBillDetailsListTable').datagrid({
		title:'Attachments',
		iconCls:'icon-edit',
		width:700,
		height:400,
		singleSelect:true,
		nowrap: false,
		striped: true,
		collapsible:true,
		url:'viewAttachmentDetails.action?transactionDetailsId='+ currentTransactionDetailsId,
		sortName: 'code',
		sortOrder: 'desc',
		remoteSort: false,
		idField:'code',
		frozenColumns:[[
            {title:'Attachment Id',field:'attachmentId',width:80,sortable:true,hidden:true},
            {title:'transactionDetailsId',field:'transactionDetailsId',width:80,sortable:true,hidden:true}
		]],

		toolbar:[
		{
			text:'Attach a file',
			iconCls:'icon-add',
			handler:function(){
				var w = window.open();
				w.document.write( jQuery("#attachedBillDetailsDiv").html() );
//				jQuery('#attachedBillDetailsDiv').dialog('open');
//				$("#attachedBillDetailsDiv a").attr("target","_blank");
			}
		}],
		columns:[[
			        {title:'Base Information',colspan:4}
				],[
					{field:'attachmentId',title:'Attachment Id',width:80,sortable:true},
					{field:'filename',title:'File Name',width:200,sortable:true},
					{field:'attachedFile',title:'Attached File',width:80,rowspan:2,required:true,
						formatter:function(value,row,index){  
		                    return '<a href="#" onclick="downloadAttachment('+index+')">Download</a>';
		                }
					},
					{field:'attachmentDesc',title:'Attachment Description',width:200,rowspan:2,
						editor:'text'
					},
					{field:'action',title:'Action',width:100,rowspan:2,align:'center',  
		                formatter:function(value,row,index){  
		                    if (row.editing){  
		                        var s = '<a href="#" onclick="saveAttachment('+index+')">Save</a> ';  
		                        var c = '<a href="#" onclick="cancelEdit('+index+')">Cancel</a>';  
		                        return s+c;  
		                    } else {  
		                        var e = '<a href="#" onclick="editAttachment('+index+')">Edit</a> ';  
		                        var d = '<a href="#" onclick="deleteAttachment('+index+')">Delete</a>';  
		                        return e+d;  
		                    }  
		                }  
		            }
		]],
		onBeforeEdit:function(index,row){  
	        row.editing = true;  
	        updateAttachmentActions();  
	    },  
	    onAfterEdit:function(index,row){  
	        row.editing = false;  
	        updateAttachmentActions();  
	    },  
	    onCancelEdit:function(index,row){  
	        row.editing = false;  
	        updateAttachmentActions();  
	    },
		pagination:true,
		rownumbers:true
	});
	
	var p = jQuery('#attachedBillDetailsListTable').datagrid('getPager');
	jQuery(p).pagination({
		onBeforeRefresh:function(){
		}
	});
	function getSelected(){
		var selected = jQuery('#attachedBillDetailsListTable').datagrid('getSelected');
		if (selected){
			alert(selected.orderId+":"+selected.orderName+":"+selected.orderAddr1+":"+selected.phone1);
		}
	}

});

function updateAttachmentActions(){  
    var rowcount = jQuery('#attachedBillDetailsListTable').datagrid('getRows').length;  
    for(var i=0; i<rowcount; i++){  
    	jQuery('#attachedBillDetailsListTable').datagrid('updateRow',{  
            index:i,  
            row:{action:''}  
        });  
    }  
} 

function editAttachment(index){  
	jQuery('#attachedBillDetailsListTable').datagrid('beginEdit', index);  
} 

function downloadAttachment(index) {
	jQuery.messager.confirm('Confirm','Are you sure?',function(r){  
        if (r){
			var selectedRow = jQuery('#attachedBillDetailsListTable').datagrid('getSelected');
			var attachmentId = 0;
			if(selectedRow) {
				attachmentId = selectedRow.attachmentId;
			}
			window.open('downloadAttachment.action?attachmentId=' + attachmentId);
        }
	});
}

function saveAttachment(index){  
	jQuery('#attachedBillDetailsListTable').datagrid('endEdit', index); 
	
	var selectedRow = jQuery('#attachedBillDetailsListTable').datagrid('getSelected');
	var attachmentDesc='';
	var attachmentId = 0;
	if(selectedRow) {
		attachmentDesc = selectedRow.attachmentDesc;
		attachmentId = selectedRow.attachmentId;
	}
	var url = 'updateAttachmentDetails.action?attachmentDesc=' + attachmentDesc + '&attachmentId=' + attachmentId;
	MEDICINE.XHR(url, 'json', '');
	var jsonString = jQuery("#ajaxJsonResponse").html();
	var json = JSON.parse(jsonString);
	if(json.errMsg != null) {
		jQuery('#attachedBillDetailsListTable').datagrid('cancelEdit',index);
		MEDICINE.errMessage(json.errMsg);
		return;
	} else {
		MEDICINE.operationMessage(json.msg);
	}
}  

function deleteAttachment(index){  
	jQuery.messager.confirm('Confirm','Are you sure?',function(r){  
        if (r){  
        	var selectedRow = jQuery('#attachedBillDetailsListTable').datagrid('getSelected');
        	var attachmentId = 0;
        	if(selectedRow) {
        		attachmentId = selectedRow.attachmentId;
        	}
        	var url = 'deleteAttachmentDetails.action?attachmentId=' + attachmentId;
        	MEDICINE.XHR(url, 'json', '');
        	var jsonString = jQuery("#ajaxJsonResponse").html();
        	var json = JSON.parse(jsonString);
        	if(json.errMsg != null) {
        		jQuery('#attachedBillDetailsListTable').datagrid('cancelEdit',index);
        		MEDICINE.errMessage(json.errMsg);
        		return;
        	} else {
        		var row = jQuery('#attachedBillDetailsListTable').datagrid('getSelected');
				if (row){
					var index = jQuery('#attachedBillDetailsListTable').datagrid('getRowIndex', row);
					jQuery('#attachedBillDetailsListTable').datagrid('deleteRow', index);
				}
        		MEDICINE.operationMessage(json.msg);
        	}
        }  
        
    });  
} 

function cancelEdit(index){  
	jQuery('#attachedBillDetailsListTable').datagrid('cancelEdit', index);  
}


jQuery(document).delegate("div[name=income]", "click", function(event) {
	jQuery('#transactionDistributorCriteria').css('display','none');
	jQuery('#transactionCustomerCriteria').css('display','block');
	jQuery('#revenueCriteria').css('display','none');
	MEDICINE.resetForm('transactionDetailsSearchForm');
	MEDICINE.clear();
	MEDICINE.selectSearchCriteria(this);
});
jQuery(document).delegate("div[name=expenditure]", "click", function(event) {
	jQuery('#transactionDistributorCriteria').css('display','block');
	jQuery('#transactionCustomerCriteria').css('display','none');
	jQuery('#revenueCriteria').css('display','none');
	MEDICINE.resetForm('transactionDetailsSearchForm');
	MEDICINE.clear();
	MEDICINE.selectSearchCriteria(this);
});
jQuery(document).delegate("div[name=revenue]", "click", function(event) {
	jQuery('#transactionDistributorCriteria').css('display','none');
	jQuery('#transactionCustomerCriteria').css('display','none');
	jQuery('#revenueCriteria').css('display','block');
	MEDICINE.resetForm('transactionDetailsSearchForm');
	MEDICINE.clear();
	MEDICINE.selectSearchCriteria(this);
});
