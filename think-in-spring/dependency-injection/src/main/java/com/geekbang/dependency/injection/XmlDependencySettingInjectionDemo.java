package com.geekbang.dependency.injection;

import com.geekbang.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * xml方式 setter 依赖注入
 */
public class XmlDependencySettingInjectionDemo {

    public static void main(String[] args) {

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String xmlPath = "classpath:/META-INF/dependency-setting-injection.xml";
        reader.loadBeanDefinitions(xmlPath);

        User bean = beanFactory.getBean(User.class);
        System.out.println(bean);

        UserHolder bean1 = beanFactory.getBean(UserHolder.class);
        System.out.println(bean1);

    }

}
