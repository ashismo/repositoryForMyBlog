
var allMedicines = new Array();
var allBatches = new Array();
var allCompanies = new Array();
var currentMedicineDetailsId = 0;
var currentInvoiceId=0;
var currentPurchaseDate = '';
var customerInvoiceSelectedRow = {};
var addMedicineIntoInvoiceRow = {};
var paymentOptions = [
		{paymentId:'Cash',paymentName:'Cash'},
		{paymentId:'CreditCard',paymentName:'Credit Card'},
		{paymentId:'DebitCard',paymentName:'Debit Card'},
		{paymentId:'Cheque',paymentName:'Cheque'}
];

function paymentFormatter(value){
	for(var i=0; i<paymentOptions.length; i++){
		if (paymentOptions[i].paymentId == value) return products[i].paymentName;
	}
	return value;
}

function medicineListFormatter(value){
//	alert(allMedicines);
	for(var i=0; i<allMedicines.length; i++){
		if (allMedicines[i].medicineId == value) {
			return allMedicines[i].medicineName;
		}
	}
	return value;
}

function batchListFormatter(value){
//	alert(allMedicines);
	for(var i=0; i<allBatches.length; i++){
		if (allBatches[i].batchId == value) {
			return allBatches[i].batchName;
		}
	}
	return value;
}

function companyListFormatter(value){
//	alert(allMedicines);
	for(var i=0; i<allCompanies.length; i++){
		if (allCompanies[i].companyId == value) {
			return allCompanies[i].companyName;
		}
	}
	return value;
}

function addOrUpdateInvoice(url, formId) {
	MEDICINE.XHR(url, 'json', formId);
	var operationMsg = jQuery("#ajaxResponse").html();
	var jsonString = jQuery("#ajaxJsonResponse").html();
	var json = JSON.parse(jsonString);
	if(json.invoiceBean.errMsg != null) {
		MEDICINE.errMessage(json.invoiceBean.errMsg);
		return;
	} else {
		if(formId == 'invoiceDisplayFormPart2') {
			// display this success message when 2nd part of the form is committed.
			MEDICINE.operationMessage(json.invoiceBean.msg);
		}
		jQuery("input[name=invoiceDetailsId]").val(json.invoiceBean.invoiceDetailsId);
		jQuery("input[name=invoiceId]").val(json.invoiceBean.invoiceId);
		currentInvoiceId = json.invoiceBean.invoiceId;
//		jQuery('#invoiceDisplayForm input[name=purchaseDate]').val(json.invoiceBean.purchaseDate);
		currentPurchaseDate = jQuery('#invoiceDisplayForm input[name=purchaseDate]').val();
		//jQuery('#invoiceDisplayWindow').window('close');
	}
}

$(function(){
	var lastIndex;
	jQuery('#invoiceTable').datagrid({
		showFooter:true,
		toolbar:[{
			text:'append',
			iconCls:'icon-add',
			handler:function(){
				if(jQuery('#invoiceDisplayForm').form('validate') == false) {
					MEDICINE.errMessage("STOP: Please enter purchase date, customer and Doctor details");
	        		return false;
				}
				MEDICINE.enableForm('invoiceDisplayFormPart2');
				var invoiceId = jQuery('input[name=invoiceId]').val();
				if(invoiceId == '' || invoiceId == '0') {
					addOrUpdateInvoice('addOrUpdateInvoice.action?invoiceFormPart1=1','invoiceDisplayForm');
				}
				jQuery('#invoiceTable').datagrid('endEdit', lastIndex);
				jQuery('#invoiceTable').datagrid('appendRow',{
					invoiceDetailsId:'',
					medicineDetailsId:'',
					slNo:'',
					soldoutStock:'',
					medicineId:'',
					batchName:'',
					companyName:'',
					mfgDate:'',
					expDate:'',
					schedule:'',
					totalPrice:'',
					stock:'',
					availableStock:'',
					purchaseDate:'',
					unitPrice:''
				});
				lastIndex = jQuery('#invoiceTable').datagrid('getRows').length-1;
				
				jQuery('#invoiceTable').datagrid('selectRow', lastIndex);
//				jQuery('#invoiceTable').datagrid('beginEdit', lastIndex);
				addMedicineIntoInvoice(0,'');
				currentMedicineDetailsId = 0;
				resetAllMedicineDetailsFields();
			}
		},'-',{
			text:'delete',
			iconCls:'icon-remove',
			handler:function(){
				var row = $('#invoiceTable').datagrid('getSelected');
				if (row){
					jQuery.messager.confirm('Confirm','Want to remove this item?',function(r){  
			            if (r){  
							var index = jQuery('#invoiceTable').datagrid('getRowIndex', row);
							deleteMedicineIntoInvoice('addMedicineIntoInvoiceForm');
							jQuery('#invoiceTable').datagrid('deleteRow', index);
							currentMedicineDetailsId = 0;
							resetAllMedicineDetailsFields();
			            }
					});
				}
			}
		},'-',{
			text:'accept',
			iconCls:'icon-save',
			handler:function(){
				if(jQuery('#invoiceDisplayForm').form('validate') == false) {
					MEDICINE.enableForm('invoiceDisplayForm');
				} else {
					jQuery('#invoiceTable').datagrid('acceptChanges');
				}
			}
		},'-',{
			text:'GetChanges',
			iconCls:'icon-search',
			handler:function(){
				var rows = jQuery('#invoiceTable').datagrid('getChanges');
				alert('changed rows: ' + rows.length + ' lines');
			}
		}],
		onBeforeLoad:function(){
			jQuery(this).datagrid('rejectChanges');
		},
		onClickCell:function(rowIndex, colIndex){
			
			if(colIndex == 'batchName') {
				var selected = jQuery('#invoiceTable').datagrid('getRows');
				if (selected != null || selected != undefined){
					medicineId = selected[rowIndex].medicineId;
				}
			}
		},
		onClickRow:function(rowIndex, rowData){
			if (lastIndex != rowIndex){
				jQuery('#invoiceTable').datagrid('endEdit', lastIndex);
//				jQuery('#invoiceTable').datagrid('beginEdit', rowIndex);
//				resetAllMedicineDetailsFields();
			}
			lastIndex = rowIndex;
			addMedicineIntoInvoice(rowData.medicineName, '');
			var row = jQuery('#invoiceTable').datagrid('getSelected');  
			if (row){  
				loadMedicineBatchListByMedicineId(row['medicineId']);
//				if(row.invoiceDetailsId == '') {
					row['medicineDetailsId'] = jQuery('.datagrid-row-selected td[field=medicineDetailsId] div').html();
					row['invoiceId'] = jQuery('.datagrid-row-selected td[field=invoiceId] div').html();
					row['invoiceDetailsId'] = jQuery('.datagrid-row-selected td[field=invoiceDetailsId] div').html();
					row['companyId'] = jQuery('.datagrid-row-selected td[field=companyId] div').html();
					row['medicineId'] = jQuery('.datagrid-row-selected td[field=medicineId] div').html();
					row['batchId'] = jQuery('.datagrid-row-selected td[field=batchId] div').html();
					row['medicineName'] = jQuery('.datagrid-row-selected td[field=medicineName] div').html();
					row['batchName'] = jQuery('.datagrid-row-selected td[field=batchName] div').html();
					row['companyName'] = jQuery('.datagrid-row-selected td[field=companyName] div').html();
					row['mfgDate'] = jQuery('.datagrid-row-selected td[field=mfgDate] div').html();
					row['expDate'] = jQuery('.datagrid-row-selected td[field=expDate] div').html();
					row['schedule'] = jQuery('.datagrid-row-selected td[field=schedule] div').html();
					row['soldoutStock'] = jQuery('.datagrid-row-selected td[field=soldoutStock] div').html();
					row['medDose'] = jQuery('.datagrid-row-selected td[field=medDose] div').html();
					row['medWeight'] = jQuery('.datagrid-row-selected td[field=medWeight] div').html();
					row['medType'] = jQuery('.datagrid-row-selected td[field=medType] div').html();
					row['stock'] = jQuery('.datagrid-row-selected td[field=stock] div').html();
					row['availableStock'] = jQuery('.datagrid-row-selected td[field=availableStock] div').html();
					row['purchaseDate'] = jQuery('.datagrid-row-selected td[field=purchaseDate] div').html();
					row['unitPrice'] = jQuery('.datagrid-row-selected td[field=totalPrice] div').html();
					row['soldoutUnitPrice'] = (jQuery('.datagrid-row-selected td[field=totalPrice] div').html())/
										(jQuery('.datagrid-row-selected td[field=soldoutStock] div').html());
					currentMedicineDetailsId = row.medicineDetailsId;
				// Load data from row to new form
				jQuery('#addMedicineIntoInvoiceDiv').form('load',row); 
				addMedicineIntoInvoiceRow = row;
				if(currentMedicineDetailsId != '' && parseInt(currentMedicineDetailsId) > 0 ) {
					// disable company id, medicine id and batch id
					MEDICINE.disableForm('addMedicineIntoInvoiceForm');
					var $form = jQuery('#addMedicineIntoInvoiceForm'),
					$inputs = $form.find("textarea,input");
					$inputs.each(function(){
						if($(this).attr('numberboxname') == 'soldoutStock') {
							$(this).removeAttr('disabled');
						}
						if($(this).attr('name') == 'schedule') {
							$(this).removeAttr('disabled');
						}
					});
				}
				var elemArray = ['mfgDate','expDate','medicineName','batchName'];
				MEDICINE.disableFields('', elemArray);
				
			} 
		}
	});
});

function updateAvailableStock(totalStock) {
	var batchId = jQuery('input[name=batchId]').val();
	var medicineId = jQuery('input[name=medicineId]').val();
	if(medicineId == -1 || batchId == -1) {
		var stock = jQuery(totalStock).val();
		jQuery('input[name=availableStock').val(stock);
	}
}


function resetMedicineIntoInvoiceRow() {
	var batchId = jQuery('input[name=batchId]').val();
	var medicineId = jQuery('input[name=medicineId]').val();
	var companyId = jQuery('input[name=companyId]').val();
	var invoiceDetailsId = jQuery('input[name=invoiceDetailsId]').val();
	var medicineDetailsId = jQuery('input[name=medicineDetailsId]').val();
	if(medicineDetailsId=='') invoiceDetailsId=0;
	if(invoiceDetailsId=='') invoiceDetailsId=0;
	addMedicineIntoInvoiceRow.invoiceDetailsId = invoiceDetailsId ;
	addMedicineIntoInvoiceRow.batchId=batchId;
	addMedicineIntoInvoiceRow.medicineId=medicineId;
	addMedicineIntoInvoiceRow.companyId=companyId;
	addMedicineIntoInvoiceRow.mfgDate=null;
	addMedicineIntoInvoiceRow.expDate=null;
	addMedicineIntoInvoiceRow.schedule=null;
	addMedicineIntoInvoiceRow.stock=null;
	addMedicineIntoInvoiceRow.purchaseDate=null;
	addMedicineIntoInvoiceRow.unitPrice=null;
	addMedicineIntoInvoiceRow.soldoutUnitPrice=null;
	addMedicineIntoInvoiceRow.medDose=null;
	addMedicineIntoInvoiceRow.medWeight=null;
	addMedicineIntoInvoiceRow.soldoutStock = null;
	addMedicineIntoInvoiceRow.availableStock = 0;
//	addMedicineIntoInvoiceRow.medicineDetailsId = 0;
}

function loadMedicineListVar(companyId) {
	MEDICINE.XHR('getAllMedicines.action', 'json', '');
	var jsonString = jQuery("#ajaxJsonResponse").html();
	var json = JSON.parse(jsonString);
//	alert(JSON.stringify(json.medicineBean.medicineList[1].medicineName));
	for(var i = 0; i < json.medicineBean.medicineList.length; i++) {
		allMedicines[i] = json.medicineBean.medicineList[i];
	}
}

function loadMedicineBatchList(medicineId) {
	MEDICINE.XHR('getMedicineDetailsByMedicineId.action?medicineId='+medicineId + '&medicineName='+medicineId, 'json', '');
	var jsonString = jQuery("#ajaxJsonResponse").html();
	var json = JSON.parse(jsonString);
	for(var i = 0; i < json.medicineBean.batchList.length; i++) {
		allBatches[i] = json.medicineBean.batchList[i];
	}
}

function loadCompanyList() {
	MEDICINE.XHR('getAllCompanies.action', 'json', '');
	var jsonString = jQuery("#ajaxJsonResponse").html();
	var json = JSON.parse(jsonString);
	for(var i = 0; i < json.companyBean.companyList.length; i++) {
		allCompanies[i] = json.companyBean.companyList[i];
	}
}

$().ready(function(){
	jQuery('.datebox').datebox({   
	    formatter:function(date) {
			return formatDate(date);
		}
	});
	jQuery('input[name=soldoutStock]').numberbox({  
	    min:1 
	});
	jQuery('input[name=stock]').numberbox({  
	    min:1 
	});
	jQuery('input[name=unitPrice]').numberbox({  
	    min:0,  
	    precision:2  
	});
	jQuery('input[name=soldoutUnitPrice]').numberbox({  
	    min:0,  
	    precision:2  
	});
	jQuery('input[name=vat]').numberbox({  
	    min:0,  
	    precision:2  
	});
	jQuery('input[name=discount]').numberbox({  
	    min:0,  
	    precision:2  
	});
//	jQuery('input[name=totalAmt]').numberbox({  
//	    min:0,  
//	    precision:2  
//	});
//	jQuery('input[name=due]').numberbox({  
//	    min:0,  
//	    precision:2  
//	});
	jQuery('input[name=totalPaid]').numberbox({  
	    min:0,  
	    precision:2  
	});
	jQuery('#paymentMode').combobox({
		filter: function(q, row){
			return row['text'].toLowerCase().indexOf(q.toLowerCase()) != -1;
		}
	});
//	loadMedicineListVar();
//	loadMedicineBatchList('dummy');
//	loadCompanyList();
	MEDICINE.clear();
	jQuery('#companyList').combobox({
	    onChange:function(newValue,oldValue){
	        if(newValue == -1) {
	        	resetAllMedicineDetailsFields();
	        } else {
	        	resetAllMedicineDetailsFields();
	        	loadMedicineList(newValue);
	        }
	    },
	    filter: function(q, row){
			return row['text'].toLowerCase().indexOf(q.toLowerCase()) != -1;
		}
	});
	jQuery('#medicineNameList').combobox({
	    onChange:function(newValue,oldValue){
	        if(newValue == -1) {
	        	/*jQuery('#medicineNameListOther').css('display', 'inline');
	        	jQuery('#medicineNameTr').attr('height', '90');
	        	jQuery('#medicineNameListOther input[name=medicineName]').attr('class', 'easyui-validatebox inp-form');
	        	jQuery('#medicineBatchListOther input[name=medicineName]').removeAttr('disabled');
	        	// display unit price(Purchased) field
	        	displayPurchasedPriceRow();
	        	
	        	loadBatchList();*/
	        	MEDICINE.operationMessage("Please select a valid value");
	        	currentMedicineDetailsId  = 0;
//	        	resetFormDetails();
//	        	jQuery('#medicineNameList').combobox('clear');
	        } else {
	        	jQuery('#medicineNameListOther').css('display', 'none');
	        	jQuery('#availableStockTr').removeAttr('style');
	        	jQuery('#medicineNameTr').attr('height', '45');
	        	jQuery('#medicineNameListOther input[name=medicineName]').removeAttr('class');
//	        	hidePurchasedPriceRow();
	        	loadMedicineBatchListByMedicineId(newValue);
	        }
	    },
	    filter: function(q, row){
			return row['medicineNameList'].toLowerCase().indexOf(q.toLowerCase()) != -1;
		}
	});
	jQuery('#medicineBatchList').combobox({
	    onChange:function(newValue,oldValue){
	        if(newValue == -1) {
	        	/*jQuery('#medicineBatchListOther').css('display', 'inline');
	        	jQuery('#medicineBatchTr').attr('height', '90');
	        	jQuery('#medicineBatchListOther input[name=batchName]').attr('class', 'easyui-validatebox inp-form');
	        	jQuery('#medicineBatchListOther input[name=batchName]').removeAttr('disabled');
	        	jQuery('input[name=batchId]').val("-1");
//	        	alert(jQuery('input[name=batchId]').val());
	        	resetMedicineIntoInvoiceRow();
	        	jQuery('#addMedicineIntoInvoiceDiv').form('load',addMedicineIntoInvoiceRow); 
	        	
	        	displayPurchasedPriceRow();*/
	        	MEDICINE.operationMessage("Please select a valid value");
	        	currentMedicineDetailsId  = 0;
//	        	resetFormDetails();
//	        	jQuery('#medicineBatchList').combobox('clear');
	        } else if(newValue != null){
	        	jQuery('#medicineBatchListOther').css('display', 'none');
	        	jQuery('#medicineBatchTr').attr('height', '45');
	        	jQuery('#availableStockTr').removeAttr('style');
	        	jQuery('#medicineBatchListOther input[name=batchName]').removeAttr('class');
	        	var medicineId = jQuery('input[name=medicineId]').val();
	        	var medicineDetails = getMedicineByBatchAndMedicineId(newValue, medicineId);
	        	if(medicineDetails.medicineBean.total > 1) {
	        		displayMultipleMedicineDetails(newValue, medicineId);
	        	} else {
	        		populateAllMedicineDetailsFromMedicineBean(medicineDetails.medicineBean);
	        	}
	        	hidePurchasedPriceRow();
	        }
	    },
	    filter: function(q, row){
			return row['batchName'].toLowerCase().indexOf(q.toLowerCase()) != -1;
		}
	});
	jQuery('#customerList').combobox({
	    onChange:function(newValue,oldValue){
	        if(newValue == -1) {
	        	jQuery('#customerListOther').css('display', 'inline');
	        	jQuery('#customerNameTr').attr('height', '90');
	        	jQuery('#customerListOther input[name=customerName]').attr('class', 'easyui-validatebox inp-form');
	        	jQuery('input[name=customerAddr1]').val('');
	        	jQuery('input[name=customerMob1]').val('');
	        } else {
	        	jQuery('#customerListOther').css('display', 'none');
	        	jQuery('#customerNameTr').attr('height', '45');
	        	jQuery('#customerListOther input[name=customerName]').removeAttr('class');
	        	
	        	var json = getCustomerDetailsByCustomerId(newValue);
	        	jQuery('input[name=customerAddr1]').val(json.customerBean.customerAddr1);
	        	jQuery('input[name=customerMob1]').val(json.customerBean.mob1);
	        }
	    },
	    filter: function(q, row){
			return row['customerName'].toLowerCase().indexOf(q.toLowerCase()) != -1;
		}
	});
	jQuery('#doctorList').combobox({
	    onChange:function(newValue,oldValue){
	        if(newValue == -1) {
	        	jQuery('#doctorListOther').css('display', 'inline');
	        	jQuery('#doctorNameTr').attr('height', '90');
	        	jQuery('#doctorListOther input[name=doctorName]').attr('class', 'easyui-validatebox inp-form');
	        	jQuery('input[name=doctorAddr1]').val('');
	        	jQuery('input[name=doctorMob1]').val('');
	        } else {
	        	jQuery('#doctorListOther').css('display', 'none');
	        	jQuery('#doctorNameTr').attr('height', '45');
	        	jQuery('#doctorListOther input[name=doctorName]').removeAttr('class');
	        	
	        	var json = getDoctorDetailsByDoctorId(newValue);
	        	jQuery('input[name=doctorAddr1]').val(json.doctorBean.doctorAddr1);
	        	jQuery('input[name=doctorMob1]').val(json.doctorBean.mob1);
	        }
	    },
	    filter: function(q, row){
			return row['doctorName'].toLowerCase().indexOf(q.toLowerCase()) != -1;
		}
	});
	
	jQuery('#paymentMode').combobox({
	    filter: function(q, row){
			return row['text'].toLowerCase().indexOf(q.toLowerCase()) != -1;
		}
	});
	
});

function searchInvoice(value,name) {
	var isDeleted = false;
	var searchByCriteria = jQuery('.searchbox-text').attr('name');
	jQuery('#searchCriteria').children('div').each(function(){
		if(jQuery(this).attr('name') == searchByCriteria) {
			jQuery(this).attr('iconCls', 'icon-ok');
		}
	});
	// Create a panel to list companies
	jQuery('#invoiceDiv').css('display', 'block');

	var startDate = jQuery('input[name=startDate]', '#invoiceDateCriteria').val();
	if(startDate == null || startDate == undefined) startDate = '';
	var endDate = jQuery('input[name=endDate]', '#invoiceDateCriteria').val();
	if(endDate == null || endDate == undefined) endDate = '';
	if(startDate != '' || endDate != '') {
		if(compareDates(startDate, endDate) == 1) {
			MEDICINE.errMessage("End Date must be greater than Start date");
			return false;
		}
	}
	
	var nameValue = name + "=" + value + "&startDate=" + startDate + "&endDate=" + endDate;
	
	jQuery('#invoiceListTable').datagrid({
		title:'Search Result',
		iconCls:'icon-save',
		width:900,
		height:400,
		nowrap: false,
		striped: true,
		collapsible:true,
		url:'searchInvoice.action?' + nameValue,
		sortName: 'code',
		sortOrder: 'desc',
		remoteSort: false,
		idField:'code',
		frozenColumns:[[
            {title:'doctorMob1',field:'doctorMob1',width:80,sortable:true,hidden:true},
            {title:'purchaseDate',field:'purchaseDate',width:80,sortable:true,hidden:true},
            {title:'customerId',field:'customerId',width:80,sortable:true,hidden:true},
            {title:'doctorId',field:'doctorId',width:80,sortable:true,hidden:true}
		]],
		columns:[[
		    {title:'Customer Information',colspan:5},
	        {title:'Base Information',colspan:6}
//			{field:'opt',title:'Delete',width:60,align:'center', rowspan:2,
//	        	formatter:function(value,row,index){  
//		        	var d = '<a href="#">Delete</a>';  
//	                return d;
//            	}
//			}
			
		],[
		    {title:'Invoice<br/>Id',field:'invoiceId',width:50,sortable:true},
			{field:'purchaseDateActual',title:'Billed Date',width:80,rowspan:2,sortable:true,
				formatter: function(value,row,index){
					var date = getDateFromString(value);
					return date;
				}
			},
			{field:'customerName',title:'Name',width:100, sortable:true,
				formatter:function(value,row,index){  
					var d = '<a href="#">' + value +'</a>';  
	                return d; 
	        	}
			},
			{field:'customerAddr1',title:'Address',width:150,rowspan:2,sortable:true,
				sorter:function(a,b){
					return (a>b?1:-1);
				}
			},
			{field:'customerMob1',title:'Contact',width:80,rowspan:2},
			{field:'doctorName',title:'Doctor',width:80,rowspan:2},
			{field:'grandTotalPrice',title:'Total Amount',width:75,rowspan:2,
				formatter:function(value,row,index){  
	            	return value.toFixed(2); 
	    		}
			},
			{field:'totalPaid',title:'Total Paid',width:75,rowspan:2,
				formatter:function(value,row,index){  
	            	return value.toFixed(2); 
	    		}
			},
			{field:'due',title:'Due Amount',width:70,rowspan:2,
				formatter:function(value,row,index){  
                	return value.toFixed(2); 
        		}
			},
			{field:'printIndicator',title:'Printed',width:50,rowspan:2},
			{field:'billNo',title:'Bill No',width:50,rowspan:2,
				formatter:function(value,row,index){  
					if(value == 0) value = '';
	            	return value; 
	    		}
			}
		]],
		pagination:true,
		rownumbers:true,
//		toolbar:[{
//			id:'btnadd',
//			text:'Export',
//			iconCls:'icon-add',
//			handler:function(){
//				jQuery('#btnsave').linkbutton('enable');
//				alert('Export');
//			}
//		},{
//			id:'btncut',
//			text:'Print',
//			iconCls:'icon-print',
//			handler:function(){
//				jQuery('#btnsave').linkbutton('enable');
//				alert('Print');
//			}
//		}],
		onClickCell:function(rowIndex, colIndex){
			if(colIndex == 'opt') {
				var selected = jQuery('#invoiceListTable').datagrid('getRows');
				if (selected != null || selected != undefined){
					jQuery('#hiddenFieldForm').children('input').each(function(){
						if(jQuery(this).attr('name') == 'invoiceId') {
							invoiceId = selected[rowIndex].invoiceId;
							jQuery(this).val(invoiceId);
						}
					});
					jQuery('#hiddenFieldForm input[name=invoiceId]').val(currentInvoiceId);
					jQuery('#addMedicineIntoInvoiceForm input[name=invoiceId]').val(currentInvoiceId);
					deleteInvoice('hiddenFieldForm');
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
			currentInvoiceId = rowData.invoiceId;
			if(isDeleted == false) {
				// Reset customer addition form and doctor addition form
				jQuery('#doctorListOther').css('display', 'none');
		    	jQuery('#doctorNameTr').attr('height', '45');
		    	jQuery('#doctorListOther input[name=doctorName]').removeAttr('class');
		    	jQuery('#customerListOther').css('display', 'none');
	        	jQuery('#customerNameTr').attr('height', '45');
	        	jQuery('#customerListOther input[name=customerName]').removeAttr('class');
	        	
		    	
				jQuery('input[name=invoiceId]').val(rowData.invoiceId);
//				var row = $('#invoiceListTable').datagrid('getSelected');
				customerInvoiceSelectedRow = rowData;
				displayInvoice(rowData.invoiceId, rowData);
//				var rows = jQuery('#invoiceTable').datagrid('getRows');
//				if(rows.length == 0) {
//					MEDICINE.disableForm('invoiceDisplayFormPart2');
//				}
			}
		}
	});
	var p = jQuery('#invoiceListTable').datagrid('getPager');
	jQuery(p).pagination({
		onBeforeRefresh:function(){
		}
	});
	function getSelected(){
		var selected = jQuery('#invoiceListTable').datagrid('getSelected');
		if (selected){
			alert(selected.invoiceId+":"+selected.invoiceName+":"+selected.invoiceAddr1+":"+selected.phone1);
		}
	}
}

function displayInvoice(invoiceId, row) {
//	MEDICINE.disableForm('invoiceDisplayForm');
	loadDoctorList();
	loadCustomerList();
	// Calculate total Price
	if(row) {
		var totalAmt = row.grandTotalPrice;// + (row.grandTotalPrice * row.vat/100);
		if(totalAmt != null && totalAmt != undefined && !isNaN(totalAmt)) {
			row.totalAmt = totalAmt.toFixed(2);
		}
		var due = row.due;
		if(due != null && due != undefined && !isNaN(due) && due != '') {
			row.due = parseFloat(due).toFixed(2);
		}
	}
	jQuery('#invoiceDisplayForm').form('load', row);
	jQuery('#invoiceDisplayForm').css('display', 'block');
	jQuery('#invoiceDisplayWindow').window('open');
	jQuery('.validatebox-tip').css('display', 'none');
	if(invoiceId == 0){
		jQuery('#invoiceDisplayWindow').dialog('setTitle','Create Invoice'); 
		jQuery('.deleteInvoice').css('display', 'none');
	} else {
		jQuery('#invoiceDisplayWindow').dialog('setTitle','Update Invoice'); 
		jQuery('.deleteInvoice').css('display','');
	}
	refreshMedicineDetailsIntoInvoiceTable();
	MEDICINE.enableForm('invoiceDisplayForm');
	jQuery('#invoiceDisplayFormPart2').form('load', row);
	MEDICINE.enableForm('invoiceDisplayFormPart2');
	
}

function refreshMedicineDetailsIntoInvoiceTable() {
	// workaround: update invoice id inside hidden form
	jQuery('#hiddenFieldForm input[name=invoiceId]').val(currentInvoiceId);
	MEDICINE.XHR('viewInvoice.action', 'json', 'hiddenFieldForm');
	var operationMsg = jQuery("#ajaxResponse").html();
	var jsonString = jQuery("#ajaxJsonResponse").html();
	var data = JSON.parse(jsonString);
	jQuery('#invoiceTable').datagrid('loadData', data);
}
function addMedicineIntoInvoice(medicineDetailsId, url) {
	jQuery('#addMedicineIntoInvoiceDiv').form('load', url);
	jQuery('#addMedicineIntoInvoiceDiv').css('display', 'block');
	jQuery('#addMedicineIntoInvoiceWindow').window('open');
	jQuery('.validatebox-tip').css('display', 'none');
	if(medicineDetailsId == 0){
		jQuery('#addMedicineIntoInvoiceWindow').dialog('setTitle','Create Invoice'); 
		jQuery('.deleteInvoice').css('display', 'none');
	} else {
		jQuery('#addMedicineIntoInvoiceWindow').dialog('setTitle','Update Invoice'); 
		jQuery('.deleteInvoice').css('display','');
	}
	var companyId = jQuery('input[name=companyId]').val();
	loadMedicineList(companyId);
	// reset form
	resetAllMedicineDetailsFields();
	MEDICINE.enableForm('addMedicineIntoInvoiceForm');
}


jQuery(document).delegate(".submitMedicineIntoInvoice", "click", function(event) {
	submitMedicineIntoInvoice();
});

jQuery(document).delegate(".deleteMedicineIntoInvoice", "click", function(event) {
	deleteMedicineIntoInvoice('addMedicineIntoInvoiceForm');
});

function deleteMedicineIntoInvoice(formName) {
	jQuery.messager.confirm('Confirm','Delete this Medicine Details?',function(r){  
        if (r){  
        	MEDICINE.enableForm('addMedicineIntoInvoiceForm');
        	MEDICINE.XHR('deleteMedicineIntoInvoice.action', 'json', formName);
        	var operationMsg = jQuery("#ajaxResponse").html();
        	MEDICINE.operationMessage(operationMsg);
        	jQuery('#addMedicineIntoInvoiceWindow').dialog('close');
        	refreshMedicineDetailsIntoInvoiceTable();
        	calculateGrandTotal();
        }  
    });
}

function calculateDue(paidAmount) {
	var rows = jQuery('#invoiceTable').datagrid('getFooterRows');
	var totalBill = rows[0]['totalPrice'];
	var vat = jQuery('#invoiceDisplayFormPart2 input[numberboxname=vat]').val();
	var discount = jQuery('#invoiceDisplayFormPart2 input[numberboxname=discount]').val();
	totalBill = totalBill + (totalBill*(vat - discount)/100);
	totalBill = totalBill.toFixed(2);
	var due = totalBill - jQuery(paidAmount).val();
	if(due != null && due != undefined) {
		due = due.toFixed(2);
	}
	//jQuery('#invoiceDisplayFormPart2 input[name=totalAmt]').val(totalBill);
	jQuery('#invoiceDisplayFormPart2 input[name=due]').val(due);
};

function calculateTotal(vat) {
	var rows = jQuery('#invoiceTable').datagrid('getFooterRows');
	var totalBill = rows[0]['totalPrice'];
	var discount = jQuery('#invoiceDisplayFormPart2 input[numberboxname=discount]').val();
	totalBill = totalBill + (totalBill*(jQuery(vat).val() - discount)/100);
	totalBill = totalBill.toFixed(2);
	jQuery('#invoiceDisplayFormPart2 input[name=totalAmt]').val(totalBill);
	calculateDue(jQuery('#invoiceDisplayFormPart2 input[numberboxname=totalPaid]'));
};

jQuery(document).delegate(".submitCreateEditInvoice", "click", function(event) {
	var paidAmt = jQuery('input[numberboxname=totalPaid]').val();
	var purchaseDate = currentPurchaseDate;
	if(currentPurchaseDate == '') {
		purchaseDate = jQuery('#invoiceDisplayForm input[name=purchaseDate]').val();
	} 
	if(jQuery('#invoiceDisplayForm').form('validate') == false) {
		MEDICINE.enableForm('invoiceDisplayForm');
		return;
	}
	if(jQuery('#invoiceDisplayFormPart2').form('validate') == false) {
		MEDICINE.enableForm('invoiceDisplayFormPart2');
		return;
	}
	//  Workaround - as the paid amount was getting 0 after executing the if block
	jQuery('input[numberboxname=totalPaid]').val(paidAmt);
	jQuery('input[name=totalPaid]').val(paidAmt);
	// Workaround - as the purchased date is getting deleted once the invoice is saved.
	jQuery('#invoiceDisplayForm input[name=purchaseDate]').val(purchaseDate);
	
	// check if atleast one medicine details entered into the invoice
	var invoiceId = jQuery('input[name=invoiceId]').val();
	if(invoiceId != '' && invoiceId != 0) {
		// workaround: update invoice id inside hidden form
		jQuery('#hiddenFieldForm input[name=invoiceId]').val(currentInvoiceId);
		MEDICINE.XHR('viewInvoice.action', 'json', 'hiddenFieldForm');
		var operationMsg = jQuery("#ajaxResponse").html();
		var jsonString = jQuery("#ajaxJsonResponse").html();
		var data = JSON.parse(jsonString);
		if(data.rows.length == 0) {
			MEDICINE.errMessage("Please add atleast one medicine into invoice");
			return false;
		}
		addOrUpdateInvoice('addOrUpdateInvoice.action?invoiceFormPart1=1&invoiceId=' + invoiceId,'invoiceDisplayForm');
		addOrUpdateInvoice('addOrUpdateInvoice.action?invoiceFormPart2=1&invoiceId=' + invoiceId,'invoiceDisplayFormPart2');
		var param = getAttrNameOfSelectedCriteria();
    	var value = jQuery('.searchbox-text').val();
    	if(value == 'Search Invoice') {
    		value = '';
    	}
    	searchInvoice(value,param);
	}
});

jQuery(document).delegate(".payCreateEditInvoice", "click", function(event) {
	if(jQuery('#invoiceDisplayForm').form('validate') == false) {
		MEDICINE.enableForm('invoiceDisplayForm');
		return;
	}
	if(jQuery('#invoiceDisplayFormPart2').form('validate') == false) {
		MEDICINE.enableForm('invoiceDisplayFormPart2');
		return;
	}
	
	// check if atleast one medicine details entered into the invoice
	var invoiceId = jQuery('input[name=invoiceId]').val();
	if(invoiceId != '' && invoiceId != 0) {
		// workaround: update invoice id inside hidden form
		jQuery('#hiddenFieldForm input[name=invoiceId]').val(currentInvoiceId);
		MEDICINE.XHR('viewInvoice.action', 'json', 'hiddenFieldForm');
		var operationMsg = jQuery("#ajaxResponse").html();
		var jsonString = jQuery("#ajaxJsonResponse").html();
		var data = JSON.parse(jsonString);
		if(data.rows.length == 0) {
			MEDICINE.errMessage("Please add atleast one medicine into invoice");
			return false;
		}
		// Ashish - 
		payAmount();
//		addOrUpdateInvoice('addOrUpdateInvoice.action?invoiceFormPart1=1&invoiceId=' + invoiceId,'invoiceDisplayForm');
//		addOrUpdateInvoice('addOrUpdateInvoice.action?invoiceFormPart2=1&invoiceId=' + invoiceId,'invoiceDisplayFormPart2');
	}
});

function payAmount() {
	jQuery('#paymentWindow').window('open');
	var lastIndex;
	jQuery('#paymentTable').datagrid({
		showFooter:true,
		title:'Payment Details',
		iconCls:'icon-edit',
		width:750,
		height:430,
		singleSelect:true,
		nowrap: false,
		striped: true,
		collapsible:true,
		url:'transactionDetailsByInvoiceId.action?invoiceId='+ currentInvoiceId,
		sortName: 'code',
		sortOrder: 'desc',
		remoteSort: false,
		idField:'code',
		frozenColumns:[[
            {title:'transactionDetailsId',field:'transactionDetailsId',width:80,sortable:true,hidden:true},
            {title:'invoiceId',field:'invoiceId',width:80,sortable:true,hidden:true}
		]],

		toolbar:[{
			text:'Append Row',
			iconCls:'icon-add',
			handler:function(){
				jQuery('#paymentTable').datagrid('endEdit', lastIndex);
				jQuery('#paymentTable').datagrid('appendRow',{
					transactionDetailsId:'',
					invoiceId:'',
					paymentDate:'',
					totalPaid:'',
					paymentMode:'',
					cardNumber:'',
					transactionDesc:''
				});
				lastIndex = jQuery('#paymentTable').datagrid('getRows').length-1;
				jQuery('#paymentTable').datagrid('selectRow', lastIndex);
				jQuery('#paymentTable').datagrid('beginEdit', lastIndex);
			}
		}],
		columns:[[
					{field:'paymentDate',title:'Date',width:80,rowspan:2,required:true,
						editor:{type:'datebox',
						options:{
							formatter:function(date) {
										return formatDate(date);
									}
							}
						}
					},
					{field:'totalPaid',title:'Payment Amount',width:100,rowspan:2,required:true,
						editor:{type:'numberbox',
											options:{precision:2,
								required:true}
						}
					},
					{field:'paymentMode',title:'Payment Mode',width:100,rowspan:2,required:true,
						editor:{type:'combobox',
								options:{valueField:'paymentId',
											textField:'paymentName',
											data:paymentOptions,
								required:true}
						}
					},
					{field:'cardNumber',title:'Cheque Number',width:120,rowspan:2,editor:'text'},
					{field:'transactionDesc',title:'Description',width:220,rowspan:2,editor:'text'},
					{field:'action',title:'Action',width:100,rowspan:2,align:'center',  
		                formatter:function(value,row,index){  
		                    if (row.editing){  
		                        var s = '<a href="#" onclick="savePayment('+index+')">Save</a> ';  
		                        var c = '<a href="#" onclick="cancelEdit('+index+')">Cancel</a>';  
		                        return s+c;  
		                    } else {  
		                        var e = '<a href="#" onclick="editPayment('+index+')">Edit</a> ';  
		                        var d = '<a href="#" onclick="deletePayment('+index+')">Delete</a>';  
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
	    onLoadSuccess: function(data){  
	    	updatePaymentDetails(data.footer[0].totalPaid);
	    },
		pagination:true,
		rownumbers:true
	});
	
	var p = jQuery('#paymentTable').datagrid('getPager');
	jQuery(p).pagination({
		onBeforeRefresh:function(){
		}
	});
	function getSelected(){
		var selected = jQuery('#paymentTable').datagrid('getSelected');
		if (selected){
			alert(selected.orderId+":"+selected.orderName+":"+selected.orderAddr1+":"+selected.phone1);
		}
	}
}

// Print invoice
jQuery(document).delegate(".printCreateEditInvoice", "click", function(event) {
	if(jQuery('#invoiceDisplayForm').form('validate') == false) {
		MEDICINE.enableForm('invoiceDisplayForm');
		MEDICINE.errMessage("Please fill invoice details then save and print");
		return;
	}
	if(jQuery('#invoiceDisplayFormPart2').form('validate') == false) {
		MEDICINE.enableForm('invoiceDisplayFormPart2');
		MEDICINE.errMessage("Please fill invoice details then save and print");
		return;
	}
	var invoiceId = jQuery('input[name=invoiceId]').val();
	window.open('printInvoice.action?invoiceId='+invoiceId);
});

//// Delete invoice
//jQuery(document).delegate(".deleteInvoice", "click", function(event) {
//	deleteInvoice('');
//});

function deleteInvoice(formName) {
	jQuery.messager.confirm('Confirm','Delete this Invoice from Database?',function(r){  
        if (r){  
        	// check if invoice id is not null
        	var invoiceId = jQuery('input[name=invoiceId]').val();
        	if(invoiceId != '' && invoiceId != 0) {
	        	MEDICINE.XHR('deleteInvoice.action?invoiceId=' + invoiceId, 'json', formName);
	        	var operationMsg = jQuery("#ajaxResponse").html();
	        	MEDICINE.operationMessage(operationMsg);
	        	jQuery('#invoiceDisplayWindow').dialog('close');
	        	var param = getAttrNameOfSelectedCriteria();
	        	var value = jQuery('.searchbox-text').val();
	        	if(value == 'Search Invoice') {
	        		value = '';
	        	}
	        	searchInvoice(value,param);
        	} else {
        		MEDICINE.errMessage("Invoice has not been created into database");
        		return false;
        	}
        }  
    });
}

jQuery(document).delegate(".deleteInvoice", "click", function(event) {
	deleteInvoice('invoiceDisplayForm');
});

function cleardata(formId){
	jQuery(formId).form('clear');
}

jQuery(document).delegate(".addNewInvoice", "click", function(event) {
	loadDoctorList();
	loadCustomerList();
	jQuery('#hiddenFieldForm input[name=invoiceId]').val('');
	jQuery('#addMedicineIntoInvoiceForm input[name=invoiceId]').val('');
	displayInvoice('0', null);
	cleardata('#invoiceDisplayForm');
	cleardata('#invoiceDisplayFormPart2');
	
	// set default values
	currentInvoiceId = 0;
	currentPurchaseDate = '';
	jQuery('#invoiceDisplayFormPart2 input[numberboxname=vat]').val('0.00');
	jQuery('#invoiceDisplayFormPart2 input[numberboxname=discount]').val('0.00');
	jQuery('#invoiceDisplayFormPart2 input[numberboxname=totalPaid]').val('0.00');
	jQuery('#invoiceDisplayFormPart2 input[name=vat]').val('0.00');
	jQuery('#invoiceDisplayFormPart2 input[name=discount]').val('0.00');
	jQuery('#invoiceDisplayFormPart2 input[name=totalPaid]').val('0.00');
	
	// Reset customer addition form and doctor addition form
	jQuery('#doctorListOther').css('display', 'none');
	jQuery('#doctorNameTr').attr('height', '45');
	jQuery('#doctorListOther input[name=doctorName]').removeAttr('class');
	jQuery('#customerListOther').css('display', 'none');
	jQuery('#customerNameTr').attr('height', '45');
	jQuery('#customerListOther input[name=customerName]').removeAttr('class');
	
	jQuery('#statex').attr('selectedIndex', 0); 
});

function loadDoctorList() {
	MEDICINE.XHR('getAllDoctors.action', 'json', '');
	var jsonString = jQuery("#ajaxJsonResponse").html();
	var json = JSON.parse(jsonString);
	var data = json.doctorBean.doctorList;
	jQuery('#doctorList').combobox({  
	    data:data,  
	    valueField:'doctorId',  
	    textField:'doctorName',
	    onLoadError: function() {
			alert('Unable to fetch doctor list');
		},
		onLoadSuccess: function() {
		},
		formatter:function(row){  
			for(var i=0; i<data.length; i++){
				if (data[i].doctorId == row.doctorId) return data[i].doctorName;
			}
			return row.doctorId;
	    }  
	});
}

function loadCustomerList() {
	MEDICINE.XHR('getAllCustomers.action', 'json', '');
	var jsonString = jQuery("#ajaxJsonResponse").html();
	var json = JSON.parse(jsonString);
	var data = json.customerBean.customerList;
	jQuery('#customerList').combobox({  
	    data:data,  
	    valueField:'customerId',  
	    textField:'customerName',
	    onLoadError: function() {
			alert('Unable to fetch customer list');
		},
		onLoadSuccess: function() {
		},
		formatter:function(row){  
			for(var i=0; i<data.length; i++){
				if (data[i].customerId == row.customerId) return data[i].customerName;
			}
			return row.customerId;
	    }  
	});
}

function getCustomerDetailsByCustomerId(customerId) {
	MEDICINE.XHR('getCustomerByCustomerId.action?customerId=' + customerId, 'json', '');
	var jsonString = jQuery("#ajaxJsonResponse").html();
	var json = JSON.parse(jsonString);
	return json;
}

function getDoctorDetailsByDoctorId(doctorId) {
	MEDICINE.XHR('getDoctorByDoctorId.action?doctorId=' + doctorId, 'json', '');
	var jsonString = jQuery("#ajaxJsonResponse").html();
	var json = JSON.parse(jsonString);
	return json;
}

function getMedicineByBatchAndMedicineId(batchName,medicineId) {
	MEDICINE.XHR('getMedicineByBatchAndMedicineId.action?batchName='+batchName + '&medicineId=' + medicineId, 'json', '');
	var jsonString = jQuery("#ajaxJsonResponse").html();
	var json = JSON.parse(jsonString);
	return json;
}

function populateAllMedicineDetailsFromMedicineBean(rowData) {
	if(rowData != null && rowData.companyId != 0) {
		currentMedicineDetailsId = rowData.medicineDetailsId;
		jQuery('#addMedicineIntoInvoiceDiv').form('load',rowData);
	}
}

function resetAllMedicineDetailsFields() {
	resetFormDetails();
	jQuery('#medicineNameListOther').css('display', 'none');
	jQuery('#medicineBatchListOther').css('display', 'none');
}

function resetFormDetails() {
	jQuery('input[name=soldoutStock]').val('');
	jQuery('input[name=medicineId]').val('');
	jQuery('input[name=medicineName]').val('');
	jQuery('input[name=batchId]').val('');
	jQuery('input[name=batchName]').val('');
	jQuery('input[name=medDose]').val('');
	jQuery('input[name=medWeight]').val('');
	jQuery('input[name=mfgDate]').val('');
	jQuery('input[name=expDate]').val('');
	jQuery('input[name=schedule]').val('');
	jQuery('input[name=totalPrice]').val('');
	// reset hidden fields
	jQuery('input[name=stock]').val('');
	jQuery('input[name=purchaseDate]').val('');
	jQuery('input[name=unitPrice]').val('');
}

function displayPurchasedPriceRow() {
	var batchId = jQuery('input[name=batchId]').val();
	var medicineId = jQuery('input[name=medicineId]').val();
	if(medicineId == -1 || batchId == -1) {
		jQuery('#unitPriceTr').removeAttr('style');
		jQuery('#purchaseDateTr').removeAttr('style');
		jQuery('#stockTr').removeAttr('style');
		jQuery('#availableStockTr').css('display','none');
	}
}


function hidePurchasedPriceRow() {
	var batchId = jQuery('input[name=batchId]').val();
	var medicineId = jQuery('input[name=medicineId]').val();
	if(medicineId != -1 && batchId != -1) {
		jQuery('#unitPriceTr input[name=unitPrice]').removeAttr('required');
    	jQuery('#unitPriceTr').css('display','none');
    	jQuery('#purchaseDateTr input[comboname=purchaseDate]').removeAttr('required');
    	jQuery('#purchaseDateTr input[name=purchaseDate]').val('01/01/2001');
    	jQuery('#purchaseDateTr').css('display','none');
    	jQuery('#stockTr input[name=stock]').removeAttr('required');
    	jQuery('#stockTr').css('display','none');
	}
	
}

/*function submitMedicineIntoInvoice(){  
	jQuery('#addMedicineIntoInvoiceForm').form('submit',{  
        url: 'saveMedicineIntoInvoice.action?',  
        onSubmit: function(){
			if(currentMedicineDetailsId == 0) {
				var addedMedicineDetailsIds = [];
				var rows = jQuery('#invoiceTable').datagrid('getRows');
				if(rows != undefined && rows != null && rows.length > 0) {
					for(i=0; i < rows.length; i++) {
						addedMedicineDetailsIds.push(rows[i].medicineDetailsId);
					}
				}
				var batchId = jQuery('input[name=batchId]').val();
				var medicineId = jQuery('input[name=medicineId]').val();
				if(medicineId == -1 || batchId == -1) {
					// Save into database first then update row.
					MEDICINE.operationMessage("Please select a valid value of Medicine Name and Medicine Batch");
					return false;
				}
				// Check for duplicate entry
				jQuery.each(addedMedicineDetailsIds, function(index,value){
					if(value != '' && value == currentMedicineDetailsId) {
						MEDICINE.errMessage("STOP: Medicine already exists in the list. Please update record.");
						return false;
					}
				});
			}
			if(jQuery('#availableStockTr').css('display') != 'none') {
	            if(parseInt(jQuery('input[name=soldoutStock]').val()) > parseInt(jQuery('input[name=availableStock]').val()) ) {
	        		MEDICINE.errMessage("STOP: Available stock <" + jQuery('input[name=availableStock]').val() + "> " +
	        				"and trying to sell <" + jQuery('input[name=soldoutStock]').val() + ">");
	        		return false;
	        	}
			}
//			if(medicineDetailsIdDuplicationCheck(currentMedicineDetailsId) == true) {
//				MEDICINE.errMessage("STOP: Medicine with Duplicate Batch Id is not allowed");
//        		return false;
//			}
			MEDICINE.enableForm('addMedicineIntoInvoiceForm');
            return jQuery(this).form('validate');
        },  
        success: function(result){  
            var result = eval('('+result+')');  
            if (result.medicineBean.medicineDetailsId != 0){  
            	jQuery('#addMedicineIntoInvoiceWindow').dialog('close');      // close the dialog  
//            	jQuery('#invoiceTable').datagrid('reload');    // reload the user data  
            	var currentRow = jQuery('.datagrid-row-selected').attr('datagrid-row-index');
            	var rows = jQuery('#invoiceTable').datagrid('getRows');
            	var data = result.medicineBean;
            	
            	if(data.batchId == -1) {
            		data.batchId = data.batchName;
            	}
            	// update invoice row details into database
            	MEDICINE.XHR('addOrUpdateInvoiceDetails.action', 'json', 'addMedicineIntoInvoiceForm');
            	var operationMsg = jQuery("#ajaxResponse").html();
            	var jsonString = jQuery("#ajaxJsonResponse").html();
            	var json = JSON.parse(jsonString);
            	if(json.invoiceBean.errMsg != null) {
            		MEDICINE.errMessage(json.invoiceBean.errMsg);
            		return;
            	}
            	if(data.invoiceDetailsId == undefined) {
            		data.invoiceDetailsId = json.invoiceBean.invoiceDetailsId;
            		data.invoiceId = json.invoiceBean.invoiceId;
            	}
            	jQuery('.datagrid-row-selected td[field=medicineDetailsId] div').html(data.medicineDetailsId);
            	jQuery('.datagrid-row-selected td[field=soldoutStock] div').html(data.soldoutStock);
            	jQuery('.datagrid-row-selected td[field=invoiceId] div').html(data.invoiceId);
            	jQuery('.datagrid-row-selected td[field=invoiceDetailsId] div').html(data.invoiceDetailsId);
            	jQuery('.datagrid-row-selected td[field=medicineName] div').html(data.medicineName);
            	jQuery('.datagrid-row-selected td[field=batchName] div').html(data.batchName);
            	jQuery('.datagrid-row-selected td[field=companyName] div').html(data.companyName);
            	jQuery('.datagrid-row-selected td[field=mfgDate] div').html(data.mfgDate);
            	jQuery('.datagrid-row-selected td[field=expDate] div').html(data.expDate);
            	jQuery('.datagrid-row-selected td[field=schedule] div').html(data.schedule);
            	jQuery('.datagrid-row-selected td[field=totalPrice] div').html(data.soldoutStock * data.soldoutUnitPrice);
            	jQuery('.datagrid-row-selected td[field=medWeight] div').html(data.medWeight);
            	jQuery('.datagrid-row-selected td[field=medDose] div').html(data.medDose);
            	jQuery('.datagrid-row-selected td[field=medType] div').html(data.medType);
            	jQuery('.datagrid-row-selected td[field=medicineId] div').html(data.medicineId);
            	jQuery('.datagrid-row-selected td[field=companyId] div').html(data.companyId);
            	jQuery('.datagrid-row-selected td[field=batchId] div').html(data.batchId);
            	jQuery('.datagrid-row-selected td[field=schedule] div').html(data.schedule);
            	jQuery('.datagrid-row-selected td[field=stock] div').html(data.stock);
            	jQuery('.datagrid-row-selected td[field=purchaseDate] div').html(data.purchaseDate);
            	jQuery('.datagrid-row-selected td[field=unitPrice] div').html(data.unitPrice);
            	jQuery('.datagrid-row-selected td[field=availableStock] div').html(data.availableStock);
            	calculateGrandTotal();
            } else {  
            	jQuery.messager.show({  
                    title: 'Error',  
                    msg: result.errMsg  
                });  
            }  
        }  
    });  
}*/

function submitMedicineIntoInvoice(){  
			if(currentMedicineDetailsId == 0) {
				var addedMedicineDetailsIds = [];
				var rows = jQuery('#invoiceTable').datagrid('getRows');
				if(rows != undefined && rows != null && rows.length > 0) {
					for(i=0; i < rows.length; i++) {
						addedMedicineDetailsIds.push(rows[i].medicineDetailsId);
					}
				}
				var batchId = jQuery('input[name=batchId]').val();
				var medicineId = jQuery('input[name=medicineId]').val();
				if(medicineId == -1 || batchId == -1) {
					// Save into database first then update row.
					MEDICINE.operationMessage("Please select a valid value of Medicine Name and Medicine Batch");
					return false;
				}
				// Check for duplicate entry
				jQuery.each(addedMedicineDetailsIds, function(index,value){
					if(value != '' && value == currentMedicineDetailsId) {
						MEDICINE.errMessage("STOP: Medicine already exists in the list. Please update record.");
						
						var row = $('#invoiceTable').datagrid('getSelected');
						if (row){
							var ind = jQuery('#invoiceTable').datagrid('getRowIndex', row);
							deleteMedicineIntoInvoice('addMedicineIntoInvoiceForm');
							jQuery('#invoiceTable').datagrid('deleteRow', ind);
						}
						
						return false;
					}
				});
			}
			if(jQuery('#availableStockTr').css('display') != 'none') {
	            if(parseInt(jQuery('input[name=soldoutStock]').val()) > parseInt(jQuery('input[name=availableStock]').val()) ) {
	        		MEDICINE.errMessage("STOP: Available stock <" + jQuery('input[name=availableStock]').val() + "> " +
	        				"and trying to sell <" + jQuery('input[name=soldoutStock]').val() + ">");
	        		return false;
	        	}
			}
//			if(medicineDetailsIdDuplicationCheck(currentMedicineDetailsId) == true) {
//				MEDICINE.errMessage("STOP: Medicine with Duplicate Batch Id is not allowed");
//        		return false;
//			}
			
			if(jQuery('#addMedicineIntoInvoiceForm').form('validate') == false) {
				MEDICINE.enableForm('addMedicineIntoInvoiceForm');
				return;
			}
			MEDICINE.XHR('saveMedicineIntoInvoice.action', 'json', 'addMedicineIntoInvoiceForm');
			var jsonString = jQuery("#ajaxJsonResponse").html();
        	var result = JSON.parse(jsonString);
            if (result.medicineBean.medicineDetailsId != 0){  
            	jQuery('#addMedicineIntoInvoiceWindow').dialog('close');      // close the dialog  
//            	jQuery('#invoiceTable').datagrid('reload');    // reload the user data  
            	var currentRow = jQuery('.datagrid-row-selected').attr('datagrid-row-index');
            	var rows = jQuery('#invoiceTable').datagrid('getRows');
            	var data = result.medicineBean;
            	
            	if(data.batchId == -1) {
            		data.batchId = data.batchName;
            	}
            	// update invoice row details into database
            	MEDICINE.XHR('addOrUpdateInvoiceDetails.action', 'json', 'addMedicineIntoInvoiceForm');
            	var operationMsg = jQuery("#ajaxResponse").html();
            	var jsonString = jQuery("#ajaxJsonResponse").html();
            	var json = JSON.parse(jsonString);
            	if(json.invoiceBean.errMsg != null) {
            		MEDICINE.errMessage(json.invoiceBean.errMsg);
            		return;
            	}
            	if(data.invoiceDetailsId == undefined) {
            		data.invoiceDetailsId = json.invoiceBean.invoiceDetailsId;
            		data.invoiceId = json.invoiceBean.invoiceId;
            	}
            	jQuery('.datagrid-row-selected td[field=medicineDetailsId] div').html(data.medicineDetailsId);
            	jQuery('.datagrid-row-selected td[field=soldoutStock] div').html(data.soldoutStock);
            	jQuery('.datagrid-row-selected td[field=invoiceId] div').html(data.invoiceId);
            	jQuery('.datagrid-row-selected td[field=invoiceDetailsId] div').html(data.invoiceDetailsId);
            	jQuery('.datagrid-row-selected td[field=medicineName] div').html(data.medicineName);
            	jQuery('.datagrid-row-selected td[field=batchName] div').html(data.batchName);
            	jQuery('.datagrid-row-selected td[field=companyName] div').html(data.companyName);
            	jQuery('.datagrid-row-selected td[field=mfgDate] div').html(data.mfgDate);
            	jQuery('.datagrid-row-selected td[field=expDate] div').html(data.expDate);
            	jQuery('.datagrid-row-selected td[field=schedule] div').html(data.schedule);
            	jQuery('.datagrid-row-selected td[field=totalPrice] div').html(data.soldoutStock * data.soldoutUnitPrice);
            	jQuery('.datagrid-row-selected td[field=medWeight] div').html(data.medWeight);
            	jQuery('.datagrid-row-selected td[field=medDose] div').html(data.medDose);
            	jQuery('.datagrid-row-selected td[field=medType] div').html(data.medType);
            	jQuery('.datagrid-row-selected td[field=medicineId] div').html(data.medicineId);
            	jQuery('.datagrid-row-selected td[field=companyId] div').html(data.companyId);
            	jQuery('.datagrid-row-selected td[field=batchId] div').html(data.batchId);
            	jQuery('.datagrid-row-selected td[field=schedule] div').html(data.schedule);
            	jQuery('.datagrid-row-selected td[field=stock] div').html(data.stock);
            	jQuery('.datagrid-row-selected td[field=purchaseDate] div').html(data.purchaseDate);
            	jQuery('.datagrid-row-selected td[field=unitPrice] div').html(data.unitPrice);
            	jQuery('.datagrid-row-selected td[field=availableStock] div').html(data.availableStock);
            	calculateGrandTotal();
            } else {  
            	jQuery.messager.show({  
                    title: 'Error',  
                    msg: result.errMsg  
                });  
            }  
}

function calculateGrandTotal() {
	var grandTotal = 0;
	jQuery('.datagrid-body table tbody').children('tr').each(function(){
		jQuery(this).children().each(function() {
			if(jQuery(this).attr('field') == 'totalPrice') {
				var totalPrice = jQuery(this).children().html();
				if(isNaN(totalPrice) == false) {
					grandTotal = grandTotal + parseFloat(totalPrice);
				}
			}
		});
	});
	var rows = jQuery('#invoiceTable').datagrid('getFooterRows');
	rows[0]['schedule'] = 'Grand Total';
	rows[0]['totalPrice'] = grandTotal;
	jQuery('#invoiceTable').datagrid('reloadFooter');
	calculateTotal(jQuery('#invoiceDisplayFormPart2 input[name=vat]'));
	calculateDue(jQuery('#invoiceDisplayFormPart2 input[numberboxname=totalPaid]'));
	calculateGrandTotalAfterDiscount(jQuery('#invoiceDisplayFormPart2 input[name=discount]'));
}

function calculateGrandTotalAfterDiscount(discount) {
//	var discount = jQuery('#invoiceDisplayFormPart2 input[name=discount]').val();
	var vat = jQuery('#invoiceDisplayFormPart2 input[name=vat]').val();
	var rows = jQuery('#invoiceTable').datagrid('getFooterRows');
	var grandTotal = rows[0]['totalPrice'];
	var diff = vat - jQuery(discount).val();
	var finalPrice = 0;
//	if(diff >= 0) {
//		finalPrice = grandTotal - (grandTotal*diff)/100;
//	} else {
//		finalPrice = grandTotal + (grandTotal*diff)/100;
//	}
	finalPrice = grandTotal + (grandTotal*diff)/100;
	jQuery('#invoiceDisplayFormPart2 input[name=totalAmt]').val(finalPrice.toFixed(2));
	// total paid
	var paidAmt = jQuery('#invoiceDisplayFormPart2 input[numberboxname=totalPaid]').val();
	var due = finalPrice - paidAmt;
	jQuery('#invoiceDisplayFormPart2 input[name=due]').val(due.toFixed(2));
	
}

function medicineDetailsIdDuplicationCheck(inputMedicineDetailsId) {
	var isDuplicateval = false;
	if(inputMedicineDetailsId != null) {
		jQuery('.datagrid-body table tbody').children('tr').each(function(){
			jQuery(this).children().each(function() {
				if(jQuery(this).attr('field') == 'medicineDetailsId') {
					var medicineDetailsId = jQuery(this).children().html();
					if(parseInt(medicineDetailsId) == parseInt(inputMedicineDetailsId)) {
						isDuplicateval = true;
					}
				}
			});
		});
	}
	return isDuplicateval;
}

jQuery(document).delegate("div[name=customerName]", "click", function(event) {
	MEDICINE.selectSearchCriteria(this);
});
jQuery(document).delegate("div[name=customerMob1]", "click", function(event) {
	MEDICINE.selectSearchCriteria(this);
});
jQuery(document).delegate("div[name=doctorName]", "click", function(event) {
	MEDICINE.selectSearchCriteria(this);
});