package org.geekbang.thinking.in.spring.bean.definition;


import org.geekbang.thinking.in.spring.bean.factory.DefaultUserFactory;
import org.geekbang.thinking.in.spring.bean.factory.UserFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 外部化单例Bean注册 demo
 * 即先手动创建Bean，然后注册到Spring上下文中
 */
public class SingleBeanRegistionDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        SingletonBeanRegistry beanFactory = context.getBeanFactory();
        UserFactory userFactory = new DefaultUserFactory();

        //注册外部单例对象，外部单例指外部手动创建的、不由IOC控制创建（通过BeanDefinition创建）过程的Bean
        beanFactory.registerSingleton("userFactory", userFactory);

        context.refresh();

        UserFactory bean = context.getBean(UserFactory.class);
        System.out.println(bean);
        System.out.println("bean == userFactory : " + (bean == userFactory));

        context.close();
    }

}
