package cn.bobohost.health.dao;

import cn.bobohost.pojo.Role;

import java.util.Set;

public interface RoleDao {
    public Set<Role> findByUserId(int id);
}