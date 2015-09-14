
function searchMultipleMedicine(batchName, medicineId) {
	// Create a panel to list medicines
	jQuery('#multipleMedicineListTable').datagrid({
		iconCls:'icon-save',
		width:900,
		height:350,
		nowrap: false,
		striped: true,
		collapsible:true,
		url:'getMedicineByBatchAndMedicineId.action?medicineId=' + medicineId + '&batchName=' + batchName,
		sortName: 'code',
		sortOrder: 'desc',
		remoteSort: false,
		idField:'code',
		frozenColumns:[[
            {title:'medicineDetailsId',field:'medicineDetailsId',width:10,sortable:true,hidden:true},
            {title:'companyId',field:'companyId',width:10,sortable:true,hidden:true},
            {title:'medicineId',field:'medicineId',width:10,sortable:true,hidden:true}
		]],
		columns:[[
	        {title:'Base Information',colspan:11}
			
		],[
		    {field:'medicineDetailsId',title:'Medicine Details Id',width:1,hidden:true},
			{field:'medicineName',title:'Medicine Name',width:160,sortable:true},
			{field:'companyName',title:'Manufacturer',width:120,rowspan:2,sortable:true},
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
			{field:'stock',title:'Total Stock',width:80,rowspan:2},
			{field:'availableStock',title:'Available Stock',width:80,rowspan:2},
			{field:'soldoutUnitPrice',title:'Soldout Unit Price',width:80,rowspan:2},
			{field:'medicineDesc',title:'Description',width:200,rowspan:2}
			
		]],
		pagination:true,
		rownumbers:true,
		onClickRow:function(rowIndex, rowData){
			jQuery('#addMedicineIntoInvoiceForm').form('load',rowData); 
			jQuery('#multipleMedicineDisplayWindow').window('close');
		}
	});
	var p = jQuery('#multipleMedicineListTable').datagrid('getPager');
	jQuery(p).pagination({
		onBeforeRefresh:function(){
		}
	});
}

function displayMultipleMedicineDetails(batchName, medicineId) {
	searchMultipleMedicine(batchName, medicineId);
	jQuery('#multipleMedicineDisplayWindow').window({  
		title: 'Select Medicine',
	    width:900,  
	    height:410,  
	    modal:true  
	});
}

