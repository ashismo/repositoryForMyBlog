'use strict';
// declare modules
angular.module('Home', []);
angular.module('myApp', []);
angular.module('config', []);
angular.module('retail', []);
angular.module('bankModel', []);
var serviceAdmin = angular.module('services.admin', []);
angular.module('cactusApp', [
    'ngResource',
    'Home',
    'ngRoute',
    'ngCookies',
    'ngMaterial',
    'services.admin',
    'ngMessages',
    'ngImgCrop',
    'ui.bootstrap',
    'config',
    'retail',
    'ngWindowManager'
  ])

  .config([
    '$routeProvider',
    '$compileProvider',
    '$httpProvider',
    '$mdIconProvider',
    function ($routeProvider, $compileProvider,$http,$mdIconProvider) {
      var imgSrcSanitizationWhitelist = /^\s*(https?|ftp|file):|data:image\//;
      $compileProvider.imgSrcSanitizationWhitelist(imgSrcSanitizationWhitelist);
      // Angular before v1.2 uses $compileProvider.urlSanitizationWhitelist(...)
      $routeProvider
        .when('/', {
          controllerAs: 'HC',
          controller: 'HomeController',
          templateUrl: 'scripts/modules/home/views/home.html'
        })
        .when('/config', {
          controllerAs: 'CC',
          controller: 'configController',
          templateUrl: 'scripts/modules/config/views/config.html'
        })
        .otherwise({redirectTo: '/'});

      $mdIconProvider
          .defaultIconSet('assets/css/img/icons/sets/core-icons.svg', 24);



    }])

  .run(['$rootScope', '$location', '$cookieStore', '$http',
    function ($rootScope, $location, $cookieStore, $http) {
      // keep user logged in after page refresh
      //$httpProvider.defaults.headers.common['Authorization'] =  'Basic ' + login + ':ashish' ;
    //  $rootScope.globals = $cookieStore.get('globals') || {};
      /*if ($rootScope.globals.currentUser) {
        //    $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata; // jshint ignore:line
      }*/
    //  $http.defaults.headers.common.Authorization = 'Basic ' + btoa('ujan' + ":" + 'ashish');
      $rootScope.$on('$viewContentLoaded', function () {
        //Here your view content is fully loaded !!
        $(".window").draggable();
      });

      if ($location.$$path !== '/') {
        $location.path('/');
      }


      $('body').on('click','.wmWindow',function(){
        $('.wmWindow').each(function(){
          $(this).css('z-index','');
        });
        $(this).css('z-index','2');
      });


      $rootScope.$on('$locationChangeStart', function (event, next, current) {
       if($location.$$path == '/config'){
         console.log($rootScope);
         $rootScope.minimizeapp[0].minimizeapp.push({
           "imgUrl":"../../../../assets/css/img/tb-explorer.png",
           "id":"t-config",
           "appUrl":"/config"
         });
       }
       });

    }])
  .controller('appController',
    ['$scope',
      '$http',
      '$rootScope',
      'mySharedService',
      '$location',
      function ($scope, $http, $rootScope, mySharedService,$location) {
        var ap = this;
        $scope.profile = {};
        function init() {
          //get application -- for start menu
          $http.get("assets/data/application.json")
            .then(function (response) {
              $rootScope.applicationBeforeOpen = response.data;
              $rootScope.minimizeapp = $rootScope.applicationBeforeOpen.filter(function(obj){return obj.minimizeapp});
              console.log($rootScope.minimizeapp);
            });
        };
        $scope.$on('handleBroadcast', function () {
          $scope.profile.profilePic = sharedService.pic;
        });
        $rootScope.application = [];
        $scope.minimizeApplication = [
          {
          "imgUrl":"../../../../assets/css/img/tb-explorer.png",
          "id":"t-explor",
          "appUrl":""
          },
          {
            "imgUrl":"../../../../assets/css/img/Calc.png",
            "id":"t-calc",
            "appUrl":""
          }
        ];
        $rootScope.miniAppNonMatch = angular.copy($scope.minimizeApplication);
        //task bar icon click function
        $scope.openApp = function(obj){
          $location.path(obj.appUrl);


        }

        $scope.openNewApp = function(data){
          if($rootScope.application.indexOf(data) === -1){
            $rootScope.application.push(data);
          }
          if($scope.minimizeApplication.indexOf(data) === -1){
            $scope.minimizeApplication.push(data);
            $rootScope.miniAppNonMatch = angular.copy($scope.minimizeApplication);

          }
        };
        $scope.options = {
          size: {width: 1200, height:550}
        };

        $scope.closeApp = function(obj){
          console.log(obj);

        }


        init();
      }])


  .filter('keyboardShortcut', function($window) {
    return function(str) {
      if (!str) return;
      var keys = str.split('-');
      var isOSX = /Mac OS X/.test($window.navigator.userAgent);
      var seperator = (!isOSX || keys.length > 2) ? '+' : '';
      var abbreviations = {
        M: isOSX ? '⌘' : 'Ctrl',
        A: isOSX ? 'Option' : 'Alt',
        S: 'Shift'
      };
      return keys.map(function(key, index) {
        var last = index == keys.length - 1;
        return last ? key : abbreviations[key];
      }).join(seperator);
    };
  })
