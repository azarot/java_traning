package com.azarot.homework.hw_27.session.impl;

public record EntityKey<T>(Class<T> type, Object id) {
}
