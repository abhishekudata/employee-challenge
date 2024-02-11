package com.example.rqchallenge.employees;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rqchallenge.dto.EmployeeDTO;
import com.example.rqchallenge.exception.OperationFailedException;
import com.example.rqchallenge.model.Employee;
import com.example.rqchallenge.service.IEmployeeService;

@RestController
@RequestMapping(path = IEmployeeControllerImplementation.URL_PREFIX)
public class IEmployeeControllerImplementation implements IEmployeeController{

    public static final String URL_PREFIX="/employee";

    @Autowired
    private IEmployeeService iEmployeeService;

    @Override
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() throws OperationFailedException {
        return new ResponseEntity<>(iEmployeeService.getAllEmployees(), HttpStatus.OK);
    }

    @Override
    @GetMapping("/search/{searchString}")
    public ResponseEntity<List<Employee>> getEmployeesByNameSearch(@PathVariable String searchString) throws OperationFailedException {
        return new ResponseEntity<>(iEmployeeService.getEmployeesByNameSearch(searchString), HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable String id) throws OperationFailedException {
        return new ResponseEntity(iEmployeeService.getEmployeeById(id), HttpStatus.OK);
    }

    @Override
    @GetMapping("/highestSalary")
    public ResponseEntity<Integer> getHighestSalaryOfEmployees() throws OperationFailedException {
        return new ResponseEntity<>(iEmployeeService.getHighestSalaryOfEmployees(), HttpStatus.OK);
    }

    @Override
    @GetMapping("/topTenHighestEarningEmployeeNames")
    public ResponseEntity<List<String>> getTopTenHighestEarningEmployeeNames() throws OperationFailedException {
        return new ResponseEntity<>(iEmployeeService.getTopTenHighestEarningEmployeeNames(), HttpStatus.OK);
    }

    @Override
    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody Map<String, Object> employeeInput) throws OperationFailedException {
        return new ResponseEntity<>(iEmployeeService.createEmployee(employeeInput), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteEmployeeById(String id) throws OperationFailedException {
        return new ResponseEntity<>(iEmployeeService.deleteEmployeeById(id), HttpStatus.OK);
    }
}
