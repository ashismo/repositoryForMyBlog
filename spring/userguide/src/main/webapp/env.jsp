<html>
<head>
	<script src="static/js/env.js"></script>
</head>

<body>
		<div id="searchEnv">
			<fieldset>
			<input class="submit" type="button" value="Add new env" id="newEnv"> </p>
			
			<div>
			<table id="envDataTable" class="display cell-border" cellspacing="0" width="100%">
				<thead>
		            <tr>
		            	<th>Env Id</th>
		                <th>Env Name</th>
		                <th>Env Desc</th>
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
		
		
			<div id="addModifyEnv" style="display:none;" title="Add/Modify Environment">
		<form class="envForm" id="envForm" method="post" action="">
			<fieldset>
				<h4><strong>Environment Name</strong></h4>
				<p><input name="envName" type="text" value="" required/></p>
				<h4><strong>Environment Description</strong></h4>
				<p><input name="envDesc" type="text" value="" required/></p>
			</fieldset>
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>
		</div>
			    
<script>
//$("#addModifyEnv").validate();

</script>
</body>
</html>