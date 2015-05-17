<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Integrate HTML5 and Angular JS</title>
	<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js"></script>
	<!-- <script src="angularjs/angular.min.js"></script> -->
	<script src="/<%=request.getContextPath()%>/angularjs/employee.js"></script>
</head>
<body>
	<div ng-controller="getEmployee">
			<p>The Employee ID is {{employeeData.empId}}</p>
			<p>The Employee Name is {{employeeData.name}}</p>
		</div>
</body>
</html>