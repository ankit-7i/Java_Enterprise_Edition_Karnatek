package com.ankit.jee.jdbc_projecthub.connection_program;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC_Connection_PostgreSQL_01
{
    public static void main(String[] args) throws ClassNotFoundException, SQLException
    {

        Class.forName("org.postgresql.Driver");

        Connection con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "ankit07"
        );

        System.out.println("Connection Established...");
        System.out.println("Type: " + con);


        con.close();
    }
}

