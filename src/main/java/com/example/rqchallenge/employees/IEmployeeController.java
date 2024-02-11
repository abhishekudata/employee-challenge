package com.example.rqchallenge.employees;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.example.rqchallenge.dto.EmployeeDTO;
import com.example.rqchallenge.dto.ServiceResponse;
import com.example.rqchallenge.exception.OperationFailedException;
import com.example.rqchallenge.model.Employee;

@RestController
public interface IEmployeeController {

    @GetMapping()
    ResponseEntity<List<Employee>> getAllEmployees() throws Exception;

    @GetMapping("/search/{searchString}")
    ResponseEntity<List<Employee>> getEmployeesByNameSearch(@PathVariable String searchString) throws OperationFailedException;

    @GetMapping("/{id}")
    ResponseEntity<Employee> getEmployeeById(@PathVariable String id) throws OperationFailedException;

    @GetMapping("/highestSalary")
    ResponseEntity<Integer> getHighestSalaryOfEmployees() throws OperationFailedException;

    @GetMapping("/topTenHighestEarningEmployeeNames")
    ResponseEntity<List<String>> getTopTenHighestEarningEmployeeNames() throws OperationFailedException;

    @PostMapping()
    ResponseEntity<EmployeeDTO> createEmployee(@RequestBody Map<String, Object> employeeInput) throws OperationFailedException;

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteEmployeeById(@PathVariable String id) throws OperationFailedException;

}
