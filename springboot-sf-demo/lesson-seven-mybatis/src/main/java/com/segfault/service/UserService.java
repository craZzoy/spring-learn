package com.segfault.service;

import com.segfault.dao.UserDao;
import com.segfault.entity.Class;
import com.segfault.entity.User;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    public SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    private UserDao userDao;

    public User selectUser(int id) {
        return userDao.selectUser(id);
    }

    public List<User> selectUsers() {
        return userDao.selectUsers();
    }

    /**
     * RowBounds是针对ResultSet结果集的内存分页，即先把数据全部查询到内存中再分页
     * 性能较差，只是用与数据量小的情况
     *
     * @param start 开始行
     * @param limit 数据条数
     * @return
     */
    public List<User> selectUsersByPage(int start, int limit) {
        return userDao.selectUsersByPage(new RowBounds(start, limit));
    }

    public List<User> selectUsersByPageParam(int start, int limit) {
        Map map = new HashMap();
        map.put("start", start);
        map.put("limit", limit);
        return userDao.selectUsersByPageParam(map);
    }

    public Class selectClassRelationTeacher(int id) {
        return userDao.selectClassRelationTeacher(id);
    }

    public Class selectOneToMuti(int id) {
        return userDao.selectOneToMuti(id);
    }
}
