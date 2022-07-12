package com.azarot.homework.hw15.nosuchbeantoinject;

import com.azarot.homework.hw15.annotation.BoboBean;
import com.azarot.homework.hw15.annotation.Inject;
import com.azarot.homework.hw15.mock.Utils;

@BoboBean
public class NoSuchBeanService {
    @Inject
    Utils utils;
}
