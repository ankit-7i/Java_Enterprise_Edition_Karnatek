package com.ankit.jee.jdbc_projecthub.drl_query_execution_in_dbs.resultsetmetadata;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class SelectRows_Execution_Using_Resultsetmetadata_In_Oracle2
{

    public static void main(String[] args)
            throws ClassNotFoundException, SQLException {

        // 1. Loading driver
        Class.forName("oracle.jdbc.driver.OracleDriver");

        //2. Establishing connection
        Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:orcl",
                "jdbcdb", "ankit07");

        //3. Creating Statement object
        Statement stmt = con.createStatement();

        //4. Executing SELECT Query and obtains ResultSet and RSMD objects
        ResultSet 	  rs = stmt.executeQuery("SELECT * FROM course");
        ResultSetMetaData rsmd = rs.getMetaData();

        //5. Fetching Columns and Rows RSMD and RS
        int count = 0;
        if(rs.next()) {
            for(int i=1; i<=rsmd.getColumnCount(); i++) {
                System.out.print(rsmd.getColumnName(i) +"\t");
            }
            System.out.println("\n-------------------------------------------");

            do {
                for(int i=1; i<=rsmd.getColumnCount(); i++) {
                    System.out.print(rs.getString(i) + "\t\t"	);
                }
                System.out.println();
                count++;
            }while(rs.next());

            System.out.println("\n"+ count + " rows selected");

        }else {
            System.out.println("no rows selected");
        }

        //6. closing connections
        rs.close();
        stmt.close();
        con.close();

    }
}
