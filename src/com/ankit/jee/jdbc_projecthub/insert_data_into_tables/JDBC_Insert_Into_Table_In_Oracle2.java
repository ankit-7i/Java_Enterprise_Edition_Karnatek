package com.ankit.jee.jdbc_projecthub.insert_data_into_tables;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC_Insert_Into_Table_In_Oracle2 {
    public static void main(String[] args)
            throws ClassNotFoundException, SQLException {

        // 1. Load driver
        Class.forName("oracle.jdbc.driver.OracleDriver");

        // 2. Establish connection
        Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:orcl",
                "jdbcdb", "ankit07");

        // 3. Create Statement
        Statement stmt = con.createStatement();

        // 4. Insert or update student rows safely using MERGE
        stmt.executeUpdate("""
                MERGE INTO student s
                USING (SELECT 'Ankit' AS sname, 101 AS course_id, 3500 AS fee FROM dual) src
                ON (s.sname = src.sname)
                WHEN MATCHED THEN
                  UPDATE SET s.course_id = src.course_id, s.fee = src.fee
                WHEN NOT MATCHED THEN
                  INSERT (sid, sname, course_id, fee)
                  VALUES (student_seq.nextval, src.sname, src.course_id, src.fee)
                """);

        stmt.executeUpdate("""
                MERGE INTO student s
                USING (SELECT 'Rohit' AS sname, 102 AS course_id, 2500 AS fee FROM dual) src
                ON (s.sname = src.sname)
                WHEN MATCHED THEN
                  UPDATE SET s.course_id = src.course_id, s.fee = src.fee
                WHEN NOT MATCHED THEN
                  INSERT (sid, sname, course_id, fee)
                  VALUES (student_seq.nextval, src.sname, src.course_id, src.fee)
                """);

        stmt.executeUpdate("""
                MERGE INTO student s
                USING (SELECT 'Virat' AS sname, 103 AS course_id, 4000 AS fee FROM dual) src
                ON (s.sname = src.sname)
                WHEN MATCHED THEN
                  UPDATE SET s.course_id = src.course_id, s.fee = src.fee
                WHEN NOT MATCHED THEN
                  INSERT (sid, sname, course_id, fee)
                  VALUES (student_seq.nextval, src.sname, src.course_id, src.fee)
                """);

        stmt.executeUpdate("""
                MERGE INTO student s
                USING (SELECT 'Dhoni' AS sname, 104 AS course_id, 3000 AS fee FROM dual) src
                ON (s.sname = src.sname)
                WHEN MATCHED THEN
                  UPDATE SET s.course_id = src.course_id, s.fee = src.fee
                WHEN NOT MATCHED THEN
                  INSERT (sid, sname, course_id, fee)
                  VALUES (student_seq.nextval, src.sname, src.course_id, src.fee)
                """);

        System.out.println("Student rows inserted/updated successfully!");

        // 5. Close connection
        stmt.close();
        con.close();
    }
}

