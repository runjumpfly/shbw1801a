package cn.bobohost.health.dao;

import cn.bobohost.pojo.User;

public interface UserDao {
    public User findByUsername(String username);
}