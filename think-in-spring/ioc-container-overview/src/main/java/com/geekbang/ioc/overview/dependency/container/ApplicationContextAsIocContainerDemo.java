package com.geekbang.ioc.overview.dependency.container;

import com.geekbang.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * {@link org.springframework.context.ApplicationContext} 作为IOC容器
 */
@Configuration
public class ApplicationContextAsIocContainerDemo {

    public static void main(String[] args) {
        //创建BeanFactory容器
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //注册配置类
        context.register(ApplicationContextAsIocContainerDemo.class);
        //启动上下文
        context.refresh();
        lookupCollectionByType(context);
        context.close();
    }

    @Bean
    public User user (){
        User user = new User();
        user.setName("测试用户");
        user.setAge(18);
        return user;
    }

    /**
     * 根据类型查找集合
     * @param factory
     */
    private static void lookupCollectionByType(BeanFactory factory) {

        if(factory instanceof ListableBeanFactory){
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) factory;
            Map<String, User> beansOfType = listableBeanFactory.getBeansOfType(User.class);
            System.out.println(beansOfType);
        }

    }

}
