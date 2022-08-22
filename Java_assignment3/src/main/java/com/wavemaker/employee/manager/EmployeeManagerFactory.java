package com.wavemaker.employee.manager;
import com.wavemaker.employee.constants.PersistenceType;
import com.wavemaker.employee.manager.impl.InMemoryEmployeeManagerImpl;
import com.wavemaker.employee.manager.impl.JDBCEmployeeManagerImpl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class EmployeeManagerFactory {
    private static Map<PersistenceType, EmployeeManager> instanceMap = new HashMap<>();

    private static final PersistenceType persistenceType = PersistenceType.valueOf(System.getProperty("persistenceImplementation", PersistenceType.DB.name()));

    public static EmployeeManager getEmployeeManager() throws SQLException {
        EmployeeManager employeeManager = instanceMap.get(persistenceType);
        if(employeeManager == null) {
            synchronized (EmployeeManagerFactory.class) {
                employeeManager = instanceMap.get(persistenceType);
                if(employeeManager == null) {
                    switch (persistenceType) {
                        case IN_MEMORY:
                            employeeManager = new InMemoryEmployeeManagerImpl();
                            instanceMap.put(persistenceType, new InMemoryEmployeeManagerImpl());
                            break;
                        case DB:
                            employeeManager = new JDBCEmployeeManagerImpl();
                            instanceMap.put(persistenceType, new JDBCEmployeeManagerImpl());
                            break;
                    }
                }
            }
        }
        return employeeManager;
    }
}
