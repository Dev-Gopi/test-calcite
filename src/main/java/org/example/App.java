package org.example;

import org.apache.calcite.adapter.jdbc.JdbcSchema;
import org.apache.calcite.jdbc.CalciteConnection;
import org.apache.calcite.schema.Schema;
import org.apache.calcite.schema.SchemaPlus;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.Properties;

/**
 * Hello world!
 *
 */
public class App 
{
    public static class HrSchema {
//        public final Employee[] emps = 0;
//        public final Department[] depts = 0;
    }
    public static void test(String[] args ) throws ClassNotFoundException, SQLException {
        System.out.println( "Hello World!" );
        Class.forName("org.apache.calcite.jdbc.Driver");
        Properties info = new Properties();
        info.setProperty("lex", "JAVA");

        Connection connection =
                DriverManager.getConnection("jdbc:calcite:", info);
        CalciteConnection calciteConnection =
                connection.unwrap(CalciteConnection.class);
        final SchemaPlus rootSchema = calciteConnection.getRootSchema();
//        Schema schema = new ReflectiveSchema(new HrSchema());
        Class.forName("com.mysql.cj.jdbc.Driver");
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost");
        dataSource.setUsername("debalina");
        dataSource.setPassword("debalina");
        Schema schema = JdbcSchema.create(rootSchema, "hotel_db", dataSource,
                null, "hotel_db");
        rootSchema.add("hotel_db", schema);
        calciteConnection.setSchema("hotel_db");
        Statement statement = calciteConnection.createStatement();
//        ResultSet resultSet = statement.executeQuery(
//                "select d.deptno, min(e.empid)\n"
//                        + "from hr.emps as e\n"
//                        + "join hr.depts as d\n"
//                        + "  on e.deptno = d.deptno\n"
//                        + "group by d.deptno\n"
//                        + "having count(*) > 1");
//        ResultSet resultSet = statement.executeQuery(
//                "select *\n"
//                        + "from user");
//        System.out.print(resultSet);
//        resultSet.close();
        System.out.println(rootSchema.getTableNames());
        statement.close();
        connection.close();
    }
}
