package org.geekbang.thinking.in.spring.bean.factory;

import com.geekbang.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.SmartInitializingSingleton;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class DefaultUserFactory implements UserFactory, InitializingBean, SmartInitializingSingleton, DisposableBean {

    @PostConstruct
    public void init(){
        System.out.println("@PostConstruct：UserFactory初始化中...");
    }

    /**
     * 自定义初始化方法
     */
    public void initUserFactory(){
        System.out.println("自定义初始化方法initUserFactory()：UserFactory初始化中...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean#afterPropertiesSet：UserFactory初始化中...");
    }

    @Override
    public void afterSingletonsInstantiated() {
        System.out.println("SmartInitializingSingleton#afterSingletonsInstantiated: UserFactoryy已经初始化中");
    }

    @PreDestroy
    public void destory(){
        System.out.println("@PreDestroy：UserFactory销毁中...");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean#destroy：UserFactory销毁中...");
    }

    public void destroyMethod(){
        System.out.println("DefaultUserFactory#destroyMethod：UserFactory销毁中...");
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("当前对象DefaultUserFactory正在回收中.......");
    }
}
