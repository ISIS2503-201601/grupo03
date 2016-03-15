(function(){
    var satt= angular.module('satt',[]);
    
    satt.directive('toolbar', function(){
        return{
            restrict:'E',
            templateUrl: 'partials/toolbar.html',
            controller:function(){
                this.tab=0;
                this.selectTab=function(setTab){
                    this.tab=setTab;
                };
                this.isSelected=function(tabParam){
                    return this.tab===tabParam;
                };
            },
            controllerAs:'toolbar'
        };
    });
    
    //Visualizacion de sensores
    satt.directive('todosSensores', function(){
        return{
            restrict:'E',
            templateUrl:'partials/todos-sensores.html',
            controller: 'getSensores'
        };
    });
 
    satt.controller("getSensores", function($http, $scope) {
    $http.get('http://localhost:8080/satt.servicios/webresources/monitoreo/sensores/').
      success(function(data, status, headers, config) {
        $scope.sensores = data;
      }).
      error(function(data, status, headers, config) {
      });
    });
    
    //Actualizacion de sensor
    satt.directive('actualizarForm', function(){
        return{
            restrict:'E',
            templateUrl:'partials/actualizar-form.html',
            controller: 'actualizarCtrl'
        };
    });
 
    satt.controller("actualizarCtrl", function($http, $scope) {
        $scope.actualizar=function(){
            $http.put('http://localhost:8080/satt.servicios/webresources/monitoreo/actualizar/', JSON.stringify($scope.sensor)).success(function(data,headers){
                //$scope.sensor={};
                $scope.toolbar.selectTab(4);
            });
        };
    });
    
    //Visualizacion de boletines
    satt.directive('todosBoletines', function(){
        return{
            restrict:'E',
            templateUrl:'partials/todos-boletines.html',
            controller: 'getBoletines'
        };
    }); 
    satt.controller("getBoletines", function($http, $scope) {
    $http.get('http://localhost:8080/satt.servicios/webresources/evento/boletines/').
      success(function(data, status, headers, config) {
        $scope.boletines = data;
      }).
      error(function(data, status, headers, config) {
      });
    });
   
   //Visualizacion de eventos
    satt.directive('todosEventos', function(){
        return{
            restrict:'E',
            templateUrl:'partials/todos-eventos.html',
            controller: 'getEventos'
        };
    }); 
    satt.controller("getEventos", function($http, $scope) {
    $http.get('http://localhost:8080/satt.servicios/webresources/evento/eventos/').
      success(function(data, status, headers, config) {
        $scope.eventos = data;
      }).
      error(function(data, status, headers, config) {
      });
    });
   
   //Gestion de evento sismico
   satt.directive('eventoForm', function(){
        return{
            restrict:'E',
            templateUrl:'partials/evento-form.html',
            controller: 'generarCtrl'
        };
    });
    satt.controller("generarCtrl", function($http, $scope) {
        $scope.generar=function(){
            $http.post('http://localhost:8080/satt.servicios/webresources/evento/generar/', JSON.stringify($scope.evento)).success(function(data,headers){
                //$scope.evento={};
                $scope.toolbar.selectTab(5);
            });
        };
    });
    
})();
