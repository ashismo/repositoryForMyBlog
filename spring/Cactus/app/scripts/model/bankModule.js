/**
 * Created by Madhurjya on 3/20/2016.
 */
angular.module('bankModel', [])
.factory('add', function($rootScope) {
  var obj = {};
  obj={
    "branchBean": {
      "branchId": 0,
        "bankName": "Kalipur",
        "branchName": "Kalipur",
        "ifscCode": "12345743",
        "micrCode": "87656321",
        "email1": "ashismo@gmail.com",
        "phone1": "9830625559",
        "startDate": "18/12/2015 00:00:00",
        "createUser": "ashish",
        "dbName": "coop_kalipur",
        "contextRoot": "CustomerSvcWS"
    },
    "modules": [
      {
        "moduleId": 0,
        "moduleName": "RD",
        "startDate": "08/12/2015 00:00:00",
        "createUser": "ashish",
      }
    ],
    "moduleRules": [
      {
        "ruleId": 0,
        "moduleName": "",
        "ruleName": "LOCK_AFTER_NO_OF_ATTEMPTS",
        "ruleDescription": "Lock User After how many attempts",
        "startDate": "10/12/2015 00:00:00",
        "createUser": "ashish",

      }
    ],
    "ruleMasterValues": [
      {
        "ruleValueId": 0,
        "moduleName": "GENERAL",
        "ruleName": "LOCK_AFTER_NO_OF_ATTEMPTS",
        "ruleValue": "5",
        "ruleValueDescription": "Lock after how many unsuccessful attempts?",
        "startDate": "10/12/2015 00:00:00",
        "createUser": "ashish"
      }
    ]
  }
  return obj;
})
  .factory('addRole', function($rootScope) {
  var obj = {};
  obj={
      "roleId": 0,
      "branchId": 1,
      "roleName": "USER",
      "roleDescription": "Normal User",
      "startDate": "11/12/2015 00:00:00",
      "createUser": "ashish"
  }
  return obj;
})
  .factory('addUser', function($rootScope) {
  var obj = {};
  obj={
    "userId": 0,
    "branchId": 1,
    "userName": "ashish",
    "password": "ashish",
    "transactionPassword": "ashish",
    "firstName": "Ashish",
    "phone1": "9830525559",
    "startDate": "11/12/2015 00:00:00",
    "createUser": "ashish"
  }
  return obj;
})

