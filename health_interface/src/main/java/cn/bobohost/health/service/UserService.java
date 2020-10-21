package cn.bobohost.health.service;

import cn.bobohost.pojo.User;

/**
 * 用户服务接口
 */
public interface UserService {
    public User findByUsername(String username);
}