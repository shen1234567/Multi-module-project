package com.spf.notes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @Description: 中文类名。类功能简介。
 * @Author shenpengfei
 * @Copyright 2017 北京科蓝软件系统股份有限公司。
 * @since 2021/3/29 09:25
 */
public class Shuang {
    public static void main(String[] args) {
        List list = new ArrayList();
        Random random = new Random();
        for (int i = 0; i <6; i++) {
            Integer num = random.nextInt(32);
            if (list.contains(num) || num.compareTo(0) == 0){
                i--;
            }else{
                list.add(num);
            }
        }
        list = (List) list.stream().sorted().collect(Collectors.toList());
        for (int i = 0; i <1; i++) {
            Integer num = random.nextInt(16);
            if (list.contains(num) || num.compareTo(0) == 0){
                i--;
            }else{
                list.add(num);
            }
        }

        System.out.println(list);

    }
}
