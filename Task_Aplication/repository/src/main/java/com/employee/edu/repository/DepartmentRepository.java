package com.employee.edu.repository;

import com.employee.edu.entity.Department;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface DepartmentRepository extends PagingAndSortingRepository<Department, Long>, JpaSpecificationExecutor {

    Department findDepartmentByDpName(String name);

}
