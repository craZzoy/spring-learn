package org.geekbang.thinking.in.spring.bean.definition;

import org.geekbang.thinking.in.spring.bean.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.StringUtils;

/**
 * @Description: 注解BeanDefinition demo
 * @Author :
 * @Date : 15:00 2020/12/16
 */
public class AnnotationBeanDefinitionDemo {

    public static void main(String[] args) {
        //创建Bean容器
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();


        //通过BeanDefinition API注册Bean
        //命名注册Bean
        registerUserBeanDefinition(context, "naming-user");
        //非命名注册Bean
        registerUserBeanDefinition(context, null);
        //启动应用上下文
        context.refresh();
        System.out.println("user 类型的所有Bean：" + context.getBeansOfType(User.class));
        //关闭上下文
        context.close();
    }


    public static void registerUserBeanDefinition(BeanDefinitionRegistry registry, String name){
        final BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder
                .addPropertyValue("id", 1)
                .addPropertyValue("name", "JackMa");
        if (StringUtils.hasText(name)){
            //命名注册Bean
            registry.registerBeanDefinition(name,beanDefinitionBuilder.getBeanDefinition());
        } else {
            //非命名注册Bean
            BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(), registry);
        }
    }

}
