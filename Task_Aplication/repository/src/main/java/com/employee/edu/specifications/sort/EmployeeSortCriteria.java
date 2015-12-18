package com.employee.edu.specifications.sort;

import com.employee.edu.specifications.EmployeeSpecificationBuilder;
import org.springframework.data.domain.Sort;

/**
 * enum of sort criteria
 * which is needed for making sorting in the
 * employee table
 */
public enum EmployeeSortCriteria {

    ID() {
        public Sort getSort(String sortOrder) {
            if(sortOrder.equalsIgnoreCase("asc")) {
                return new Sort(Sort.Direction.ASC, EmployeeSpecificationBuilder.ID);
            } else {
                return new Sort(Sort.Direction.DESC, EmployeeSpecificationBuilder.ID);
            }
        }
    },
    NAME() {
        public Sort getSort(String sortOrder) {
            if(sortOrder.equalsIgnoreCase("asc")) {
                return new Sort(Sort.Direction.ASC, EmployeeSpecificationBuilder.NAME);
            } else {
                return new Sort(Sort.Direction.DESC, EmployeeSpecificationBuilder.NAME);
            }
        }
    },
    ACTIVE() {
        public Sort getSort(String sortOrder) {
            if(sortOrder.equalsIgnoreCase("asc")) {
                return new Sort(Sort.Direction.ASC, EmployeeSpecificationBuilder.isACTIVE);
            } else {
                return new Sort(Sort.Direction.DESC, EmployeeSpecificationBuilder.isACTIVE);
            }
        }
    },
    DEPARTMENTNAME() {
        public Sort getSort(String sortOrder) {
            if(sortOrder.equalsIgnoreCase("asc")) {
                return new Sort(Sort.Direction.ASC, EmployeeSpecificationBuilder.DEPARTMENTNAME);
            } else {
                return new Sort(Sort.Direction.DESC, EmployeeSpecificationBuilder.DEPARTMENTNAME);
            }
        }
    };

    public Sort getSort(String sortOrder) {
        return null;
    }
}
