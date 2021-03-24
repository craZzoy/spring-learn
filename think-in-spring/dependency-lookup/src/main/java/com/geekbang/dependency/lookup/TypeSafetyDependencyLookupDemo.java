package com.geekbang.dependency.lookup;

import com.geekbang.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 类型安全地依赖查找
 */
public class TypeSafetyDependencyLookupDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(TypeSafetyDependencyLookupDemo.class);
        context.refresh();

        //演示BeanFactory#getBean方法安全性
        displayBeanFactoryGetBean(context);

        //演示ObjectFactory#getObject方法安全性
        displayObjectFactoryGetBean(context);

        //演示ObjectProvider#getIfAvailable方法安全性
        displayObjectProviderGetIfAvailable(context);

        //演示ListableBeanFactory#getBeansOfType方法安全性
        displayListableBeanFactoryGetBeansOfType(context);

        //演示ObjectProvider#stream 方法安全性
        displayObjectProviderStream(context);

        context.close();
    }

    private static void displayObjectProviderStream(AnnotationConfigApplicationContext context) {
        ObjectProvider<User> beanProvider = context.getBeanProvider(User.class);
        printBeansException("displayObjectProviderStream", () -> beanProvider.stream().forEach(System.out::println));
    }

    private static void displayListableBeanFactoryGetBeansOfType(AnnotationConfigApplicationContext context) {
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        printBeansException("displayListableBeanFactoryGetBeansOfType", () -> beanFactory.getBeansOfType(User.class));
    }

    private static void displayObjectProviderGetIfAvailable(AnnotationConfigApplicationContext context) {
        ObjectProvider<User> beanProvider = context.getBeanProvider(User.class);
        printBeansException("displayObjectProviderGetIfAvailable", () -> beanProvider.getIfAvailable());
    }

    private static void displayObjectFactoryGetBean(AnnotationConfigApplicationContext context) {
        //ObjectProvider is ObjectFactory
        ObjectFactory<User> objectFactory = context.getBeanProvider(User.class);
        printBeansException("displayObjectFactoryGetBean", () -> objectFactory.getObject());
    }

    private static void displayBeanFactoryGetBean(AnnotationConfigApplicationContext context) {
        printBeansException("displayBeanFactoryGetBean", ()->context.getBean(User.class));
    }


    public static void printBeansException(String source, Runnable runnable) {
        System.err.println("===========================");
        System.err.printf("source from %s\n", source);
        try {
            runnable.run();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.err.println("\n");
        }
    }
}
