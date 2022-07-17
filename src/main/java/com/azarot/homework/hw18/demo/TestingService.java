package com.azarot.homework.hw18.demo;

import com.azarot.homework.hw18.annotation.Trimmed;
import org.springframework.stereotype.Service;

@Service
@Trimmed
public class TestingService {

    public String test(int num, String string) {
        System.out.println(string);
        return "   test   !   ";
    }
}
