angular
    .module('employeeModule')
    .factory('EmployeeService', ['$http', function ($http) {

    return {
        getEmployees: function (pageNumber, itemsPerPage, searchData, sortCriteria, sortOrder) {
            return getAll('employee/get/' + pageNumber + '/' + itemsPerPage + '/' + sortCriteria + '/' + sortOrder, searchData);
        },
        deleteEmployee: function (id){
            return deleteEmployeeById('employee/delete?id=' + id);
        },
        editEmployee: function (employee){
            return edit('employee/edit', employee);
        },
        findEmployee: function (id){
             return findEmployeeById('employee/find?id=' + id);
            }
        };


        function getAll (url, searchData) {
            return $http.get(url,{
                params: searchData
            })
                .success(function (data) {
                    return data;
                }).error(function (err) {
                    console.log(err);
                    return err;
                });
        }

        function deleteEmployeeById (url) {
            return $http.post(url)
             .success(function (data) {
                    return data;
             }).error(function (err) {
                    console.log(err);
                    return err;
             });
        }
        function findEmployeeById (url) {
            return $http.post(url)
                .success(function (data) {
                    return data;
                }).error(function (err) {
                    console.log(err);
                    return err;
                });
        }
        function edit (url, employee) {
            return $http.post(url, employee)
                .success(function (data) {
                    return data;
                }).error(function (err) {
                    console.log(err);
                    return err;
                });
        }
}]);
