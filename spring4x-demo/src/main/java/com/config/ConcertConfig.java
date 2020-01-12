package com.config;

import com.aspect.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Random;

/**
 * Created by zwz on 2019/5/4.
 */
@Configuration
@EnableAspectJAutoProxy //启动apsectj自动代理
@ComponentScan
public class ConcertConfig {

    @Bean
    public Audience audience() {
        return new Audience();
    }

    @Bean
    public EncoreableIntroducer encoreableIntroducer() {
        return new EncoreableIntroducer();
    }

    //@Bean
    public Encoreable encoreable() {
        return new DefaultEncoreable();
    }

    @Bean
    public Performance performance() {
        return new Performance() {
            @Override
            public void perform() {
                System.out.println("performing...");
            }

            @Override
            public int countPerson(int count) {
                return count;
            }
        };
    }
}
