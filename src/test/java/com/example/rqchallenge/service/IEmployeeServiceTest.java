package com.example.rqchallenge.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.rqchallenge.exception.OperationFailedException;
import com.example.rqchallenge.helper.EmployeeHelper;
import com.example.rqchallenge.model.Employee;
import com.example.rqchallenge.utils.RestTemplateUtils;

@SpringBootTest
class IEmployeeServiceTest {

    @InjectMocks
    private IEmployeeService iEmployeeService;

    @Mock
    private RestTemplateUtils restTemplateUtils;

    @Mock
    private EmployeeHelper employeeHelper;

    @Mock
    private Employee employee;

    @Test
    void getEmployeesByNameSearch() throws OperationFailedException {
        String response="response";
        when(restTemplateUtils.get(anyString())).thenReturn(response);
        when(employeeHelper.deserializeEmployeeList(response)).thenReturn(getMockEmployeeList());
        List<Employee>  employees= iEmployeeService.getEmployeesByNameSearch("foo");
        assertEquals(1, employees.size());
        assertEquals("foo", employees.get(0).getName());
    }

    @Test
    void getEmployeeById() throws OperationFailedException {
        String response="response";
        when(restTemplateUtils.get(anyString())).thenReturn(response);
        when(employeeHelper.deserializeEmployee(response)).thenReturn(employee);
        iEmployeeService.getEmployeeById("1");
        Mockito.verify(employeeHelper, times(1)).deserializeEmployee(response);
    }

    @Test
    void getHighestSalaryOfEmployees() throws OperationFailedException {
        String response="response";
        when(restTemplateUtils.get(anyString())).thenReturn(response);
        when(employeeHelper.deserializeEmployeeList(response)).thenReturn(getMockEmployeeList());
        Integer salary= iEmployeeService.getHighestSalaryOfEmployees();
        assertEquals(9999, salary );
    }

    @Test
    void getTopTenHighestEarningEmployeeNames() throws OperationFailedException {
        String response="response";
        when(restTemplateUtils.get(anyString())).thenReturn(response);
        when(employeeHelper.deserializeEmployeeList(response)).thenReturn(getMockEmployeeList());
        List<String> salary= iEmployeeService.getTopTenHighestEarningEmployeeNames();
        assertEquals(2, salary.size());
        assertEquals("bar", salary.get(0));
        assertEquals("foo", salary.get(1));
    }

    private List<Employee> getMockEmployeeList(){
        Employee employee1 = new Employee();
        employee1.setId(1);
        employee1.setName("foo");
        employee1.setSalary(8888);
        employee1.setAge(24);

        Employee employee2 = new Employee();
        employee2.setId(2);
        employee2.setName("bar");
        employee2.setSalary(9999);
        employee2.setAge(25);

        return Arrays.asList(employee1, employee2);
    }

}