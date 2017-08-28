package com.exercise.organisationhierarchy.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.exercise.organisationhierarchy.data.Employee;

/**
 * Employee Service class
 * @author ssalian
 *
 */
public class EmployeeService {
    /**
     * Finds & returns root employees from the list of employees
     * @param employees
     * @return
     */
    public static List<Employee> findRootEmployees(List<Employee> employees) {
        List<Employee> rootEmployees = new ArrayList<Employee>();
        if(employees == null || employees.size() <=0) {
            return rootEmployees;
        }
        for(Employee employee: employees) {
            if(employee != null && employee.getManagerId() <= 0) {
                rootEmployees.add(employee);
            }
        }
        return rootEmployees;
    }
    
    /**
     * Finds & returns sub employees for an employee from the list of employees
     * @param employeeId
     * @param employees
     * @return
     */
    public static List<Employee> findSubEmployees(int employeeId, List<Employee> employees) {
        List<Employee> subEmployees = new ArrayList<Employee>();
        if(employees == null || employees.size() <=0 || employeeId <= 0) {
            return subEmployees;
        }
        for(Employee employee: employees) {
            if(employee != null && employee.getManagerId() == employeeId) {
                subEmployees.add(employee);
            }
        }
        return subEmployees;
    }
    /**
     * Prints employee name on the console based on the row & column position
     * @param row
     * @param column
     * @param employeeName
     */
    public static void printEmployeeName(int row, int column, String employeeName){
        if(row > 0) {
            for(int i = 0; i < row; i++){
                System.out.println(" ");
            }
        }
        if(column > 0) {
            for(int i = 0; i < column; i++){
                System.out.print("   ");
            }
        }
        System.out.println(employeeName);
        
    }
    /**
     * Prints All Employee names
     * @param employees
     */
    public static  void printAllEmployeeNames(List<Employee> employees) {
        if(employees == null || employees.size() <=0 ) {
            System.out.print("No employees");
            return ;
        }
        List<Employee> employeeWithInvalidManagers = findEmployeesWithInvalidManager(employees);
        if(employeeWithInvalidManagers.size() > 0) {
            System.out.println( "Employees with Invalid Managers");
            for(Employee e: employeeWithInvalidManagers) {
                System.out.println(e.getEmployeeName());
            }
            System.out.println();
        }
        List<Employee> rootEmployees = EmployeeService.findRootEmployees(employees);
        if(rootEmployees == null || rootEmployees.size() <=0 ) {
            System.out.print("No root employee. Unable to create a hierarchy.");
            return ;
        }
        int i = 0;
        int j = 0;
       
        for(Employee e: rootEmployees) {
           constructEmployeeHierarchy(e, i, j, employees);                     
        }
    }
    
    /**
     * Constructs employee name hierarchy
     * @param employee
     * @param row
     * @param column
     * @param employees
     */
    public static void constructEmployeeHierarchy(Employee employee, 
                                                  int row, int column, List<Employee> employees) {
        List<Employee> subEmployees = findSubEmployees(employee.getEmployeeId(), employees);

        printEmployeeName(row, column, employee.getEmployeeName());
        row += 1;
        column += 1;
        if(subEmployees.size() > 0) {
            for(Employee subEmployee : subEmployees) {
                constructEmployeeHierarchy(subEmployee, row, column, employees);
            }            
        }                
    }
	
     /**
     * Filters out employee with invalid Manager & return the list of employee with the invalid managers   
     * @param employees
     */
    public static List<Employee> findEmployeesWithInvalidManager(List<Employee> allEmployees) {
        List<Employee> employeeWithInvalidManagers = new ArrayList<Employee>();
        List<Integer> employeeIdList = new ArrayList<Integer>();
        for(Employee e: allEmployees) {
            employeeIdList.add(e.getEmployeeId());
        }

        Iterator<Employee> employeeIterator = allEmployees.iterator();
        while(employeeIterator.hasNext()) {
            Employee employee = employeeIterator.next();
            if(employee.getManagerId() > 0 && !employeeIdList.contains(employee.getManagerId())) {
                employeeWithInvalidManagers.add(employee);
                employeeIterator.remove();
              
            }
        }
        return employeeWithInvalidManagers;
       
    }
    
    
    
    
}
