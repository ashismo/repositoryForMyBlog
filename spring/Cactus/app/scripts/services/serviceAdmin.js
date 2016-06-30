'use strict';
var isLocalEnv = false;
var ip = "localhost:8080";
//var ip = "52.38.174.233";
//var ip = "52.36.207.255";
serviceAdmin.factory('serviceAdminProfile', function ($resource,$http) {
  return {
   branch: function () {
      var URL = isLocalEnv ? "assets/data/adminprofile.json" : "http://" + ip + "/AdminSvcWS/rest/createBranch";
      return $resource(URL, null, {
        update: {
          method: 'POST',
          params: {},
          isArray: false,
          headers: {
            'Authorization': "Basic " + btoa('ujan' + ":" + 'ashish')
          }
        },
        get: {
          method: 'GET',
          params: {},
          isArray: false,
          headers: {
            'Authorization': "Basic " + btoa('ujan' + ":" + 'ashish'),
          }
        }
      })
    },
   getUserBranch: function () {
      var URL = isLocalEnv ? "assets/data/getUserBranch.json" : "http://" + ip + "/AdminSvcWS/rest/getUserBranch?username=ujan";
      return $resource(URL, null, {
        get: {
          method: 'GET',
          isArray: false,
          headers: {
            'Authorization': "Basic " + btoa('ujan' + ":" + 'ashish')
          }
        }
      })
    },
    getCountryStateDistDetail: function () {
      var URL = isLocalEnv ? "assets/data/branchById.json" : "http://" + ip + "/AdminSvcWS/rest/getCountryStateDist?countryCode=:countryCode&stateCode=:stateCode&distCode=:distCode";
      return $resource(URL, null, {
        get: {
          method: 'GET',
          params: {},
          isArray: false,
          headers: {
            'Authorization': "Basic " + btoa('ujan' + ":" + 'ashish')
          }
        }
      })
    },
   getAllDeatilOfBranchById: function () {
      var URL = isLocalEnv ? "assets/data/branchById.json" : "http://" + ip + "/AdminSvcWS/rest/getBranch?branchId=:bid";
      return $resource(URL, null, {
        get: {
          method: 'GET',
          params: {},
          isArray: false,
          headers: {
            'Authorization': "Basic " + btoa('ujan' + ":" + 'ashish')
          }
        }
      })
    },
   saveRulesModulePermission: function () {
      var URL = isLocalEnv ? "assets/data/branchById.json" : "http://" + ip + "/AdminSvcWS/rest/saveModuleRulesAndPermissions";
      return $resource(URL, null, {
        update: {
          method: 'POST',
          params: {},
          isArray: false,
          headers: {
            'Authorization': "Basic " + btoa('ujan' + ":" + 'ashish')
          }
        }
      })
    },
   getRulesModulePermission: function () {
      var URL = isLocalEnv ? "assets/data/allmodule.json" : "http://" + ip + "/AdminSvcWS/rest/getModuleRulesAndPermissions";
      return $resource(URL, null, {
        get: {
          method: 'GET',
          params: {},
          isArray: false,
          headers: {
            'Authorization': "Basic " + btoa('ujan' + ":" + 'ashish')
          }
        }
      })
    },
    attachRetailToBranch: function () {
      var URL = isLocalEnv ? "assets/data/allmodule.json" : "http://" + ip + "/RetailSvcWS/rest/createRetailBranch";
      return $resource(URL, null, {
        update: {
          method: 'POST',
          params: {},
          isArray: false,
          headers: {
            'Authorization': "Basic " + btoa('ujan' + ":" + 'ashish')
          }
        }
      })
    }
  }
});
serviceAdmin.factory('getBranch', function ($resource) {
  var URL = isLocalEnv ? "http://52.36.18.167/AdminSvcWS/rest/getBranch?branchId=1" : "http://52.36.18.167/AdminSvcWS/rest/getBranch?branchId=12";
  return $resource(URL, null, {
    get: {
      method: 'GET',
      params: {},
      isArray: false
    },
    update: {
      method: 'POST',
      params: {},
      isArray: false
      }
  })
});

serviceAdmin.factory('mySharedService', function ($rootScope) {
  var sharedService = {};

  sharedService.pic = '';

  sharedService.prepForBroadcast = function (msg) {
    this.message = msg;
    this.broadcastItem();
  };

  sharedService.broadcastItem = function () {
    $rootScope.$broadcast('handleBroadcast');
  };

  return sharedService;
});
