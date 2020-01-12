package com.segfault.controller;

import com.segfault.entity.Class;
import com.segfault.entity.User;
import com.segfault.service.UserService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MybatisController {

    //通过spring组装SqlSessionTemplate
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    private UserService userService;

    @RequestMapping("/mybatis/user/{id}")
    public User getUser(@PathVariable int id) {
        User user = sqlSessionTemplate.selectOne("mybatis.mapper.UserMapper.selectUser", id);
        return user;
    }

    @RequestMapping("/mybatis/user")
    public User selectUesr(@RequestParam int id) {
        return userService.selectUser(id);
    }

    @RequestMapping("/mybatis/users")
    public List<User> selectUesrs() {
        return userService.selectUsers();
    }

    @RequestMapping("/mybatis/pageUsers")
    public List<User> selectUesrsByPage(@RequestParam int start, int limit) {
        return userService.selectUsersByPage(start, limit);
    }

    @RequestMapping("/mybatis/pageParamUsers")
    public List<User> selectUsersByPageParam(@RequestParam int start, int limit) {
        return userService.selectUsersByPageParam(start, limit);
    }

    @RequestMapping("/mybatis/class")
    public Class selectClass(@RequestParam int id) {
        return userService.selectClassRelationTeacher(id);
    }

    @RequestMapping("/mybatis/oneToMuti")
    public Class selectOneToMuti(@RequestParam int id) {
        return userService.selectOneToMuti(id);
    }
}
