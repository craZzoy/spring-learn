package org.geekbang.thinking.in.spring.bean.definition;

import com.geekbang.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean实例化 demo
 */
public class BeanInstantiationDemo {

    public static void main(String[] args) {
        BeanFactory factory = new ClassPathXmlApplicationContext
                ("classpath:/META-INF/bean-instantination-context.xml");

        User userByConstructorMethod = factory.getBean("user-by-constructor-method", User.class);
        User userByStaticMethod = factory.getBean("user-by-static-method", User.class);
        User userByInstanceBean = factory.getBean("user-by-instance-bean", User.class);
        User userByFactoryBean = factory.getBean("user-by-factory-bean", User.class);

        System.out.println(userByConstructorMethod);
        System.out.println(userByStaticMethod);
        System.out.println(userByInstanceBean);
        System.out.println(userByFactoryBean);

        System.out.println(userByStaticMethod == userByInstanceBean);
        System.out.println(userByStaticMethod == userByFactoryBean);



    }

}
