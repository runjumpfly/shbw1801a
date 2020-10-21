package cn.bobohost.health.dao;

import cn.bobohost.pojo.CheckItem;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * 持久层Dao接口
 */
public interface CheckItemDao {
    public void add(CheckItem checkItem);
    public Page<CheckItem> selectByCondition(String queryString);

    public void deleteById(Integer id);
    public long findCountByCheckItemId(Integer checkItemId);

    CheckItem findById(Integer id);

    void edit(CheckItem checkItem);

    List<CheckItem> findAll();

    CheckItem findCheckItemById(Integer id);
}