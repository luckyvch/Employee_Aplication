(function () {
    angular
        .module('employeeModule', ['ngTable', 'ngRoute', 'ui.bootstrap', 'ui.router'])
        .config(function($routeProvider) {
            $routeProvider
                .when('/edit', {
                    templateUrl: 'editForm.html'
                });
        });
    define([
        'controllers/EmployeeController',
        'controllers/EditEmployeeController',
        'services/EmployeeService'
    ], function () {});
})();
