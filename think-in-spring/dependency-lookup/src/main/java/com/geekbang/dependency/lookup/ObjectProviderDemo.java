package com.geekbang.dependency.lookup;

import com.geekbang.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

public class ObjectProviderDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ObjectProviderDemo.class);
        context.refresh();

        lookupByObjectProvider(context);

        lookupByStreamOps(context);

        ObjectProvider<User> beanProvider = context.getBeanProvider(User.class);
        User user1 = beanProvider.getIfAvailable();
        User user2 = context.getBean(User.class);
        System.out.println(user1 == user2);

        context.close();
    }

    /**
     * 查找集合
     * @param context
     */
    private static void lookupByStreamOps(AnnotationConfigApplicationContext context) {
        ObjectProvider<String> beanProvider = context.getBeanProvider(String.class);
        beanProvider.stream().forEach(System.out::println);
    }

    @Bean
    public String hello(){
        return "Hello World";
    }

    @Bean
    @Primary
    public String message(){
        return "message";
    }

    @Bean
    public User user(){
        User user = new User();
        user.setName("Tom");
        user.setAge(80);
        return user;
    }

    private static void lookupByObjectProvider(AnnotationConfigApplicationContext context) {

        ObjectProvider<String> beanProvider = context.getBeanProvider(String.class);
        System.out.println(beanProvider.getIfAvailable());

        ObjectProvider<User> beanProvider1 = context.getBeanProvider(User.class);
        System.out.println(beanProvider1.getIfAvailable());

    }


}
