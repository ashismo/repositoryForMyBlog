
function searchBuy(value,name) {
	
	var searchByCriteria = jQuery('.searchbox-text').attr('name');
	jQuery('#searchCriteria').children('div').each(function(){
		if(jQuery(this).attr('name') == searchByCriteria) {
			jQuery(this).attr('iconCls', 'icon-ok');
		}
	});
	// Create a panel to list companies
	jQuery('#buyDiv').css('display', 'block');
//	jQuery('#buyList').panel({  
//		  width:900,  
//		  height:450,  
//		  title: 'Buy Search Result',  
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
	
//	jQuery('#buyList').panel({  
//	    href:'friendsLink.action',  
//	    onLoad:function(){  
//	        alert('loaded successfully');  
//	    }  
//	});
	
	jQuery('#buyListTable').datagrid({
		title:'Search Result',
		iconCls:'icon-save',
		width:900,
		height:400,
		nowrap: false,
		striped: true,
		collapsible:true,
		url:'searchBuy.action?' + name + '=' + value,
		sortName: 'code',
		sortBuy: 'desc',
		remoteSort: false,
		idField:'code',
		frozenColumns:[[
            {title:'buyId',field:'buyId',width:80,sortable:true,hidden:true}
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
			{field:'buyName',title:'Name',width:120},
			{field:'buyAddr1',title:'Address',width:220,rowspan:2,sortable:true,
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
			
			var selected = jQuery('#buyListTable').datagrid('getSelected');
			if (selected != null || selected != undefined){
//				alert(selected.buyId+":"+selected.buyName+":"+selected.buyAddr1+":"+selected.phone1);
				displayBuy(selected.buyId, 'viewBuy.action');
			}
			
		}
	});
	var p = jQuery('#buyListTable').datagrid('getPager');
	jQuery(p).pagination({
		onBeforeRefresh:function(){
		}
	});
	function getSelected(){
		var selected = jQuery('#buyListTable').datagrid('getSelected');
		if (selected){
			alert(selected.buyId+":"+selected.buyName+":"+selected.buyAddr1+":"+selected.phone1);
		}
	}
}

function displayBuy(buyId, url) {
	jQuery('#buyDisplayDiv').form('load',url);
	jQuery('#buyDisplayDiv').css('display', 'block');
//	jQuery('#buyDisplayWindow').attr('title', title);
	jQuery('#buyDisplayWindow').window('open');
	
}

function cleardata(){
	jQuery('#buyDisplayForm').form('clear');
}

jQuery(document).delegate(".addNewBuy", "click", function(event) {
	displayBuy('0', 'addBuy.action');
	
});

jQuery(document).delegate(".submitCreateEditBuy", "click", function(event) {
	var buyId = jQuery('input[name=buyId]').val();
	var reqParamVal = jQuery("#buyDisplayForm").serialize();
	//displayBuy(buyId, 'addBuy.action');
	MEDICINE.XHR('createBuy.action', 'json', 'buyDisplayForm');
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

