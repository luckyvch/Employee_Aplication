package com.employee.edu.service.impl;


import com.employee.edu.entity.Department;
import com.employee.edu.entity.Employee;
import com.employee.edu.repository.DepartmentRepository;
import com.employee.edu.repository.EmployeeRepository;
import com.employee.edu.service.EmployeeService;
import com.employee.edu.specifications.EmployeeSpecificationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    /**
     * make transaction to database to get
     * list of employees. If filter data (sortOrder, sortCriteria, searchData)
     * is empty it returns all employees due to values of
     * page anf items per page, else it return employee sorted and filtered
     * by (sortOrder, sortCriteria, searchData)
     *
     * @param pageNumber
     * @param itemsPerPage
     * @param sortCriteria
     * @param sortOrder
     * @param searchData
     * @return List<Employee>
     */
    @Override
    public List<Employee> getAllEmployees(int pageNumber, int itemsPerPage, String sortCriteria, String sortOrder, Map<String, String> searchData) {
        EmployeeSpecificationBuilder specificationBuilder = new EmployeeSpecificationBuilder();
        Pageable pageable = specificationBuilder.constructPageSpecification(pageNumber, itemsPerPage, sortCriteria, sortOrder);
        Page<Employee> employeesPage = null;
        if (searchData.size() == 0) {
            employeesPage = employeeRepository.findAll(pageable);
        } else {
            Specification<Employee> specification = specificationBuilder.buildPredicate(searchData);
            employeesPage = employeeRepository.findAll(specification, pageable);
        }
        return employeesPage.getContent();
    }

    /**
     * gets count of all employees in the database
     *
     * @return long
     */
    @Override
    public long getAllEmployeesCount(){
        return employeeRepository.count();
    }

    /**
     * delete employee by id
     *
     * @param id
     */
    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.delete(employeeRepository.findOne(id));

    }

    /**
     * finds employee by id
     *
     * @param id
     * @return
     */
    @Override
    public Employee findEmployee(Long id) {
        Employee employee = employeeRepository.findOne(id);
        if (employee == null){
            throw new NullPointerException ();
        }
        return employee;
    }

    /**
     * update employee information
     *
     * @param id
     * @param name
     * @param active
     * @param departmentName
     */
    @Override
    public void editEmployee(Long id, String name, boolean active, String departmentName) {
        Employee employee = employeeRepository.findOne(id);
        Department department = departmentRepository.findDepartmentByDpName(departmentName);
        if (employee == null || department == null){
            throw new NullPointerException();
        }
        employee.setEmpName(name);
        employee.setEmpActive(active);
        employee.setDepartment(department);
        employeeRepository.save(employee);
    }


}
