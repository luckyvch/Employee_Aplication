package com.employee.edu.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeTableDTO {

    private Long id;

    private String name;

    private String active;

    private String departmentName;

    public EmployeeTableDTO(Long id, String name, String active, String dpName) {
        this.id = id;
        this.name = name;
        this.active = active;
        this.departmentName = dpName;
    }
}
