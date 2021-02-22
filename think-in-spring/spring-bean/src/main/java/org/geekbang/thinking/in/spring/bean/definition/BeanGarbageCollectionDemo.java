package org.geekbang.thinking.in.spring.bean.definition;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Bean 垃圾回收示例
 */
public class BeanGarbageCollectionDemo {

    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(InitializationDemo.class);
        context.refresh();
        context.close();
        Thread.sleep(5000L);
        //强制gc，确保finalize()方法能被执行
        System.gc();
        Thread.sleep(5000L);
    }

}
