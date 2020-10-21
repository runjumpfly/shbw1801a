package cn.bobohost.health.service;

import cn.bobohost.entity.PageResult;
import cn.bobohost.pojo.CheckItem;

import java.util.List;

/**
 * 检查项服务接口
 */
public interface CheckItemService {
    public void add(CheckItem checkItem);
    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);
    public void delete(Integer id);

    CheckItem findById(Integer id);

    public void edit(CheckItem checkItem);

    List<CheckItem> findAll();
}