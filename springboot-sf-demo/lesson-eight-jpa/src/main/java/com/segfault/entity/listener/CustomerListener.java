package com.segfault.entity.listener;


import javax.persistence.PostPersist;
import javax.persistence.PrePersist;

public class CustomerListener {

    @PrePersist
    public void perPersist(Object source) {
        System.out.println("@perPersist" + source);
    }

    @PostPersist
    public void postPersist(Object source) {
        System.out.println("@perPersist" + source);
    }
}
