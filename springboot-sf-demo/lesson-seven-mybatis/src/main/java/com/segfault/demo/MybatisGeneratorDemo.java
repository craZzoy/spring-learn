package com.segfault.demo;

import com.segfault.entity2.User;
import com.segfault.entity2.UserExample;
import com.segfault.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;

import java.io.Reader;
import java.util.List;
import java.util.Properties;

public class MybatisGeneratorDemo {
    public static void main(String[] args) throws Exception {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("classpath:mybatis/mybatis-config.xml");
        EncodedResource encodedResource = new EncodedResource(resource, "utf-8");
        Reader reader = encodedResource.getReader();
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = builder.build(reader, "dev", new Properties());
        SqlSession sqlSession = sqlSessionFactory.openSession();

        /**
         * 通过UserExample查询，面向对象的方式
         */
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andIdEqualTo(1);

        List<User> users = userMapper.selectByExample(userExample);

        User user = users.get(0);

        //User user = sqlSession.selectOne("mybatis.mapper.UserMapper.selectUser",1);

        System.out.println(user);
    }
}
