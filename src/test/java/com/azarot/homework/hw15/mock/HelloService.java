package com.azarot.homework.hw15.mock;

import com.azarot.homework.hw15.annotation.BoboBean;
import com.azarot.homework.hw15.annotation.Inject;

@BoboBean("hiService")
public class HelloService implements TalkingService {

    @Inject
    private HowAreYouService howAreYouService;

    @Override
    public void talk() {
        System.out.println("Hello world!");
    }

    public HowAreYouService getHowAreYouService() {
        return howAreYouService;
    }
}
