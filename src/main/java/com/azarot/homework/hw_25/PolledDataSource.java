package com.azarot.homework.hw_25;

import lombok.SneakyThrows;
import org.postgresql.ds.PGSimpleDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PolledDataSource extends PGSimpleDataSource {
    private final Queue<Connection> connectionQueue = new LinkedList<>();

    public void initPool(int poolSize) throws SQLException {
        for (int i = 0; i < poolSize; i++) {
            connectionQueue.add(new RetrivableConnection(super.getConnection(), connectionQueue));
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        return connectionQueue.poll();
    }
}
