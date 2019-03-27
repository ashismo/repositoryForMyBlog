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
    
    ============================ REST CALL FROM MVC CONTROLLER ===============
    <h2>Employee Id ${employee.id}</h2>
    <h2>Hello ${employee.name}!</h2>
    <h2>Object is ${employee}</h2>
</body>
</html>