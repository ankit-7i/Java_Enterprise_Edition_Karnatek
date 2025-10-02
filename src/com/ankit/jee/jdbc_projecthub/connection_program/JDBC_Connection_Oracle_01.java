package com.ankit.jee.jdbc_projecthub.connection_program;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC_Connection_Oracle_01
{
    public static void main(String[] args) throws ClassNotFoundException, SQLException
    {
        Class.forName("oracle.jdbc.OracleDriver");

        Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:ORCL",
                "c##ankitdb",
                "ankit07"
        );

        System.out.println("Connection Established...");
        System.out.println("Type: " + con);
        con.close();
    }
}
