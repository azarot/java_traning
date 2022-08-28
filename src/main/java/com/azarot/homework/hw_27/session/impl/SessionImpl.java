package com.azarot.homework.hw_27.session.impl;

import javax.sql.DataSource;

import java.util.HashMap;
import java.util.Map;

import com.azarot.homework.hw_27.session.Session;

public class SessionImpl implements Session {
    private final JdbcEntityDao jdbcEntityDao;
    private final Map<EntityKey<?>, Object> entitiesMap = new HashMap<>();

    public SessionImpl(DataSource dataSource) {
        jdbcEntityDao = new JdbcEntityDao(dataSource);
    }

    @Override
    public <T> T findByID(Object id, Class<T> type) {
        var key = new EntityKey<>(type, id);
        var result = entitiesMap.computeIfAbsent(key, k -> jdbcEntityDao.findById(id, type));

        return type.cast(result);
    }

    @Override
    public void close() {

    }
}
