package com.azarot.homework.hw15.errormock;

import com.azarot.homework.hw15.annotation.BoboBean;

@BoboBean
public class BeanWithoutDefaultConstructor {
    private final int i;

    BeanWithoutDefaultConstructor(int i) {
        this.i = i;
    }

    public int getI() {
        return i;
    }
}
