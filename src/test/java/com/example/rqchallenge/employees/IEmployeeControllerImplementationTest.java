package com.example.rqchallenge.employees;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.rqchallenge.dto.EmployeeDTO;
import com.example.rqchallenge.exception.OperationFailedException;
import com.example.rqchallenge.model.Employee;
import com.example.rqchallenge.service.IEmployeeService;

@SpringBootTest
class IEmployeeControllerImplementationTest {

    @InjectMocks
    private IEmployeeControllerImplementation iEmployeeController;

    @Mock
    private IEmployeeService iEmployeeService;

    @Mock
    private Employee employee;

    @Mock
    private EmployeeDTO employeeDTO;

    @Test
    void getAllEmployees() throws OperationFailedException {
        Mockito.when(iEmployeeService.getAllEmployees()).thenReturn(Collections.singletonList(employee));
        iEmployeeController.getAllEmployees();
    }

    @Test
    void getAllEmployeesException() throws OperationFailedException {
        Mockito.when(iEmployeeService.getAllEmployees()).thenThrow(OperationFailedException.class);
        Assert.assertThrows(OperationFailedException.class,() -> iEmployeeController.getAllEmployees());
    }

    @Test
    void getEmployeesByNameSearch() throws OperationFailedException {
        String name="DummyName";
        Mockito.when(iEmployeeService.getEmployeesByNameSearch(name)).thenReturn(Collections.singletonList(employee));
        iEmployeeController.getEmployeesByNameSearch(name);
    }

    @Test
    void getEmployeeById() throws OperationFailedException {
        Mockito.when(iEmployeeService.getEmployeeById(Mockito.anyString())).thenReturn(employee);
        iEmployeeController.getEmployeeById("1");
    }

    @Test
    void getHighestSalaryOfEmployees() throws OperationFailedException {
        Mockito.when(iEmployeeService.getHighestSalaryOfEmployees()).thenReturn(9999);
        iEmployeeController.getHighestSalaryOfEmployees();
    }

    @Test
    void getTopTenHighestEarningEmployeeNames() throws OperationFailedException {
        Mockito.when(iEmployeeService.getTopTenHighestEarningEmployeeNames()).thenReturn(Collections.singletonList("foo"));
        iEmployeeController.getTopTenHighestEarningEmployeeNames();
    }

    @Test
    void createEmployee() throws OperationFailedException {
        Mockito.when(iEmployeeService.createEmployee(Mockito.anyMap())).thenReturn(employeeDTO);
        iEmployeeController.createEmployee(createEmployeeMap());
    }


    private Map<String, Object> createEmployeeMap(){
        Map<String,Object> map = new HashMap<>();
        map.put("name", "foo");
        map.put("salary", 9999);
        map.put("age", 12);
        return map;
    }

    @Test
    void deleteEmployeeById() throws OperationFailedException {
        Mockito.when(iEmployeeService.deleteEmployeeById(Mockito.anyString())).thenReturn("success");
        iEmployeeController.deleteEmployeeById("1");
    }

}