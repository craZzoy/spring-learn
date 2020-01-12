package com.segfault.demo;


import com.segfault.annotation.MyMapper;
import com.segfault.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.LocalCacheScope;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

public class MybatisAnnotionConfigDemo {
    public static void main(String[] args) throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("mybatis/mybatis-config.xml");
        //ResourceLoader resourceLoader = new DefaultResourceLoader();
        //Resource resource = resourceLoader.getResource("classpath:mybatis/mybatis-config.xml");
        //EncodedResource encodedResource = new EncodedResource(inputStream,"utf-8");
        //Reader reader = encodedResource.getReader();
        Reader reader = new InputStreamReader(inputStream);
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        Properties properties = new Properties();
        SqlSessionFactory sqlSessionFactory = builder.build(reader, "dev", new Properties());
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //清空本地缓存
        sqlSession.clearCache();

        MyMapper mapper = sqlSession.getMapper(MyMapper.class);

        User user = mapper.selectUser(1);

        //User user = sqlSession.selectOne("mybatis.mapper.UserMapper.selectUser",1);

        System.out.println(user);
    }
}
