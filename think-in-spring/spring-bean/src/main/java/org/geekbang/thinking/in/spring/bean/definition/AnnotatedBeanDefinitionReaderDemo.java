package org.geekbang.thinking.in.spring.bean.definition;

import com.geekbang.ioc.overview.dependency.domain.User;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * AnnotatedBeanDefinitionReader demo
 */
public class AnnotatedBeanDefinitionReaderDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        AnnotatedBeanDefinitionReader beanDefinitionReader = new AnnotatedBeanDefinitionReader(context);
        beanDefinitionReader.register(Config.class);
        context.refresh();
        Map<String, User> beansOfType = context.getBeansOfType(User.class);
        System.out.println(beansOfType);
        context.close();
    }


    @Component
    public static class Config {

        @Bean(name = {"user", "user-alias"})
        public User user (){
            User user1 = new User();
            user1.setName("杰克马");
            user1.setAge(35);
            return user1;
        }
    }
}
