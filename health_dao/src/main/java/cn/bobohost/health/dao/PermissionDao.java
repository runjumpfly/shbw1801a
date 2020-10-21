package cn.bobohost.health.dao;

import cn.bobohost.pojo.Permission;

import java.util.Set;

public interface PermissionDao {
    public Set<Permission> findByRoleId(int roleId);
}