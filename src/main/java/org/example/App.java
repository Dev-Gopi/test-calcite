package org.example;

import lombok.val;
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
    public static void main(String[] args ) throws ClassNotFoundException, SQLException {
        System.out.println( "Hello World!" );
        Class.forName("org.apache.calcite.jdbc.Driver");
        Properties info = new Properties();
        info.setProperty("lex", "MYSQL");

        Connection connection =
                DriverManager.getConnection("jdbc:calcite:", info);
        CalciteConnection calciteConnection =
                connection.unwrap(CalciteConnection.class);
        final SchemaPlus rootSchema = calciteConnection.getRootSchema();
     //  Schema schema = new ReflectiveSchema(new HrSchema());
//        Class.forName("com.mysql.cj.jdbc.Driver");
        BasicDataSource dataSource = new BasicDataSource();
//        dataSource.setUrl("jdbc:mysql://localhost:3306/user");
//        dataSource.setUsername("debalina");
//        dataSource.setPassword("debalina");




//        Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/user","debalina","debalina");
//        Statement statement = connection1.createStatement();
//        ResultSet resultSet = statement.executeQuery("select * from user_table");
//        while (resultSet.next()){
//            System.out.println(resultSet.getString("user_firstname"));
//        }

        val dsInsightUser = JdbcSchema.dataSource("jdbc:mysql://localhost:3306/hotel_db?useSSL=false&serverTimezone=UTC", "com.mysql.cj.jdbc.Driver", "debalina","debalina");

        val abc = rootSchema.add("user", JdbcSchema.create(rootSchema, "user", dsInsightUser, null, null));


//        Schema schema = JdbcSchema.create(rootSchema, "user_table", dsInsightUser,
//                null, "user");
//        rootSchema.add("user_table", schema);

//        System.out.println(calciteConnection.getSchema());
//        calciteConnection.setSchema("user_table");
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
        System.out.println(abc.getTableNames());
//        statement.close();
        statement.close();

        connection.close();
    }
}
