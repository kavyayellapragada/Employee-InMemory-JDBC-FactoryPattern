package com.wavemaker.employee.manager.impl;

import com.wavemaker.employee.connection.MySQLConnectionUtility;
import com.wavemaker.employee.manager.EmployeeManager;
import com.wavemaker.employee.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCEmployeeManagerImpl implements EmployeeManager {
    Connection connection = MySQLConnectionUtility.getConnection();

    public JDBCEmployeeManagerImpl() throws SQLException {
    }

    @Override
    public List<Employee> listEmployees() {
        List<Employee> employeeList = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String list_emp = "SELECT * FROM employee";
            ResultSet resultSet = statement.executeQuery(list_emp);
            while(resultSet.next()) {
                String id = resultSet.getString("employee_id");
                String first_name = resultSet.getString("firstname");
                String last_name = resultSet.getString("lastname");
                String dob = resultSet.getString("DOB");
                String company_name = resultSet.getString("companyname");
                String blood_group = resultSet.getString("bloodgroup");
                employeeList.add(new Employee(id, first_name, last_name, dob, company_name, blood_group));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    @Override
    public int addEmployee(Employee employee) throws SQLException {
        String insert_emp = "INSERT INTO employee " +
                "(employee_id,firstname,lastname,DOB,companyname,bloodgroup) VALUES (?,?,?,?,?,?)";
        PreparedStatement prep = connection.prepareStatement(insert_emp);
        prep.setString(1,employee.getId());
        prep.setString(2,employee.getFirstName());
        prep.setString(3,employee.getLastName());
        prep.setString(4,employee.getDOB());
        prep.setString(5,employee.getCompanyName());
        prep.setString(6,employee.getBloodGroup());
        if(prep.executeUpdate()==1){
            System.out.println("Data Inserted Successfully in Employee table");
        }
        else{
            System.out.println("Failed to insert into Employee table");
        }
        return Integer.parseInt(employee.getId());
    }

    @Override
    public void updateEmployee(Employee employee) throws SQLException {
        String update_emp = "UPDATE employee SET firstname=?,lastname=?,DOB=?,companyname=?,bloodgroup=?" +
                " WHERE employee_id=" + employee.getId();
        PreparedStatement prep = connection.prepareStatement(update_emp);
        //prep.setString(1,ID);
        prep.setString(1,employee.getFirstName());
        prep.setString(2,employee.getLastName());
        prep.setString(3,employee.getDOB());
        prep.setString(4,employee.getCompanyName());
        prep.setString(5,employee.getBloodGroup());
        int code = prep.executeUpdate();
        if(code == 1){
            System.out.println("Employee details updated successfully");
        }
        else{
            System.out.println("Failed to update employee details");
        }
    }


    @Override
    public void deleteEmployee(int ID) throws SQLException {
        Statement stmt = connection.createStatement();
        String S_ID = String.valueOf(ID);
        String del_emp = "DELETE FROM employee WHERE employee_id="+S_ID;
        int code = stmt.executeUpdate(del_emp);
        if(code == 1){
            System.out.println("Employee details with ID "+S_ID+" deleted successfully");
        }
        else{
            System.out.println("Failed to delete record "+S_ID+" from employee table");
        }
    }

    @Override
    public List<Employee> searchEmployees(String keyword) {
        return null;
    }
}
