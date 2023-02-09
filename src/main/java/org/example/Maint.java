package org.example;

import com.mysql.cj.util.TestUtils;

import java.sql.*;
import java.util.Properties;

public class Maint {
    public static void main(String[] args) throws SQLException {
        Properties config = new Properties();
        config.put("model", "/home/cbnits-29/calcite-java/test-calcite/src/main/java/org/example/model.json");
        config.put("lex", "MYSQL");
        Connection con = DriverManager.getConnection("jdbc:calcite:", config);
        Statement stmt = con.createStatement();
        System.out.println(con.getSchema());
        ResultSet rs = stmt.executeQuery("select c.user_firstname from user_table as c");
        while (rs.next()) {
            System.out.println(rs.getString("user_firstname"));
        }
    }
}
