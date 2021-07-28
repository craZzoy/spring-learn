package com.geekbang.dependency.injection;

import com.geekbang.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * setter注入 注解方式
 */
public class AnnotationDependencySettingInjectionDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AnnotationDependencySettingInjectionDemo.class);

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(context);
        reader.loadBeanDefinitions("classpath:/META-INF/dependency-lookup-context.xml");

        context.refresh();

        UserHolder bean = context.getBean(UserHolder.class);
        System.out.println(bean);


        context.close();
    }

    /**
     * 此处方法注入，经测试是通过名称user找到对象注入的
     * @param user
     * @return
     */
    @Bean
    public UserHolder userHolder(User user) {
        UserHolder userHolder = new UserHolder();
        userHolder.setUser(user);
        return userHolder;
    }
}
