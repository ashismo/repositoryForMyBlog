function openModuleEnvDialog(selectedItem) {
	$("#addModifyModuleEnv").css('display','block');
	
	
	// dropdown - Env Id
	refreshEnvData(true);
	
	// dropdown - Module Id
	refreshModuleData(true);

	// dropdown - Role Id
	refreshRoleData();
	
	if(selectedItem != null) {
		$('#addModifyModuleEnv input[name="urlName"]').val(selectedItem.find('td').eq(1).html());
		$('#addModifyModuleEnv input[name="urlDesc"]').val(selectedItem.find('td').eq(2).html());
		$('#addModifyModuleEnv input[name="url"]').val(selectedItem.find('td').eq(3).html());
		$('#addModifyModuleEnv input[name="userName"]').val(selectedItem.find('td').eq(4).html());
		$('#addModifyModuleEnv input[name="password"]').val(selectedItem.find('td').eq(5).html());
		
		$('#addModifyModuleEnv select[name="moduleId"]').val(selectedItem.find('td').eq(6).find('input').val());
		$('#addModifyModuleEnv select[name="envId"]').val(selectedItem.find('td').eq(7).find('input').val());
		$('#addModifyModuleEnv select[name="roleId"]').val(selectedItem.find('td').eq(8).find('input').val());
		
		var visibility = selectedItem.find('td').eq(9).find('input').val();
		if(visibility == "NO") {
			visibility = "false";
		} else {
			visibility = "true";
		}
		$('#addModifyModuleEnv select[name="visibility"]').val(visibility);
		
		$('#addModifyModuleEnv input[name="email"]').val(selectedItem.find('td').eq(10).html());
	}
	var dialog = $( "#addModifyModuleEnv" ).dialog({
	      autoOpen: false,
	      height: 650,
	      width: 750,
	      modal: true,
	      buttons: {
	        "Submit": function() {
	        	var isValid = $("form#moduleEnvForm").valid();
	        	if(!isValid) {
	        		return;
	        	}
	        	
	        	if(selectedItem != null) {
	        		moduleEnvId = selectedItem.find('td').eq(0).html();
	        	} else {
	        		moduleEnvId = 0;
	        	}
	        	
    			data = {
	        			"urls": [{
	        				"urlId":moduleEnvId,
	        				"name":$('#addModifyModuleEnv input[name="urlName"]').val(),
	        				"description":$('#addModifyModuleEnv input[name="urlDesc"]').val(),
	        				"url":$('#addModifyModuleEnv input[name="url"]').val(),
	        				"username":$('#addModifyModuleEnv input[name="userName"]').val(),
	        				"password":$('#addModifyModuleEnv input[name="password"]').val(),
	        				"moduleId":$('#addModifyModuleEnv select[name="moduleId"]').val(),
	        				"envId":$('#addModifyModuleEnv select[name="envId"]').val(),
	        				"roleId":$('#addModifyModuleEnv select[name="roleId"]').val(),
	        				"visible":$('#addModifyModuleEnv select[name="visibility"]').val(),
	        				"email":$('#addModifyModuleEnv input[name="email"]').val()
	        			}]
	        		};
    			
	        	data = JSON.stringify(data);
	        	var _csrf = $('form#moduleEnvForm input[name="_csrf"]').val();
	        	$.ajax({
	                type: "POST",
	                url: "rest/svc/guide/createUrl?_csrf=" + _csrf,
	                contentType: "application/json; charset=utf-8",
	                dataType: 'json',
	                data: data,
	                async: false,
	                success: function( response ) {
	                	if(response.errorMsg) {
	                		alert(response.errorMsg);
	                	} else {
	                		$('#addModifyModuleEnv').find('input:text').val('');
	                		dialog.dialog( "destroy" );
	                		$("#addModifyModuleEnv").hide();
	                		alert("Record updated successfully");
	                		refreshModuleEnvData(false);
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
	        	$('#addModifyModuleEnv').find('input:text').val('');
	          dialog.dialog( "destroy" );
	          $("#addModifyModuleEnv").hide();
	        },
	        Delete: function() {
	        	if(selectedItem != null) {
	        		urlId = selectedItem.find('td').eq(0).html();
	        	} else {
	        		urlId = 0;
	        	}
	        	
    			data = {
	        			"urls": [{
	        				"urlId":urlId,
	        				"name":$('#addModifyModuleEnv input[name="urlName"]').val(),
	        				"description":$('#addModifyModuleEnv input[name="urlDesc"]').val(),
	        				"url":$('#addModifyModuleEnv input[name="url"]').val(),
	        				"username":$('#addModifyModuleEnv input[name="userName"]').val(),
	        				"password":$('#addModifyModuleEnv input[name="password"]').val(),
	        				"moduleId":$('#addModifyModuleEnv select[name="moduleId"]').val(),
	        				"envId":$('#addModifyModuleEnv select[name="envId"]').val(),
	        				"roleId":$('#addModifyModuleEnv select[name="roleId"]').val(),
	        				"visible":$('#addModifyModuleEnv select[name="visibility"]').val(),
	        				"email":$('#addModifyModuleEnv input[name="email"]').val()
	        			}]
	        		};
    			
	        	data = JSON.stringify(data);
	        	var _csrf = $('form#moduleEnvForm input[name="_csrf"]').val();
	        	$.ajax({
	                type: "POST",
	                url: "rest/svc/guide/deleteUrl?_csrf=" + _csrf,
	                contentType: "application/json; charset=utf-8",
	                dataType: 'json',
	                data: data,
	                async: false,
	                success: function( response ) {
	                	if(response.errorMsg) {
	                		alert(response.errorMsg);
	                	} else {
	                		$('#addModifyModuleEnv').find('input:text').val('');
	                		dialog.dialog( "destroy" );
	                		$("#addModifyModuleEnv").hide();
	                		alert("Record deleted successfully");
	                		refreshModuleEnvData(false);
	                	}
	                    console.log( response );
	                    
	                },
	                statusCode: {
	                    404: function() {
	                      alert( "Service not found" );
	                    }
	                  }
	            });
	        }
	      },
	      close: function() {
	    	  $('#addModifyModuleEnv').find('input:text').val('');
	          dialog.dialog( "destroy" );
	          $("#addModifyModuleEnv").hide();
	      }
	    });
	dialog.dialog( "open" );
}

function refreshModuleEnvData(isGuestUser) {
	data = {
			"urls": [{
				"name":"",
				"description":""
			}]
	};
	if(isGuestUser) {
		data.guestUser = "true";
	} else {
		data.guestUser = "false";
	}
	data = JSON.stringify(data);
	var _csrf = $('form#moduleEnvForm input[name="_csrf"]').val();
	
	$.ajax({
        type: "POST",
        url: "rest/svc/guide/getUrl",
        contentType: "application/json; charset=utf-8",
        dataType: 'json',
        data: data,
        async: false,
        success: function( response ) {
        	
        	if(response.errorMsg) {
        		alert(response.errorMsg);
        		return;
        	}
        	
        	// Generate table
        	var tableBody = "";
        	
        	if(!isGuestUser) {
        		tableBody = $('#moduleEnvDataTable').find('tbody');
        	} else {
        		tableBody = $('#searchDataTable').find('tbody');
        	}
        	
        	tableBody.html('');
        	
        	var trs = "";
        	$.each(response.urls, function(index, object) {
        		
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
        		
        		if(object.moduleId == undefined) {
        			object.moduleId = "";
        			object.moduleName = "";
        		}
        		if(object.envId == undefined) {
        			object.envId = "";
        			object.envName = "";
        		}
        		if(object.roleId == undefined) {
        			object.roleId = "";
        			object.roleName = "";
        		}
        		if(object.username == undefined) {
        			object.username = "";
        		}
        		if(object.password == undefined) {
        			object.password = "";
        		}
        		if(object.email == undefined) {
        			object.email = "";
        		}
        		if(object.visible == true) {
        			object.visible = "YES";
        		} else {
        			object.visible = "NO";
        		}
        		
        		
        		trs += "<tr>";
        		if(!isGuestUser) {
        			trs += "<td>" + object.urlId + "</td>";
        		}
        		trs += "<td>" + object.name + "</td>";
        		trs += "<td>" + object.description + "</td>";
        		if(!isGuestUser) {
        			trs += "<td>" + object.url + "</td>";
        		} else {
//        			trs += "<td><a href='" + object.url + "' target='_blank'>" + object.url + "</a></td>";
        			trs += "<td>" + object.url + "</td>";
        			trs += "<td><input type='button' value='Open URL'></td>";
        		}
        		trs += "<td>" + object.username + "</td>";
        		trs += "<td>" + object.password + "</td>";
        		trs += "<td>" + object.moduleName + "<input type='hidden' value='" + object.moduleId + "'/></td>";
        		trs += "<td>" + object.envName + "<input type='hidden' value='" + object.envId + "'/></td>";
        		
        		if(!isGuestUser) {
	        		trs += "<td>" + object.roleName + "<input type='hidden' value='" + object.roleId + "'/></td>";
	        		trs += "<td>" + object.visible + "<input type='hidden' value='" + object.visible + "'/></td>";
        		}
        		trs += "<td>" + object.email + "</td>";
        		if(!isGuestUser) {
	        		trs += "<td>" + object.createUser + "</td>";
	        		trs += "<td>" + object.createDate + "</td>";
	        		trs += "<td>" + object.updateUser + "</td>";
	        		trs += "<td>" + object.updateDate + "</td>";
        		}
        		trs += "</tr>";
        	});
        	tableBody.append(trs);
        	var table = "";
        	if(!isGuestUser) {
        		table = $('#moduleEnvDataTable').DataTable();
        	} else {
        		table = $('#searchDataTable').DataTable();
        	}
        	
        	$('#moduleEnvDataTable tbody').on( 'click', 'tr', function () {
                var $row = $(this).closest("tr");
                openModuleEnvDialog($row);
            } );
        	
        	$('#searchDataTable tbody').on( 'click', 'tr td:nth-child(4)', function () {
                var url = $(this).closest("tr").find('td').eq(2).html();
                if(url.length > 0) {
                	window.open(url,'_blank');
                }
                
            } );
        	
        },
        statusCode: {
            404: function() {
              alert( "Service not found" );
            }
          }
    })
    
}
