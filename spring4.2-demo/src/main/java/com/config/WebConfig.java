package com.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

import org.apache.commons.fileupload.FileItemFactory;

/**
 * WebApplicationContext
 * Created by zwz on 2019/5/13.
 */
@Configuration
@EnableWebMvc //启用mvc
@ComponentScan("com") //启用组件扫描
public class WebConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware {

    private static final String UTF8 = "UTF-8";

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    /**
     * 启用Thymeleaf
     *
     * @return
     */
    @Bean
    public ITemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(this.applicationContext);
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCacheable(true);
        return templateResolver;
    }

    @Bean
    public TemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }


    @Bean
    public ViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setCharacterEncoding(UTF8);
        viewResolver.setOrder(1);
        //viewResolver.setViewNames(new String[]{".html",".xhtml"});
        return viewResolver;
    }


    /**
     * 配置静态资源的处理
     * 使的对于静态资源的的请求转发到servlet容器默认的上下文，而不是DispatherServlet处理
     *
     * @param configurer
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }


    /**
     * 配置信息源
     *
     * @return
     */
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        //设置文件基础名字
        messageSource.setBasename("message");
        //解决中文乱码
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    /**
     * 配置信息源(可重新加载)
     *
     * @return
     */
    //@Bean
    public MessageSource messageSourceForReload() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        //设置文件基础名字
        messageSource.setBasename("classpath:message");
        messageSource.setCacheSeconds(10);
        //解决中文乱码
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }


    //@Bean
    public MultipartResolver multipartResolver() throws Exception {

        //return new StandardServletMultipartResolver();

        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        //临时路径
        multipartResolver.setUploadTempDir(new FileSystemResource("D:\\user"));
        //上传文件大小的最大值
        multipartResolver.setMaxUploadSize(2097152);
        //指定一个容量大小（byte），当文件容量达到这个值时就将其存入临时路径
        multipartResolver.setMaxInMemorySize(0);
        return multipartResolver;
    }
}
