
function searchCustomer(value,name) {
	var isDeleted = false;
	var searchByCriteria = jQuery('.searchbox-text').attr('name');
	jQuery('#searchCriteria').children('div').each(function(){
		if(jQuery(this).attr('name') == searchByCriteria) {
			jQuery(this).attr('iconCls', 'icon-ok');
		}
	});
	// Create a panel to list companies
	jQuery('#customerDiv').css('display', 'block');
//	jQuery('#customerList').panel({  
//		  width:900,  
//		  height:450,  
//		  title: 'Customer Search Result',  
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
	
//	jQuery('#customerList').panel({  
//	    href:'friendsLink.action',  
//	    onLoad:function(){  
//	        alert('loaded successfully');  
//	    }  
//	});
	
	jQuery('#customerListTable').datagrid({
		title:'Search Result',
		iconCls:'icon-save',
		width:900,
		height:400,
		nowrap: false,
		striped: true,
		collapsible:true,
		url:'searchCustomer.action?' + name + '=' + value,
		sortName: 'code',
		sortOrder: 'desc',
		remoteSort: false,
		idField:'code',
		frozenColumns:[[
            {title:'customerId',field:'customerId',width:80,sortable:true,hidden:true}
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
			{field:'customerName',title:'Name',width:130, sortable:true,
				formatter:function(value,row,index){  
					var d = '<a href="#">' + value +'</a>';  
	                return d; 
	        	}
			},
			{field:'customerAddr1',title:'Address Line1',width:150,rowspan:2,sortable:true,
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
				var selected = jQuery('#customerListTable').datagrid('getRows');
				if (selected != null || selected != undefined){
					jQuery('#hiddenFieldForm').children('input').each(function(){
						if(jQuery(this).attr('name') == 'customerId') {
							customerId = selected[rowIndex].customerId;
							jQuery(this).val(customerId);
						}
					});
					
					deleteCustomer('hiddenFieldForm');
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
				displayCustomer(rowData.customerId, 'viewCustomer.action?customerId=' + rowData.customerId);
			}
		}
	});
	var p = jQuery('#customerListTable').datagrid('getPager');
	jQuery(p).pagination({
		onBeforeRefresh:function(){
		}
	});
	function getSelected(){
		var selected = jQuery('#customerListTable').datagrid('getSelected');
		if (selected){
			alert(selected.customerId+":"+selected.customerName+":"+selected.customerAddr1+":"+selected.phone1);
		}
	}
}

function displayCustomer(customerId, url) {
//	MEDICINE.disableForm('customerDisplayForm');
	jQuery('#customerDisplayDiv').form('load', url);
	jQuery('#customerDisplayDiv').css('display', 'block');
	jQuery('#customerDisplayWindow').window('open');
	jQuery('.validatebox-tip').css('display', 'none');
	if(customerId == 0){
		jQuery('#customerDisplayWindow').dialog('setTitle','Create Customer'); 
		jQuery('.deleteCustomer').css('display', 'none');
	} else {
		jQuery('#customerDisplayWindow').dialog('setTitle','Update Customer'); 
		jQuery('.deleteCustomer').css('display','');
	}
	MEDICINE.enableForm('customerDisplayForm');
	
}

jQuery(document).delegate(".submitCreateEditCustomer", "click", function(event) {
	if(jQuery('#customerDisplayForm').form('validate') == false) {
		MEDICINE.enableForm('customerDisplayForm');
		return;
	}
	MEDICINE.XHR('createCustomer.action', 'json', 'customerDisplayForm');
	var operationMsg = jQuery("#ajaxResponse").html();
	MEDICINE.operationMessage(operationMsg);
	if(operationMsg.indexOf("Already Exists") == -1) {
		jQuery('#customerDisplayWindow').dialog('close');
	}
	MEDICINE.clear();
	var param = getAttrNameOfSelectedCriteria();
	var value = jQuery('.searchbox-text').val();
	if(value == 'Search Customer') {
		value = '';
	}
	searchCustomer(value,param);
});

jQuery(document).delegate(".deleteCustomer", "click", function(event) {
	deleteCustomer('customerDisplayForm');
});

function deleteCustomer(formName) {
	jQuery.messager.confirm('Confirm','Delete this Customer?',function(r){  
        if (r){  
        	MEDICINE.XHR('deleteCustomer.action', 'json', formName);
        	var operationMsg = jQuery("#ajaxResponse").html();
        	MEDICINE.operationMessage(operationMsg);
        	jQuery('#customerDisplayWindow').dialog('close');
        	var param = getAttrNameOfSelectedCriteria();
        	var value = jQuery('.searchbox-text').val();
        	if(value == 'Search Customer') {
        		value = '';
        	}
        	searchCustomer(value,param);
        }  
    });
}

function cleardata(){
	jQuery('#customerDisplayForm').form('clear');
}

jQuery(document).delegate(".addNewCustomer", "click", function(event) {
	displayCustomer('0', 'addCustomerForm.action');
	cleardata('#customerDisplayForm');
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

jQuery(document).delegate("div[name=customerName]", "click", function(event) {
	MEDICINE.selectSearchCriteria(this);
});
jQuery(document).delegate("div[name=customerAddr1]", "click", function(event) {
	MEDICINE.selectSearchCriteria(this);
});
