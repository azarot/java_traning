package com.azarot.onlinetraining.l19;

import com.azarot.homework.hw18.annotation.EnableStringTrimming;
import com.azarot.homework.hw18.demo.TestingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicIntegerArray;

@SpringBootApplication
@EnableStringTrimming
public class Task2Application {

//    @Autowired
//    TestingService testingService;
//
//    public static void main(String[] args) {
//        SpringApplication.run(Task2Application.class, args);
//    }
//
//    @EventListener(ApplicationReadyEvent.class)
//    public void doSomethingAfterStartup() {
//        System.out.println(testingService.test(42, "  test  .  ") + "|");
//    }
//
//    public void test(int[][] arrs) {
//        Arrays.stream(arrs)
//                .map(b -> new BoxType(b[0], b[1]))
//                .sorted(Comparator.comparing(BoxType::unit).reversed())
//                .toList();
//    }
}
record BoxType(int number, int unit) {

}