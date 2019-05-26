package com.gp12713.blocking;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 场景：场地接待，经理发起邀约，给门卫及受约人发短信接待验证码
 */
public class BlockingQueue {
    //FIFO的队列
    ArrayBlockingQueue<String> queue = new ArrayBlockingQueue(5);

    public static void main(String[] args) throws InterruptedException{
        BlockingQueue bq = new BlockingQueue();
        bq.receive();
        for (int i = 0; i < 20; i++) {
            bq.sendMessage("validateCode:"+String.valueOf(ThreadLocalRandom.current().nextInt(10000)));
        }
    }

    /**
     * 发送验证码
     * @param data
     * @throws InterruptedException
     */
    public void sendMessage(String data) throws InterruptedException{
        queue.add(data);
        System.out.println(String.format("sendMessage %s", data));
        Thread.sleep(1000);
    }

    /**
     * 接收到验证码
     */
    public void receive(){
        new Thread(() -> {
            while (true) {
                try {
                    String validateCode = queue.take();
                    System.out.println(String.format("receive %s", validateCode));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
