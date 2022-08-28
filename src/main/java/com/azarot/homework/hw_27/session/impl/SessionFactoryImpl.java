package com.azarot.homework.hw_27.session.impl;

import javax.sql.DataSource;

import com.azarot.homework.hw_27.session.Session;
import com.azarot.homework.hw_27.session.SessionFactory;

public class SessionFactoryImpl implements SessionFactory {
    @Override
    public Session createSession(DataSource dataSource) {
        return new SessionImpl(dataSource);
    }
}
