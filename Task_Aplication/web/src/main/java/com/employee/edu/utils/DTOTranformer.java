package com.employee.edu.utils;


import com.employee.edu.dto.EmployeeTableDTO;
import com.employee.edu.entity.Employee;

import java.util.ArrayList;
import java.util.List;

public class DTOTranformer {

    public static List<EmployeeTableDTO> transformEmployessToDTO(List<Employee> employees){
        List<EmployeeTableDTO> employeeTableDTOs = new ArrayList<>();
        String active = null;
        for (Employee employee : employees) {
            if (employee.isEmpActive()) {
                active = "Yes";
            } else {
                active = "No";
            }
            employeeTableDTOs.add(new EmployeeTableDTO(employee.getEmpID(), employee.getEmpName(),
                    active, employee.getDepartment().getDpName()));
        }
        return employeeTableDTOs;
    }

}
