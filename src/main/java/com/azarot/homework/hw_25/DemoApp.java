package com.azarot.homework.hw_25;

import lombok.SneakyThrows;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.time.Instant;

public class DemoApp {
    public static void main(String[] args) {
        DataSource dataSource = initializeDataSource();
        var total = 0.0;
        var start = Instant.now();
        for (int i = 0; i < 500; i++) {
            try (Connection connection = dataSource.getConnection()) {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select price from products");
                resultSet.next();
                total += resultSet.getDouble("price");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(Duration.between(start, Instant.now()).toMillis());
        System.out.println(total);
    }

    private static DataSource initializeDataSource() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setURL("jdbc:postgresql://localhost:5432/postgres");
        dataSource.setUser("postgres");
        dataSource.setPassword("postgres");
        return dataSource;
    }

    @SneakyThrows
    private static DataSource polledDataSource() {
        PolledDataSource dataSource = new PolledDataSource();
        dataSource.setURL("jdbc:postgresql://localhost:5432/postgres");
        dataSource.setUser("postgres");
        dataSource.setPassword("postgres");
        dataSource.initPool(10);
        return dataSource;
    }
}
