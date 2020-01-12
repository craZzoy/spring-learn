package com.segfault;

import java.sql.Timestamp;
import java.util.concurrent.Executor;

/**
 * Created by zwz on 2019/9/4.
 */
public class Main {
    public static void main(String[] args) {
        //1970-01-01 08:00:00.0
        Timestamp timestamp = new Timestamp(0L);
        System.out.println(timestamp);

        Executor executor = (r) -> r.run();

        executor.execute(Main::print);

    }

    public static void print() {
        System.out.println("hahaha");
    }

}
