<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Hello ${name}!</title>
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script>
	
		$(document).ready(function() {
		    $.ajax({
		        url: "/rest/getEmployee"
		    }).then(function(data) {
		    	var json = JSON.stringify(data);
		    	$('.greeting-json').append(json);
		       $('.greeting-id').append(data.id);
		       $('.greeting-content').append(data.name);
		    });
		});
	</script>
</head>
<body>
    <h2>Hello ${name}!</h2>
    
	<div>
		<p class="greeting-json">JSON: <p>
        <p class="greeting-id">Employee ID: </p>
        <p class="greeting-content">Name: </p>
    </div>
    
    ============================ BELOW CONTENT WILL BE UPDATED WITH REST CALL===============
    <#assign employeeJsonStr = "{ \"name\": \"Ashish 100\", \"id\": \"100\"}">
    <#assign employeeJson = employeeJsonStr?eval>
    <h2>Hello ${employeeJson.name}!</h2>
</body>
</html>