package com.ankit.jee.jdbc_projecthub.create_new_schema_program;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class JDBC_Create_SchemaCreation_MySQL {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 1. Loading driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver is loaded");

        // 2. Establishing connection
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/mysql",
                "root", "@ankit07");
        System.out.println("Connection is created");

        // 3. Creating Statement object
        Statement stmt = con.createStatement();
        System.out.println("Statement is created");

        // 4. Executing queries
        stmt.execute("CREATE USER 'jdbcdb1'@'localhost' IDENTIFIED BY '@ankit07'");
        stmt.execute("GRANT ALL PRIVILEGES ON *.* TO 'jdbcdb1'@'localhost'");
        System.out.println("User is created and permissions are granted");

        // 5. Closing connections
        stmt.close();
        con.close();
    }
}
