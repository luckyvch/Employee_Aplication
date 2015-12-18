angular.module('employeeModule')
    .controller('EditEmployeeController', ['$scope', '$rootScope', '$log', 'EmployeeService',
        '$modalInstance', '$modal',
        function($scope, $rootScope, $log, service, $modalInstance, $modal){

            /**
             * Closes modal window on browser's back/forward button click.
             */
            $scope.$on('$locationChangeStart', function() {
                $modalInstance.close();
            });

            /**
             * close modal window
             * */
            $scope.close = function () {
                $modalInstance.close();
            };

            $scope.empId = $rootScope.employeeId;


            $scope.employee = {}

            /**
             * find employee by id by sending
             * request to the server
             * */
            service.findEmployee($scope.empId)
                .then(function (response) {
                    $scope.employee = response.data;
            });

            /**
             * control active button.
             * after pressing the button change
             * $scope.employee.active value
             * */
            $scope.resolve = function(){
                if($scope.employee.active === false){
                    $scope.employee.active = true;
                } else {
                    $scope.employee.active = false;
                }
            }

            /**
             * validation function.
             * check if name is correct
             * */
            $scope.check = function (caseForValidation) {
                switch (caseForValidation) {
                    case ('name'):
                        var name = $scope.employee.name;
                        if (name == null) {
                            validator('name', false, true);
                        } else if (/^[a-zA-Z]{3,16}$/.test(name)) {
                            validator('name', false, false);
                        } else {
                            validator('name', true, false);
                        }
                        break;
                }

            }

            /**
             * gives the values for validation parameters
             * */
            function validator(caseForValidation, isValid, isNull) {
                switch (caseForValidation) {
                    case ('name'):
                        $scope.nameValidation = {
                            isValid: isValid,
                            css: isValid ? 'has-error' : 'has-success',
                            isNull: isNull
                        }
                        break;
                }
            }

            $scope.editEmployee = function(){
                if ($scope.employee.name==null){
                    $scope.nameValidation.isNull = true;
                } else {
                    service.editEmployee($scope.employee)
                        .then(function (data) {
                            if (data.status == 200) {
                                $scope.close();
                                $modal.open({
                                    animation: true,
                                    templateUrl: '/resources/app/modals/success.html',
                                    size: 'md'
                             });

                            } else if (data.status == 409) {
                                $scope.close();
                                $modal.open({
                                    animation: true,
                                    templateUrl: '/resources/app/calibrator/views/modals/conflict.html',
                                    size: 'md'
                                });
                            }
                    });
                }
            }




        }]);



