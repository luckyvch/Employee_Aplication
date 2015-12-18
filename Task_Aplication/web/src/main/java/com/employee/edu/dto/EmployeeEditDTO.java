package com.employee.edu.dto;

import com.employee.edu.entity.Employee;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class EmployeeEditDTO {

    private Long id;

    private String name;

    private boolean active;

    private String departmentName;

    private ArrayList<String> departmentNames;

    public EmployeeEditDTO() {
    }

    public EmployeeEditDTO (Employee employee, ArrayList<String> departmentNames){
        this.id = employee.getEmpID();
        this.name = employee.getEmpName();
        this.active = employee.isEmpActive();
        this.departmentName = employee.getDepartment().getDpName();
        this.departmentNames = departmentNames;
    }
}
