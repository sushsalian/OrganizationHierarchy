package com.exercise.organisationhierarchy.service;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.*;

import com.exercise.organisationhierarchy.data.Employee;

public class EmployeeServiceTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    
    @Test
    public void testRootEmployees() {
      Employee employee3 = new Employee(123, "Jamie");         
      Employee employee1 = new Employee(124, "Alan", 123);
      Employee employee2 = new Employee(125, "Matin", 124);
      Employee employee4 = new Employee(126, "Alex", 124);         
      Employee employee5 = new Employee(127, "Steve", 123);
      Employee employee6 = new Employee(128, "David", 127);
      Employee employee7 = new Employee(129, "Gary");
      List<Employee> employees  = new ArrayList<Employee>();
      employees.add(employee1);
      employees.add(employee2);
      employees.add(employee3);
      employees.add(employee4);
      employees.add(employee5);
      employees.add(employee6);
      employees.add(employee7);
      
      List<Employee> rootEmployees =  EmployeeService.findRootEmployees(employees);
      assertTrue(rootEmployees.size() == 2);
    }
    
    @Test
    public void testSubEmployees() {        
      Employee employee1 = new Employee(124, "Alan", 123);
      Employee employee2 = new Employee(125, "Matin", 124);
      Employee employee3 = new Employee(126, "Alex", 124);         

      List<Employee> employees  = new ArrayList<Employee>();
      employees.add(employee1);
      employees.add(employee2);
      employees.add(employee3);
      
      List<Employee> subEmployees =  EmployeeService.findSubEmployees(124,
              employees);
      assertTrue(subEmployees.size() == 2);
    }
    
    
    @Test
    public void testOneLevelEmployeeHierarchy() {
        Employee employee1 = new Employee(123, "Jamie");        
        Employee employee2 = new Employee(124, "Samie");

        List<Employee> employees  = new ArrayList<Employee>();
        employees.add(employee1);
        employees.add(employee2);
        System.setOut(new PrintStream(outContent));
        EmployeeService.printAllEmployeeNames(employees);
        assertEquals("Jamie\r\nSamie\r\n", outContent.toString());
        
    }
    @Test
    public void testTwoLevelEmployeeHierarchy() {
        Employee employee1 = new Employee(123, "Jamie");         
        Employee employee2 = new Employee(124, "Jamie Jr", 123);
        List<Employee> employees  = new ArrayList<Employee>();
        employees.add(employee1);
        employees.add(employee2);
        System.setOut(new PrintStream(outContent));
        EmployeeService.printAllEmployeeNames(employees);
        assertEquals("Jamie\r\n \r\n   Jamie Jr\r\n", outContent.toString());
        
    }
    
    @Test
    public void testInvalidManagers() {
        Employee employee1 = new Employee(123, "Jamie");         
        Employee employee2 = new Employee(124, "Jamie Jr", 123);
        Employee employee3 = new Employee(125, "Alan", 400);
        List<Employee> employees  = new ArrayList<Employee>();
        employees.add(employee1);
        employees.add(employee3);
        employees.add(employee2);
        List<Employee> filteredList = EmployeeService.findEmployeesWithInvalidManager(employees);
        assertEquals(1, filteredList.size());
        assertEquals(2, employees.size());
        
       
    }

}
