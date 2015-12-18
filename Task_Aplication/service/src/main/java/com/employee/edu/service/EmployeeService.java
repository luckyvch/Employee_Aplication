package com.employee.edu.service;


import com.employee.edu.entity.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeService {

    List<Employee> getAllEmployees (int pageNumber, int itemsPerPage, String sortCriteria, String sortOrder, Map<String, String> searchData);

    long getAllEmployeesCount();

    void deleteEmployee(Long id);

    Employee findEmployee(Long id);

    void editEmployee(Long id, String Name, boolean active, String departmentName);

}
