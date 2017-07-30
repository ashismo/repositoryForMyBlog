function openUserDialog(selectedItem) {
	$("#addModifyUser").css('display','block');
	
	
	// dropdown - Role Id
	refreshRoleData();
	
	if(selectedItem != null) {
		$('#addModifyUser input[name="name"]').val(selectedItem.find('td').eq(1).html());
		$('#addModifyUser input[name="username"]').val(selectedItem.find('td').eq(2).html());
		$('#addModifyUser input[name="password"]').val(selectedItem.find('td').eq(3).find('input').val());
		$('#addModifyUser select[name="roleId"]').val(selectedItem.find('td').eq(4).find('input').val());
		$('#addModifyUser input[name="email"]').val(selectedItem.find('td').eq(5).html());
	}
	var dialog = $( "#addModifyUser" ).dialog({
	      autoOpen: false,
	      height: 700,
	      width: 350,
	      modal: true,
	      buttons: {
	        "Submit": function() {
	        	var isValid = $("form#userForm").valid();
	        	if(!isValid) {
	        		return;
	        	}
	        	
	        	if(selectedItem != null) {
	        		userId = selectedItem.find('td').eq(0).html();
	        	} else {
	        		userId = 0;
	        	}
	        	
    			data = {
	        			"users": [{
	        				"userId":userId,
	        				"name":$('#addModifyUser input[name="name"]').val(),
	        				"username":$('#addModifyUser input[name="username"]').val(),
	        				"password":$('#addModifyUser input[name="password"]').val(),
	        				"roleId":$('#addModifyUser select[name="roleId"]').val(),
	        				"email":$('#addModifyUser input[name="email"]').val()
	        			}]
	        		};
    			
	        	data = JSON.stringify(data);
	        	var _csrf = $('form#userForm input[name="_csrf"]').val();
	        	$.ajax({
	                type: "POST",
	                url: "rest/svc/guide/admin/createUser?_csrf=" + _csrf,
	                contentType: "application/json; charset=utf-8",
	                dataType: 'json',
	                data: data,
	                async: false,
	                success: function( response ) {
	                	if(response.errorMsg) {
	                		alert(response.errorMsg);
	                	} else {
	                		$('#addModifyUser').find('input:text').val('');
	                		dialog.dialog( "destroy" );
	                		$("#addModifyUser").hide();
	                		alert("Record updated successfully");
	                		refreshUserData();
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
	        	$('#addModifyUser').find('input:text').val('');
	          dialog.dialog( "destroy" );
	          $("#addModifyUser").hide();
	        }
	      },
	      close: function() {
	    	  $('#addModifyUser').find('input:text').val('');
	          dialog.dialog( "destroy" );
	          $("#addModifyUser").hide();
	      }
	    });
	dialog.dialog( "open" );
}

function refreshUserData() {
	data = {
			"users": [{
				"username":"",
				"password":""
			}]
	};
	data = JSON.stringify(data);
	var _csrf = $('form#userForm input[name="_csrf"]').val();
	
	$.ajax({
        type: "POST",
        url: "rest/svc/guide/admin/getUser?csrf=" + _csrf,
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
        	var tableBody = $('#userDataTable').find('tbody');
        	
        	
        	tableBody.html('');
        	
        	var trs = "";
        	$.each(response.users, function(index, object) {
        		
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
        		
        		if(object.roleId == undefined) {
        			object.roleId = "";
        			object.roleName = "";
        		}
        		if(object.email == undefined) {
        			object.email = "";
        		}
        		
        		
        		trs += "<tr>";
        		trs += "<td>" + object.userId + "</td>";
        		trs += "<td>" + object.name + "</td>";
        		trs += "<td>" + object.username + "</td>";
        		trs += "<td> <input type='password' value='" + object.password + "'/></td>";
	        	trs += "<td>" + object.roleName + "<input type='hidden' value='" + object.roleId + "'/></td>";
        		trs += "<td>" + object.email + "</td>";
        		trs += "<td>" + object.createUser + "</td>";
        		trs += "<td>" + object.createDate + "</td>";
        		trs += "<td>" + object.updateUser + "</td>";
        		trs += "<td>" + object.updateDate + "</td>";
        		trs += "</tr>";
        	});
        	tableBody.append(trs);
        	var table = $('#userDataTable').DataTable();
        	
        	$('#userDataTable tbody').on( 'click', 'tr', function () {
                var $row = $(this).closest("tr");
                
                openUserDialog($row);
            } );
        },
        statusCode: {
            404: function() {
              alert( "Service not found" );
            }
          }
    })
    
}
