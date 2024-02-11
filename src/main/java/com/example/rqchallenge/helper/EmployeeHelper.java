package com.example.rqchallenge.helper;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.rqchallenge.dto.EmployeeDTO;
import com.example.rqchallenge.dto.EmployeeListResponse;
import com.example.rqchallenge.dto.EmployeeResponse;
import com.example.rqchallenge.exception.OperationFailedException;
import com.example.rqchallenge.model.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class EmployeeHelper {

    @Autowired
    private ObjectMapper objectMapper;

    private static final Logger log = LoggerFactory.getLogger(EmployeeHelper.class);

    public List<Employee> deserializeEmployeeList(String response) throws OperationFailedException {
        try {
            EmployeeListResponse employeeListResponse= objectMapper.readValue(response, EmployeeListResponse.class);
            return employeeListResponse.getData();
        } catch (JsonProcessingException e) {
            log.error("Failed to deserialize Employee List", e);
            throw new OperationFailedException("Failed to deserialize Employee List");
        }
    }

    public Employee deserializeEmployee(String response) throws OperationFailedException {
        try {
            EmployeeResponse employeeResponse= objectMapper.readValue(response, EmployeeResponse.class);
            return employeeResponse.getData();
        } catch (JsonProcessingException e) {
            log.error("Failed to deserialize Employee List", e);
            throw new OperationFailedException("Failed to deserialize Employee");
        }
    }

    public String buildEmployeePayload(Employee employee) throws OperationFailedException {
        try {
            return objectMapper.writeValueAsString(employee);
        } catch (JsonProcessingException e) {
            log.error("Failed to serialize Employee", e);
            throw new OperationFailedException("Failed to Build Employee Payload");
        }
    }

    public Employee buildEmployee(Map<String, Object> employeeInput){
        Employee employee = new Employee();
        if(Objects.nonNull(employeeInput.get(Employee.NAME)))
            employee.setName(employeeInput.get(Employee.NAME).toString());
        if(Objects.nonNull(employeeInput.get(Employee.SALARY)))
            employee.setSalary(Integer.parseInt(employeeInput.get(Employee.SALARY).toString()));
        if(Objects.nonNull(employeeInput.get(Employee.AGE)))
            employee.setAge(Integer.parseInt(employeeInput.get(Employee.AGE).toString()));
        return employee;
    }

    public EmployeeDTO getEmployeeDTO(Employee employee, Integer id){
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(id);
        employeeDTO.setName(employee.getName());
        employeeDTO.setSalary(employee.getSalary());
        employeeDTO.setAge(employee.getAge());
        return employeeDTO;
    }

}
