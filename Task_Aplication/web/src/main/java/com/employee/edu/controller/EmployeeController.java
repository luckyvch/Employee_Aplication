package com.employee.edu.controller;

import com.employee.edu.dto.*;
import com.employee.edu.entity.Employee;
import com.employee.edu.service.DepartmentService;
import com.employee.edu.service.EmployeeService;
import com.employee.edu.utils.DTOTranformer;
import com.employee.edu.utils.TypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/employee/")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(EmployeeController.class);

    /**
     * This controller get the request from the frontend
     * with parameters witch in necessary for getting
     * data from the database, sorted and filtered by all
     * parameters.
     *
     * @param pageNumber
     * @param itemsPerPage
     * @param sortCriteria
     * @param sortOrder
     * @param searchData
     * @return (PageDTO<EmployeeTableDTO>) - page of employees with the amount value of all employee in database
     */
    @RequestMapping(value = "get/{pageNumber}/{itemsPerPage}/{sortCriteria}/{sortOrder}", method = RequestMethod.GET)
    public PageDTO<EmployeeTableDTO> getAllEmployees (@PathVariable Integer pageNumber, @PathVariable Integer itemsPerPage,
                                                      @PathVariable String sortCriteria, @PathVariable String sortOrder,
                                                      EmployeeFilterDTO searchData){
        Map<String, String> searchDataMap = TypeConverter.ObjectToMap(searchData);
        System.out.println(searchDataMap.size());
        List<Employee> employees = employeeService.getAllEmployees(pageNumber, itemsPerPage, sortCriteria, sortOrder, searchDataMap);
        long count  = employeeService.getAllEmployeesCount();
        List<EmployeeTableDTO> content = DTOTranformer.transformEmployessToDTO(employees);
        return new PageDTO<>(count, content);
    }

    /**
     *  This method deletes the employee by id
     *  and return HttpStatus.OK if everything is correct
     *  else return HttpStatus.CONFLICT
     *
     * @param id
     * @return HttpStatus
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
       public HttpStatus deleteEmployee (@RequestParam (value = "id") Long id){
        HttpStatus status = HttpStatus.OK;
        try{
            employeeService.deleteEmployee(id);
        } catch (Exception e){
            logger.error("GOT EXCEPTION ", e);
            status = HttpStatus.CONFLICT;
        }
        return status;
    }

    /**
     * Find employee by id
     *
     * @param id
     * @return EmployeeEditDTO
     */
    @RequestMapping(value = "find", method = RequestMethod.POST)
    public EmployeeEditDTO findEmployee (@RequestParam (value = "id") Long id){
        EmployeeEditDTO editDTO = null;
        try{
            Employee employee = employeeService.findEmployee(id);
            ArrayList<String> departmentNames = departmentService.findAllDepartmentsNames();
            editDTO = new EmployeeEditDTO(employee, departmentNames);
        } catch (Exception e){
            logger.error("GOT EXCEPTION ", e);
        }
        return editDTO;
    }

    /**
     * save employee info which was
     * improved and returns HttpStatus.OK
     * if everything is correct else return
     * HttpStatus.CONFLICT
     *
     * @param employeeSaveDTO
     * @return HttpStatus
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public HttpStatus editEmployee (@RequestBody EmployeeSaveDTO employeeSaveDTO){
        HttpStatus status = HttpStatus.OK;
        try{
            employeeService.editEmployee(employeeSaveDTO.getId(), employeeSaveDTO.getName(),
                    employeeSaveDTO.isActive(), employeeSaveDTO.getDepartmentName());
        } catch (Exception e){
            logger.error("GOT EXCEPTION ", e);
            status = HttpStatus.CONFLICT;
        }
        return status;
    }

}
