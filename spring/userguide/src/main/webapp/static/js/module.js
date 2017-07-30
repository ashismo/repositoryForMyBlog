function openModuleDialog(selectedItem) {
	$("#addModifyModule").css('display','block');
	
	if(selectedItem != null) {
		$('#addModifyModule input[name="moduleName"]').val(selectedItem.find('td').eq(1).html())
		$('#addModifyModule input[name="moduleDesc"]').val(selectedItem.find('td').eq(2).html())
	}
	
	var dialog = $( "#addModifyModule" ).dialog({
	      autoOpen: false,
	      height: 400,
	      width: 350,
	      modal: true,
	      buttons: {
	        "Submit": function() {
	        	var isValid = $("form#moduleForm").valid();
	        	if(!isValid) {
	        		return;
	        	}
	        	
	        	if(selectedItem != null) {
	        		moduleId = selectedItem.find('td').eq(0).html();
	        	} else {
	        		moduleId = 0;
	        	}
	        	
    			data = {
	        			"modules": [{
	        				"moduleId": moduleId,
	        				"moduleName":$('#addModifyModule input[name="moduleName"]').val(),
	        				"description":$('#addModifyModule input[name="moduleDesc"]').val()
	        			}]
	        		};
	        	data = JSON.stringify(data);
	        	var _csrf = $('form#moduleForm input[name="_csrf"]').val();
	        	$.ajax({
	                type: "POST",
	                url: "rest/svc/config/master/createModule?_csrf=" + _csrf,
	                contentType: "application/json; charset=utf-8",
	                dataType: 'json',
	                data: data,
	                async: false,
	                success: function( response ) {
	                	if(response.errorMsg) {
	                		alert(response.errorMsg);
	                	} else {
	                		$('#addModifyModule').find('input:text').val('');
	                		dialog.dialog( "destroy" );
	                		$("#addModifyModule").hide();
	                		alert("Record updated successfully");
	                		refreshModuleData(false);
	                	}
	                    console.log( response );
	                    
	                },
	                statusCode: {
	                    404: function() {
	                      alert( "Service not found" );
	                    }
	                  }
	            });
    		},
	        Cancel: function() {
	        	$('#addModifyModule').find('input:text').val('');
	          dialog.dialog( "destroy" );
	          $("#addModifyModule").hide();
	        }
	      },
	      close: function() {
	    	  $('#addModifyModule').find('input:text').val('');
	          dialog.dialog( "destroy" );
	          $("#addModifyModule").hide();
	      }
	    });
	dialog.dialog( "open" );
}

function refreshModuleData(isModuleDropdown) {
	data = {
			"modules": [{
				"moduleName":"",
				"description":""
			}]
	};
	data = JSON.stringify(data);
	var _csrf = $('form#moduleForm input[name="_csrf"]').val();
	
	
	$.ajax({
        type: "POST",
        url: "rest/svc/config/master/getModule?_csrf=" + _csrf,
        contentType: "application/json; charset=utf-8",
        dataType: 'json',
        data: data,
        async: false,
        success: function( response ) {
        	// Generate table
        	
        	if(!isModuleDropdown) {
	        	var tableBody = $('#moduleDataTable').find('tbody');
	        	tableBody.html('');
	        	var trs = "";
	        	$.each(response.modules, function(index, object) {
	        		
	        		if(object.createUser == undefined) {
	        			object.createUser = "";
	        		}
	        		if(object.createDate == undefined) {
	        			object.createDate = "";
	        		}
	        		if(object.updateUser == undefined) {
	        			object.updateUser = "";
	        		}
	        		if(object.updateDate == undefined) {
	        			object.updateDate = "";
	        		}
	        		
	        		trs += "<tr>";
	        		trs += "<td>" + object.moduleId + "</td>";
	        		trs += "<td>" + object.moduleName + "</td>";
	        		trs += "<td>" + object.description + "</td>";
	        		trs += "<td>" + object.createUser + "</td>";
	        		trs += "<td>" + object.createDate + "</td>";
	        		trs += "<td>" + object.updateUser + "</td>";
	        		trs += "<td>" + object.updateDate + "</td>";
	        		trs += "</tr>";
	        	});
	        	tableBody.append(trs);
	        	$('#moduleDataTable').DataTable();
	        	
	        	$('#moduleDataTable tbody').on( 'click', 'tr', function () {
	                var $row = $(this).closest("tr");
	                
	                openModuleDialog($row);
	            } );
        	} else {
        		var options = "";
        		$("#moduleEnvForm").find("select#moduleId").html("<option value=''>Select Module</option>");
        		$.each(response.modules, function(index, object) {
        			options = options + "<option value=" + object.moduleId +">" + object.moduleName + "</option>";
        		});
        		
        		$("#moduleEnvForm").find("select#moduleId").append(options);
        	}
        },
        statusCode: {
            404: function() {
              alert( "Service not found" );
            }
          }
    });
}
