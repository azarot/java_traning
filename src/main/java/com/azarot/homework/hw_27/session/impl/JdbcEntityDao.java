package com.azarot.homework.hw_27.session.impl;

import static com.azarot.homework.hw_27.util.EntityUtil.findIdField;
import static com.azarot.homework.hw_27.util.EntityUtil.resolveColumnName;
import static com.azarot.homework.hw_27.util.EntityUtil.resolveTableName;

import javax.sql.DataSource;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;

import lombok.SneakyThrows;

public class JdbcEntityDao {

    private final String SELECT_FROM_TABLE_BY_COLUMN = "select * from %s where %s = ?";

    private final DataSource dataSource;

    public JdbcEntityDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @SneakyThrows
    public <T> T findById(Object id, Class<T> type) {
        try (Connection connection = dataSource.getConnection()) {
            var tableName = resolveTableName(type);
            var idField = findIdField(type);
            String columnName = resolveColumnName(idField);

            var selectSql = String.format(SELECT_FROM_TABLE_BY_COLUMN, tableName, columnName);
            try (var statement = connection.prepareStatement(selectSql)) {
                statement.setObject(1, id);
                var resultSet = statement.executeQuery();
                resultSet.next();
                return mapResultSetToEntity(resultSet, type);
            }
        }
    }

    @SneakyThrows
    private <T> T mapResultSetToEntity(ResultSet resultSet, Class<T> type) {
        T instance = type.getConstructor().newInstance();
        for (Field field : type.getDeclaredFields()) {
            field.setAccessible(true);
            var columnName = resolveColumnName(field);
            var value = resultSet.getObject(columnName);
            field.set(instance, value);
        }

        return instance;
    }
}
