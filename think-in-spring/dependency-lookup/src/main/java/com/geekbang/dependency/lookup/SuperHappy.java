package com.geekbang.dependency.lookup;

import org.springframework.util.Assert;

import javax.jnlp.IntegrationService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SuperHappy {

    public static void main(String[] args) throws IOException {
        System.out.println("请输入你今天的幸运数字：");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Integer luckyNum = 1;
        List<Integer> front = new ArrayList<>();
        List<Integer> back = new ArrayList<>();
        while (true) {
            String s = reader.readLine();
            try {
                luckyNum = Integer.valueOf(s);
                while (luckyNum > 0) {
                    front = getRamNumbers(35, 5);
                    back = getRamNumbers(12, 2);
                    luckyNum --;
                }
                System.out.printf("%s %s%n", front, back);
            } catch (RuntimeException e) {
                System.out.println("请输入数字");
            }
        }


        /*Random random = new Random(System.currentTimeMillis());
        List<Integer> front = new ArrayList<>();
        do{
            Integer a = random.nextInt(35);
            if (a > 0 && !front.contains(a)){
                front.add(a);
            }
        } while (front.size() < 5);
        Collections.sort(front);

        List<Integer> back = new ArrayList<>();
        do{
            Integer a = random.nextInt(12);
            if (a > 0 && !back.contains(a)){
                back.add(a);
            }
        } while (back.size() < 2);
        Collections.sort(back);
        System.out.printf("%s %s%n", front, back);*/
    }

    public static List<Integer> getRamNumbers(Integer max, Integer count) {
        Assert.notNull(max, "最大值不能为空");
        Assert.notNull(count, "数值不能为空");
        Random random = new Random(System.currentTimeMillis());
        List<Integer> front = new ArrayList<>();
        do {
            Integer a = random.nextInt(max);
            if (a > 0 && !front.contains(a)) {
                front.add(a);
            }
        } while (front.size() < count);
        Collections.sort(front);
        return front;
    }

}
