package org.example;

import lombok.val;
import org.apache.calcite.adapter.jdbc.JdbcSchema;
import org.apache.calcite.avatica.util.Casing;
import org.apache.calcite.jdbc.CalciteConnection;
import org.apache.calcite.plan.RelOptUtil;
import org.apache.calcite.rel.RelNode;
import org.apache.calcite.rel.RelRoot;
import org.apache.calcite.schema.SchemaPlus;
import org.apache.calcite.sql.SqlNode;
import org.apache.calcite.sql.parser.SqlParseException;
import org.apache.calcite.sql.parser.SqlParser;
import org.apache.calcite.sql.parser.impl.SqlParserImpl;
import org.apache.calcite.tools.*;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.Properties;

public class FinalCode {
    public static void main(String[] args ) throws ClassNotFoundException, SQLException, SqlParseException, ValidationException, RelConversionException {
        System.out.println( "Hello World!" );
        Class.forName("org.apache.calcite.jdbc.Driver");
        Properties info = new Properties();
        info.setProperty("lex", "MYSQL");

        Connection connection =
                DriverManager.getConnection("jdbc:calcite:", info);
        CalciteConnection calciteConnection =
                connection.unwrap(CalciteConnection.class);
        final SchemaPlus rootSchema = calciteConnection.getRootSchema();

        val user = JdbcSchema.dataSource("jdbc:mysql://localhost:3306/user?useSSL=false&serverTimezone=UTC", "com.mysql.cj.jdbc.Driver", "debalina","debalina");

        val userRootSchema = rootSchema.add("user", JdbcSchema.create(rootSchema, "user", user, null, null));

        FrameworkConfig config = Frameworks.newConfigBuilder()
                .defaultSchema(userRootSchema)
                .build();
        Planner planner = Frameworks.getPlanner(config);
        SqlNode node = planner.parse("select * from \"user_table\"");
        System.out.println(node.toString());

         node = planner.validate(node);

        RelRoot relRoot = planner.rel(node);
        System.out.println(relRoot.toString());

        RelNode relNode = relRoot.project();
        System.out.println("relnode : " + RelOptUtil.toString(relNode));



//        String sql = "SELECT * FROM user";
//        SqlParser sqlParser = SqlParser.create(sql,
//                SqlParser.configBuilder()
//                        .setParserFactory(SqlParserImpl.FACTORY)
//                        .setUnquotedCasing(Casing.TO_UPPER)
//                        .setQuotedCasing(Casing.UNCHANGED)
//                        .build());
//        SqlNode sqlNode = sqlParser.parseQuery();
//        System.out.println(sqlNode.toString());

//        Statement statement = calciteConnection.createStatement();
//        System.out.println(userRootSchema.getTableNames());
//        ResultSet rs = statement.executeQuery("select * from 'user_table'");
//        while (rs.next()) {
//            System.out.println(rs.getString("user_firstname"));
//        }
//        statement.close();
        connection.close();
    }
}
