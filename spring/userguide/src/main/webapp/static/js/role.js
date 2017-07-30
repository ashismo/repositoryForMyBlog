
function refreshRoleData() {
	data = {
			"roles": [{
				"roleName":"",
				"roleDesc":""
			}]
	};
	data = JSON.stringify(data);
	var _csrf = $('form#envForm input[name="_csrf"]').val();
	
	$.ajax({
        type: "POST",
        url: "rest/svc/config/master/getRole?_csrf=" + _csrf,
        contentType: "application/json; charset=utf-8",
        dataType: 'json',
        data: data,
        async: false,
        success: function( response ) {
        	$("#moduleEnvForm").find("select#roleId").html("<option value=''>All Role</option>");
        	$("#userForm").find("select#roleId").html('');
    		var options = "";
    		$.each(response.roles, function(index, object) {
    			options = options + "<option value=" + object.roleId +">" + object.roleName + "</option>";
    		});
    		
    		$("#moduleEnvForm").find("select#roleId").append(options);
    		$("#userForm").find("select#roleId").append(options);
        },
        statusCode: {
            404: function() {
              alert( "Service not found" );
            }
          }
    })
    
}
