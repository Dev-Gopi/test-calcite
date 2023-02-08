package org.example;

import org.apache.calcite.adapter.jdbc.JdbcSchema;
import org.apache.calcite.jdbc.CalciteConnection;
import org.apache.calcite.schema.SchemaPlus;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test2 {


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("org.apache.calcite.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:calcite:");
        final SchemaPlus rootSchema = connection.unwrap(CalciteConnection.class).getRootSchema();


        final BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/user?user=debalina&password=debalina");
        ds.setMaxTotal(15);

        final JdbcSchema schema = JdbcSchema.create(rootSchema, "user", ds, null, null);
        rootSchema.add("user", schema);

//        try (Connection connection1 = ds.getConnection()) {
//            connection1.createStatement().execute("DROP TABLE IF EXISTS dummy;");
//            try (Statement stm = connection1.createStatement()) {
//                stm.execute("create table dummy (id INTEGER);");
//                for (int i = 0; i < 10; i++) {
//                    stm.execute(String.format("INSERT INTO dummy (id) VALUES (%d);", i));
//                }
//            }
//        }


        try (Statement stm = connection.createStatement();
             ResultSet resultSet = stm.executeQuery("select * from user.user_table")) {
            int rows = 0;
            while (resultSet.next()) {
                rows++;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try (Connection connection1 = ds.getConnection()) {
//            connection1.createStatement().execute("DROP TABLE IF EXISTS dummy;");
            try (Statement stm = connection1.createStatement()) {
//                stm.execute("create table dummy (id INTEGER);");
//                for (int i = 0; i < 10; i++) {
                ResultSet resultSet = stm.executeQuery("select * from user.user_table");
                int rows = 0;
                while (resultSet.next()) {
                    rows++;
                }
                System.out.println(rows);
            }
        }
    }
}

// End CalciteConcurrentTest.java


