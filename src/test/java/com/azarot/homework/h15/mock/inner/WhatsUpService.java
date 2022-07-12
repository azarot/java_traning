package com.azarot.homework.h15.mock.inner;

import com.azarot.homework.h15.annotation.BoboBean;
import com.azarot.homework.h15.nouniquebeantoinject.TalkingService;

@BoboBean
public class WhatsUpService implements TalkingService {

    @Override
    public void talk() {
        System.out.println("What's up?");
    }
}
