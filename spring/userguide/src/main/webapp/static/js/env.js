function openEnvDialog(selectedItem) {
	$("#addModifyEnv").css('display','block');
	
	if(selectedItem != null) {
		$('#addModifyEnv input[name="envName"]').val(selectedItem.find('td').eq(1).html())
		$('#addModifyEnv input[name="envDesc"]').val(selectedItem.find('td').eq(2).html())
	}
	var dialog = $( "#addModifyEnv" ).dialog({
	      autoOpen: false,
	      height: 400,
	      width: 350,
	      modal: true,
	      buttons: {
	        "Submit": function() {
	        	var isValid = $("form#envForm").valid();
	        	if(!isValid) {
	        		return;
	        	}
	        	
	        	if(selectedItem != null) {
	        		envId = selectedItem.find('td').eq(0).html();
	        	} else {
	        		envId = 0;
	        	}
	        	
    			data = {
	        			"environments": [{
	        				"envId":envId,
	        				"envName":$('#addModifyEnv input[name="envName"]').val(),
	        				"envDesc":$('#addModifyEnv input[name="envDesc"]').val()
	        			}]
	        		};
    			
	        	data = JSON.stringify(data);
	        	var _csrf = $('form#envForm input[name="_csrf"]').val();
	        	$.ajax({
	                type: "POST",
	                url: "rest/svc/config/master/createEnv?_csrf=" + _csrf,
	                contentType: "application/json; charset=utf-8",
	                dataType: 'json',
	                data: data,
	                async: false,
	                success: function( response ) {
	                	if(response.errorMsg) {
	                		alert(response.errorMsg);
	                	} else {
	                		$('#addModifyEnv').find('input:text').val('');
	                		dialog.dialog( "destroy" );
	                		$("#addModifyEnv").hide();
	                		alert("Record updated successfully");
	                		refreshEnvData(false);
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
	        	$('#addModifyEnv').find('input:text').val('');
	          dialog.dialog( "destroy" );
	          $("#addModifyEnv").hide();
	        }
	      },
	      close: function() {
	    	  $('#addModifyEnv').find('input:text').val('');
	          dialog.dialog( "destroy" );
	          $("#addModifyEnv").hide();
	      }
	    });
	dialog.dialog( "open" );
}

function refreshEnvData(isEnvDropdown) {
	data = {
			"environments": [{
				"envName":"",
				"envDesc":""
			}]
	};
	data = JSON.stringify(data);
	var _csrf = $('form#envForm input[name="_csrf"]').val();
	
	$.ajax({
        type: "POST",
        url: "rest/svc/config/master/getEnv?_csrf=" + _csrf,
        contentType: "application/json; charset=utf-8",
        dataType: 'json',
        data: data,
        async: false,
        success: function( response ) {
        	// Generate table
        	
        	if(!isEnvDropdown) {
	        	var tableBody = $('#envDataTable').find('tbody');
	        	tableBody.html('');
	        	
	        	var trs = "";
	        	$.each(response.environments, function(index, object) {
	        		
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
	        		trs += "<td>" + object.envId + "</td>";
	        		trs += "<td>" + object.envName + "</td>";
	        		trs += "<td>" + object.envDesc + "</td>";
	        		trs += "<td>" + object.createUser + "</td>";
	        		trs += "<td>" + object.createDate + "</td>";
	        		trs += "<td>" + object.updateUser + "</td>";
	        		trs += "<td>" + object.updateDate + "</td>";
	        		trs += "</tr>";
	        	});
	        	tableBody.append(trs);
	        	var table = $('#envDataTable').DataTable();
	        	
	        	$('#envDataTable tbody').on( 'click', 'tr', function () {
	                var $row = $(this).closest("tr");
	                
	                openEnvDialog($row);
	            } );
	        	
        	} else { // Populate environment dropdown
        		$("#moduleEnvForm").find("select#envId").html("<option value=''>Select Env</option>");
        		var options = "";
        		$.each(response.environments, function(index, object) {
        			options = options + "<option value=" + object.envId +">" + object.envName + "</option>";
        		});
        		
        		$("#moduleEnvForm").find("select#envId").append(options);
        	}
        },
        statusCode: {
            404: function() {
              alert( "Service not found" );
            }
          }
    })
    
}
