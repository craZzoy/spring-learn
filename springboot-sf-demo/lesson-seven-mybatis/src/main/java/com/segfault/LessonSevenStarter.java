package com.segfault;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;

import java.util.stream.Stream;

@SpringBootApplication
//@MapperScan(basePackages = "com.segfault.dao")
public class LessonSevenStarter {

    public static void main(String[] args) {
        /*System.out.println(System.getProperty("user.dir"));


        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("classpath:/application.xml");
        EncodedResource encodedResource = new EncodedResource(resource,"utf-8");

        Stream.of(System.getProperties()).forEach(LessonSevenStarter::print);*/

        SpringApplication.run(LessonSevenStarter.class, args);
    }


    public static void print(Object obj) {
        System.out.println(obj + "\n");
    }


}
