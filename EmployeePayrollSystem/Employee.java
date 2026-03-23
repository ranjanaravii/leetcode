package EmployeePayrollSystem;

import java.time.LocalDateTime;

public class Employee {
    private String id;
    private String name;
    private String phoneNumber;
    private Department department;
    private EmployeeStatus employeeStatus;
    private  EmployeeType employeeType;
    private LeaveType leaveType;
    private LocalDateTime doj;
    private SalaryStructure salaryStructure;

    public String getId() {
        return id;
    }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }
}
