package com.geekbang.ioc.overview.dependency.injection;

import com.geekbang.ioc.overview.dependency.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

/**
 * 依赖注入
 */
public class DependencyInjectionDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");

        //依赖来源一：自定义对象
        UserRepository userRepository = beanFactory.getBean("userRepository", UserRepository.class);
        System.out.println(userRepository.getUsers());

        //依赖来源二：依赖注入（内建依赖）: 依赖注入的beanFactory和用于依赖查找的BeanFactory并非同一个
        System.out.println(userRepository.getBeanFactory());
        System.out.println(beanFactory);
        System.out.println(beanFactory == userRepository.getBeanFactory());

        //错误查找，不用在依赖查找中找到BeanFactory
        //System.out.println(beanFactory.getBean(BeanFactory.class));

        //依赖来源三、容器内建Bean
        Environment bean = beanFactory.getBean(Environment.class);
        System.out.println("获取到的Environment类型的Bean：" + bean);

    }

}
