package com.ankit.jee.jdbc_projecthub.table_creation_in_dbs;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class JDBC_Create_Table_MySQL{
    public static void main(String[] args)
            throws ClassNotFoundException, SQLException {

        //1. Loading MySQL driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver is loaded");

        //2. Establishing connection
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/j2eedb",
                "root", "@ankit07");
        System.out.println("Connection is created");

        //3. Creating Statement object
        Statement stmt = con.createStatement();
        System.out.println("Statement is created");

        //4. Preparing queries for table creation
        String courseTableQuery =
                "CREATE TABLE IF NOT EXISTS course (" +
                        "course_id INT PRIMARY KEY," +
                        "course_name VARCHAR(50) UNIQUE NOT NULL," +
                        "course_fee DECIMAL(7,2)" +
                        ")";

        String studentTableQuery =
                "CREATE TABLE IF NOT EXISTS student (" +
                        "sid INT PRIMARY KEY," +
                        "sname VARCHAR(20) NOT NULL," +
                        "course_id INT," +
                        "fee DECIMAL(7,2)," +
                        "FOREIGN KEY (course_id) REFERENCES course(course_id)" +
                        ")";

        //5. Executing queries
        stmt.execute(courseTableQuery);
        System.out.println("Course table is created");

        stmt.execute(studentTableQuery);
        System.out.println("Student table is created");

        //6. Closing connection
        stmt.close();
        con.close();
        System.out.println("Connection closed");
    }
}

