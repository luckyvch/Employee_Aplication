angular.module('employeeModule')
    .controller('EmployeeController', ['$scope', '$rootScope', '$log', 'ngTableParams', 'EmployeeService',
        '$modal', '$q',
        function($scope, $rootScope, $log, ngTableParams, service, $modal, $q){

            $scope.resultsCount = 0;

            $scope.searchData = {};

            /**
            * make default parameters for
            * select filter in ng-table
            * */
            $scope.importActiveDefaultParams = function(){
                var def = $q.defer();
                var options = [];
                options.push({
                    'id':'Yes',
                    'title': 'Yes'
                });
                options.push({
                    'id':'No',
                    'title': 'No'
                });
                def.resolve(options);
                return def;
            }

            /**
            * ng-table for employee
            *
            * */
            $scope.tableParams = new ngTableParams({
                page: 1,
                count: 5
            }, {
                total: 0,
                filterDelay: 1500,
                getData: function ($defer, params) {
                    var sortCriteria = Object.keys(params.sorting())[0];
                    var sortOrder = params.sorting()[sortCriteria];
                    service.getEmployees(params.page(), params.count(), params.filter(), sortCriteria, sortOrder)
                        .then(function (result) {
                            $scope.resultsCount = result.data.totalItems;
                            $defer.resolve(result.data.content);
                            params.total(result.data.totalItems);
                        });
                }

            });

            /**
            * delete employee from the table.
            * sends employee id by asynchronous
            * request to the server. if http response
            * is OK it opens success.html, else opens
            * conflict.html
            * */
            $scope.deleteEmployee = function (id){
                service.deleteEmployee(id)
                    .then(function (result) {
                        $log.debug(result.status);
                        if (result.status = 200) {
                            $scope.tableParams.reload();
                            $scope.$modalInstance  = $modal.open({
                                animation: true,
                                templateUrl: '/resources/app/modals/success.html'
                            });
                        } else {
                            $scope.$modalInstance  = $modal.open({
                                animation: true,
                                templateUrl: '/resources/app/modals/conflict.html'
                            });
                        }
                    });
            }

            /**
            * opens edit employee modal.
            * after editing reloads table data
            * */
            $scope.editEmployee = function(id){
                $rootScope.employeeId = id;
                $log.debug($rootScope.verificationId);
                $scope.$modalInstance  = $modal.open({
                    animation: true,
                    url: '/edit',
                    controller: 'EditEmployeeController',
                    templateUrl: '/resources/app/modals/editForm.html'
                });
                $scope.$modalInstance.result.then(function () {
                    $scope.tableParams.reload();
                });
            }



        }]);



