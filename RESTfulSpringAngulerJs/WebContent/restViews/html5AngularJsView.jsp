<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app="empRecordApp">
	<head>
		<meta charset="UTF-8">
		<title>Integrate HTML5 and Angular JS</title>
		<!-- <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js"></script> -->
		<script src="<%=request.getContextPath()%>/angularjs/angular.min.js"></script>
		<script src="<%=request.getContextPath()%>/angularjs/employee.js"></script>
	</head>
	<body>
		<div>
		   GET restful Webservice call<br/>
		   ============================
		</div>
		<div ng-controller="getEmployee">
			<div>
				<p>The Employee ID is {{employeeData.empId}}</p>
				<p>The Employee Name is {{employeeData.name}}</p>
			</div>
			<div>
				EmpId: <input type="text" placeholder="Emp Id" ng-model="employeeData.empId">
				Name: <input type="text" placeholder="Name" ng-model="employeeData.name">
			</div>
		</div>
		<br/><br/>
		<div>
		   POST restful Webservice call<br/>
		   ===========================
		</div>
		<br>
		<div ng-controller="getSalary">
			<div>
				EmpId: <input type="text" placeholder="Name" ng-model="employeeData.name">
				Salary: <input type="text" placeholder="Salary" ng-model="employeeData.salary">
			</div>
			<div>
				<p>The Employee Name is {{employeeData.name}}</p>
				<p>The Employee Salary is {{employeeData.salary}}</p>
			</div>
		</div>
	</body>
</html>