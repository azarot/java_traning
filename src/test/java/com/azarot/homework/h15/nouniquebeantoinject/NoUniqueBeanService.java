package com.azarot.homework.h15.nouniquebeantoinject;

import com.azarot.homework.h15.annotation.BoboBean;
import com.azarot.homework.h15.annotation.Inject;

@BoboBean
public class NoUniqueBeanService {
    @Inject
    TalkingService talkingService;
}
