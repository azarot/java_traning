package com.azarot.homework.h15.mock;

import com.azarot.homework.h15.annotation.BoboBean;

@BoboBean("hiService")
public class HelloService implements TalkingService {
    @Override
    public void talk() {
        System.out.println("Hello world!");
    }
}
