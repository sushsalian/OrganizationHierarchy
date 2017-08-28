package com.exercise.organisationhierarchy.data;


public class Employee {
    private int employeeId;
    private String employeeName;
    private int managerId;
    
    public int getEmployeeId() {
        return employeeId;
    }
    
    public Employee(int employeeId, String employeeName) {
        super();
        this.employeeId = employeeId;
        this.employeeName = employeeName;
    }

    public Employee(int employeeId, String employeeName, int managerId) {
        super();
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.managerId = managerId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
    
    public String getEmployeeName() {
        return employeeName;
    }
    
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
    
    public int getManagerId() {
        return managerId;
    }
    
    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }
}
