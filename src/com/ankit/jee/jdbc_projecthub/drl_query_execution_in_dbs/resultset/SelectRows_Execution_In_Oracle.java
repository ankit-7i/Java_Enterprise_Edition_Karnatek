package com.ankit.jee.jdbc_projecthub.drl_query_execution_in_dbs.resultset;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectRows_Execution_In_Oracle {
    public static void main(String[] args)
            throws ClassNotFoundException, SQLException {

        //1. Loading DB driver
        Class.forName("oracle.jdbc.driver.OracleDriver");
        System.out.println("Driver is loaded");

        //2. Establishing connection
        Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:XE",
                "hknit11am3", "hari");
        System.out.println("Connection is created");

        //3. Creating Statement object
        Statement stmt = con.createStatement();
        System.out.println("Statement is created");

        //4. Preparing select query
        String selectQuery =
                """
                SELECT course_id, course_name, course_fee
                FROM   course
                ORDER BY course_id
                """;

        //5. Executing the above select query and obtaining RS object
        ResultSet rs = stmt.executeQuery(selectQuery);

        System.out.println();
        //6. retrieving results from DB by using RS object
        System.out.println("Table data");
        System.out.println("=======================================");
        while(rs.next()) {
            System.out.println(
                    rs.getInt(1) 	+ " " +
                            rs.getString(2) + "\t" +
                            rs.getDouble(3)
            );
        }
        System.out.println("=======================================");

        //7.closing connections
        rs.close();
        stmt.close();
        con.close();

    }
}
