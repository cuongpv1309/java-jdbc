package org.example.pooling;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.*;

public class Pooling {

    public static void main(String[] args) {
        DataSource ds = createDatasource();
        try (Connection conn = createDatasource().getConnection()) {
            boolean isValid = conn.isValid(0);
            System.out.println("is there connect database valid = " + isValid);

            PreparedStatement ps = conn.prepareStatement("select * from test.customer");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                System.out.println("Information = " + firstname + " " + lastname);
            }

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    // Note: if running on VM need to sync NTP

    private static DataSource createDatasource() {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/test?serverTimezone=UTC");
        ds.setUsername("root");
        ds.setPassword("!Aa123456");
        // maxinum connect
        ds.setMaximumPoolSize(20);
        // mininum connect
        ds.setMinimumIdle(10);
        // enable cache for prepared statement
        ds.addDataSourceProperty("cachePrepStmts", "true");
        // max statement
        ds.addDataSourceProperty("prepStmtCacheSize", "250");
        // max length for statement
        ds.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        return ds;
    }
}
