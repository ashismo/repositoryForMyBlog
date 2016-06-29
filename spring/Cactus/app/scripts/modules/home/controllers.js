'use strict';
angular.module('Home')
  .controller('HomeController',
    ['$scope',
      '$http',
      '$rootScope',
      'mySharedService',
      function ($scope, $http, $rootScope, mySharedService) {
        var hc = this;

        /*$rootScope.application = [
          {
            'appName':'test app name',
            'templateUrl':'scripts/modules/applications/test.tpl.html'
          }
        ]*/


        $scope.profile = {};
        function init() {

        };
        $scope.$on('handleBroadcast', function () {
       //   $scope.profile.profilePic = sharedService.pic;
        });
        init();
      }])
  .directive("appContent", function() {
  return {
    restrict: 'E',
    scope: {
      url: '=url'
    },

    link: function(scope, element, attrs) {
      scope.getContentUrl = function() {
        return scope.url;
      }
    },
    template : '<div ng-include="getContentUrl()"></div>',
  };
})
  .controller('profileController',
    ['$scope', '$rootScope', '$location', '$mdDialog', '$mdMedia', 'serviceAdminProfile', '$uibModal', '$log', 'mySharedService',
      function ($scope, $rootScope, $location, $mdDialog, $mdMedia, serviceAdminProfile, $uibModal, $log, mySharedService) {
        //$http.defaults.headers.common.Authorization = 'Basic ' + btoa('ujan' + ":" + 'ashish');
        serviceAdminProfile.branch().get({}, function (data) {
          $rootScope.profile = {};
          $scope.branchBean = angular.copy(data.branchBean);
          $rootScope.profile.profilePic = $scope.branchBean.users[0].profilePic;
          setTimeout(function () {
            console.log($('.profile-pic-edit').attr('src'));
            $('.profile-pic-edit').attr('src', $('.profile-pic-edit').attr('src').replace('unsafe:', ''));
          }, 2000)
        });
        $scope.$on('handleBroadcast', function () {
          $scope.profile.profilePic = 'ONE: ' + sharedService.pic;
          $scope.branchBean.users[0].profilePic = $scope.profile.profilePic;
        });
        $scope.status = '  ';
        $scope.customFullscreen = $mdMedia('xs') || $mdMedia('sm');
        $scope.showAlert = function (ev) {
          // Appending dialog to document.body to cover sidenav in docs app
          // Modal dialogs should fully cover application
          // to prevent interaction outside of dialog
          $mdDialog.show(
            $mdDialog.alert()
              .parent(angular.element(document.querySelector('#popupContainer')))
              .clickOutsideToClose(true)
              .title('This is an alert title')
              .textContent('You can specify some description text in here.')
              .ariaLabel('Alert Dialog Demo')
              .ok('Got it!')
              .targetEvent(ev)
          );
        };
        $scope.showAdvanced = function (ev) {
          var useFullScreen = ($mdMedia('sm') || $mdMedia('xs')) && $scope.customFullscreen;
          $mdDialog.show({
              controller: DialogController,
              templateUrl: 'scripts/modules/home/views/userprofile.tmpl.html',
              parent: angular.element(document.body),
              targetEvent: ev,
              clickOutsideToClose: true,
              fullscreen: useFullScreen
            })
            .then(function (answer) {
              $scope.status = 'You said the information was "' + answer + '".';
            }, function () {
              $scope.status = 'You cancelled the dialog.';
            });
          $scope.$watch(function () {
            return $mdMedia('xs') || $mdMedia('sm');
          }, function (wantsFullScreen) {
            $scope.customFullscreen = (wantsFullScreen === true);
          });
        };
        function DialogController($scope, $mdDialog) {
          $scope.hide = function () {
            $mdDialog.hide();
          };
          $scope.cancel = function () {
            $mdDialog.cancel();
          };
          $scope.answer = function (answer) {
            $mdDialog.hide(answer);
          };
        }

        $scope.project = {
          description: 'Nuclear Missile Defense System',
          rate: 500
        };
        /*//Image crop start here
         $scope.myImage='';
         $scope.myCroppedImage='';

         var handleFileSelect=function(evt) {
         var file=evt.currentTarget.files[0];
         var reader = new FileReader();
         reader.onload = function (evt) {
         $scope.$apply(function($scope){
         $scope.myImage=evt.target.result;
         });
         };
         reader.readAsDataURL(file);
         };
         angular.element(document.querySelector('#fileInput')).on('change',handleFileSelect);
         //Image crop end here*/
        //Bootstrap Model start here
        $scope.items = ['item1', 'item2', 'item3'];

        $scope.animationsEnabled = true;
        $scope.open = function (size) {
          var modalInstance = $uibModal.open({
            animation: $scope.animationsEnabled,
            templateUrl: 'myModalContent.html',
            controller: 'ModalInstanceCtrl',
            size: size,
            resolve: {
              items: function () {
                return $scope.items;
              }
            }
          });
          modalInstance.result.then(function (selectedItem) {
            $scope.selected = selectedItem;
          }, function () {
            $log.info('Modal dismissed at: ' + new Date());
          });
        };
        $scope.toggleAnimation = function () {
          $scope.animationsEnabled = !$scope.animationsEnabled;
        };
        //Bootstrap Model end here
        console.log($rootScope);
        $scope.myImage = '';
        $scope.myCroppedImage = '';
        var handleFileSelect = function (evt) {
          var file = evt.currentTarget.files[0];
          var reader = new FileReader();
          reader.onload = function (evt) {
            $scope.$apply(function ($scope) {
              $scope.myImage = evt.target.result;
            });
          };
          reader.readAsDataURL(file);
        };
        angular.element(document.querySelector('#fileInput')).on('change', handleFileSelect);
        //if($rootScope.profile){
        $scope.$on('handleBroadcast', function () {
          $scope.profile.profilePic = $scope.myCroppedImage.split(',')[1];
        });
        // }


      }
    ])
  // Please note that $uibModalInstance represents a modal window (instance) dependency.
  // It is not the same as the $uibModal service used above.

  .controller('ModalInstanceCtrl', ['$scope', '$uibModalInstance', 'items', '$rootScope', 'mySharedService',
    function ($scope, $uibModalInstance, items, $rootScope, mySharedService) {
      console.log($rootScope);
      $scope.items = items;
      $scope.selected = {
        item: $scope.items[0]
      };

      $scope.ok = function () {
        $uibModalInstance.close($scope.selected.item);
        //  $rootScope.profile.profilePic = $rootScope.profile.myCroppedImage.split(',')[1];
        //
        // $scope.$on('handleBroadcast', function() {
        $scope.profile.profilePic = $scope.myCroppedImage.split(',')[1];
        //  });
      };

      $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
      };
    }])

