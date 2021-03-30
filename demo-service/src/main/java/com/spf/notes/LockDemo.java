package com.spf.notes;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: 中文类名。类功能简介。
 * @Author shenpengfei
 * @Copyright 2017 北京科蓝软件系统股份有限公司。
 * @since 2020-12-15 15:48
 */
public class LockDemo {

    public static Lock lock = new ReentrantLock();
    public static Condition A = lock.newCondition();
    public static Condition B = lock.newCondition();

    public static Boolean flag = true;

    public static final int NUM = 10;


    public static void main(String[] args) {

        new Thread(() -> {
            lock.lock();
            try {
                for (int i = 0; i < NUM; i++) {
                    if (flag) {
                        System.out.print("A");
                        B.signal();
                        flag = false;
                    } else {
                        A.await();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }, "A").start();

        new Thread(() -> {
            lock.lock();
            try {
                for (int i = 0; i < NUM; i++) {
                    if (!flag) {
                        System.out.print("B");
                        A.signal();
                        flag = true;
                    } else {
                        B.await();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }, "B").start();

    }

}
