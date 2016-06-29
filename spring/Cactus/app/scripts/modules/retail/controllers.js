/**
 * Created by Madhurjya on 6/13/2016.
 */
'use strict';
angular.module('retail', ['bankModel','ngMaterial','ngMaterialDatePicker'])
  .controller('retailController',
    ['$scope',
      '$http',
      '$rootScope',
      '$timeout',
      '$mdSidenav',
      '$log',
      '$mdToast',
      'serviceRetailProfile',
      '$interval',
      '$mdDialog',
      function ($scope, $http, $rootScope, $timeout, $mdSidenav, $log, $mdToast, serviceRetailProfile,$interval,$mdDialog) {

        function init(){
          serviceRetailProfile.materialGroup().get({"serviceUrl":"getMaterialGroup","bid":"1"}, function (data){
              $scope.model.materialGroups = data.branchBean.materialGroups;
          });

        }
        init();
        this.sampleAction = function(name, ev) {
          $mdDialog.show($mdDialog.alert()
            .title(name)
            .textContent('You triggered the "' + name + '" action')
            .ok('Great')
            .targetEvent(ev)
          );
        };




        //inline editable code
        $scope.modelMaterial = {
          materials: [{
            id: 0,
            name: "Ben",
            glcode: 28
          }, {
            id: 1,
            name: "Sally",
            glcode: 24
          }, {
            id: 2,
            name: "John",
            glcode: 32
          }, {
            id: 3,
            name: "Jane",
            glcode: 40
          }],
          selected: {}
        };

        $scope.model = {
          materialGroups: [],
          selected: {}
        };
        $scope.addMaterialGroupMaterial = function(sec){
          if($scope.model.materialGroups[0].name !== undefined && $scope.model.materialGroups[0].glcode !== undefined && sec==="group"){
            $scope.model.materialGroups.unshift({"materialGrpId":0});
            $scope.editMaterialGroup("group",$scope.model.materialGroups[0]);
          } else if($scope.modelMaterial.materials[0].name !== undefined && $scope.modelMaterial.materials[0].glcode !== undefined && sec==="material"){
            $scope.modelMaterial.materials.unshift({"id":$scope.modelMaterial.materials.length});
            $scope.editMaterialGroup("material",$scope.modelMaterial.materials[0]);
          }
        }
        // gets the template to ng-include for a table row / item
        $scope.getTemplate = function (sec,material) {
          if(sec === 'material'){
            if (material.id === $scope.modelMaterial.selected.id) return 'editMat';
            else return 'displayMat';
          } else if(sec === 'group'){
            if (material.materialGrpId === $scope.model.selected.materialGrpId) return 'edit';
            else return 'display';
          }

        };

        $scope.editMaterialGroup = function (sec,material) {
          if(sec === "material"){
            $scope.modelMaterial.selected = angular.copy(material);
          } else if (sec === "group"){
            $scope.model.selected = angular.copy(material);
          }

        };

        $scope.saveMaterialGroup = function (sec,idx) {
          if(sec === "material"){
            $scope.modelMaterial.materials[idx] = angular.copy($scope.modelMaterial.selected);
            $scope.reset("material");

          } else if(sec === "group"){
            $scope.model.materialGroups[idx] = angular.copy($scope.model.selected);
            $scope.reset("group");
          }

        };

        $scope.reset = function (sec) {
          if(sec === "material"){
            $scope.modelMaterial.selected = {};
          }  else if(sec === "group"){
            $scope.model.selected = {};
          }

        };



      }
    ])
