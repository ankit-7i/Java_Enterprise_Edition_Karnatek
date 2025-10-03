package com.ankit.jee.jdbc_projecthub.insert_data_into_tables;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC_Insert_Into_Table_In_Oracle {
    public static void main(String[] args)
            throws ClassNotFoundException, SQLException {

        // 1. Loading driver
        Class.forName("oracle.jdbc.driver.OracleDriver");

        // 2. Establishing connection
        Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:orcl",
                "jdbcdb", "ankit07");

        // 3. Creating Statement object
        Statement stmt = con.createStatement();

        // 4. Execute queries with MERGE (avoid unique constraint violation)
        stmt.executeUpdate("""
                MERGE INTO course c
                USING (SELECT 'Core Java' AS course_name, 3500 AS course_fee FROM dual) src
                ON (c.course_name = src.course_name)
                WHEN MATCHED THEN
                  UPDATE SET c.course_fee = src.course_fee
                WHEN NOT MATCHED THEN
                  INSERT (course_id, course_name, course_fee)
                  VALUES (course_seq.nextval, src.course_name, src.course_fee)
                """);

        stmt.executeUpdate("""
                MERGE INTO course c
                USING (SELECT 'Oracle' AS course_name, 2500 AS course_fee FROM dual) src
                ON (c.course_name = src.course_name)
                WHEN MATCHED THEN
                  UPDATE SET c.course_fee = src.course_fee
                WHEN NOT MATCHED THEN
                  INSERT (course_id, course_name, course_fee)
                  VALUES (course_seq.nextval, src.course_name, src.course_fee)
                """);

        stmt.executeUpdate("""
                MERGE INTO course c
                USING (SELECT 'HTML, CSS, JS' AS course_name, 2500 AS course_fee FROM dual) src
                ON (c.course_name = src.course_name)
                WHEN MATCHED THEN
                  UPDATE SET c.course_fee = src.course_fee
                WHEN NOT MATCHED THEN
                  INSERT (course_id, course_name, course_fee)
                  VALUES (course_seq.nextval, src.course_name, src.course_fee)
                """);

        stmt.executeUpdate("""
                MERGE INTO course c
                USING (SELECT 'Adv Java' AS course_name, 3500 AS course_fee FROM dual) src
                ON (c.course_name = src.course_name)
                WHEN MATCHED THEN
                  UPDATE SET c.course_fee = src.course_fee
                WHEN NOT MATCHED THEN
                  INSERT (course_id, course_name, course_fee)
                  VALUES (course_seq.nextval, src.course_name, src.course_fee)
                """);

        System.out.println("Rows inserted or updated successfully!");

        // 5. Closing connection
        stmt.close();
        con.close();
    }
}
