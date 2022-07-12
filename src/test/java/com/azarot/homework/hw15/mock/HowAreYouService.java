package com.azarot.homework.hw15.mock;

import com.azarot.homework.hw15.annotation.BoboBean;
import com.azarot.homework.hw15.annotation.Inject;

@BoboBean
public class HowAreYouService implements TalkingService {

    @Inject
    private HelloService helloService;

    @Override
    public void talk() {
        System.out.println("How are you?");
    }


    public void setHelloService(HelloService helloService) {
        this.helloService = helloService;
    }

    public HelloService getHelloService() {
        return helloService;
    }
}
