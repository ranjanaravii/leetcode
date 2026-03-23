package EmployeePayrollSystem;

import java.util.Map;

public class EmployeeManagement {
    private Map<String, Employee> employeeDetails;

    public void addEmployee(Employee employee) {
        employeeDetails.put(employee.getId(), employee);
    }

//    public double calculateSalary(String employeeId) {
//        Employee employee = employeeDetails.getOrDefault(employeeId, null);
//
//        if (employee != null) {
//            EmployeeTypeContext employeeTypeContext =
//            salaryCompuatation(employee.getEmployeeType());
//        }
//    }
}
