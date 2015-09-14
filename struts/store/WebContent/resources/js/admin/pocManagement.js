
function searchCompany(value,name) {
	
	var searchByCriteria = jQuery('.searchbox-text').attr('name');
	jQuery('#searchCriteria').children('div').each(function(){
		if(jQuery(this).attr('name') == searchByCriteria) {
			jQuery(this).attr('iconCls', 'icon-ok');
		}
	});
	// Create a panel to list companies
	jQuery('#companyDiv').css('display', 'block');
//	jQuery('#companyList').panel({  
//		  width:900,  
//		  height:450,  
//		  title: 'Company Search Result',  
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
	
//	jQuery('#companyList').panel({  
//	    href:'friendsLink.action',  
//	    onLoad:function(){  
//	        alert('loaded successfully');  
//	    }  
//	});
	
	jQuery('#companyListTable').datagrid({
		title:'Search Result',
		iconCls:'icon-save',
		width:900,
		height:400,
		nowrap: false,
		striped: true,
		collapsible:true,
		url:'searchCompany.action?' + name + '=' + value,
		sortName: 'code',
		sortOrder: 'desc',
		remoteSort: false,
		idField:'code',
		frozenColumns:[[
            {title:'companyId',field:'companyId',width:80,sortable:true,hidden:true}
		]],
		columns:[[
	        {title:'Base Information',colspan:3},
	        {field:'opt',title:'Edit',width:100,align:'center', rowspan:2,
				formatter:function(value,rec){
					return '<span style="color:blue">Edit</span>';
				}
			},
			{field:'opt',title:'Delete',width:100,align:'center', rowspan:2,
				formatter:function(value,rec){
					return '<span style="color:red">Delete</span>';
				}
			}
			
		],[
			{field:'companyName',title:'Name',width:120},
			{field:'companyAddr1',title:'Address',width:220,rowspan:2,sortable:true,
				sorter:function(a,b){
					return (a>b?1:-1);
				}
			},
			{field:'phone1',title:'Phone',width:150,rowspan:2}
			
		]],
		pagination:true,
		rownumbers:true,
		toolbar:[{
			id:'btnadd',
			text:'Add',
			iconCls:'icon-add',
			handler:function(){
				jQuery('#btnsave').linkbutton('enable');
				alert('add');
			}
		},{
			id:'btncut',
			text:'Cut',
			iconCls:'icon-cut',
			handler:function(){
				jQuery('#btnsave').linkbutton('enable');
				alert('cut');
			}
		},'-',{
			id:'btnsave',
			text:'Save',
			disabled:true,
			iconCls:'icon-save',
			handler:function(){
				jQuery('#btnsave').linkbutton('disable');
				alert('save');
			}
		}],
		onClickRow:function(rowIndex){
			jQuery('.datagrid-row-selected').each(function(){
				if(jQuery(this).attr('datagrid-row-index') != rowIndex ) {
					jQuery(this).removeClass('datagrid-row-selected');
				}
			});
			
			var selected = jQuery('#companyListTable').datagrid('getSelected');
			if (selected != null || selected != undefined){
//				alert(selected.companyId+":"+selected.companyName+":"+selected.companyAddr1+":"+selected.phone1);
				displayCompany(selected.companyId, 'viewCompany.action');
			}
			
		}
	});
	var p = jQuery('#companyListTable').datagrid('getPager');
	jQuery(p).pagination({
		onBeforeRefresh:function(){
		}
	});
	function getSelected(){
		var selected = jQuery('#companyListTable').datagrid('getSelected');
		if (selected){
			alert(selected.companyId+":"+selected.companyName+":"+selected.companyAddr1+":"+selected.phone1);
		}
	}
}

function displayCompany(companyId, url) {
	jQuery('#companyDisplayDiv').form('load',url);
	jQuery('#companyDisplayDiv').css('display', 'block');
//	jQuery('#companyDisplayWindow').attr('title', title);
	jQuery('#companyDisplayWindow').window('open');
	
}

function cleardata(){
	jQuery('#companyDisplayForm').form('clear');
}

jQuery(document).delegate(".addNewCompany", "click", function(event) {
	displayCompany('0', 'addCompany.action');
	
});

jQuery(document).delegate(".submitCreateEditCompany", "click", function(event) {
	var companyId = jQuery('input[name=companyId]').val();
	var reqParamVal = jQuery("#companyDisplayForm").serialize();
	//displayCompany(companyId, 'addCompany.action');
	MEDICINE.XHR('createCompany.action', 'json', 'companyDisplayForm');
	alert(jQuery("#ajaxResponse").html());
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

jQuery(document).delegate(".displayPieGraph", "click", function(event) {
	// Load the Visualization API and the piechart package.       
//	google.load('visualization', '1.0', {'packages':['corechart']});        
	// Set a callback to run when the Google Visualization API is loaded.       
//	google.setOnLoadCallback(drawChart);        
	// Callback that creates and populates a data table,       
	// instantiates the pie chart, passes in the data and       
	// draws it.       
		// Create the data table.         
		var data = new google.visualization.DataTable();         
		data.addColumn('string', 'Topping');        
		data.addColumn('number', 'Slices');         
		data.addRows([           
		              ['Mushrooms', 3],           
		              ['Onions', 1],           
		              ['Olives', 1],           
		              ['Zucchini', 1],           
		              ['Pepperoni', 2]         
		             ]);          
		// Set chart options        
		var options = {'title':'How Much Pizza I Ate Last Night',                        
				'width':400,                        
				'height':300};          
		// Instantiate and draw our chart, passing in some options.         
		var chart = new google.visualization.PieChart(document.getElementById('pieChartsDisplayPanel'));         
		chart.draw(data, options);       
});

jQuery(document).delegate(".displayPDF", "click", function(event) {
	window.open('generateInvoice.action');
});
