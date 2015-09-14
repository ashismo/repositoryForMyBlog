
function searchMedRep(value,name) {
	var isDeleted = false;
	var searchByCriteria = jQuery('.searchbox-text').attr('name');
	jQuery('#searchCriteria').children('div').each(function(){
		if(jQuery(this).attr('name') == searchByCriteria) {
			jQuery(this).attr('iconCls', 'icon-ok');
		}
	});
	// Create a panel to list companies
	jQuery('#medRepDiv').css('display', 'block');
//	jQuery('#medRepList').panel({  
//		  width:900,  
//		  height:450,  
//		  title: 'MedRep Search Result',  
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
	
//	jQuery('#medRepList').panel({  
//	    href:'friendsLink.action',  
//	    onLoad:function(){  
//	        alert('loaded successfully');  
//	    }  
//	});
	
	jQuery('#medRepListTable').datagrid({
		title:'Search Result',
		iconCls:'icon-save',
		width:900,
		height:400,
		nowrap: false,
		striped: true,
		collapsible:true,
		url:'searchMedRep.action?' + name + '=' + value,
		sortName: 'code',
		sortOrder: 'desc',
		remoteSort: false,
		idField:'code',
		frozenColumns:[[
            {title:'medRepId',field:'medRepId',width:80,sortable:true,hidden:true}
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
			{field:'medRepName',title:'Name',width:130, sortable:true,
				formatter:function(value,row,index){  
					var d = '<a href="#">' + value +'</a>';  
	                return d; 
	        	}
			},
			{field:'wholesellerName',title:'Wholeseller<br/>Name',width:90,rowspan:2,sortable:true,
				sorter:function(a,b){
					return (a>b?1:-1);
				}
			},
			{field:'medRepAddr1',title:'Address Line1',width:130,rowspan:2,sortable:true,
				sorter:function(a,b){
					return (a>b?1:-1);
				}
			},
			{field:'state',title:'State',width:60,rowspan:2},
			{field:'mob1',title:'Mobile1',width:80,rowspan:2},
			{field:'mob2',title:'Mobile2',width:80,rowspan:2},
			{field:'phone1',title:'Phone1',width:80,rowspan:2},
			{field:'email',title:'Email',width:150,rowspan:2}
			
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
				var selected = jQuery('#medRepListTable').datagrid('getRows');
				if (selected != null || selected != undefined){
					jQuery('#hiddenFieldForm').children('input').each(function(){
						if(jQuery(this).attr('name') == 'medRepId') {
							medRepId = selected[rowIndex].medRepId;
							jQuery(this).val(medRepId);
						}
					});
					
					deleteMedRep('hiddenFieldForm');
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
				loadWholeSellers('wholesellerList');
				displayMedRep(rowData.medRepId, 'viewMedRep.action?medRepId=' + rowData.medRepId);
				var jsonString = jQuery("#ajaxJsonResponse").html();
				var json = JSON.parse(jsonString);
				if(json.medRepBean) {
					var data = json.medRepBean.errMsg;
					if(data != null && data != undefined && data != '') {
						MEDICINE.errMessage(data);
					}
				}
			}
		}
	});
	var p = jQuery('#medRepListTable').datagrid('getPager');
	jQuery(p).pagination({
		onBeforeRefresh:function(){
		}
	});
	function getSelected(){
		var selected = jQuery('#medRepListTable').datagrid('getSelected');
		if (selected){
			alert(selected.medRepId+":"+selected.medRepName+":"+selected.medRepAddr1+":"+selected.phone1);
		}
	}
}

jQuery().ready(function(){
	MEDICINE.clear();
	jQuery('.datebox').datebox({   
	    formatter:function(date) {
			return formatDate(date);
		}
	});
	jQuery('#wholesellerList').combobox({
	    filter: function(q, row){
	    	return row['text'].toLowerCase().indexOf(q.toLowerCase()) != -1;
		}
	});
});

function displayMedRep(medRepId, url) {
//	MEDICINE.disableForm('medRepDisplayForm');
	jQuery('#medRepDisplayDiv').form('load', url);
	jQuery('#medRepDisplayDiv').css('display', 'block');
	jQuery('#medRepDisplayWindow').window('open');
	jQuery('.validatebox-tip').css('display', 'none');
	if(medRepId == 0){
		jQuery('#medRepDisplayWindow').dialog('setTitle','Create Medical Representative'); 
		jQuery('.deleteMedRep').css('display', 'none');
	} else {
		jQuery('#medRepDisplayWindow').dialog('setTitle','Update Medical Representative'); 
		jQuery('.deleteMedRep').css('display','');
	}
	MEDICINE.enableForm('medRepDisplayForm');
	
}



jQuery(document).delegate(".submitCreateEditMedRep", "click", function(event) {
	if(jQuery('#medRepDisplayForm').form('validate') == false) {
		MEDICINE.enableForm('medRepDisplayForm');
		return;
	}
	MEDICINE.XHR('createMedRep.action', 'json', 'medRepDisplayForm');
	var operationMsg = jQuery("#ajaxResponse").html();
	MEDICINE.operationMessage(operationMsg);
	if(operationMsg.indexOf("Already Exists") == -1) {	
		jQuery('#medRepDisplayWindow').dialog('close');
	}
	MEDICINE.clear();
	var param = getAttrNameOfSelectedCriteria();
	var value = jQuery('.searchbox-text').val();
	if(value == 'Search Medical Representative') {
		value = '';
	}
	searchMedRep(value,param);
});

jQuery(document).delegate(".deleteMedRep", "click", function(event) {
	deleteMedRep('medRepDisplayForm');
});

function deleteMedRep(formName) {
	jQuery.messager.confirm('Confirm','Delete this Medical Representative?',function(r){  
        if (r){  
        	MEDICINE.XHR('deleteMedRep.action', 'json', formName);
        	var operationMsg = jQuery("#ajaxResponse").html();
        	MEDICINE.operationMessage(operationMsg);
        	jQuery('#medRepDisplayWindow').dialog('close');
        	var param = getAttrNameOfSelectedCriteria();
        	var value = jQuery('.searchbox-text').val();
        	if(value == 'Search Medical Representative') {
        		value = '';
        	}
        	searchMedRep(value,param);
        }  
    });
}

function cleardata(){
	jQuery('#medRepDisplayForm').form('clear');
}

jQuery(document).delegate(".addNewMedRep", "click", function(event) {
	loadWholeSellers('wholesellerList');
	displayMedRep('0', 'addMedRepForm.action');
	cleardata('#medRepDisplayForm');
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

jQuery(document).delegate("div[name=medRepName]", "click", function(event) {
	MEDICINE.selectSearchCriteria(this);
});
jQuery(document).delegate("div[name=wholesellerName]", "click", function(event) {
	MEDICINE.selectSearchCriteria(this);
});

