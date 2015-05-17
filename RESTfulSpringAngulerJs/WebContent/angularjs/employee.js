/*
 * In this below example, the GET webservice got called inside the controller
 */
var empRecordAppCtrl = angular.module('empRecordApp', []);

empRecordAppCtrl.controller('getEmployee', function($scope, $http) {
    $http.get("http://localhost:8080/test/rest/hello/getEmployee/123")
	    .success(function(response) {
	    	$scope.employeeData = response;
	    });
});


/*
 * In this below example, the POST webservice got called inside the controller
 */

empRecordAppCtrl.controller('getSalary', function($scope, $http) {
    $http.post("http://localhost:8080/test/rest/hello/getSalary", {empId:'123'})
    .success(function(response) {
    	$scope.employeeData = response;
    });
});

