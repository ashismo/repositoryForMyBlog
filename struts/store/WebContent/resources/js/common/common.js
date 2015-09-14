function loadMedicineList(companyId) {
	MEDICINE.XHR('getMedicinesByCompanyId.action?companyId='+companyId, 'json', '');
	var jsonString = jQuery("#ajaxJsonResponse").html();
	var json = JSON.parse(jsonString);
//	alert(JSON.stringify(json.medicineBean.medicineList[1].medicineName));
	var data = json.medicineBean.medicineList;
	jQuery('#medicineNameList').combobox({  
	    data:data,  
	    valueField:'medicineId',  
	    textField:'medicineName',
	    onLoadError: function() {
			alert('Unable to fetch medicine list');
		},
		onLoadSuccess: function() {
		},
		formatter:function(row){  
			for(var i=0; i<data.length; i++){
				if (data[i].medicineId == row.medicineId) return data[i].medicineName;
			}
			jQuery('input[name=medicineDesc]').html(row.medicineDesc);
			return row.medicineId;

	    },
	    filter: function(q, row){
			return row['medicineName'].toLowerCase().indexOf(q.toLowerCase()) != -1;
		}
	});
}

function loadBatchList() {
	MEDICINE.XHR('getAllMedicineBatches.action', 'json', 'medicineDisplayForm');
	var jsonString = jQuery("#ajaxJsonResponse").html();
	var json = JSON.parse(jsonString);
//	alert(JSON.stringify(json.medicineBean.medicineList[1].medicineName));
	var data = json.medicineBean.batchList;
	jQuery('#medicineBatchList').combobox({  
	    data:data,  
	    valueField:'batchId',  
	    textField:'batchName',
	    onLoadError: function() {
			alert('Unable to fetch batch list');
		},
		onLoadSuccess: function() {
		},
		formatter:function(row){  
			for(var i=0; i<data.length; i++){
				if (data[i].batchId == row.batchId) return data[i].batchName;
			}
			return row.batchId;

	    },
	    filter: function(q, row){
			return row['batchName'].toLowerCase().indexOf(q.toLowerCase()) != -1;
		}
	});
}


function loadMedicineBatchListByMedicineId(medicineId) {
	// If medicine name does not match with database then it loads all medicine ids.
	MEDICINE.XHR('getMedicineDetailsByMedicineId.action?medicineId='+medicineId + '&medicineName='+medicineId, 'json', '');
	var jsonString = jQuery("#ajaxJsonResponse").html();
	var json = JSON.parse(jsonString);
	var data = json.medicineBean.batchList;
	jQuery('#medicineBatchList').combobox({  
	    data:data,  
	    valueField:'batchId',  
	    textField:'batchName',
	    onLoadError: function() {
			alert('Unable to fetch batch list');
		},
		onLoadSuccess: function() {
		},
		formatter:function(row){  
			for(var i=0; i<data.length; i++){
				if (data[i].batchId == row.batchId) return data[i].batchName;
			}
			return row.batchId;

	    }  
	});
}

function loadMedRepList(wholesellerId,selectElemId) {
	MEDICINE.XHR('medRepByWholeSellerId.action?wholesellerId='+wholesellerId, 'json', '');
	var jsonString = jQuery("#ajaxJsonResponse").html();
	var json = JSON.parse(jsonString);
	var data = json.medRepBean.medRepList;
	jQuery('.' + selectElemId).combobox({  
	    data:data,  
	    valueField:'medRepId',  
	    textField:'medRepName',
	    onLoadError: function() {
			alert('Unable to fetch medicine list');
		},
		onLoadSuccess: function() {
		},
	    filter: function(q, row){
			return row['medRepName'].toLowerCase().indexOf(q.toLowerCase()) != -1;
		}
	});
}

function loadWholeSellers(selectElemId) { 
	MEDICINE.XHR('getAllWholesellers.action', 'json', '');
	var jsonString = jQuery("#ajaxJsonResponse").html();
	var json = JSON.parse(jsonString);
	var data = json.wholesellerBean.wholeSellerList;
	jQuery('#' + selectElemId).combobox({  
	    data:data,  
	    valueField:'wholesellerId',  
	    textField:'wholesellerName',
	    onLoadError: function() {
			alert('Unable to fetch whole seller list');
		},
		onLoadSuccess: function() {
		},
		formatter:function(row){  
			for(var i=0; i<data.length; i++){
				if (data[i].wholesellerId == row.wholesellerId) return data[i].wholesellerName;
			}
			return row.wholesellerId;

	    }  
	});
}

function displayDoctor(doctorId, url) {
//	MEDICINE.disableForm('doctorDisplayForm');
	jQuery('#doctorDisplayDiv').form('load', url);
	jQuery('#doctorDisplayDiv').css('display', 'block');
	jQuery('#doctorDisplayWindow').window('open');
	jQuery('.validatebox-tip').css('display', 'none');
	if(doctorId == 0){
		jQuery('#doctorDisplayWindow').dialog('setTitle','Create Doctor'); 
		jQuery('.deleteDoctor').css('display', 'none');
	} else {
		jQuery('#doctorDisplayWindow').dialog('setTitle','Update Doctor'); 
		jQuery('.deleteDoctor').css('display','');
	}
	MEDICINE.enableForm('doctorDisplayForm');
	
}