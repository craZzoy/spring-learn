package com.geekbang.dependency.lookup;

import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 层次性依赖查找示例
 */
public class HierarchicalDependencyLookupDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(HierarchicalDependencyLookupDemo.class);

        //1. 获取 HierarchicalBeanFactory <- ConfigurableBeanFactory <- ConfigurableListableBeanFactory
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        System.out.println(beanFactory.getParentBeanFactory());

        //2.设置parent BeanFactory
        HierarchicalBeanFactory hierarchicalBeanFactory = createHierarchicalBeanFactory();
        beanFactory.setParentBeanFactory(hierarchicalBeanFactory);
        System.out.println(beanFactory.getParentBeanFactory());

        //3.使用containsLocalBean方法查找Bean
        displayContainsLocalBean(beanFactory, "user");
        displayContainsLocalBean(hierarchicalBeanFactory, "user");

        //4.使用containBean方法查找Bean
        displayContainsBean(beanFactory, "user");
        displayContainsBean(hierarchicalBeanFactory, "user");

        context.refresh();
        context.close();
    }

    private static void displayContainsBean(HierarchicalBeanFactory beanFactory, String beanName) {
        System.out.printf("当前BeanFactory[%s]是否包含Local Bean [%s] : %s\n", beanFactory, beanName,
                beanFactory.containsBean(beanName));
    }

    private static void displayContainsLocalBean(HierarchicalBeanFactory beanFactory, String beanName) {
        System.out.printf("当前BeanFactory[%s]是否包含Local Bean [%s] : %s\n", beanFactory, beanName,
                beanFactory.containsLocalBean(beanName));
    }

    /**
     * 创建BeanFactory容器（层次性）
     * @return
     */
    private static HierarchicalBeanFactory createHierarchicalBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        reader.loadBeanDefinitions(location);
        return beanFactory;
    }

}
