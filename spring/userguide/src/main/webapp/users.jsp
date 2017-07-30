<html>
<head>
	<script src="static/js/role.js"></script>
	<script src="static/js/users.js"></script>
</head>

<body>
			<fieldset>
			<input class="submit" type="button" value="Add new user" id="newUser"> </p>
			
			<div>
			<table id="userDataTable" class="display cell-border" cellspacing="0" width="100%">
				<thead>
		            <tr>
		            	<th>User Id</th>
		            	<th>Name</th>
		            	<th>User Name</th>
		            	<th>Password</th>
		                <th>Role Name</th>
		                <th>Email</th>
		                <th>Created By</th>
		                <th>Created On</th>
		                <th>Updated By</th>
		                <th>Updated On</th>
		            </tr>
	        	</thead>
	        	<tbody>
	        	</tbody>
			</table>
		</div>
			
			</fieldset>
			
		
		
			<div id="addModifyUser" style="display:none;" title="Add/Modify User">
		<form class="userForm" id="userForm" method="post" action="">
			<fieldset>
				<h4><strong>User Name</strong></h4>
				<p><input name="username" type="text" value="" required/></p>
				
				<h4><strong>Password</strong></h4>
				<p><input name="password" type="password" value="" required/></p>
				
				<h4><strong>Name</strong></h4>
				<p><input name="name" type="text" value="" required/></p>
				
				<h4><strong>Role Name</strong></h4>
				<p>
					 <select name="roleId" id="roleId">
				    </select>
				
				</p>
				<h4><strong>Email</strong></h4>
				<p><input name="email" type="text" value=""/></p>
				
			</fieldset>
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>
		</div>
			    
<script>
//$("#addModifyModuleEnv").validate();

</script>
</body>
</html>