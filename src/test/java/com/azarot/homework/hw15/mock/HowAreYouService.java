package com.azarot.homework.hw15.mock;

import com.azarot.homework.hw15.annotation.BoboBean;

@BoboBean
public class HowAreYouService implements TalkingService {

    @Override
    public void talk() {
        System.out.println("How are you?");
    }
}
