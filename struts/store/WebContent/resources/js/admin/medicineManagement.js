
function searchMedicine(value,name) {
	var isDeleted = false;
	var searchByCriteria = jQuery('.searchbox-text').attr('name');
	jQuery('#searchCriteria').children('div').each(function(){
		if(jQuery(this).attr('name') == searchByCriteria) {
			jQuery(this).attr('iconCls', 'icon-ok');
		}
	});
	// Create a panel to list companies
	jQuery('#medicineDiv').css('display', 'block');
//	jQuery('#medicineList').panel({  
//		  width:900,  
//		  height:450,  
//		  title: 'Medicine Search Result',  
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
	
//	jQuery('#medicineList').panel({  
//	    href:'friendsLink.action',  
//	    onLoad:function(){  
//	        alert('loaded successfully');  
//	    }  
//	});
	
	var status = jQuery('input[name=status]:checked', '#medicineSearchCriteria').val();
	var medType = jQuery('input[name=medType]', '#medicineSearchCriteria').val();
	var expiringAfter = jQuery('input[name=expiringAfter]', '#medicineSearchCriteria').val();
	var expiringBefore = jQuery('input[name=expiringBefore]', '#medicineSearchCriteria').val();
	var startDate = jQuery('input[name=startDate]', '#medicineSearchCriteria1').val();
	
	if(startDate == null || startDate == undefined) startDate = '';
	var endDate = jQuery('input[name=endDate]', '#medicineSearchCriteria1').val();
	if(endDate == null || endDate == undefined) endDate = '';
	if(startDate != '' || endDate != '') {
		if(compareDates(startDate, endDate) == 1) {
			MEDICINE.errMessage("End Date must be greater than Start date");
			return false;
		}
	}
	
	var nameValue = name + "=" + value + "&status=" + status + "&medType=" + medType + 
						"&startDate=" + startDate + "&endDate=" + endDate +
						"&expiringAfter=" + expiringAfter +
						"&expiringBefore=" + expiringBefore;
	
	jQuery('#medicineListTable').datagrid({
		showFooter:true,
		title:'Search Result',
		iconCls:'icon-save',
		width:900,
		height:400,
		nowrap: false,
		striped: true,
		collapsible:true,
		url:'searchMedicine.action?' + nameValue,
		sortName: 'code',
		sortOrder: 'desc',
		remoteSort: false,
		idField:'code',
		frozenColumns:[[
            {title:'medicineDetailsId',field:'medicineDetailsId',width:10,sortable:true,hidden:true},
            {title:'companyId',field:'companyId',width:10,sortable:true,hidden:true},
            {title:'medicineId',field:'medicineId',width:10,sortable:true,hidden:true},
            {title:'medRepId',field:'medRepId',width:10,sortable:true,hidden:true},
            {title:'wholesellerId',field:'wholesellerId',width:10,sortable:true,hidden:true}
		]],
		columns:[[
	        {title:'Base Information',colspan:13},
	        {field:'opt',title:'Delete',width:60,align:'center', rowspan:2,
	        	formatter:function(value,row,index){  
		        	var d = '<a href="#">Delete</a>';  
	                return d;
	        	}
	        }
			
		],[
			{field:'medicineName',title:'Medicine Name',width:160,sortable:true},
			{field:'companyName',title:'Manufacturer',width:120,rowspan:2,sortable:true,
				sorter:function(a,b){
					return (a>b?1:-1);
				}
			},
			{field:'medRepName',title:'MR Name',width:80,rowspan:2,sortable:true,
				sorter:function(a,b){
					return (a>b?1:-1);
				}
			},
			{field:'medDose',title:'Dose',width:50,rowspan:2,sortable:true},
			{field:'medWeight',title:'Net Wt.',width:50,rowspan:2,sortable:true},
			{field:'batchName',title:'Batch',width:80,rowspan:2,sortable:true},
			{field:'mfgDateActual',title:'Mfg. Date',width:80,rowspan:2,sortable:true,
				formatter: function(value,row,index){
					var date = getDateFromString(value);
					return date;
            	}
			},
			{field:'expDateActual',title:'Exp. Date',width:80,rowspan:2,sortable:true,
				formatter: function(value,row,index){
					return getDateFromString(value);
        		}
			},
			{field:'stock',title:'Total Stock',width:80,rowspan:2, sortable:true},
			{field:'availableStock',title:'Available<br/>Stock',width:80,rowspan:2},
			{field:'unitPrice',title:'Purchased<br/>Unit Price',width:80,rowspan:2},
			{field:'soldoutUnitPrice',title:'Soldout<br/>Unit Price',width:80,rowspan:2},
			{field:'medicineDesc',title:'Description',width:200,rowspan:2}
			
		]],
		pagination:true,
		rownumbers:true,
//		toolbar:[{
//			id:'btnExport',
//			text:'Export',
//			iconCls:'icon-add',
//			handler:function(){
//				jQuery('#btnsave').linkbutton('enable');
//				alert('Export');
//			}
//		},{
//			id:'btnPrint',
//			text:'Print',
//			iconCls:'icon-print',
//			handler:function(){
//				jQuery('#btnsave').linkbutton('enable');
//				alert('Print');
//			}
//		}],
		onClickCell:function(rowIndex, colIndex){
			if(colIndex == 'opt') {
				var selected = jQuery('#medicineListTable').datagrid('getRows');
				if (selected != null || selected != undefined){
					jQuery('#hiddenFieldForm').children('input').each(function(){
						if(jQuery(this).attr('name') == 'medicineDetailsId') {
							medicineDetailsId = selected[rowIndex].medicineDetailsId;
							jQuery(this).val(medicineDetailsId);
						}
					});
					
					deleteMedicineDetails('hiddenFieldForm');
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
				MEDICINE.clear();
				loadWholeSellers('wholesellerList');
				loadMedRepList(rowData.wholesellerId,'medRepList');
				displayMedicine(rowData.medicineDetailsId, 'viewMedicine.action?medicineDetailsId=' + rowData.medicineDetailsId);
				loadMedicineList(rowData.companyId);
				loadBatchList();
				jQuery('#medicineBatchList').combobox('setValue',rowData.batchName);
				jQuery('#medicineNameList').combobox('setValue',rowData.medicineId);
				MEDICINE.clear();
			}
		}
	});
	var p = jQuery('#medicineListTable').datagrid('getPager');
	jQuery(p).pagination({
		onBeforeRefresh:function(){
		}
	});
	function getSelected(){
		var selected = jQuery('#medicineListTable').datagrid('getSelected');
		if (selected){
			alert(selected.medicineId+":"+selected.medicineName+":"+selected.medicineAddr1+":"+selected.phone1);
		}
	}
}

jQuery(document).delegate(".deleteMedicineDetails", "click", function(event) {
	deleteMedicineDetails('medicineDisplayForm');
});

function deleteMedicineDetails(formName) {
	jQuery.messager.confirm('Confirm','Delete this Medicine Details?',function(r){  
        if (r){  
        	MEDICINE.XHR('deleteMedicineDetails.action', 'json', formName);
        	var operationMsg = jQuery("#ajaxResponse").html();
        	MEDICINE.operationMessage(operationMsg);
        	jQuery('#medicineDisplayWindow').dialog('close');
        	var param = getAttrNameOfSelectedCriteria();
        	var value = jQuery('.searchbox-text').val();
        	if(value == 'Search Medicine') {
        		value = '';
        	}
        	searchMedicine(value,param);
        }  
    });
}

function displayMedicine(medicineDetailsId, url) {
	MEDICINE.clear();
//	jQuery('#manufacturerList').addClass('easyui-combobox');
	jQuery('#manufacturerList').attr('required', 'true');
	jQuery('#medicineDisplayDiv').form('load',url);
	jQuery('#medicineDisplayDiv').css('display', 'block');
	jQuery('#medicineDisplayWindow').window('open');
	jQuery('.validatebox-tip').css('display', 'none');
	
	jQuery('input[name=medicineDetailsId]').val(medicineDetailsId);
	if(medicineDetailsId == 0){
		jQuery('#medicineDisplayWindow').dialog('setTitle','New Medicine Details'); 
		jQuery('.deleteMedicine').css('display', 'none');
		jQuery('#availableStockTr').css('display', 'none');
		jQuery('input[name=availableStock]').removeAttr('class');
	} else {
		jQuery('#medicineDisplayWindow').dialog('setTitle','Update Medicine Details'); 
		jQuery('.deleteMedicine').css('display','');
		jQuery('#availableStockTr').css('display', '');
		jQuery('input[name=availableStock]').attr('class','easyui-validatebox inp-form');
	}
	
	MEDICINE.enableForm('medicineDisplayForm');
	
}

function cleardata(formId){
	jQuery(formId).form('clear');
}

jQuery(document).delegate(".addNewMedicine", "click", function(event) {
	loadWholeSellers('wholesellerList');
	MEDICINE.clear();
	displayMedicine('0', 'addMedicineForm.action');
	cleardata('#medicineDisplayForm');
//	jQuery('input[name=medicineName]').attr('readonly','true');
});

jQuery(document).delegate(".submitCreateEditMedicine", "click", function(event) {
	if(jQuery('#medicineDisplayForm').form('validate') == false) {
		MEDICINE.enableForm('medicineDisplayForm');
		return;
	}
	if(validateInputs() == false) {
		MEDICINE.enableForm('medicineDisplayForm');
		MEDICINE.clear();
		return;
	}
	MEDICINE.XHR('addOrUpdateMedicine.action', 'json', 'medicineDisplayForm');
	var operationMsg = jQuery("#ajaxResponse").html();
	var jsonString = jQuery("#ajaxJsonResponse").html();
	var json = JSON.parse(jsonString);
	if(json.medicineBean.errMsg != null) {
		MEDICINE.errMessage(json.medicineBean.errMsg);
		return;
	}
	loadBatchList();
	loadMedicineList(0);
	jQuery('#medicineNameListOther').css('display', 'none');
	jQuery('#medicineNameTr').attr('height', '45');
	jQuery('#medicineNameListOther input[name=medicineName]').removeAttr('class');
	cleardata('#medicineDisplayForm');
	MEDICINE.operationMessage(operationMsg);
	jQuery('#medicineDisplayWindow').dialog('close');
	MEDICINE.clear();
	var param = getAttrNameOfSelectedCriteria();
	var value = jQuery('.searchbox-text').val();
	if(value == 'Search Medicine') {
		value = '';
	}
	searchMedicine(value,param);
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

jQuery().ready(function(){
	MEDICINE.clear();
	jQuery('#medicineNameList').combobox({
		filter: function(q, row){
			var opts = $(this).combobox('options');
			return row[opts.textField].indexOf(q) == 0;
		},
	    onChange:function(newValue,oldValue){
	        if(newValue == -1) {
	        	jQuery('#medicineNameListOther').css('display', 'inline');
	        	jQuery('#medicineNameTr').attr('height', '90');
	        	jQuery('#medicineNameListOther input[name=medicineName]').attr('class', 'easyui-validatebox inp-form');
	        } else {
	        	jQuery('#medicineNameListOther').css('display', 'none');
	        	jQuery('#medicineNameTr').attr('height', '45');
	        	jQuery('#medicineNameListOther input[name=medicineName]').removeAttr('class');
	        }
	    }
	});
	jQuery('#medicineBatchList').combobox({
	    onChange:function(newValue,oldValue){
	        if(newValue == -1) {
	        	jQuery('#medicineBatchListOther').css('display', 'inline');
	        	jQuery('#medicineBatchTr').attr('height', '90');
	        	jQuery('#medicineBatchListOther input[name=batchName]').attr('class', 'easyui-validatebox inp-form');
	        } else {
	        	jQuery('#medicineBatchListOther').css('display', 'none');
	        	jQuery('#medicineBatchTr').attr('height', '45');
	        	jQuery('#medicineBatchListOther input[name=batchName]').removeAttr('class');
	        }
	    }
	});
	
	jQuery('#companyList').combobox({
	    onChange:function(newValue,oldValue){
			loadMedicineList(newValue);
	    }
		
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
	
	jQuery('#medicineTypeList').combobox({
	    filter: function(q, row){
	    	return row['text'].toLowerCase().indexOf(q.toLowerCase()) != -1;
		}
	});
	
	jQuery('.datebox').datebox({   
	    formatter:function(date) {
			return formatDate(date);
		}
	});
	
	jQuery('input[name=stock]').numberbox({  
	    min:1 
	});
	
	jQuery('input[name=availableStock]').numberbox({  
	    min:0  
	});
	jQuery('input[name=unitPrice]').numberbox({  
	    min:0,  
	    precision:2  
	});
	jQuery('input[name=soldoutUnitPrice]').numberbox({  
	    min:0,  
	    precision:2  
	});
	
	jQuery('input[name=status]:radio', '#medicineSearchCriteria').click(function() {
	    jQuery('.expiringAfter', '#medicineSearchCriteria').val('');
	    jQuery('.expiringBefore', '#medicineSearchCriteria').val('');
		if(jQuery(this).val() == '1') {
			jQuery('.expiringAfter', '#medicineSearchCriteria').css('display','inline');
			jQuery('.expiringBefore', '#medicineSearchCriteria').css('display','inline');
		} else {
			jQuery('.expiringAfter', '#medicineSearchCriteria').css('display','none');
			jQuery('.expiringBefore', '#medicineSearchCriteria').css('display','none');
		}
	});
});


function validateInputs() {
	// stock can not be lesser than available stock
	var stock = jQuery('#medicineDisplayForm input[name=stock]').val();
	var availableStock = jQuery('#medicineDisplayForm input[name=availableStock]').val();
	var medicineDetailsId = jQuery('#medicineDisplayForm input[name=medicineDetailsId]').val();
	if(parseInt(availableStock) > parseInt(stock) && medicineDetailsId == 0) {
		MEDICINE.errMessage("Available stock can not be greater than total stock");
		return false;
	}
	//mfg date can not be greater than exp date
	var mfgDate = jQuery('#medicineDisplayForm input[name=mfgDate]').val();
	var expDate = jQuery('#medicineDisplayForm input[name=expDate]').val();
	var today = new Date();
	var dd = today.getDate();
	var mm = (today.getMonth() + 1);
	var yyyy = today.getFullYear();
	if(dd < 10 && dd.toString().length < 2) dd = '0' + dd;
	if(mm < 10 && mm.toString().length < 2) mm = '0' + mm;
	var todayStr = dd + '/' + mm + '/' + yyyy;
	if(compareDates(mfgDate, todayStr) == 1) {
		MEDICINE.errMessage("MFG Date can not be greater than Today's date");
		return false;
	}
	if(compareDates(mfgDate, expDate) == 1) {
		MEDICINE.errMessage("MFG Date can not be greater than EXP date");
		return false;
	}
	if(compareDates(mfgDate, expDate) == 0) {
		MEDICINE.errMessage("MFG Date can not be equals with EXP date");
		return false;
	}
	return true;
}
//jQuery(document).delegate("#medicineNameList", "click", function(event) {
//	alert('a');
//	jQuery('input[name=medicineName]').attr('disabled','disabled');
//	jQuery('select[name=medicineName]').removeAttr('disabled');
//});

//jQuery("input[name='status']", "#medicineSearchCriteria").change(function(){
//   alert('a');
//});

jQuery(document).delegate("div[name=medicineName]", "click", function(event) {
	MEDICINE.selectSearchCriteria(this);
});
jQuery(document).delegate("div[name=companyName]", "click", function(event) {
	MEDICINE.selectSearchCriteria(this);
});
jQuery(document).delegate("div[name=batchName]", "click", function(event) {
	MEDICINE.selectSearchCriteria(this);
});
jQuery(document).delegate("input[name=medicineName]", "click", function(event) {
	jQuery('select[name=medicineName]').attr('disabled','disabled');
	jQuery('input[name=medicineName]').removeAttr('disabled');
});