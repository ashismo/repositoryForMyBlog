
function searchContacts(value,name) {
	var isDeleted = false;
	var searchByCriteria = jQuery('.searchbox-text').attr('name');
	jQuery('#searchCriteria').children('div').each(function(){
		if(jQuery(this).attr('name') == searchByCriteria) {
			jQuery(this).attr('iconCls', 'icon-ok');
		}
	});
	// Create a panel to list companies
	jQuery('#contactsDiv').css('display', 'block');
//	jQuery('#contactsList').panel({  
//		  width:900,  
//		  height:450,  
//		  title: 'Contacts Search Result',  
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
	
//	jQuery('#contactsList').panel({  
//	    href:'friendsLink.action',  
//	    onLoad:function(){  
//	        alert('loaded successfully');  
//	    }  
//	});
	
	jQuery('#contactsListTable').datagrid({
		title:'Search Result',
		iconCls:'icon-save',
		width:900,
		height:400,
		nowrap: false,
		striped: true,
		collapsible:true,
		url:'searchContacts.action?' + name + '=' + value,
		sortName: 'code',
		sortOrder: 'desc',
		remoteSort: false,
		idField:'code',
		frozenColumns:[[
            {title:'contactId',field:'contactId',width:80,sortable:true,hidden:true}
		]],
		columns:[[
	        {title:'Base Information',colspan:5},
			{field:'opt',title:'Delete',width:60,align:'center', rowspan:2,
	        	formatter:function(value,row,index){  
		        	var d = '<a href="#">Delete</a>';  
	                return d;
            	}
			}
			
		],[
			{field:'contactName',title:'Name',width:150, sortable:true,
				formatter:function(value,row,index){  
					var d = '<a href="#">' + value +'</a>';  
	                return d; 
	        	},
	        	sorter:function(a,b){
					return (a>b?1:-1);
				}
			},
			{field:'contactNo1',title:'Contact Number1',width:120,rowspan:2},
			{field:'contactNo2',title:'Contact Number2',width:120,rowspan:2},
			{field:'contactFax',title:'Fax',width:120,rowspan:2},
			{field:'contactEmail',title:'Email Id',width:180,rowspan:2}
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
				var selected = jQuery('#contactsListTable').datagrid('getRows');
				if (selected != null || selected != undefined){
					jQuery('#hiddenFieldForm').children('input').each(function(){
						if(jQuery(this).attr('name') == 'contactId') {
							contactId = selected[rowIndex].contactId;
							jQuery(this).val(contactId);
						}
					});
					
					deleteContacts('hiddenFieldForm');
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
				displayContacts(rowData.contactId, 'viewContacts.action?contactId=' + rowData.contactId);
			}
		}
	});
	var p = jQuery('#contactsListTable').datagrid('getPager');
	jQuery(p).pagination({
		onBeforeRefresh:function(){
		}
	});
	function getSelected(){
		var selected = jQuery('#contactsListTable').datagrid('getSelected');
		if (selected){
			alert(selected.contactId+":"+selected.contactsName+":"+selected.contactsAddr1+":"+selected.phone1);
		}
	}
}

function displayContacts(contactId, url) {
//	MEDICINE.disableForm('contactsDisplayForm');
	jQuery('#contactsDisplayDiv').form('load', url);
	jQuery('#contactsDisplayDiv').css('display', 'block');
	jQuery('#contactsDisplayWindow').window('open');
	jQuery('.validatebox-tip').css('display', 'none');
	if(contactId == 0){
		jQuery('#contactsDisplayWindow').dialog('setTitle','Create Contacts'); 
		jQuery('.deleteContacts').css('display', 'none');
	} else {
		jQuery('#contactsDisplayWindow').dialog('setTitle','Update Contacts'); 
		jQuery('.deleteContacts').css('display','');
	}
	MEDICINE.enableForm('contactsDisplayForm');
	
}



jQuery(document).delegate(".submitCreateEditContacts", "click", function(event) {
	if(jQuery('#contactsDisplayForm').form('validate') == false) {
		MEDICINE.enableForm('contactsDisplayForm');
		return;
	}
	MEDICINE.XHR('createContacts.action', 'json', 'contactsDisplayForm');
	var operationMsg = jQuery("#ajaxResponse").html();
	MEDICINE.operationMessage(operationMsg);
	if(operationMsg.indexOf("Already Exists") == -1) {
		jQuery('#contactsDisplayWindow').dialog('close');
	}
	MEDICINE.clear();
	var param = getAttrNameOfSelectedCriteria();
	var value = jQuery('.searchbox-text').val();
	if(value == 'Search Contacts') {
		value = '';
	}
	searchContacts(value,param);
});

jQuery(document).delegate(".deleteContacts", "click", function(event) {
	deleteContacts('contactsDisplayForm');
});

function deleteContacts(formName) {
	jQuery.messager.confirm('Confirm','Delete this Contacts?',function(r){  
        if (r){  
        	MEDICINE.XHR('deleteContacts.action', 'json', formName);
        	var operationMsg = jQuery("#ajaxResponse").html();
        	MEDICINE.operationMessage(operationMsg);
        	jQuery('#contactsDisplayWindow').dialog('close');
        	var param = getAttrNameOfSelectedCriteria();
        	var value = jQuery('.searchbox-text').val();
        	if(value == 'Search Contacts') {
        		value = '';
        	}
        	searchContacts(value,param);
        }  
    });
}

function cleardata(){
	jQuery('#contactsDisplayForm').form('clear');
}

jQuery(document).delegate(".addNewContacts", "click", function(event) {
	displayContacts('0', 'addContactsForm.action');
	cleardata('#contactsDisplayForm');
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

jQuery(document).delegate("div[name=contactName]", "click", function(event) {
	MEDICINE.selectSearchCriteria(this);
});
jQuery(document).delegate("div[name=contactNo1]", "click", function(event) {
	MEDICINE.selectSearchCriteria(this);
});
