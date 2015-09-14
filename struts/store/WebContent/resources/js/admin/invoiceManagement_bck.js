
var allMedicines = new Array();
var allBatches = new Array();
var allCompanies = new Array();


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

$(function(){
	var lastIndex;
	jQuery('#invoiceTable').datagrid({
		toolbar:[{
			text:'append',
			iconCls:'icon-add',
			handler:function(){
				jQuery('#invoiceTable').datagrid('endEdit', lastIndex);
				jQuery('#invoiceTable').datagrid('appendRow',{
					invoiceDetailsId:'',
					slNo:'',
					soldoutStock:'',
					medicineId:'',
					batchName:'',
					companyName:'',
					mfgDate:'',
					expDate:'',
					schedule:'',
					totalPrice:''
				});
				lastIndex = jQuery('#invoiceTable').datagrid('getRows').length-1;
				jQuery('#invoiceTable').datagrid('selectRow', lastIndex);
				jQuery('#invoiceTable').datagrid('beginEdit', lastIndex);
			}
		},'-',{
			text:'delete',
			iconCls:'icon-remove',
			handler:function(){
				var row = $('#invoiceTable').datagrid('getSelected');
				if (row){
					var index = $('#invoiceTable').datagrid('getRowIndex', row);
					jQuery('#invoiceTable').datagrid('deleteRow', index);
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
			text:'reject',
			iconCls:'icon-undo',
			handler:function(){
				jQuery('#invoiceTable').datagrid('rejectChanges');
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
//					loadMedicineBatchList(medicineId);
//					var options = {"index" : rowIndex, "field": colIndex};
//					var editor = jQuery('#invoiceTable').datagrid('getEditor', options);
//					alert(editor);
//				    editor.target.val('a');
				}
			}
		},
		onClickRow:function(rowIndex, rowData){
			if (lastIndex != rowIndex){
				jQuery('#invoiceTable').datagrid('endEdit', lastIndex);
				jQuery('#invoiceTable').datagrid('beginEdit', rowIndex);
//				var editor = jQuery('#invoiceTable').datagrid('getEditors',rowIndex);
//				alert(editor[2]);
//				var editors = jQuery('#invoiceTable').datagrid('getEditors', rowIndex);
//				var invoiceName = editors[0];  
//			    var invoiceAddr1 = editors[1];  
//			    invoiceName.target.bind('change', function(){  
//			        alert('a');  
//			    }); 
			}
			lastIndex = rowIndex;
//			alert(rowData);
//			var editor = jQuery('#invoiceTable').datagrid('getEditors',rowIndex);
//			alert(editor[2]);
		}
	});
});


function loadMedicineList(companyId) {
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
//	MEDICINE.XHR('getAllMedicineBatches.action', 'json', '');
	var jsonString = jQuery("#ajaxJsonResponse").html();
	var json = JSON.parse(jsonString);
//	alert(JSON.stringify(json.medicineBean.batchList[0].batchName));
	for(var i = 0; i < json.medicineBean.batchList.length; i++) {
		allBatches[i] = json.medicineBean.batchList[i];
	}
//	var rows = jQuery('#invoiceTable').datagrid('getRows');
//	rows[4].combobox({  
//	    data:allBatches,  
//	    valueField:'batchId',  
//	    textField:'batchName'  
//	});
//	var rows = $('#dg').datagrid('getRows');
//	rows[4]['batchName'] = allBatches;
//	jQuery('#invoiceTable').datagrid('loadData',{
//		batchName: allBatches
//		
//	});
}

function loadCompanyList() {
	MEDICINE.XHR('getAllCompanies.action', 'json', '');
	var jsonString = jQuery("#ajaxJsonResponse").html();
	var json = JSON.parse(jsonString);
//	alert(JSON.stringify(json.medicineBean.batchList[0].batchName));
	for(var i = 0; i < json.companyBean.companyList.length; i++) {
		allCompanies[i] = json.companyBean.companyList[i];
	}
}

function loadMedicineDetailsByBatch(batchName) {
	MEDICINE.XHR('getMedicineByBatchAndMedicineId.action?batchName='+batchName, 'json', '');
	var jsonString = jQuery("#ajaxJsonResponse").html();
	var json = JSON.parse(jsonString);
	var currentRow = jQuery('.datagrid-row-selected').attr('datagrid-row-index');
	var rows = jQuery('#invoiceTable').datagrid('getRows');
//	alert(jQuery('#invoiceTable').datagrid('getEditors', currentRow)[3]);
	
//    $.extend($.fn.datagrid.defaults.editors, {  
//        text: {  
//            init: function(container, options){  
//                var input = $('<input type="text" class="datagrid-editable-input">').appendTo(container);  
//                return input;  
//            },  
//            getValue: function(target){  
//                return $(target).val();  
//            },  
//            setValue: function(target, value){  
//                $(target).val(value);  
//            },  
//            resize: function(target, width){  
//                var input = $(target);  
//                if ($.boxModel == true){  
//                    input.width(width - (input.outerWidth() - input.width()));  
//                } else {  
//                    input.width(width);  
//                }  
//            }  
//        }  
//    });  
	rows[currentRow]['companyName'] = json.medicineBean.companyId;
	rows[currentRow]['mfgDate'] = json.medicineBean.mfgDateActual;
	rows[currentRow]['expDate'] = json.medicineBean.expDateActual;
//	rows[currentRow]['schedule'] = JSON.stringify(json.medicineBean.schedule);
	alert(json.medicineBean.companyId);
	rows[currentRow]['totalPrice'] = json.medicineBean.totalPrice;
}

$().ready(function(){
	jQuery('.datebox').datebox({   
	    formatter:function(date) {
			return formatDate(date);
		}
	});
	loadMedicineList();
//	loadMedicineBatchList('dummy');
//	loadCompanyList();
	MEDICINE.clear();
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
//	jQuery('#invoiceList').panel({  
//		  width:900,  
//		  height:450,  
//		  title: 'Invoice Search Result',  
//		  collapsible: "true",
//		  maximizable: "true"
//		  tools: [{  
//		    iconCls:'icon-add',  
//		    handler:function(){alert('new');}  
//		  },{  
//		    iconCls:'icon-save',  
//		    handler:function(){alert('save');}  
//		  }]  
//		});  
	
//	jQuery('#invoiceList').panel({  
//	    href:'friendsLink.action',  
//	    onLoad:function(){  
//	        alert('loaded successfully');  
//	    }  
//	});
	
	jQuery('#invoiceListTable').datagrid({
		title:'Search Result',
		iconCls:'icon-save',
		width:900,
		height:400,
		nowrap: false,
		striped: true,
		collapsible:true,
		url:'searchInvoice.action?' + name + '=' + value,
		sortName: 'code',
		sortOrder: 'desc',
		remoteSort: false,
		idField:'code',
		frozenColumns:[[
            {title:'invoiceId',field:'invoiceId',width:80,sortable:true,hidden:true}
		]],
		columns:[[
	        {title:'Base Information',colspan:8},
			{field:'opt',title:'Delete',width:60,align:'center', rowspan:2,
	        	formatter:function(value,row,index){  
		        	var d = '<a href="#">Delete</a>';  
	                return d;
            	}
			}
			
		],[
			{field:'invoiceName',title:'Name',width:130, sortable:true,
				formatter:function(value,row,index){  
					var d = '<a href="#">' + value +'</a>';  
	                return d; 
	        	}
			},
			{field:'invoiceAddr1',title:'Address Line1',width:150,rowspan:2,sortable:true,
				sorter:function(a,b){
					return (a>b?1:-1);
				}
			},
			{field:'pin',title:'PIN',width:60,rowspan:2},
			{field:'state',title:'State',width:60,rowspan:2},
			{field:'mob1',title:'Mobile1',width:80,rowspan:2},
			{field:'mob2',title:'Mobile2',width:80,rowspan:2},
			{field:'phone1',title:'Phone1',width:80,rowspan:2},
			{field:'emailId',title:'Email',width:150,rowspan:2}
			
		]],
		pagination:true,
		rownumbers:true,
		toolbar:[{
			id:'btnadd',
			text:'Export',
			iconCls:'icon-add',
			handler:function(){
				jQuery('#btnsave').linkbutton('enable');
				alert('Export');
			}
		},{
			id:'btncut',
			text:'Print',
			iconCls:'icon-print',
			handler:function(){
				jQuery('#btnsave').linkbutton('enable');
				alert('Print');
			}
		}],
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
			if(isDeleted == false) {
				displayInvoice(rowData.invoiceId, 'viewInvoice.action?invoiceId=' + rowData.invoiceId);
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

function displayInvoice(invoiceId, url) {
//	MEDICINE.disableForm('invoiceDisplayForm');
	jQuery('#invoiceDisplayDiv').form('load', url);
	jQuery('#invoiceDisplayDiv').css('display', 'block');
	jQuery('#invoiceDisplayWindow').window('open');
	jQuery('.validatebox-tip').css('display', 'none');
	if(invoiceId == 0){
		jQuery('#invoiceDisplayWindow').dialog('setTitle','Create Invoice'); 
		jQuery('.deleteInvoice').css('display', 'none');
	} else {
		jQuery('#invoiceDisplayWindow').dialog('setTitle','Update Invoice'); 
		jQuery('.deleteInvoice').css('display','');
	}
	MEDICINE.enableForm('invoiceDisplayForm');
	
}



jQuery(document).delegate(".submitCreateEditInvoice", "click", function(event) {
	if(jQuery('#invoiceDisplayForm').form('validate') == false) {
		MEDICINE.enableForm('invoiceDisplayForm');
		return;
	}
	var allHiddenFieldsName = [];
	var i = 0;
	jQuery('tbody tr td', jQuery('#invoiceDisplayForm div.datagrid-body table')).each( function() {
        var fieldName = (jQuery(this).attr('field'));
        if(fieldName != undefined) {
        	if(fieldName == 'soldoutStock' || fieldName == 'totalPrice' || fieldName == 'invoiceDetailsId' ||
        			fieldName == 'medicineName' || fieldName == 'schedule' || fieldName == 'companyName' || fieldName == 'batchName') {
        		fieldName = fieldName + 'Arr';
        	}
        	allHiddenFieldsName.push( fieldName );
        	var value = jQuery('div', jQuery(this)).html();
        	var $hiddenInput = $('<input/>',{type:'hidden',name:fieldName,value:value});
        	$hiddenInput.appendTo('#invoiceDisplayForm');
        }
    });
	MEDICINE.XHR('createInvoice.action', 'json', 'invoiceDisplayForm');
	$.each( allHiddenFieldsName, function(index, value){
		 jQuery('#invoiceDisplayForm input[name='+value+']').remove();
	});
	var operationMsg = jQuery("#ajaxResponse").html();
	MEDICINE.operationMessage(operationMsg);
});

jQuery(document).delegate(".deleteInvoice", "click", function(event) {
	deleteInvoice('invoiceDisplayForm');
});

function deleteInvoice(formName) {
	jQuery.messager.confirm('Confirm','Delete this Invoice?',function(r){  
        if (r){  
        	MEDICINE.XHR('deleteInvoice.action', 'json', formName);
        	var operationMsg = jQuery("#ajaxResponse").html();
        	MEDICINE.operationMessage(operationMsg);
        	jQuery('#invoiceDisplayWindow').dialog('close');
        	var param = getAttrNameOfSelectedCriteria();
        	var value = jQuery('.searchbox-text').val();
        	if(value == 'Search Invoice') {
        		value = '';
        	}
        	searchInvoice(value,param);
        }  
    });
}

function cleardata(){
	jQuery('#invoiceDisplayForm').form('clear');
}

jQuery(document).delegate(".addNewInvoice", "click", function(event) {
	loadDoctorList();
	loadCustomerList();
	displayInvoice('0', 'addInvoiceForm.action');
	cleardata('#invoiceDisplayForm');
	jQuery('#statex').attr('selectedIndex', 0); 
});


jQuery(document).delegate(".displayGraph", "click", function(event) {
	MEDICINE.XHR('sellByMonths.action', 'html', '');
//	jQuery('#myDataTable').visualize();
	
	/**
     * Visualize an HTML table using Highcharts. The top (horizontal) header
     * is used for series names, and the left (vertical) header is used
     * for category names. This function is based on jQuery.
     * @param {Object} table The reference to the HTML table to visualize
     * @param {Object} options Highcharts options
     */
    Highcharts.visualize = function(table, options) {
        // the categories
        options.xAxis.categories = [];
        jQuery('tbody th', table).each( function(i) {
            options.xAxis.categories.push(this.innerHTML);
        });

        // the data series
        options.series = [];
        jQuery('tr', table).each( function(i) {
            var tr = this;
            jQuery('th, td', tr).each( function(j) {
                if (j > 0) { // skip first column
                    if (i == 0) { // get the name and init the series
                        options.series[j - 1] = {
                            name: this.innerHTML,
                            data: []
                        };
                    } else { // add values
                        options.series[j - 1].data.push(parseFloat(this.innerHTML));
                    }
                }
            });
        });

        var chart = new Highcharts.Chart(options);
    }

    var table = document.getElementById('myDataTable'),
    options = {
        chart: {
            renderTo: 'chartsDisplayPanel',
            type: 'column'
        },
        title: {
            text: 'Data extracted from a HTML table in the page'
        },
        xAxis: {
        },
        yAxis: {
            title: {
                text: 'Units'
            }
        },
        tooltip: {
            formatter: function() {
                return '<b>'+ this.series.name +'</b><br/>'+
                    this.y +' '+ this.x.toLowerCase();
            }
        }
    };

    Highcharts.visualize(table, options);
});

jQuery(document).delegate("div[name=invoiceName]", "click", function(event) {
	MEDICINE.selectSearchCriteria(this);
});
jQuery(document).delegate("div[name=pin]", "click", function(event) {
	MEDICINE.selectSearchCriteria(this);
});
jQuery(document).delegate("div[name=state]", "click", function(event) {
	MEDICINE.selectSearchCriteria(this);
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
