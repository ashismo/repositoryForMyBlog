function searchOrder(nameValue) {
	var searchByCriteria = jQuery('.searchbox-text').attr('name');
	jQuery('#searchCriteria').children('div').each(function(){
		if(jQuery(this).attr('name') == searchByCriteria) {
			jQuery(this).attr('iconCls', 'icon-ok');
		}
	});
	
	jQuery('#orderDiv').css('display', 'block');
}

function updateActions(){  
    var rowcount = jQuery('#orderListTable').datagrid('getRows').length;  
    for(var i=0; i<rowcount; i++){  
    	jQuery('#orderListTable').datagrid('updateRow',{  
            index:i,  
            row:{action:''}  
        });  
    }  
} 

function editOrder(index){  
	jQuery('#orderListTable').datagrid('beginEdit', index);  
}  
function deleteOrder(index){  
	jQuery.messager.confirm('Confirm','Are you sure?',function(r){  
        if (r){  
        	var selectedRow = jQuery('#orderListTable').datagrid('getSelected');
        	if(selectedRow) {
        		orderId = selectedRow.orderId;
        		if(orderId == null || orderId == undefined || orderId == '') {
        			deleteSelectedRow();
        			MEDICINE.errMessage("Please Note: This medicine has not been ordered. Deleting from table");
            		return;
        		}
        		url = 'deleteOrder.action?orderId=' + orderId;
        	}
        	
        	MEDICINE.XHR(url, 'json', '');
        	var jsonString = jQuery("#ajaxJsonResponse").html();
        	var json = JSON.parse(jsonString);
        	if(json.orderBean.errorMsg != null) {
        		jQuery('#orderListTable').datagrid('cancelEdit',index);
        		MEDICINE.errMessage(json.orderBean.errorMsg);
        		return;
        	} else {
        		selectedRow.orderId = null;
        		selectedRow.orderDesc = null;
        		selectedRow.orderDate = null;
        		jQuery('#orderListTable').datagrid('reload',selectedRow);
        		MEDICINE.operationMessage(json.orderBean.msg);
        	}
        }  
    });  
}  
function saveOrder(index){  
	jQuery('#orderListTable').datagrid('endEdit', index); 
	
	var selectedRow = jQuery('#orderListTable').datagrid('getSelected');
	var medicineId=0;
	var orderDate='';
	var orderId=0;
	var medType = '';
	var medWeight = '';
	var medDose = '';
	var url = 'addOrUpdateOrder.action?medicineId=0&orderDate=';
	if(selectedRow) {
		medicineId = selectedRow.medicineId;
		medType = selectedRow.medType;
		medWeight = selectedRow.medWeight;
		medDose = selectedRow.medDose;
		orderDate = selectedRow.orderDate;
		orderId = selectedRow.orderId;
		orderDesc = selectedRow.orderDesc;
		if(orderId == null || orderId == undefined || orderId == '') orderId = 0;
		url = 'addOrUpdateOrder.action?medicineId=' + medicineId + 
					'&orderDate=' + orderDate + '&orderId=' + orderId +
					'&medType=' + medType + '&medWeight=' + medWeight + 
					'&medDose=' + medDose + '&orderDesc=' + orderDesc;
	}
	
	MEDICINE.XHR(url, 'json', '');
	var jsonString = jQuery("#ajaxJsonResponse").html();
	var json = JSON.parse(jsonString);
	if(json.orderBean.errorMsg != null) {
		jQuery('#orderListTable').datagrid('cancelEdit',index);
		MEDICINE.errMessage(json.orderBean.errorMsg);
		return;
	} else {
		//selectedRow.orderId = json.orderBean.orderId;
		var rows = jQuery('#orderListTable').datagrid('getRows');
		rows[index].orderId = json.orderBean.orderId;
		jQuery('#orderListTable').datagrid('load',rows);
		MEDICINE.operationMessage(json.orderBean.msg);
	}
}  

function deleteSelectedRow() {
	var row = jQuery('#orderListTable').datagrid('getSelected');
	if (row){
		var index = jQuery('#orderListTable').datagrid('getRowIndex', row);
		jQuery('#orderListTable').datagrid('deleteRow', index);
	}
}
function deleteNote(index){  
	jQuery.messager.confirm('Confirm','Are you sure?',function(r){  
        if (r){  
        	var selectedRow = jQuery('#orderListTable').datagrid('getSelected');
        	if(selectedRow) {
        		orderId = selectedRow.orderId;
        		if(orderId == null || orderId == undefined || orderId == '') {
        			deleteSelectedRow();
        			MEDICINE.errMessage("Please Note: This Note does not exist into database.Deleting from table");
            		return;
        		}
        		url = 'deleteNotes.action?orderId=' + orderId;
        	}
        	
        	MEDICINE.XHR(url, 'json', '');
        	var jsonString = jQuery("#ajaxJsonResponse").html();
        	var json = JSON.parse(jsonString);
        	if(json.orderBean.errorMsg != null) {
        		jQuery('#orderListTable').datagrid('cancelEdit',index);
        		MEDICINE.errMessage(json.orderBean.errorMsg);
        		return;
        	} else {
        		var row = jQuery('#orderListTable').datagrid('getSelected');
				if (row){
					var index = jQuery('#orderListTable').datagrid('getRowIndex', row);
					jQuery('#orderListTable').datagrid('deleteRow', index);
				}
        		MEDICINE.operationMessage(json.orderBean.msg);
        	}
        }  
    });  
} 

function saveNote(index){  
	jQuery('#orderListTable').datagrid('endEdit', index); 
	
	var selectedRow = jQuery('#orderListTable').datagrid('getSelected');
	var orderExecutionDate='';
	var orderDate='';
	var orderId=0;
	var orderDesc = '';
	var url = 'addOrUpdateNotes.action?orderId=0&orderDate=';
	if(selectedRow) {
		orderDate = selectedRow.orderDate;
		orderExecutionDate = selectedRow.orderExecutionDate;
		orderId = selectedRow.orderId;
		orderDesc = selectedRow.orderDesc;
		if(orderId == null || orderId == undefined || orderId == '') orderId = 0;
		if(orderDesc == null || orderDesc.toString().trim() == '' || orderDate == null || orderDate.toString().trim() == '') {
			MEDICINE.errMessage("Note Date and Note description can not be null or empty");
			return;
		}
		url = 'addOrUpdateNotes.action?orderId=' + orderId + '&orderDesc=' + orderDesc + 
					'&orderDate=' + orderDate + '&orderExecutionDate=' + orderExecutionDate;
	}
	
	MEDICINE.XHR(url, 'json', '');
	var jsonString = jQuery("#ajaxJsonResponse").html();
	var json = JSON.parse(jsonString);
	if(json.orderBean.errorMsg != null) {
		jQuery('#orderListTable').datagrid('cancelEdit',index);
		MEDICINE.errMessage(json.orderBean.errorMsg);
		return;
	} else {
		//selectedRow.orderId = json.orderBean.orderId;
		var rows = jQuery('#orderListTable').datagrid('getRows');
		rows[index].orderId = json.orderBean.orderId;
		$('#orderListTable').datagrid('updateRow',{
			index: index,
			row: rows[index]
		});
//		jQuery('#orderListTable').datagrid('loadData',rows[index]);
		MEDICINE.operationMessage(json.orderBean.msg);
	}
}  

function cancelEdit(index){  
	jQuery('#orderListTable').datagrid('cancelEdit', index);  
}


function displayOrder(orderId, url) {
	jQuery('#orderDisplayDiv').form('load',url);
	jQuery('#orderDisplayDiv').css('display', 'block');
//	jQuery('#orderDisplayWindow').attr('title', title);
	jQuery('#orderDisplayWindow').window('open');
	
}

function cleardata(){
	jQuery('#orderDisplayForm').form('clear');
}

jQuery(document).delegate(".addNewOrder", "click", function(event) {
	displayOrder('0', 'addOrder.action');
	
});

jQuery(document).delegate(".submitCreateEditOrder", "click", function(event) {
	var orderId = jQuery('input[name=orderId]').val();
	var reqParamVal = jQuery("#orderDisplayForm").serialize();
	//displayOrder(orderId, 'addOrder.action');
	MEDICINE.XHR('createOrder.action', 'json', 'orderDisplayForm');
	alert(jQuery("#ajaxResponse").html());
});

jQuery().ready(function(){
	jQuery('#orderSearch').searchbox({  
	    searcher:function(value,name){
			// Prepare Name Value Pair
			var medType = jQuery('input[name=medType]', '#lowStockMedicineTypeCriteria').val();
			var notesCriteria = jQuery('input[name=notesCriteria]').val();
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
			if(isNaN(value)) {
				MEDICINE.operationMessage("Please Enter Whole Number");
				return false;
			} else {
				searchOrder(nameValue);
			}
			if(name == 'lowStock') {
				jQuery('#orderListTable').datagrid({
					title:'Search Result',
					iconCls:'icon-edit',
					width:900,
					height:400,
					singleSelect:true,
					nowrap: false,
					striped: true,
					collapsible:true,
					url:'searchOrderDetails.action?' + nameValue  + "&medType=" + medType,
					sortName: 'code',
					sortOrder: 'desc',
					remoteSort: false,
					idField:'code',
					frozenColumns:[[
			            {title:'medicineId',field:'medicineId',width:80,sortable:true,hidden:true},
			            {title:'orderId',field:'orderId',width:80,sortable:true,hidden:true}
					]],
					columns:[[
						        {title:'Base Information',colspan:8}
							],[
								{field:'medicineName',title:'Medicine Name',width:150},
								{field:'companyName',title:'Company Name',width:120},
								{field:'medType',title:'Type',width:70,rowspan:2},
								{field:'medDose',title:'Dose',width:70,rowspan:2},
								{field:'medWeight',title:'Weight',width:70,rowspan:2},
								{field:'availableStock',title:'Available<br/>Stock',width:60,rowspan:2,sortable:true,
									sorter:function(a,b){
										return (a>b?1:-1);
									}
								},
								{field:'orderDate',title:'Order<br/>Placed On',width:80,rowspan:2,
									editor:{type:'datebox',
									options:{
										formatter:function(date) {
													return formatDate(date);
												}
										}
									}
								},
								{field:'orderDesc',title:'Order desc',width:150,rowspan:2,
									editor:'text'
								},
								{field:'action',title:'Action',width:100,rowspan:2,align:'center',  
					                formatter:function(value,row,index){  
					                    if (row.editing){  
					                        var s = '<a href="#" onclick="saveOrder('+index+')">Save</a> ';  
					                        var c = '<a href="#" onclick="cancelEdit('+index+')">Cancel</a>';  
					                        return s+c;  
					                    } else {  
					                        var e = '<a href="#" onclick="editOrder('+index+')">Edit</a> ';  
					                        var d = '<a href="#" onclick="deleteOrder('+index+')">Delete</a>';  
					                        return e+d;  
					                    }  
					                }  
					            }
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
					pagination:true,
					rownumbers:true
				});
			} else if(name == 'executedOrder') {
				jQuery('#orderListTable').datagrid({
					title:'Search Result',
					iconCls:'icon-edit',
					width:900,
					height:400,
					singleSelect:true,
					nowrap: false,
					striped: true,
					collapsible:true,
					url:'searchOrderDetails.action?' + nameValue,
					sortName: 'code',
					sortOrder: 'desc',
					remoteSort: false,
					idField:'code',
					frozenColumns:[[
			            {title:'medicineId',field:'medicineId',width:80,sortable:true,hidden:true},
			            {title:'orderId',field:'orderId',width:80,sortable:true,hidden:true}
					]],
					columns:[[
						        {title:'Base Information',colspan:11}
							],[
								{field:'medicineName',title:'Medicine Name',width:150},
								{field:'companyName',title:'Company Name',width:120},
								{field:'medType',title:'Type',width:70,rowspan:2},
								{field:'medDose',title:'Dose',width:50,rowspan:2},
								{field:'medWeight',title:'Weight',width:50,rowspan:2},
								{field:'batchName',title:'Batch',width:70,rowspan:2},
								{field:'mfgDate',title:'Mfg date',width:70,rowspan:2,
										formatter:function(date) {
											return date;
										}
								},
								{field:'expDate',title:'Exp Date',width:70,rowspan:2,
									formatter:function(date) {
										return date;
									}
								},
								{field:'orderDate',title:'Order<br/>Placed On',width:80,rowspan:2,
									formatter:function(date) {
										return date;
									}
								},
								{field:'orderExecutionDate',title:'Order<br/>Executed On',width:80,rowspan:2,
									formatter:function(date) {
										return date;
									}
								},
								{field:'orderDesc',title:'Order desc',width:100,rowspan:2,
									editor:'text'
								}
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
							pagination:true,
							rownumbers:true
				});
			} else if(name == 'recentNotes') {
				var lastIndex;
				jQuery('#orderListTable').datagrid({
					title:'Search Result',
					iconCls:'icon-edit',
					width:900,
					height:400,
					singleSelect:true,
					nowrap: false,
					striped: true,
					collapsible:true,
					url:'searchOrderDetails.action?' + nameValue + '&notesCriteria='+notesCriteria,
					sortName: 'code',
					sortOrder: 'desc',
					remoteSort: false,
					idField:'code',
					frozenColumns:[[
			            {title:'orderId',field:'orderId',width:80,sortable:true,hidden:true}
					]],

					toolbar:[{
						text:'Append Row',
						iconCls:'icon-add',
						handler:function(){
							jQuery('#orderListTable').datagrid('endEdit', lastIndex);
							jQuery('#orderListTable').datagrid('appendRow',{
								orderId:'',
								orderDate:'',
								orderDesc:'',
								orderExecutionDate:''
							});
							lastIndex = jQuery('#orderListTable').datagrid('getRows').length-1;
							jQuery('#orderListTable').datagrid('selectRow', lastIndex);
							jQuery('#orderListTable').datagrid('beginEdit', lastIndex);
						}
					}],
					columns:[[
						        {title:'Base Information',colspan:3}
							],[
								{field:'orderDate',title:'Date',width:80,rowspan:2,required:true,
									editor:{type:'datebox',
									options:{
										formatter:function(date) {
													return formatDate(date);
												}
										}
									}
								},
								{field:'orderDesc',title:'Description',width:500,rowspan:2,required:true,
									editor:'text'
								},
								{field:'orderExecutionDate',title:'Execution Date',width:120,rowspan:2,
									editor:{type:'datebox',
									options:{
										formatter:function(date) {
													return formatDate(date);
												}
										}
									}
								},
								{field:'action',title:'Action',width:100,rowspan:2,align:'center',  
					                formatter:function(value,row,index){  
					                    if (row.editing){  
					                        var s = '<a href="#" onclick="saveNote('+index+')">Save</a> ';  
					                        var c = '<a href="#" onclick="cancelEdit('+index+')">Cancel</a>';  
					                        return s+c;  
					                    } else {  
					                        var e = '<a href="#" onclick="editOrder('+index+')">Edit</a> ';  
					                        var d = '<a href="#" onclick="deleteNote('+index+')">Delete</a>';  
					                        return e+d;  
					                    }  
					                }  
					            }
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
					pagination:true,
					rownumbers:true
				});
				
				var p = jQuery('#orderListTable').datagrid('getPager');
				jQuery(p).pagination({
					onBeforeRefresh:function(){
					}
				});
				function getSelected(){
					var selected = jQuery('#orderListTable').datagrid('getSelected');
					if (selected){
						alert(selected.orderId+":"+selected.orderName+":"+selected.orderAddr1+":"+selected.phone1);
					}
				}
			}
	    },  
	    menu:'#searchCriteria',  
	    prompt:'Search Orders'  
	}); 
	
	jQuery('.datebox').datebox({   
	    formatter:function(date) {
			return formatDate(date);
		}
	});
});
jQuery(document).delegate("div[name=lowStock]", "click", function(event) {
	jQuery('#lowStockCriteria').css('display','none');
	jQuery('#recentNotesTr').css('display','none');
	jQuery('#orderDiv').css('display', 'none');
	jQuery('#lowStockMedicineTypeCriteria').css('display','block');
	jQuery('#orderManagement').css('height','80px');
	jQuery('input[name=startDate]').val('');
	jQuery('input[name=endDate]').val('');
	MEDICINE.selectSearchCriteria(this);
});
jQuery(document).delegate("div[name=recentNotes]", "click", function(event) {
	MEDICINE.selectSearchCriteria(this);
	jQuery('#orderDiv').css('display', 'none');
	jQuery('#lowStockCriteria').css('display','block');
	jQuery('#recentNotesTr').css('display','block');
	jQuery('#lowStockMedicineTypeCriteria').css('display','none');
	jQuery('#orderManagement').css('height','120px');
});
jQuery(document).delegate("div[name=executedOrder]", "click", function(event) {
	jQuery('#lowStockCriteria').css('display','block');
	jQuery('#orderDiv').css('display', 'none');
	jQuery('#recentNotesTr').css('display','none');
	jQuery('#lowStockMedicineTypeCriteria').css('display','none');
	jQuery('#orderManagement').css('height','100px');
	MEDICINE.selectSearchCriteria(this);
});