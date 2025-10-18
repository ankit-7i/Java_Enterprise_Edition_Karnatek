package com.ankit.jee.jdbc_projecthub.Blob_Operation;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBC_Blob_Operation {

    String dbdriver = "oracle.jdbc.OracleDriver";
    String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
    String username = "jdbcdb";
    String pwd = "ankit07";
    String sqlQuery1 = "INSERT INTO mydata1 VALUES (?, ?)";
     String sqlQuery2 = "SELECT * FROM mydata1 WHERE id = ?";

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

    void meth1() {
        System.out.println("Inserting an image into the database");
        Connection con = null;
        PreparedStatement pstmt = null;
        FileInputStream fis = null;

        try {
            con = connect();
            pstmt = con.prepareStatement(sqlQuery1);
            pstmt.setString(1, "101");

            // ✅ FIXED FILE PATH
            fis = new FileInputStream("D:/Naresh i Technology/J2EE_Workspace/Database_X_Java/src/com/jdbc/ankit/ImageFolder/image1.png");
            pstmt.setBinaryStream(2, fis, fis.available());

            int rowCount = pstmt.executeUpdate();
            if (rowCount == 0) {
                throw new RuntimeException("Data not inserted");
            } else {
                System.out.println("Image data inserted successfully");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) fis.close();
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
    }



    void meth2() {
        System.out.println("Retrieving image from the database");
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        FileOutputStream fos = null;

        try {
            con = connect();
            pstmt = con.prepareStatement(sqlQuery2);
            pstmt.setString(1, "101");
            rs = pstmt.executeQuery();

            if (rs.next()) {
                Blob b = rs.getBlob(2); // Assuming the BLOB is in the second column
                byte[] arr = b.getBytes(1, (int) b.length());

                fos = new FileOutputStream("path/to/save/image.jpg"); // Replace with your desired output path
                fos.write(arr);
                System.out.println("Image fetched successfully");
            } else {
                throw new RuntimeException("Failed to fetch image");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) fos.close();
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }

    }


    public static void main(String[] args) {
        JDBC_Blob_Operation obj = new JDBC_Blob_Operation();
       // obj.meth1();
        obj.meth2();
    }
}
