package org.example.pooling;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class Pooling {

    public static void main(String[] args) {
        DataSource ds = createDatasource();
        try (Connection conn = createDatasource().getConnection()) {
            boolean isValid = conn.isValid(0);
            System.out.println("is there connect database valid = " + isValid);

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    private static DataSource createDatasource() {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/test?serverTimezone=UTC");
        ds.setUsername("root");
        ds.setPassword("root");
        return ds;
    }
}
