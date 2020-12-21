package org.geekbang.thinking.in.spring.bean.definition;

import com.geekbang.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

/**
 * @Description: 别名Bean demo
 * @Author : 郑玮泽
 * @Date : 10:08 2020/12/21
 */
public class BeanAliasDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-definitions-context.xml");
        final User user = beanFactory.getBean("user", User.class);
        //通过别名获取Bean
        final User user1 = beanFactory.getBean("alias-test-user", User.class);
        System.out.println(Arrays.asList(beanFactory.getAliases("user")));
        System.out.println("user == alias-test-user : " + (user == user1));
    }

}
