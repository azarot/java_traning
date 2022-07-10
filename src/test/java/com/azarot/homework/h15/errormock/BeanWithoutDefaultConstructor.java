package com.azarot.homework.h15.errormock;

import com.azarot.homework.h15.BoboBean;

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
