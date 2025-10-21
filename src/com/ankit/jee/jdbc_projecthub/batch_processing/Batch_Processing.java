package com.ankit.jee.jdbc_projecthub.batch_processing;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Scanner;

public class Batch_Processing {


    String dbdriver = "oracle.jdbc.OracleDriver";
    String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
    String username = "jdbcdb";
    String pwd = "ankit07";


    Scanner sc = new Scanner(System.in);

    void meth1() {
        System.out.println("Batch Procceing Implementation in jdbc");
        try {

            Class.forName(dbdriver);
            Connection con = DriverManager.getConnection(URL,username,pwd);
            Statement stmt  = con.createStatement();

            System.out.print("Please Enter How many queries you want to add to the Batch :");
            int num = Integer.parseInt(sc.nextLine());

            for(int i = 1 ; i<= num ; i++) {
                System.out.println("Enter "+i+"query :");
                String sqlQuery = sc.nextLine();
                stmt.addBatch(sqlQuery);
            }

            int arr[]=stmt.executeBatch();
            System.out.println(Arrays.toString(arr));
            System.out.println("Batch Processing Complete");


        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new Batch_Processing().meth1();
    }

}

/*
 * 1. CREATE TABLE emp11 (emp_no NUMBER PRIMARY KEY, name VARCHAR2(50),email VARCHAR2(100))
 *
 * 2.CREATE TABLE product11 (pid NUMBER PRIMARY KEY,pname VARCHAR2(50))
 *
 * 3. INSERT INTO emp11 VALUES (101, 'Ankit Rout', 'ankit.rout@example.com')
 *
 * 4. INSERT INTO emp11 VALUES (102, 'Sipun Mohapatra', 'Sipun.234@example.com')
 *
 * 5.INSERT INTO product11 VALUES (1, 'Laptop')
 *
 * 6.INSERT INTO product11 VALUES (2, 'Smartphone')
 */


