package org.example;

import org.apache.calcite.adapter.jdbc.JdbcSchema;
import org.apache.calcite.jdbc.CalciteConnection;
import org.apache.calcite.schema.Schema;
import org.apache.calcite.schema.SchemaPlus;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.Properties;

public class Test {
    public static void main(String [] arg) throws ClassNotFoundException, SQLException {

       Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/user","debalina","debalina");
       Statement statement = connection.createStatement();
       ResultSet resultSet = statement.executeQuery("select * from user_table");
       while (resultSet.next()){
           System.out.println(resultSet.getString("user_firstname"));
       }
    }
     }
