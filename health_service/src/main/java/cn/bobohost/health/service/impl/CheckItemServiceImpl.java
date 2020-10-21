package cn.bobohost.health.service.impl;

import cn.bobohost.entity.PageResult;
import cn.bobohost.health.dao.CheckItemDao;
import cn.bobohost.health.service.CheckItemService;
import cn.bobohost.pojo.CheckItem;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 检查项服务
 */
@Service(interfaceClass = CheckItemService.class)
//事务
@Transactional
public class CheckItemServiceImpl implements CheckItemService {
    @Autowired
    private CheckItemDao checkItemDao;
  	//新增
    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);
    }

    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString) {
       //分页助手
        PageHelper.startPage(currentPage,pageSize);
        //分页结果对象
        Page<CheckItem> page = checkItemDao.selectByCondition(queryString);

        return new PageResult(page.getTotal(),page.getResult());
    }

    //删除
    public void delete(Integer id) throws RuntimeException{
        //查询当前检查项是否和检查组关联
        long count = checkItemDao.findCountByCheckItemId(id);
        if(count > 0){
            //当前检查项被引用，不能删除
            throw new RuntimeException("当前检查项被引用，不能删除");
        }
        checkItemDao.deleteById(id);
    }

    @Override
    public CheckItem findById(Integer id) {
        return checkItemDao.findById(id);
    }
    //编辑
    public void edit(CheckItem checkItem) {
        checkItemDao.edit(checkItem);
    }
    public List<CheckItem> findAll() {
        return checkItemDao.findAll();
    }
}