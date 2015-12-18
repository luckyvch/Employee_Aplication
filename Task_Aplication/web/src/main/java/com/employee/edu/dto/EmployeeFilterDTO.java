package com.employee.edu.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmployeeFilterDTO {

    private Long id;

    private String name;

    private String active;

    private String departmentName;

}
