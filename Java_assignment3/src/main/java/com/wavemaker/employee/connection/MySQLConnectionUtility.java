package com.wavemaker.employee.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnectionUtility {
    private static String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String MYSQL_HOST = "jdbc:mysql://localhost:3306/jdbc_test";
    private static String MYSQL_USER_NAME = "root";
    private static String MYSQL_PASSWORD = "root";

    private static volatile Connection connection;

    public static Connection getConnectionInSingleTon() throws SQLException {
        if(connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(MYSQL_HOST, MYSQL_USER_NAME, MYSQL_PASSWORD);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("SQL Exception while Creating Mysql Connection " + MYSQL_HOST + " With User " + MYSQL_USER_NAME);
            }
        }
        return connection;
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            synchronized (MySQLConnectionUtility.class) {
                if(connection == null) {
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        connection = DriverManager.getConnection(MYSQL_HOST, MYSQL_USER_NAME, MYSQL_PASSWORD);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                        System.out.println("SQL Exception while Creating Mysql Connection " + MYSQL_HOST + " With User " + MYSQL_USER_NAME);
                    }
                }
            }
        }
        return connection;
    }

    public static Connection getConnectionNonSingleTon() throws ClassNotFoundException {
        Connection mySQLConnection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            mySQLConnection = DriverManager.getConnection(MYSQL_HOST, MYSQL_USER_NAME, MYSQL_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Exception while Creating Mysql Connection " + MYSQL_HOST + " With User " + MYSQL_USER_NAME);
        }
        return mySQLConnection;
    }

    public static void closeConnection(Connection connection){
        try{
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
