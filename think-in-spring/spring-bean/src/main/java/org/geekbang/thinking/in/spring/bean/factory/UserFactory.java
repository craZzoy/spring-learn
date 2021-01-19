package org.geekbang.thinking.in.spring.bean.factory;

import com.geekbang.ioc.overview.dependency.domain.User;

public interface UserFactory {

    default User createUser(){
        return User.createUser();
    }

}
