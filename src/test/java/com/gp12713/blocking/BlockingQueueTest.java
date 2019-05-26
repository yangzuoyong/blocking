package com.gp12713.blocking;

import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

public class BlockingQueueTest {
    @Test
    public void test() throws InterruptedException{
        BlockingQueue bq = new BlockingQueue();
        bq.receive();
        for (int i = 0; i <= 10; i++) {
            bq.sendMessage("validateCode:"+String.valueOf(ThreadLocalRandom.current().nextInt(10000)));
        }
    }
}
