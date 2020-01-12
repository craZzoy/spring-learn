package src;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import src.spring.MyFilterForSpring;
import src.spring.MyServletForSpring;
import src.spring.MyServletRequestListenerForSpring;

import java.util.Arrays;

@SpringBootApplication
@ServletComponentScan(basePackages = {"src.servlet"})
public class LessonFourDemoStarter extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(LessonFourDemoStarter.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        builder.sources(LessonFourDemoStarter.class);
        return builder;
    }


    /**
     * 注册一个Servlet
     * Spring Boot 1.4.0 开始支持org.springframework.boot.web.servlet.ServletRegistrationBean
     * Spring Boot  1.4.0 之前org.springframework.boot.context.embedded.ServletRegistrationBean
     *
     * @return
     */
    @Bean
    public static ServletRegistrationBean registrationMyServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        servletRegistrationBean.setServlet(new MyServletForSpring());
        servletRegistrationBean.setName("myServletForSpring");
        servletRegistrationBean.setUrlMappings(Arrays.asList("/myServletForSpring"));
        return servletRegistrationBean;
    }

    /**
     * 注册一个Filter
     * Spring Boot 1.4.0 开始org.springframework.boot.web.servlet.FilterRegistrationBean
     * Spring Boot  1.4.0 之前 org.springframework.boot.context.embedded.FilterRegistrationBean
     *
     * @return
     */
    @Bean
    public static FilterRegistrationBean registrationMyFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new MyFilterForSpring());
        filterRegistrationBean.setName("myFilterForSpring");
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/myServletForSpring"));
        return filterRegistrationBean;
    }

    /**
     * 注册一个监听器
     * Spring Boot 1.4.0 开始org.springframework.boot.web.servlet.ServletListenerRegistrationBean
     * Spring Boot  1.4.0 之前org.springframework.boot.context.embedded.ServletListenerRegistrationBean
     *
     * @return
     */
    @Bean
    public static ServletListenerRegistrationBean registrationMyListener() {
        ServletListenerRegistrationBean servletListenerRegistrationBean = new ServletListenerRegistrationBean();
        servletListenerRegistrationBean.setListener(new MyServletRequestListenerForSpring());
        return servletListenerRegistrationBean;
    }

}
