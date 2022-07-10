package com.azarot.homework.h15.mock;

import com.azarot.homework.h15.annotation.BoboBean;
import com.azarot.homework.h15.annotation.Inject;

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
