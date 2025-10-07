package com.ankit.jee.jdbc_projecthub.drl_query_execution_in_dbs.resultsetmetadata;


import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class SelectRows_Execution_Using_Resultsetmetadata_In_Oracle {
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
                SELECT course_id, course_name , course_fee 
                FROM   course
                ORDER BY course_id
                """;

        //5. Executing the above select query and obtaining RS object
        ResultSet rs = stmt.executeQuery(selectQuery);

        //6. Obtaining RSMD object from RS object
        ResultSetMetaData rsmd = rs.getMetaData();

        //7. printing columns information of Course table
        System.out.println("\ncolumns count: "+ rsmd.getColumnCount());

        System.out.println("\ncolumn1 name: "+ rsmd.getColumnName(1) +", " +rsmd.getColumnLabel(1));
        System.out.println("column2 name: "+ rsmd.getColumnName(2) +", " +rsmd.getColumnLabel(2));
        System.out.println("column3 name: "+ rsmd.getColumnName(3) +", " +rsmd.getColumnLabel(3));

        System.out.println("\ncolumn1 type: "+ rsmd.getColumnTypeName(1));
        System.out.println("column2 type: "+ rsmd.getColumnTypeName(2));
        System.out.println("column3 type: "+ rsmd.getColumnTypeName(3));

        System.out.println("\ncolumn1 size: "+ rsmd.getColumnDisplaySize(1) + ", " + rsmd.getPrecision(1) + ", " + rsmd.getScale(1));
        System.out.println("column2 size: "+ rsmd.getColumnDisplaySize(2) + ", " + rsmd.getPrecision(2) + ", " + rsmd.getScale(2));
        System.out.println("column3 size: "+ rsmd.getColumnDisplaySize(3) + ", " + rsmd.getPrecision(3) + ", " + rsmd.getScale(3));

        //above code is static nature code,
        //because we must add same Sopln code for each new column

        System.out.println();

        //Below code is dynamic code, displays all columns informations
        //This code display columns information exactly like it displayed on SQLPlus window
        for(int i=1; i<=rsmd.getColumnCount(); i++) {
            System.out.print("  "+
                    rsmd.getColumnName(i)+"\t"+
                    rsmd.getColumnTypeName(i)+"("+
                    rsmd.getPrecision(i)+")");
            System.out.println();
        }

        //8.closing connections
        rs.close();
        stmt.close();
        con.close();

    }
}

