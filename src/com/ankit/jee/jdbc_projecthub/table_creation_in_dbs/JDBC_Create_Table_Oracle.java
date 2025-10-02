package com.ankit.jee.jdbc_projecthub.table_creation_in_dbs;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class JDBC_Create_Table_Oracle{
    public static void main(String[] args)
            throws ClassNotFoundException, SQLException {

        //1. Loading DB driver
        Class.forName("oracle.jdbc.driver.OracleDriver");
        System.out.println("Driver is loaded");

        //2. Establishing connection
        Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:orcl",
                "jdbcdb", "ankit07");
        System.out.println("Connection is created");

        //3. Creating Statement object
        Statement stmt = con.createStatement();
        System.out.println("Statement is created");

        //4. Preparing queries for tables creation
        String courseTableQuery =
                "CREATE TABLE course(\n" 					+
                        "	course_id	NUMBER(4)	PRIMARY KEY,\n"		+
                        "	course_name	VARCHAR2(50)	UNIQUE NOT NULL,\n"	+
                        "	course_fee	Number(7,2)\n"				+
                        ")";

        String studentTableQuery =
                """
                CREATE TABLE student(
                   sid		NUMBER(4)	PRIMARY KEY,
                   sname	VARCHAR2(20)	NOT NULL,
                   course_id	NUMBER(4)	REFERENCES Course(course_id),
                   fee		NUMBER(7,2)	
                )
                """; //Java 15v new features Text blocks (""" """)

        //5. Executing above queries
        stmt.execute(courseTableQuery);
        System.out.println("Course table is created");

        stmt.execute(studentTableQuery);
        System.out.println("Student table is created");

        //6.closing connection
        stmt.close();
        con.close();

    }
}
