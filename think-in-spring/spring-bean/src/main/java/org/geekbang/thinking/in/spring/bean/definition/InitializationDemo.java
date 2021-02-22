package org.geekbang.thinking.in.spring.bean.definition;

import org.geekbang.thinking.in.spring.bean.factory.DefaultUserFactory;
import org.geekbang.thinking.in.spring.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * Bean初始化 demo
 */
@Configuration
public class InitializationDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(InitializationDemo.class);

        context.refresh();
        System.out.println("spring应用上下文已启动...");

        UserFactory bean = context.getBean(UserFactory.class);
        System.out.println(bean);

        System.out.println("spring上下文准备关闭");
        context.close();
        System.out.println("spring上下文已关闭");
    }

    @Bean(initMethod = "initUserFactory", destroyMethod = "destroyMethod")
    @Lazy(value = true)
    public UserFactory userFactory(){
        return new DefaultUserFactory();
    }

}
