package com.segfault.dao;


import com.segfault.entity.Class;
import com.segfault.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserDao {

    public User selectUser(int id);

    List<User> selectUsers();

    List<User> selectUsersByPage(RowBounds rowBounds);

    List<User> selectUsersByPageParam(Map map);

    Class selectClassRelationTeacher(int id);

    Class selectOneToMuti(int id);
}
