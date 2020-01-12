package com.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

/**
 * 通过java来配置DispatherServlet(使用servlet3的规范，spring3.1以上)，而非web.xml的方式
 * 实现AbstractAnnotationConfigDispatcherServletInitializer的类会自动配置DispatherServlet和Spring应用上下文
 * Spring上下文位于servlet上下文中
 * Created by zwz on 2019/5/13.
 */
public class SpittrWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * 指定配置类用于构建root上下文，即Servlet上下文
     *
     * @return
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{RootConfig.class};
    }

    /**
     * 指定配置类用于构建web上下文，即Spring上下文
     *
     * @return
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebConfig.class};
    }

    /**
     * DispatherServlet的映射，这里映射到“/”
     *
     * @return
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    /**
     * 添加额外的配置
     *
     * @param registration
     */
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setMultipartConfig(
                new MultipartConfigElement("/tmp/spittr/uploads", 2097152, 4194304, 0));
    }

    /**
     * 注册Filter，所有的Filter都会映射到DispatherServlet上
     *
     * @return
     */
    @Override
    protected Filter[] getServletFilters() {
        return super.getServletFilters();
    }
}
