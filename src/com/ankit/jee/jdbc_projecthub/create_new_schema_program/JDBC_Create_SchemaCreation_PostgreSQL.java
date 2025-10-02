package com.ankit.jee.jdbc_projecthub.create_new_schema_program;


import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class JDBC_Create_SchemaCreation_PostgreSQL {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 1. Loading driver
        Class.forName("org.postgresql.Driver");
        System.out.println("Driver is loaded");

        // 2. Establishing connection
        Connection con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres", "ankit07");
        System.out.println("Connection is created");

        // 3. Creating Statement object
        Statement stmt = con.createStatement();
        System.out.println("Statement is created");


        // 4. Executing queries
        stmt.execute("CREATE USER jdbcdb WITH PASSWORD 'ankit07'");
        stmt.execute("GRANT CONNECT ON DATABASE postgres TO jdbcdb");
        stmt.execute("GRANT ALL PRIVILEGES ON SCHEMA public TO jdbcdb");
        System.out.println("User is created and permissions are granted");

        // 5. Closing connections
        stmt.close();
        con.close();
    }
}

