package com.ankit.jee.jdbc_projecthub.sequence_creation;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class JDBC_Create_Sequence_Oracle {

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

        //4. Executing Sequences creation queries
        StringBuilder courseTableSeqQueryBuilder = new StringBuilder();
        courseTableSeqQueryBuilder.append("CREATE SEQUENCE course_seq\n");
        courseTableSeqQueryBuilder.append("START WITH 1\n");
        courseTableSeqQueryBuilder.append("INCREMENT BY 1");

        String studentTableSeqQuery =
                """
                CREATE SEQUENCE student_seq
                START WITH 101
                INCREMENT BY 1
                """;

        stmt.execute(courseTableSeqQueryBuilder.toString());
        System.out.println("Course_Seq is created");

        stmt.execute(studentTableSeqQuery);
        System.out.println("Student_Seq is created");

        //5.closing connection
        stmt.close();
        con.close();

    }
}
