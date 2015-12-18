package com.employee.edu.service.impl;


import com.employee.edu.entity.Department;
import com.employee.edu.repository.DepartmentRepository;
import com.employee.edu.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;


@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    /**
     * finds list of all departments
     *
     * @return ArrayList
     */
    @Override
    public ArrayList<String> findAllDepartmentsNames() {
        ArrayList<String> depNames = new ArrayList<>();
        Iterable<Department> departments = departmentRepository.findAll();
        for (Department department : departments) {
            depNames.add(department.getDpName());
        }
        return depNames;
    }
}
