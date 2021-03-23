package com.geekbang.dependency.lookup;


import com.geekbang.ioc.overview.dependency.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 单一类型查找示例
 */
@Configuration
public class SingleTypeLookupDemo {

    public static void main(String[] args) {
        //ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-lookup-context.xml");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(SingleTypeLookupDemo.class);
        context.refresh();
        Object user = context.getBean(User.class, 1, "Jack", 30);
        System.out.println(user);
        context.close();
    }

    @Bean
    public User user(){
        User user = new User(2, "Tom", 29);
        return user;
    }

}
