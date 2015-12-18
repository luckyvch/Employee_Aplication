package com.employee.edu.specifications;

import com.employee.edu.entity.Employee;
import com.employee.edu.specifications.sort.EmployeeSortCriteria;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * class for building the specification
 */
public class EmployeeSpecificationBuilder {

    public static final String ID = "empID";

    public static final String NAME = "empName";

    public static final String isACTIVE = "empActive";

    public static final String DEPARTMENTNAME = "department.dpName";

    /**
     * gets sort object from EmployeeSortCriteria
     * due to sort parameters
     *
     * @param sortCriteria
     * @param sortOrder
     * @return Sort
     */
    public Sort getSort(String sortCriteria, String sortOrder){
        return EmployeeSortCriteria.valueOf(sortCriteria.toUpperCase()).getSort(sortOrder);
    }

    /**
     * returns specification which is needed for
     * making qwery to database. It used when user
     * type data in filters in the tha table. SearchData id the
     * map with filter parameters
     *
     * @param searchData
     * @return Specification<Employee>
     */
    public Specification<Employee> buildPredicate(Map<String,String> searchData){
        return new Specification<Employee>() {
            @Override
            public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                final List<Predicate> predicates = new ArrayList<>();
                Set<String> keys = searchData.keySet();
                for (String key : keys) {
                    searchData.get(key);
                    switch(key){
                        case "id": {
                            predicates.add(criteriaBuilder.equal(root.get(ID), searchData.get(key)));
                        } break;
                        case "name": {
                            predicates.add(criteriaBuilder.like(root.get(NAME), '%' + searchData.get(key) + '%'));
                        } break;
                        case "active": {
                            boolean isActive = true;
                            if (searchData.get(key).equals("Yes")) {
                                    isActive = true;
                            } else if (searchData.get(key).equals("No")) {
                                    isActive = false;
                            }
                            predicates.add(criteriaBuilder.equal(root.get(isACTIVE), isActive));
                        } break;
                        case "departmentName": {
                            predicates.add(criteriaBuilder.like(root.get("department").get("dpName"), '%' + searchData.get(key) + '%'));
                        } break;
                    }
                }
                Predicate queryPredicate = criteriaBuilder.conjunction();
                if (!predicates.isEmpty()) {
                    queryPredicate = criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
                }
                return queryPredicate;
            }
        };
    }

    /**
     * construct pageable object. if sort
     * parameters is empty it returns only page, else
     * it returns page with sort object to make sorting in the transaction
     * to the database
     *
     * @param pageNumber
     * @param itemsPerPage
     * @param sortCriteria
     * @param sortOrder
     * @return Pageable
     */
    public Pageable constructPageSpecification(int pageNumber, int itemsPerPage, String sortCriteria, String sortOrder){
        if (sortCriteria.equals("undefined") && sortOrder.equals("undefined")) {
            return new PageRequest(pageNumber-1, itemsPerPage);
        } else return new PageRequest(pageNumber-1, itemsPerPage, getSort(sortCriteria, sortOrder));
    }


}
