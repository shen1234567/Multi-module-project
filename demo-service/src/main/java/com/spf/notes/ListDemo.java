package com.spf.notes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: 中文类名。类功能简介。
 * @Author shenpengfei
 * @Copyright 2017 北京科蓝软件系统股份有限公司。
 * @since 2021-01-12 10:26
 */
public class ListDemo {

    public static void main(String[] args) {
        List list = new ArrayList();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("1");
        list.add("4");
        list.add("5");
        list.add("6");

        System.out.println("----------原始的list:"+ list);
        list = (List) list.stream().sorted().collect(Collectors.toList());
        System.out.println("----------排序后的list:"+ list);






         //Iterator遍历
//        Iterator iterator = list.iterator();
//        while (iterator.hasNext()){
//            System.out.println(iterator.next());
//            iterator.remove();
//        }

    }
}
