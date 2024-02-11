package com.example.rqchallenge.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rqchallenge.dto.EmployeeDTO;
import com.example.rqchallenge.exception.OperationFailedException;
import com.example.rqchallenge.helper.EmployeeHelper;
import com.example.rqchallenge.model.Employee;
import com.example.rqchallenge.utils.RestTemplateUtils;

@Service
public class IEmployeeService {

    @Autowired
    private RestTemplateUtils restTemplateUtils;

    @Autowired
    private EmployeeHelper employeeHelper;

    public static final String HOST_URL_PREFIX = "https://dummy.restapiexample.com/api/v1";
    public static final String EMPTY_STRING = "";
    public static final String DELETE_SUCCESS="successfully! deleted Record";
    private static final Logger log= LoggerFactory.getLogger(IEmployeeService.class);

    public List<Employee> getAllEmployees() throws OperationFailedException {
        try {
            String response= restTemplateUtils.get(HOST_URL_PREFIX+"/employees");
            List<Employee> employeeList= employeeHelper.deserializeEmployeeList(response);
            return employeeList.isEmpty() ? Collections.emptyList() : employeeList;
        } catch (Exception e){
            log.error("Failed to fetch employees ", e);
            throw new OperationFailedException("Employees not found");
        }
    }


    public List<Employee> getEmployeesByNameSearch(String searchString) throws OperationFailedException {
        return getAllEmployees().stream()
                .filter(employee-> Objects.nonNull(searchString) && !searchString.isEmpty() &&
                        employee.getName().toLowerCase().contains(searchString.toLowerCase()))
                .collect(Collectors.toList());
    }

    public Employee getEmployeeById(String id) throws OperationFailedException {
        try {
            String response= restTemplateUtils.get(HOST_URL_PREFIX+ "/employee/"+ id);
            return employeeHelper.deserializeEmployee(response);
        } catch (Exception e){
            log.error("Failed to fetch employees ", e);
            throw new OperationFailedException("Employee with ID-" +id+ " not found" );
        }
    }

    public Integer getHighestSalaryOfEmployees() throws OperationFailedException {
        Optional<Integer> highestSalaryOfEmployees= getAllEmployees().stream()
                .map(Employee::getSalary)
                .max(Comparator.naturalOrder());
        return highestSalaryOfEmployees.orElseThrow(() -> new OperationFailedException("No Employees found"));
    }

    public List<String> getTopTenHighestEarningEmployeeNames() throws OperationFailedException {

        return getAllEmployees().stream()
                .sorted(Comparator.comparingInt(Employee::getSalary).reversed())
                .limit(10)
                .map(Employee::getName)
                .collect(Collectors.toList());
    }

    public EmployeeDTO createEmployee(Map<String, Object> employeeInput) throws OperationFailedException {
        Employee employee= employeeHelper.buildEmployee(employeeInput);
        try{
            String response = restTemplateUtils.post(HOST_URL_PREFIX + "/create", employeeHelper.buildEmployeePayload(employee));
            return employeeHelper.getEmployeeDTO(employee, employeeHelper.deserializeEmployee(response).getId());
        } catch(Exception e)
        {
            log.error("Failed to create employee :" + e);
            throw new OperationFailedException("Failed to create employee");
        }
    }

    public String deleteEmployeeById(String id) throws OperationFailedException {
        if(Objects.isNull(id) || id.isEmpty()){
            log.error("Employee Id cannot be empty");
            throw new OperationFailedException("Invalid Employee");
        }
        try{
            restTemplateUtils.delete(HOST_URL_PREFIX + "/delete/" + id);
        } catch(Exception exception)
        {
            log.error("Failed to delete employee " + exception);
            throw new OperationFailedException("Failed to delete employee - "+ id);
        }
        return DELETE_SUCCESS;
    }
}
