package com.aspect;

/**
 * Created by zwz on 2019/5/4.
 */
public class DefaultEncoreable implements Encoreable {
    @Override
    public void performEncore() {
        System.out.println("add new method:performEncore");
    }
}
