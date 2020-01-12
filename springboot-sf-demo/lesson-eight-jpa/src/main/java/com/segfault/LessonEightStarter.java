package com.segfault;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.segfault.entity"})
@EnableTransactionManagement(proxyTargetClass = true)
public class LessonEightStarter {
    public static void main(String[] args) {
        SpringApplication.run(LessonEightStarter.class, args);
    }
}
