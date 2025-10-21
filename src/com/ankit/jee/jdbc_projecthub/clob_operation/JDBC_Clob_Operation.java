package com.ankit.jee.jdbc_projecthub.clob_operation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBC_Clob_Operation {

    String dbdriver = "oracle.jdbc.OracleDriver";
    String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
    String username = "jdbcdb";
    String pwd = "ankit07";


    String sqlQuery = "insert into mydata2 values(? ,?) ";
    String sqlQuery1 = "select data from mydata2 where id = ?";

    Connection connect() {
        Connection connection = null;
        try {
            Class.forName(dbdriver);
            connection = DriverManager.getConnection(URL, username, pwd);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error connecting to database: " + e.getMessage());
        }
        return connection;
    }


    void method3() {

        System.out.println("Trying Passing file to the database ");

        try {
            Connection con = connect();
            System.out.println("Connection Established");
            PreparedStatement pstmt = con.prepareStatement(sqlQuery);
            pstmt.setString(1, "101");
            pstmt.setClob(2, new FileReader("D:\\Naresh i Technology\\J2EE_Workspace\\Database_X_Java\\src\\com\\jdbc\\ankit\\ImageFolder\\File1.txt"));

            int rowCount = pstmt.executeUpdate();
            if(rowCount == 0)
                throw new SQLException("PLEASE CHECK AGAIN");
            else
                System.out.println("Success ! File Inserted ");

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }



    void method4() {

        System.out.println("Retriving the file from the db ");
        try {
            Connection con = connect();

            System.out.println("Database Connection Established \n");

            PreparedStatement  pstmt = con.prepareStatement(sqlQuery1);
            pstmt.setString(1, "101");

            ResultSet rs = pstmt.executeQuery();


            if(rs.next()) {
                Clob b = rs.getClob(1);
                Reader data = b.getCharacterStream();
                BufferedReader br = new BufferedReader(data);
                FileWriter  fw = new FileWriter("D:\\Naresh i Technology\\J2EE_Workspace\\Database_X_Java\\src\\com\\jdbc\\ankit\\ImageFolder\\File2.txt");
                String line;
                while ((line = br.readLine())!= null) {
                    fw.write(line);

                }

                br.close();
                fw.close();
                System.out.println("CLob data Retrived ");


            }
            else {
                throw new SQLException("Inavlid Id");
            }


        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {


        JDBC_Clob_Operation cbo = new JDBC_Clob_Operation();
        //cbo.method3();
        //cbo.method4();
    }
}
