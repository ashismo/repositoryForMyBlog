<html>
<head>
	<script src="static/js/module.js"></script>
</head>

<body>
		<div>
			<fieldset>
			<input class="submit" type="button" value="Add new module" id="newModule"> </p>
			
			<div>
			<table id="moduleDataTable" class="display cell-border" cellspacing="0" width="100%">
				<thead>
		            <tr>
		            	<th>Module Id</th>
		                <th>Module Name</th>
		                <th>Module Desc</th>
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
			
		</div>
		
		
			<div id="addModifyModule" style="display:none;" title="Add/Modify Module">
		<form class="moduleForm" id="moduleForm" method="post" action="">
			<fieldset>
				<h4><strong>Module Name</strong></h4>
				<p><input name="moduleName" type="text" value="" required/></p>
				<h4><strong>Module Description</strong></h4>
				<p><input name="moduleDesc" type="text" value="" required/></p>
			</fieldset>
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>
		</div>
			    
<script>
</script>
</body>
</html>