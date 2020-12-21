package org.geekbang.thinking.in.spring.bean.definition;

import com.geekbang.ioc.overview.dependency.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

/**
 * @Description: 注解BeanDefinition demo
 * @Author :
 * @Date : 15:00 2020/12/16
 */
@Import(AnnotationBeanDefinitionDemo.Config.class)
public class AnnotationBeanDefinitionDemo {

    public static void main(String[] args) {
        //创建Bean容器
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //注册配置Bean
        context.register(AnnotationBeanDefinitionDemo.class);
        //启动应用上下文
        context.refresh();
        System.out.println("user 类型的所有Bean：" + context.getBeansOfType(User.class));
        //关闭上下文
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
