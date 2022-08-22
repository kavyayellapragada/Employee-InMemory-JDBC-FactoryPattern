package com.wavemaker.employee.manager;
import com.wavemaker.employee.model.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeManager {
    int addEmployee(Employee emp1) throws SQLException;
    void updateEmployee(Employee emp) throws SQLException;
    List<Employee> listEmployees();
    void deleteEmployee(int id) throws SQLException;
    List<Employee> searchEmployees(String keyword);
}
