package com.azarot.homework.hw_27.session;

public interface Session {
    <T> T findByID(Object id, Class<T> type);

    void close();
}
