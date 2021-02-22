package org.geekbang.thinking.in.spring.bean.definition;

import org.geekbang.thinking.in.spring.bean.factory.DefaultUserFactory;
import org.geekbang.thinking.in.spring.bean.factory.UserFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * 特殊Bean实例化demo
 */
public class SpecialBeanInstantiationDemo {

    public static void main(String[] args) {

        //通过jdk api @{java.util.ServiceLoader} 查找 ServiceLoader
        ServiceLoader serviceLoader = demoServiceLoader();

        //将ServiceLoader交给Spring IOC管理
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/special-bean-instantination-context.xml");

        ServiceLoader<UserFactory> userFactoryServiceLoader = context.getBean("userFactoryServiceLoader", ServiceLoader.class);
        ServiceLoader<UserFactory> userFactoryServiceLoaderTwo = context.getBean("userFactoryServiceLoader", ServiceLoader.class);
        System.out.println(userFactoryServiceLoader == userFactoryServiceLoaderTwo);
        displayServiceLoader(userFactoryServiceLoader);

        System.out.println(serviceLoader == userFactoryServiceLoader);


        //通过 AutowireCapableBeanFactory#createBean(java.lang.Class, int, boolean)实例化
        AutowireCapableBeanFactory autowireCapableBeanFactory = context.getAutowireCapableBeanFactory();
        UserFactory bean = autowireCapableBeanFactory.createBean(DefaultUserFactory.class);
        System.out.println(bean.createUser());

    }

    private static ServiceLoader demoServiceLoader() {
        ServiceLoader<UserFactory> serviceLoader = ServiceLoader.load(UserFactory.class, Thread.currentThread().getContextClassLoader());
        displayServiceLoader(serviceLoader);
        return serviceLoader;
    }

    private static void displayServiceLoader(ServiceLoader<UserFactory> userFactoryServiceLoader) {

        Iterator<UserFactory> iterator = userFactoryServiceLoader.iterator();
        while (iterator.hasNext()){
            UserFactory next = iterator.next();
            System.out.println(next.createUser());
        }

    }

}
