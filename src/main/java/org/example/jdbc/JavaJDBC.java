package org.example.jdbc;

import java.sql.*;

public class JavaJDBC {
    public static void main(String[] args) {
        // root@127.0.0.1:3306
        // root
        // !Aa123456
        // connect to mysql
        try(Connection conn =
                    DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test?serverTimezone=UTC",
                            "root", "!Aa123456")) {
            boolean isValid = conn.isValid(0);
            System.out.println("is there connect database valid = " + isValid);


            // query select
            PreparedStatement ps = conn.prepareStatement("select * from test.customer where firstname = ?");
            ps.setString(1, "leo");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                System.out.println("Information = " + firstname + " " + lastname);
            }

            // query insert
            PreparedStatement insertStatement = conn.prepareStatement("insert into test.customer (firstname, lastname) values (?,?)");
            insertStatement.setString(1, "Rick");
            insertStatement.setString(2, "De Jong");
            int inserted = insertStatement.executeUpdate();
            System.out.println("Insert rows = " + inserted);

            // query update
            PreparedStatement updateStatement = conn.prepareStatement("update test.customer set firstname = 'Bod' where id = ?");
            updateStatement.setInt(1, 1);
            int updateRow = updateStatement.executeUpdate();
            System.out.println("Update rows = " + updateRow);

            // query delete
            PreparedStatement deleteStatement = conn.prepareStatement("delete from test.customer where id > ?");
            deleteStatement.setInt(1, 3);
            int deletedRows = deleteStatement.executeUpdate();
            System.out.println("Delete rows = " + deletedRows);

        } catch (SQLException e) {
            throw new UnsupportedOperationException(e);
        }
    }
}
