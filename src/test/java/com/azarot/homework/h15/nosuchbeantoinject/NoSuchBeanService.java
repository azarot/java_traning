package com.azarot.homework.h15.nosuchbeantoinject;

import com.azarot.homework.h15.annotation.BoboBean;
import com.azarot.homework.h15.annotation.Inject;
import com.azarot.homework.h15.mock.Utils;

@BoboBean
public class NoSuchBeanService {
    @Inject
    Utils utils;
}
