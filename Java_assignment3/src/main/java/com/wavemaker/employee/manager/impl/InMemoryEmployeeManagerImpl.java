package com.wavemaker.employee.manager.impl;

import com.wavemaker.employee.manager.EmployeeManager;
import com.wavemaker.employee.model.Employee;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class InMemoryEmployeeManagerImpl implements EmployeeManager {
    private List<Employee> empsList = new ArrayList<>();

    @Override
    public int addEmployee(Employee emp1) {
        empsList.add(emp1);
        return Integer.parseInt(emp1.getId());
    }
    @Override
    public void updateEmployee(Employee emp) {
        ListIterator<Employee> empListIterator = empsList.listIterator();
        while (empListIterator.hasNext()) {
            Employee existingUser = empListIterator.next();
            if (existingUser.getId() == emp.getId()) {
                //userListIterator.set(user);
                existingUser.setFirstName(emp.getFirstName());
            }
        }
    }
    @Override
    public List<Employee> listEmployees() {
        System.out.println(empsList);
        return empsList;
    }

    @Override
    public void deleteEmployee(int id) {
        boolean flag=false;

        Iterator<Employee> empIterator = empsList.iterator();
        while (empIterator.hasNext()) {

            Employee eId = empIterator.next();
            System.out.println(eId.getId());
            System.out.println();
            if (eId.getId() == String.valueOf(id)) {
                flag = true;
                empIterator.remove();
                break;

            }
        }
        if (flag == true) {
            System.out.println(empsList);

        } else {
            System.out.println("NOT FOUND");
        }
    }

    @Override
    public List<Employee> searchEmployees(String searchWord) {
        ArrayList<Employee> searchlist = new ArrayList<Employee>();
        boolean flag = false;
        Iterator<Employee> empIterator = empsList.iterator();
        while (empIterator.hasNext()) {
            Employee user = empIterator.next();
            if (user.getFirstName().equals(searchWord)) {
                flag = true;
                searchlist.add(user);
                break;

            }
        }
        if (flag == true) {
            System.out.println(searchlist);
        } else {
            System.out.println("Not Found");
        }
        return searchlist;
    }
}
