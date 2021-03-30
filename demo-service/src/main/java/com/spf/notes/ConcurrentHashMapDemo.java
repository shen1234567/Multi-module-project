package com.spf.notes;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description: 中文类名。类功能简介。
 * @Author shenpengfei
 * @Copyright 2017 北京科蓝软件系统股份有限公司。
 * @since 2021/3/15 15:21
 */
public class ConcurrentHashMapDemo {

    private static ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();

    public static void main(String[] args) {
        concurrentHashMap.put("11","111");

        Map map = new HashMap();

        map.put("33","33");
    }
}
