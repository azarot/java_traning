package com.azarot.homework.hw_27.util;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Optional;

import com.azarot.homework.hw_27.annotation.Column;
import com.azarot.homework.hw_27.annotation.Id;
import com.azarot.homework.hw_27.annotation.Table;

public class EntityUtil {
    public static <T> String resolveTableName(Class<T> type) {
        return Optional.ofNullable(type.getAnnotation(Table.class))
          .map(Table::name)
          .orElseThrow();
    }

    public static <T> Field findIdField(Class<T> type) {
        return Arrays.stream(type.getDeclaredFields())
          .filter(f -> f.getAnnotation(Id.class) != null)
          .findAny()
          .orElseThrow();
    }

    public static String resolveColumnName(Field field) {
        return Optional.ofNullable(field.getAnnotation(Column.class))
          .map(Column::name)
          .orElseThrow();
    }
}
