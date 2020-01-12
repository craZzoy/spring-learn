package com.segfault.demo;

import com.segfault.entity.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;

import java.io.Reader;
import java.util.Properties;

public class MybatisXmlConfigurationDemo {
    public static void main(String[] args) throws Exception {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("classpath:mybatis/mybatis-config.xml");
        EncodedResource encodedResource = new EncodedResource(resource, "utf-8");
        Reader reader = encodedResource.getReader();
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = builder.build(reader, "dev", new Properties());
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //User user = sqlSession.selectOne("mybatis.mapper.UserMapper.selectUser",1);

        User user = sqlSession.selectOne("mybatis.mapper.UserMapper.selectUserForName",
                "'tom' and 1=1");
        System.out.println(user);

    }
}
