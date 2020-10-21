package cn.bobohost.health.dao;

import cn.bobohost.pojo.CheckGroup;
import cn.bobohost.pojo.CheckItem;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

/**
 * 持久层Dao接口
 */
public interface CheckGroupDao {
    void add(CheckGroup checkGroup);
    void setCheckGroupAndCheckItem(Map map);

    Page<CheckItem> selectByCondition(String queryString);

    CheckGroup findById(Integer id);
    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);
//    void setCheckGroupAndCheckItem(Map map);
    void deleteAssociation(Integer id);
    void edit(CheckGroup checkGroup);

    List<CheckGroup> findAll();

    CheckGroup findCheckGroupById(Integer id);
}