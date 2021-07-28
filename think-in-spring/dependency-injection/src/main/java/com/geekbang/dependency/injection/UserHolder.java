package com.geekbang.dependency.injection;

import com.geekbang.ioc.overview.dependency.domain.User;

public class UserHolder {

    private User user;


    public UserHolder() {
    }

    public UserHolder(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                '}';
    }
}
