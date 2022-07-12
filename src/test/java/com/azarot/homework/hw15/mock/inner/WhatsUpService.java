package com.azarot.homework.hw15.mock.inner;

import com.azarot.homework.hw15.annotation.BoboBean;
import com.azarot.homework.hw15.nouniquebeantoinject.TalkingService;

@BoboBean
public class WhatsUpService implements TalkingService {

    @Override
    public void talk() {
        System.out.println("What's up?");
    }
}
