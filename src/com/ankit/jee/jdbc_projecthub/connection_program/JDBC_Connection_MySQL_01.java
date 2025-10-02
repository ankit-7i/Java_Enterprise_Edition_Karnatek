package com.ankit.jee.jdbc_projecthub.connection_program;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC_Connection_MySQL_01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/j2eedb",
                "root",
                "@nkit07"
        );

        System.out.println("Connection Established...");
        System.out.println("Type: " + con);

        con.close();
    }
}

