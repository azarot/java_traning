package com.azarot.homework.hw15.utils;

public class StringUtils {
    public static String lowerCaseFirstCharacter(String str) {
        String firstLetter = str.substring(0, 1);
        return firstLetter.toLowerCase() + str.substring(1);
    }

    public static String upperCaseFirstCharacter(String str) {
        String firstLetter = str.substring(0, 1);
        return firstLetter.toUpperCase() + str.substring(1);
    }
}
