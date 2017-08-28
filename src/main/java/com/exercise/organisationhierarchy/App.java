package com.exercise.organisationhierarchy;

import java.util.ArrayList;
import java.util.List;

import com.exercise.organisationhierarchy.data.Employee;
import com.exercise.organisationhierarchy.service.EmployeeService;

/**
 * Main App
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Employee employee3 = new Employee(123, "Jamie");         
        Employee employee1 = new Employee(124, "Alan", 123);
        Employee employee2 = new Employee(125, "Matin", 124);
        Employee employee4 = new Employee(126, "Alex", 124);         
        Employee employee5 = new Employee(127, "Steve", 123);
        Employee employee6 = new Employee(128, "David", 127);
        List<Employee> employees  = new ArrayList<Employee>();
        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);
        employees.add(employee4);
        employees.add(employee5);
        employees.add(employee6);               
       
        EmployeeService.printAllEmployeeNames(employees);
    }
}
