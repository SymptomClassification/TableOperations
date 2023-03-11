package com.lancaster.databaseaccess;

import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

    private static Connection con = null;

    @Value("${spring.datasource.url}")
    private static String dbUrl;
    @Value("${spring.datasource.username}")
    private static String userName;
    @Value("${spring.datasource.password}")
    private static String password;

    public static java.sql.Connection getDBConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(dbUrl, userName, password);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
        }

        return con;
    }
}
