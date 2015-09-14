
function searchDoctor(value,name) {
	var isDeleted = false;
	var searchByCriteria = jQuery('.searchbox-text').attr('name');
	jQuery('#searchCriteria').children('div').each(function(){
		if(jQuery(this).attr('name') == searchByCriteria) {
			jQuery(this).attr('iconCls', 'icon-ok');
		}
	});
	// Create a panel to list companies
	jQuery('#doctorDiv').css('display', 'block');
//	jQuery('#doctorList').panel({  
//		  width:900,  
//		  height:450,  
//		  title: 'Doctor Search Result',  
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
	
//	jQuery('#doctorList').panel({  
//	    href:'friendsLink.action',  
//	    onLoad:function(){  
//	        alert('loaded successfully');  
//	    }  
//	});
	
	jQuery('#doctorListTable').datagrid({
		title:'Search Result',
		iconCls:'icon-save',
		width:900,
		height:400,
		nowrap: false,
		striped: true,
		collapsible:true,
		url:'searchDoctor.action?' + name + '=' + value,
		sortName: 'code',
		sortOrder: 'desc',
		remoteSort: false,
		idField:'code',
		frozenColumns:[[
            {title:'doctorId',field:'doctorId',width:80,sortable:true,hidden:true}
		]],
		columns:[[
	        {title:'Base Information',colspan:10},
			{field:'opt',title:'Delete',width:60,align:'center', rowspan:2,
	        	formatter:function(value,row,index){  
		        	var d = '<a href="#">Delete</a>';  
	                return d;
            	}
			}
			
		],[
			{field:'doctorName',title:'Name',width:100, sortable:true,
				formatter:function(value,row,index){  
					var d = '<a href="#">' + value +'</a>';  
	                return d; 
	        	}
			},
			{field:'doctorAddr1',title:'Address Line1',width:120,rowspan:2,sortable:true,
				sorter:function(a,b){
					return (a>b?1:-1);
				}
			},
			{field:'pin',title:'PIN',width:60,rowspan:2},
			{field:'state',title:'State',width:60,rowspan:2},
			{field:'mob1',title:'Mobile1',width:80,rowspan:2},
			{field:'mob2',title:'Mobile2',width:80,rowspan:2},
			{field:'phone1',title:'Phone1',width:80,rowspan:2},
			{field:'emailId',title:'Email',width:100,rowspan:2},
			{field:'dateOfRelease',title:'Release<br/>Date',width:80,rowspan:2},
			{field:'isActive',title:'Is<br/>Active',width:50,rowspan:2}
			
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
				var selected = jQuery('#doctorListTable').datagrid('getRows');
				if (selected != null || selected != undefined){
					jQuery('#hiddenFieldForm').children('input').each(function(){
						if(jQuery(this).attr('name') == 'doctorId') {
							doctorId = selected[rowIndex].doctorId;
							jQuery(this).val(doctorId);
						}
					});
					
					deleteDoctor('hiddenFieldForm');
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
				displayDoctor(rowData.doctorId, 'viewDoctor.action?doctorId=' + rowData.doctorId);
			}
		}
	});
	var p = jQuery('#doctorListTable').datagrid('getPager');
	jQuery(p).pagination({
		onBeforeRefresh:function(){
		}
	});
	function getSelected(){
		var selected = jQuery('#doctorListTable').datagrid('getSelected');
		if (selected){
			alert(selected.doctorId+":"+selected.doctorName+":"+selected.doctorAddr1+":"+selected.phone1);
		}
	}
}

jQuery(document).delegate(".submitCreateEditDoctor", "click", function(event) {
	if(jQuery('#doctorDisplayForm').form('validate') == false) {
		MEDICINE.enableForm('doctorDisplayForm');
		return;
	}
	if((jQuery('#doctorDisplayForm input[name=dateOfAssociation]').val()).toString().trim() == '' && 
			(jQuery('#doctorDisplayForm input[name=dateOfRelease]').val()).toString().trim() != '') {
		MEDICINE.errMessage("Date of Association must be entered");
		return;
	}
	var dateOfAssociation = jQuery('#doctorDisplayForm input[name=dateOfAssociation]').val();
	var dateOfRelease = jQuery('#doctorDisplayForm input[name=dateOfRelease]').val();
	if(compareDates(dateOfAssociation, dateOfRelease) == 1) {
		MEDICINE.errMessage("Date of Association can not be greater than Date of Release");
		return false;
	}
	MEDICINE.XHR('createDoctor.action', 'json', 'doctorDisplayForm');
	var operationMsg = jQuery("#ajaxResponse").html();
	MEDICINE.operationMessage(operationMsg);
	if(operationMsg.indexOf("Already Exists") == -1) {
		jQuery('#doctorDisplayWindow').dialog('close');
	}
	MEDICINE.clear();
	var param = getAttrNameOfSelectedCriteria();
	var value = jQuery('.searchbox-text').val();
	if(value == 'Search Doctor') {
		value = '';
	}
	searchDoctor(value,param);
});

jQuery(document).delegate(".deleteDoctor", "click", function(event) {
	deleteDoctor('doctorDisplayForm');
});

jQuery().ready(function(){
	jQuery('.datebox').datebox({   
	    formatter:function(date) {
			return formatDate(date);
		}
	});
});

function deleteDoctor(formName) {
	jQuery.messager.confirm('Confirm','Delete this Doctor?',function(r){  
        if (r){  
        	MEDICINE.XHR('deleteDoctor.action', 'json', formName);
        	var operationMsg = jQuery("#ajaxResponse").html();
        	MEDICINE.operationMessage(operationMsg);
        	jQuery('#doctorDisplayWindow').dialog('close');
        	var param = getAttrNameOfSelectedCriteria();
        	var value = jQuery('.searchbox-text').val();
        	if(value == 'Search Doctor') {
        		value = '';
        	}
        	searchDoctor(value,param);
        }  
    });
}

function cleardata(){
	jQuery('#doctorDisplayForm').form('clear');
}

jQuery(document).delegate(".addNewDoctor", "click", function(event) {
	displayDoctor('0', 'addDoctorForm.action');
	cleardata('#doctorDisplayForm');
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

jQuery(document).delegate("div[name=doctorName]", "click", function(event) {
	MEDICINE.selectSearchCriteria(this);
});
jQuery(document).delegate("div[name=pin]", "click", function(event) {
	MEDICINE.selectSearchCriteria(this);
});
jQuery(document).delegate("div[name=state]", "click", function(event) {
	MEDICINE.selectSearchCriteria(this);
});
