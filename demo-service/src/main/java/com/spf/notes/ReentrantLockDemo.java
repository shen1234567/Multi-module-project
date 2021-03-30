package com.spf.notes;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: 中文类名。类功能简介。
 * @Author shenpengfei
 * @Copyright 2017 北京科蓝软件系统股份有限公司。
 * @since 2020-12-15 15:47
 */
public class ReentrantLockDemo {

   static Lock lock = new ReentrantLock(true);


    public static void main(String[] args) {
        lock.lock();

    }
    }
