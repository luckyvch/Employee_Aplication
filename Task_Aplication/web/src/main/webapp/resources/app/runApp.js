require.config({
    paths: {
        angular: '../assets/bower_components/angular/angular.min',
        employeeModule: 'employeeModule',
        ngTable: '../assets/bower_components/ng-table/ng-table',
        ngRoute : '../assets/bower_components/angular-route/angular-route.min',
        angularBootstrap: '../assets/bower_components/angular-bootstrap/ui-bootstrap-tpls.min',
        angularUIRouter: '../assets/bower_components/angular-ui-router/release/angular-ui-router.min',
    },
    shim: {
        angular: {
            exports: "angular"
        },
        ngTable: {
            exports:"ngTable",
            deps: [ 'angular' ]
        },
        ngRoute : {
            deps : [ 'angular' ],
            exports : "ngRoute"
        },
        angularBootstrap: {
            deps: ['angular']
        },
        angularUIRouter: {
            deps: ['angular']
        },
        employeeModule: {
            deps: ['angular', 'ngTable', 'ngRoute', 'angularBootstrap', 'angularUIRouter']
        }

    }
});

require(['employeeModule'], function () {
    angular.bootstrap(document.getElementById('employeeModule'), ['employeeModule']);
});
