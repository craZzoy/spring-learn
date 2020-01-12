package com.segfault.annotation;

import com.segfault.entity.User;
import com.segfault.handle.DescriptionTypeHandle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MyMapper {

    @Results(
            value = {
                    @Result(property = "id", column = "id", id = true),
                    @Result(property = "name", column = "name"),
                    @Result(property = "age", column = "age"),
                    @Result(property = "description", column = "desc", typeHandler = DescriptionTypeHandle.class),
                    @Result(property = "height", column = "height"),
            }
    )
    @Select("select id,name,age,`desc`,height from user where id = #{id}")
    User selectUser(int id);

}
