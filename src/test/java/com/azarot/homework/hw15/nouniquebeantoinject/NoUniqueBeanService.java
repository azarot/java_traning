package com.azarot.homework.hw15.nouniquebeantoinject;

import com.azarot.homework.hw15.annotation.BoboBean;
import com.azarot.homework.hw15.annotation.Inject;

@BoboBean
public class NoUniqueBeanService {
    @Inject
    TalkingService talkingService;
}
