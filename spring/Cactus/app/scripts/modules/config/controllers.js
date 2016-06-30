'use strict';
angular.module('config', ['bankModel'])
  .controller('configController',
    ['$scope',
      '$http',
      '$rootScope',
      'add',
      'addRole',
      'addUser',
      '$timeout',
      '$mdSidenav',
      '$log',
      '$mdToast',
      'serviceAdminProfile',
      '$interval',
      function ($scope, $http, $rootScope, add,addRole,addUser, $timeout, $mdSidenav, $log, $mdToast, serviceAdminProfile,$interval) {
        var cc = this;
        $scope.progressActivated = false;
        $scope.crud = [
          {
            "permissionId": 0,
            "moduleName": "SB",
            "permission": "CREATE",
            "startDate": "22/12/2015 00:00:00",
            "createUser": "ashish",
            "disabled":false,
            "checked":false
          },{
            "permissionId": 0,
            "moduleName": "SB",
            "permission": "READ",
            "startDate": "22/12/2015 00:00:00",
            "createUser": "ashish",
            "disabled":false,
            "checked":false
          },{
            "permissionId": 0,
            "moduleName": "SB",
            "permission": "UPDATE",
            "startDate": "22/12/2015 00:00:00",
            "createUser": "ashish",
            "disabled":false,
            "checked":false
          },{
            "permissionId": 0,
            "moduleName": "SB",
            "permission": "DELETE",
            "startDate": "22/12/2015 00:00:00",
            "createUser": "ashish",
            "disabled":false,
            "checked":false
          }
        ];
        function init() {
          console.log('m at config');
          $scope.progressActivated = true;
          serviceAdminProfile.getUserBranch().get({}, function (data){
            $scope.userBranchData = data.branches;
            $scope.userRole = $scope.userBranchData[0].users[0].userRoles;
            $scope.branchIdSelect = data.branches.map(function( obj ) {
              return {
                bId:obj.branchId,
                bName:obj.branchName
              }
            })

            /**
             * Populate Country, State, Dist by Ashish
             */
            serviceAdminProfile.getCountryStateDistDetail().get({"countryCode":"IND", "stateCode" : "WB", "distCode": "HLY"}, function (data){
              console.log(data);
              })

            $scope.parentBranchIdSelect = data.branches.filter(function( obj ) {
              if(obj.parentId == undefined ||  obj.parentId == 0) {
                return {
                  branchId:obj.branchId,
                  branchName:obj.branchName,
                  contextRoot:obj.contextRoot,
                  dbName:obj.dbName
                }
              }
            });
            $scope.parentBranchIdSelect.splice(0,0,{"branchId":0,"branchName":"--This is parent branch--"});

            $scope.progressActivated = false;
          });
          $scope.progressActivated = true;
          serviceAdminProfile.getRulesModulePermission().get({}, function (data){
            console.log(data);
            var isFound;
            for(var i=0;i<$scope.crud.length;i++){
              for(var j=0;j<data.modules.length;j++){
               if(data.modules[j].modulePermissions){
                 isFound = false;
                 for(var v=0;v<data.modules[j].modulePermissions.length;v++){
                   if(data.modules[j].modulePermissions[v].permissionId !== 0 && !data.modules[j].modulePermissions[v].disabled && !data.modules[j].modulePermissions[v].checked){
                     data.modules[j].modulePermissions[v].disabled = true;
                     data.modules[j].modulePermissions[v].checked = true;
                   }

                  if(data.modules[j].modulePermissions[v].permission === $scope.crud[i].permission){
                    isFound = true;
                  }
                 }
                 if(!isFound){
                   data.modules[j].modulePermissions.push(angular.copy($scope.crud[i]));
                 }
               } else {
                 data.modules[j].modulePermissions = angular.copy($scope.crud);
               }
              }
            }
            $scope.avaiableModules = data.modules;
            $scope.progressActivated = false;
          });

        };

        $scope.addAvalModulePermission = function(active,data){


        }

        // Populate data from parent
        $scope.populateDataFromParent = function (parentBranch) {
          $scope.cc.addBranch.parentId = parentBranch.branchId;
          $scope.cc.addBranch.bankName = parentBranch.bankName;
          if($scope.cc.addBranch.branchId == 0) { // Do not update contextRoot and dbName for Edit case
            $scope.cc.addBranch.contextRoot = parentBranch.contextRoot;
            $scope.cc.addBranch.dbName = parentBranch.dbName;
          }
        }

        //progress bar
        var self = this;
        self.activated = true;
        self.determinateValue = 30;
        // Iterate every 100ms, non-stop and increment
        // the Determinate loader.
        $interval(function() {
          self.determinateValue += 1;
          if (self.determinateValue > 100) {
            self.determinateValue = 30;
          }
        }, 100);


        $scope.checkTrueOrFalse = function(perId,rol){
          for(var i=0;i<rol.rolePermissions.length;i++){
            if(perId.moduleName === rol.rolePermissions[i].moduleName && perId.permissionId === rol.rolePermissions[i].permissionId){
              return true;
              break;
            }
          }

        }
        this.editUser = function(data){
          this.addUser = data;

         }
        $scope.attachRolePermission = function(active,per,rol){
          var data = {};
          data.branchBean = {};
          data.branchBean.branchId = $scope.selectedBid;
          data.branchBean.roles = [];
          data.branchBean.roles[0] = {};
          data.branchBean.roles[0].roleId = rol.roleId;
          data.branchBean.roles[0].rolePermissions= [];
          data.branchBean.roles[0].rolePermissions[0] = {};
          data.branchBean.roles[0].rolePermissions[0].rolePermId = 0;
          data.branchBean.roles[0].rolePermissions[0].roleId = rol.roleId;
          data.branchBean.roles[0].rolePermissions[0].permissionId = per.permissionId;
          data.branchBean.roles[0].rolePermissions[0].roleName = rol.roleName;
          data.branchBean.roles[0].rolePermissions[0].startDate = rol.startDate;
          data.branchBean.roles[0].rolePermissions[0].createUser = rol.createUser;

          if(active){
            $scope.progressActivated = true;
            var post = new serviceAdminProfile.branch();
            post.update(data,function (res) {
              moduleRefresh(res);
              $scope.progressActivated = false;
            })
          } else if(!active) {
            data.branchBean.roles[0].rolePermissions[0].deleteInd = 'Y';
            delete data.branchBean.roles[0].rolePermissions[0].createUser;
            delete data.branchBean.roles[0].rolePermissions[0].startDate;
            data.branchBean.roles[0].rolePermissions[0].updateUser = rol.createUser;


            // finding role permission id from role list

            for(var i=0;i<rol.rolePermissions.length;i++){
              if(per.permissionId === rol.rolePermissions[i].permissionId){
                data.branchBean.roles[0].rolePermissions[0].rolePermId = rol.rolePermissions[i].rolePermId;
              }
            }
            //***
            $scope.progressActivated = true;
            var post = new serviceAdminProfile.branch();
            post.update(data,function (res) {
              moduleRefresh((res))
              $scope.progressActivated = false;
            })
          }



        }
        function moduleRefresh (data) {
          if(data.branchBean.modules && data.branchBean.modules.branchModulePermissions){
            for(var i=0;i<data.branchBean.modules.branchModulePermissions.length;i++){
              data.branchBean.modules.branchModulePermissions[i].checked = true;
            }
         }


          $scope.modules = data.branchBean.modules;
          $scope.attachtedBranchRuleBeans = data.branchBean.branchRuleBeans || [];
          $scope.attachtedBranchRoleBeans = data.branchBean.roles || [];
          $scope.attachtedBranchUserBeans = data.branchBean.users || [];
        }

        this.addBranch = add.branchBean;
        this.addRole = addRole;
        this.addUser = addUser;
        this.addModule = add.modules[0];
        this.moduleRules = add.moduleRules[0];
        this.ruleMasterValues = add.ruleMasterValues[0];

        $scope.modulePopup = true;
        /* this.modelPopup = function(){
         if(!$scope.modulePopup){
         // $scope.modulePopup = true;
         } else {
         // $scope.modulePopup = false;
         }

         }*/
        this.add_new_module_permission = function (mdl) {
          var newModulePermission = angular.copy(this.moduleRules);
          var newModulePermissionValue = angular.copy(this.moduleRules);
          var data = {};
          delete mdl.modulePermissions;
          delete newModulePermission.ruleValue;
          delete newModulePermission.ruleValueDescription;
          newModulePermission.moduleName = mdl.moduleName
          data.modules = [mdl];
          data.modules[0].moduleRules = [newModulePermission];
          // data.branchRuleBeans = [newModulePermission];
          //$scope.progressActivated = true;
          var post = new serviceAdminProfile.saveRulesModulePermission();
          post.update(data,function (res) {
           // $scope.progressActivated = false;
            moduleRefresh(res);
            newModulePermissionValue.moduleName = mdl.moduleName;
            newModulePermissionValue.ruleValueId = 0;
            delete newModulePermissionValue.ruleDescription;
            data.modules[0].moduleRules[0].ruleMasterValues = [newModulePermissionValue]
            data.modules[0].moduleRules[0].ruleId =  res.modules[0].moduleRules[0].ruleId;
            var post = new serviceAdminProfile.saveRulesModulePermission();
            post.update(data,function (resSub) {
              moduleRefresh(resSub);
              $('.close-popup').click();
             // $scope.progressActivated = false;
            })

          });
        }

          /**
           * Added by Ashish - Reset form
           */
          this.resetBranchForm = function () {
            this.addBranch = add.branchBean;
            $scope.branchAddressAttached = null;
            $scope.branchAddress = null; // Nullify branch address
            if(this.addBranch.addresses) {
              $scope.branchAddress = selected.addresses;
              $scope.branchAddressAttached = 'Y';
            }

          }
          $scope.populateBranchAddress = function (isBranchAttached) {
            if(isBranchAttached) {
              this.selectedBranch.addresses = add.branchBean.addresses;
            } else {
              this.selectedBranch.addresses = [];
            }
          }

          this.saveBranch = function () {
          // this.addBranch.startDate = new Date();
          this.addBranch.startDate = "23/03/2016 00:00:00";
          var branchBean = {}
          branchBean.branchBean = this.addBranch;
          if(branchBean.branchBean.users) {
            delete branchBean.branchBean.users;
          }
          var post = new serviceAdminProfile.branch();
          $scope.progressActivated = true;
          post.update(branchBean,function (data) {
            moduleRefresh(data);
            $scope.progressActivated = false;

          });
          // this.branch.push(this.addBranch);
          $scope.showCustomToast();
          $timeout(function(){
            $scope.toggleRight();
          },300)
        };
        $scope.editAttachtedModule = false;
        this.attachtedRuleUpdate = function(rule,module){
          $scope.editAttachtedModule = true;
          $scope.editModule = module;
          $scope.editRuleNon = rule;
          $scope.editRule = angular.copy($scope.editRuleNon);

        }
        this.updateModulePermission = function(){
          $scope.editAttachtedModule = false;
          var data = {};
          var editRule = angular.copy($scope.editRule);
          data.branchBean = {};
          data.branchBean.branchId = editRule.branchId;
          data.branchBean.branchRuleBeans = [];
          data.branchBean.branchRuleBeans[0] = {};
          data.branchBean.branchRuleBeans[0].branchRuleId = editRule.branchRuleId;
          data.branchBean.branchRuleBeans[0].branchId = editRule.branchId;
          data.branchBean.branchRuleBeans[0].ruleId = editRule.ruleId;
          data.branchBean.branchRuleBeans[0].ruleValue = editRule.ruleValue;
          data.branchBean.branchRuleBeans[0].updateUser = editRule.createUser;
          var post = new serviceAdminProfile.branch();
          $scope.progressActivated = true;
          post.update(data,function (res) {
            moduleRefresh(res);
            $scope.progressActivated = false;
            $scope.showCustomToast('data saved');


          });
          // this.branch.push(this.addBranch);


        }
        this.saveRole = function () {
          var branch = {};
          var branch = angular.copy($scope.selectedBranch);
          var role = this.addRole;
          role.branchId = parseInt(branch.branchId);
          // this.addBranch.startDate = new Date();

          branch.roles = [this.addRole];
          var branchBean = {};
          branchBean.branchBean = branch;
          this.addRole.startDate = "23/03/2016 00:00:00";
          if(branchBean.branchBean.users) {
            delete branchBean.branchBean.users;
          }
          $scope.progressActivated = true;
          var post = new serviceAdminProfile.branch();
          post.update(branchBean,function (data) {
            moduleRefresh(data);
            console.log('done', data);
            $scope.progressActivated = false;
            $scope.showCustomToast();
          });
          // this.branch.push(this.addBranch);

          $timeout(function(){
            $scope.toggleRight();
          },300)
        };

        this.saveModule = function () {
          // this.addBranch.startDate = new Date();
          this.addModule.startDate = "31/03/2016 00:00:00";
          var data = {};
          data.modules = [this.addModule];
          $scope.progressActivated = true;
          var post = new serviceAdminProfile.saveRulesModulePermission();
          post.update(data,function (data) {
            $scope.allModulePermission = allModuleWithPermission();
            $scope.progressActivated = false;
            $scope.showCustomToast();
          });
          // this.branch.push(this.addBranch);

          $timeout(function(){
            $scope.toggleRight();
          },300)
        };
        this.saveUser = function(){
          this.addUser.branchId = $scope.selectedBranch.branchId;
          var data = {};
          data.branchBean = angular.copy($scope.selectedBranch);
          data.branchBean.users= [this.addUser];
          delete data.branchBean.fyClosed;
          delete data.branchBean.branchActive;
          $scope.progressActivated = true;
          var post = new serviceAdminProfile.branch();
          post.update(data,function (res) {
            moduleRefresh(res);
            $scope.progressActivated = false;
          });
        }
        function allModuleWithPermission (){
          $scope.progressActivated = true;
          serviceAdminProfile.getRulesModulePermission().get({},function(data){
            $scope.progressActivated = false;
            return data;

          });
        };
// check for avaiable user role
        $scope.attachRoleUser = function(active,user,role){
          var data = {};
          data.branchBean = {};
          data.branchBean.branchId = angular.copy($scope.selectedBranch.branchId);
          data.branchBean.users = [];
          data.branchBean.users[0] = {};
          data.branchBean.users[0].userId = user.userId;
          data.branchBean.users[0].branchId = angular.copy($scope.selectedBranch.branchId);;
          data.branchBean.users[0].userRoles = [];
          data.branchBean.users[0].userRoles[0] = {};
          data.branchBean.users[0].userRoles[0].userId = user.userId;
          data.branchBean.users[0].userRoles[0].roleId = role.roleId;
          data.branchBean.users[0].userRoles[0].startDate = role.startDate;
          data.branchBean.users[0].userRoles[0].createUser = role.createUser;
          if(active){
            data.branchBean.users[0].userRoles[0].userRoleId = 0;
            $scope.progressActivated = true;
            var post = new serviceAdminProfile.branch();
            post.update(data,function (res) {
              moduleRefresh(res);
              $scope.progressActivated = false;
              $scope.showCustomToast('data saved');
            });
          } else {
            user.userRoles.filter(function(obj){
             if(obj.roleId === role.roleId){
               data.branchBean.users[0].userRoles[0].userRoleId=obj.userRoleId;
             }
            });
            data.branchBean.users[0].userRoles[0].deleteInd = 'Y';
            $scope.progressActivated = true;
            var post = new serviceAdminProfile.branch();
            post.update(data,function (res) {
              moduleRefresh(res);
              $scope.progressActivated = false;
              $scope.showCustomToast('data saved');
            });
          }

        }
// check for avaiable user role
        $scope.checkTrueOrFalseUser = function(role,user){
          if(user.userRoles){
            for(var i=0;i<user.userRoles.length;i++){
              if(role.roleId === user.userRoles[i].roleId ){
                return true;
                break;
              }
            }
          }
        }

//:TODO
        this.addPermissionToModule = function (data,module) {
          var deal = {};
          console.log(data)
          deal.modules = [data];
          if(data === 'CREATE'){
            var Permissions = {
              "permissionId": 0,
              "moduleName": $scope.create,
              "startDate": "08/12/2015 00:00:00",
              "createUser": "ashish"
            }
            Permissions.permission = $scope.create;
            deal.modules[0].modulePermissions = [Permissions];
            $scope.progressActivated = true;
            var post = new serviceAdminProfile.saveRulesModulePermission();
            post.update(deal,function (data) {
              $scope.allModulePermission = allModuleWithPermission();
              $scope.progressActivated = false;
            });
          }
          if(data === 'READ'){
            var Permissions = {
              "permissionId": 0,
              "moduleName": $scope.read,
              "startDate": "08/12/2015 00:00:00",
              "createUser": "ashish"
            }
            Permissions.permission = $scope.read;
            deal.modules[0].modulePermissions = [Permissions];
            $scope.progressActivated = true;
            var post = new serviceAdminProfile.saveRulesModulePermission();
            post.update(deal,function (data) {
              $scope.progressActivated = false;
            });
          }
          if(data === 'UPDATE'){
            var Permissions = {
              "permissionId": 0,
              "moduleName": $scope.update,
              "startDate": "08/12/2015 00:00:00",
              "createUser": "ashish"
            }
            Permissions.permission = $scope.update;
            deal.modules[0].modulePermissions = [Permissions];
            $scope.progressActivated = true;
            var post = new serviceAdminProfile.saveRulesModulePermission();
            post.update(deal,function (data) {
              $scope.progressActivated = false;
            });
          }
          if(data === 'DELETE'){
            var Permissions = {
              "permissionId": 0,
              "moduleName": $scope.delete,
              "startDate": "08/12/2015 00:00:00",
              "createUser": "ashish"
            }
            Permissions.permission =  $scope.delete;
            deal.modules[0].modulePermissions = [Permissions];
            $scope.progressActivated = true;
            var post = new serviceAdminProfile.saveRulesModulePermission();
            post.update(deal,function (data) {
              $scope.progressActivated = false;
            });
          }



        }
        $scope.attachModuleToBranch = function(data,mode,newData){
          if(mode==='parent'){
            $scope.post = {};
            $scope.post.branchBean = angular.copy($scope.selectedBranch);
            $scope.post.branchBean.branchRuleBeans = [data];
            if(data.ruleMasterValues){
              $scope.value = data.ruleMasterValues;
            } else {
              $scope.value = [$scope.cc.ruleMasterValues];
            }
            $('value-set').show();
          } else if (mode==='child') {
            var post = angular.copy($scope.post);
            post.branchBean.branchRuleBeans[0].ruleMasterValues =[];
            if($scope.value.length>1){
              post.branchBean.branchRuleBeans[0].ruleMasterValues[0] = JSON.parse(newData);
            } else {
              post.branchBean.branchRuleBeans[0].ruleValue = newData;
            }


            post.branchBean.branchRuleBeans[0].branchId = post.branchBean.branchId;

            delete post.branchBean.users;
            delete post.branchBean.fyClosed;
            delete post.branchBean.branchActive;
            $scope.attachtedBranchRuleBeans.map(function(obj){
              if(obj.ruleId === post.branchBean.branchRuleBeans[0].ruleId){
                post.branchBean.branchRuleBeans[0].branchRuleId = obj.branchRuleId
              }

            })
            $scope.progressActivated = true;
            serviceAdminProfile.branch().update(post,function(res){
              //TODO:tempory code fixing for retail module addition to branch need to work on it for generalize the code
              if($scope.post.branchBean.branchRuleBeans[0].moduleName.toUpperCase() === 'RETAIL'){
                var attachRetail = {"branchBean" : {"branchId" : post.branchBean.branchId}};
                serviceAdminProfile.attachRetailToBranch().update(attachRetail,function(r){
                  console.log('attach successful');
                });
              };
              moduleRefresh(res);
              $('value-set').hide();
              $scope.progressActivated = false;
            });
          }
        }

        init();
        $scope.setBranch = function(selected){
          $scope.selectedBranch = selected;
          $scope.selectedBid = selected.branchId;
          $scope.cc.addBranch = selected;
          if(selected.parentId == undefined) {
            $scope.cc.addBranch.parentId = 0;
            $scope.cc.addBranch.dbName = selected.dbName;
            $scope.cc.addBranch.contextRoot = selected.contextRoot;
            $scope.cc.selectedParent = 0;
          } else {
            $scope.cc.selectedParent = selected.parentId;
          }
          $scope.branchAddressAttached = null;
          $scope.branchAddress = null; // Nullify branch address
          if(selected.addresses) {
            $scope.branchAddress = selected.addresses;
            $scope.branchAddressAttached = 'Y';
          }
        }
        $scope.$watch('selectedIndex', function(current, old){
          if(!$scope.selectedBid){
            //  alert('Please select a Branch');
            return false;
          }
          console.log($scope.selectedBid);
          if(current === 2 || current === 1){
            //repeat in init function
            //	if(!$scope.modules){
            $scope.progressActivated = true;
            serviceAdminProfile.getAllDeatilOfBranchById().get({"bid":$scope.selectedBid}, function (data){
              $scope.modules = data.branchBean.modules;
              if(data.branchBean.branchRuleBeans !== 'undefine'){
                moduleRefresh(data);
                $scope.progressActivated = false;
              }
            });
            //	}
          }

        });

        $scope.showAttachtedRolePermission = function(data){
          if(data.rolePermissions.length < 4){

          } else {

          }
          //$scope.permissionrole = data.rolePermissions;
        }

        //angular bootstrap UI


        $scope.oneAtATime = true;



        $scope.status = {
          isFirstOpen: true,
          isFirstDisabled: false
        };
        // end angular bootstrap Ui







        //toast start here as notification
        $scope.showCustomToast = function () {
          $mdToast.show({
            hideDelay: 3000,
            position: 'top right',
            controller: 'ToastCtrl',
            templateUrl: 'scripts/modules/config/views/toast-template.html'
          });
        };
        //toast start here as notification

        $scope.toggleLeft = buildDelayedToggler('left');
        $scope.toggleRight = buildToggler('right');
        $scope.toggleRightRole = buildToggler('rightRole');
        $scope.toggleRightModule = buildToggler('rightModule');
        $scope.toggleRightUser = buildToggler('rightUser');
        $scope.isOpenRight = function () {
          return $mdSidenav('right').isOpen();
        };
        $scope.isOpenRightRole = function () {
          return $mdSidenav('rightRole').isOpen();
        };
        $scope.isOpenRightModule = function () {
          return $mdSidenav('rightModule').isOpen();
        };
        $scope.isOpenRightUser = function () {
          return $mdSidenav('rightUser').isOpen();
        };


        //  $scope.getTabIndex = localStorage.getItem('currentTabIndex');

        /**
         * Supplies a function that will continue to operate until the
         * time is up.
         */
        function debounce(func, wait, context) {
          var timer;
          return function debounced() {
            var context = $scope,
              args = Array.prototype.slice.call(arguments);
            $timeout.cancel(timer);
            timer = $timeout(function () {
              timer = undefined;
              func.apply(context, args);
            }, wait || 10);
          };
        }

        /**
         * Build handler to open/close a SideNav; when animation finishes
         * report completion in console
         */
        function buildDelayedToggler(navID) {
          return debounce(function () {
            $mdSidenav(navID)
              .toggle()
              .then(function () {
                $log.debug("toggle " + navID + " is done");
              });
          }, 200);
        }

        function buildToggler(navID) {
          return function () {
            $mdSidenav(navID)
              .toggle()
              .then(function () {
                $log.debug("toggle " + navID + " is done");
              });
          }
        }

        $scope.close = function () {
          $mdSidenav('right').close()
            .then(function () {
              $log.debug("close RIGHT is done");
            });
        };
      }])


  .controller('ToastCtrl', function ($scope, $mdToast, $mdDialog) {
    $scope.closeToast = function () {
      if (isDlgOpen) return;
      $mdToast
        .hide()
        .then(function () {
          isDlgOpen = false;
        });
    };
    $scope.openMoreInfo = function (e) {
      if (isDlgOpen) return;
      isDlgOpen = true;
      $mdDialog
        .show($mdDialog
          .alert()
          .title('More info goes here.')
          .textContent('Something witty.')
          .ariaLabel('More info')
          .ok('Got it')
          .targetEvent(e)
        )
        .then(function () {
          isDlgOpen = false;
        })
    };
  })
  .directive('valueSet',function($parse){
    return{
      restrict: 'E',
      template: '<div ng-if="value.length > 1"><ul><li ng-repeat="val in value">'+
      '<input name="option" ng-model="ruleValue.value" type="radio" value="{{val}}"/>'+
      '{{val.ruleValueDescription}}</li></ul></div><div ng-if="value.length === 1" >'+
      '<label>{{value[0].ruleValueDescription}}</label><input type="text" ng-value="{{value[0].ruleValue}}" ng-model="ruleValue.value" /></div>'+
      '<button type="button" class="btn btn-info">Save</button>',
      scope: {
        value: '=value'
      },
      link: function (scope, element, attrs) {
        scope.ruleValue = {};
        scope.ruleValue.value = scope.value[0].ruleValue;
        element.find('button').on('click', function (e) {
          scope.$parent.$parent.$apply(attrs.confirmAction);
        });
        scope.$watch('ruleValue.value',function(val){
          if(val !== 'undefine'){
            scope.$parent.$parent.ruleValue = val;
          }
        });
      },
      controller:['$scope',function($scope){

      }]
    }

  })
  .directive('rolePermission',function($parse){
    return{
      restrict: 'E',
      template: '<div><md-checkbox ng-repeat="per in permissionrole" ng-checked="per.permissionId" ng-disabled="per.permissionId" aria-label="Disabled checkbox" ng-model="permissionrole.permissionId">'+
      '<span ng-if="per.permissionId===1">CREATE</span><span ng-if="per.permissionId===2">READ</span><span ng-if="per.permissionId===3">UPDATE</span><span ng-if="per.permissionId===4">DELETE</span></md-checkbox></div>',
      //'<md-checkbox ng-disabled="true" aria-label="Disabled checkbox" ng-model="data.cb3"></md-checkbox>'+
      // '<md-checkbox ng-disabled="true" aria-label="Disabled checkbox" ng-model="data.cb3"></md-checkbox>'+
      //'<md-checkbox ng-disabled="true" aria-label="Disabled checkbox" ng-model="data.cb3"></md-checkbox></div>',
      scope: {
        permissionrole: '=permissionrole'
      },
      link: function (scope, element, attrs) {
        /* scope.ruleValue = {};
         element.find('button').on('click', function (e) {
         scope.$parent.$parent.$apply(attrs.confirmAction);
         });
         scope.$watch('ruleValue.value',function(val){
         if(val !== 'undefine'){
         scope.$parent.$parent.ruleValue = val;
         }
         });*/
      },
      controller:['$scope',function($scope){

      }]
    }

  })
