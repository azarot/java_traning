package com.azarot.homework.hw_27.session;

import javax.sql.DataSource;

public interface SessionFactory {
    Session createSession(DataSource dataSource);
}
