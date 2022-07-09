package com.azarot.homework.h15.errormock;

public class BeanWithoutDefaultConstructor {
    private final int i;

    BeanWithoutDefaultConstructor(int i) {
        this.i = i;
    }

    public int getI() {
        return i;
    }
}
