package com.azarot.homework.hw23;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class DeadLock {
    public static final ReentrantLock lock1 = new ReentrantLock();
    public static final ReentrantLock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        new Thread(DeadLock::operation1, "tread1").start();
        new Thread(DeadLock::operation2, "tread2").start();
    }

    @SneakyThrows
    public static void operation1() {
        log.info("TH_1: Trying to lock 1");
        lock1.lock();
        log.info("TH_1: Successfully locked 1");

        Thread.sleep(50);

        log.info("TH_1: Trying to lock 2");
        lock2.lock();
        log.info("TH_1: Successfully locked 2");
    }

    @SneakyThrows
    public static void operation2() {
        log.info("TH_2: Trying to lock 2");
        lock2.lock();
        log.info("TH_2: Successfully locked 2");

        Thread.sleep(50);

        log.info("TH_2: Trying to lock 1");
        lock1.lock();
        log.info("TH_2: Successfully locked 1");
    }
}
