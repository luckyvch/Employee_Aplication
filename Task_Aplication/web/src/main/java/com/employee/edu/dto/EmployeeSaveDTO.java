package com.employee.edu.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeSaveDTO {

    private Long id;

    private String name;

    private boolean active;

    private String departmentName;

    public EmployeeSaveDTO() {
    }
}
