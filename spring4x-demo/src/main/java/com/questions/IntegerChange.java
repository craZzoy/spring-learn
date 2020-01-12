package com.questions;

import java.lang.reflect.Field;

/**
 * Created by zwz on 2019/8/14.
 */
public class IntegerChange {

    class A {
    }

    public static void main(String[] args) throws Exception {
        Integer a = Integer.parseInt("10");
        Integer b = Integer.valueOf(10);
        Integer c = 10;
        Integer d = new Integer(10);
        changeValue(a, 100);
        changeValue(b, 200);
        changeValue(c, 300);
        System.out.printf("%d %d %d %d", a, b, c, d);
    }

    private static void changeValue(Integer i, int vaule) throws Exception {
        //i = new Integer(vaule)
        Field field = Integer.class.getDeclaredField("value");
        field.setAccessible(true);
        field.set(i, vaule);
    }

}
