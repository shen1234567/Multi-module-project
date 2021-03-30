package com.spf.notes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Semaphore;

/**
 * @Description: 中文类名。类功能简介。
 * @Author shenpengfei
 * @Copyright 2017 北京科蓝软件系统股份有限公司。
 * @since 2021/2/5 15:13
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(4);
        for (int i = 0; i < 8; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"-------占用了机器");
                    Thread.sleep(10000);
                    System.out.println(Thread.currentThread().getName() + "释放了机器");
                    semaphore.release();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }).start();


        }
    }


}
