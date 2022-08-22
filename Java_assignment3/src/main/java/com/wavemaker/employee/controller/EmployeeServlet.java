package com.wavemaker.employee.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wavemaker.employee.manager.EmployeeManager;
import com.wavemaker.employee.manager.EmployeeManagerFactory;
import com.wavemaker.employee.model.Employee;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class EmployeeServlet extends HttpServlet {
    private ObjectMapper objectMapper = new ObjectMapper();
    private EmployeeManager employeeManager = EmployeeManagerFactory.getEmployeeManager();

    public EmployeeServlet() throws SQLException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //TODO Get Persistance type from request or configuration/ property file or environment
        List<Employee> users = employeeManager.listEmployees();
        response.getWriter().write(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(users));
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Employee emp = objectMapper.readValue(request.getReader(), Employee.class);
        try {
            employeeManager.addEmployee(emp);
        }
        catch (Exception e) {
            response.setStatus(500);
            response.getWriter().write("Internal server error");
        }
    }

    @Override
    protected final void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            employeeManager.deleteEmployee(Integer.valueOf(req.getParameter("empId")));
        }catch (Exception e) {
            resp.setStatus(500);
            resp.getWriter().write("Internal server error");
        }
    }
}
