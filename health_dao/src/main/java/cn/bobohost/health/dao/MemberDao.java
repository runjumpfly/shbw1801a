package cn.bobohost.health.dao;

import cn.bobohost.pojo.Member;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MemberDao {
    public List<Member> findAll();
    public Page<Member> selectByCondition(String queryString);
//    public void add(Member member);
    public void deleteById(Integer id);
    public Member findById(Integer id);
//    public Member findByTelephone(String telephone);
    public void edit(Member member);



    public Member findByTelephone(String telephone);
    public void add(Member member);

    //查询每个月的会员数量
    int findMemberCountByMonth(@Param("monthBegin") String monthBegin, @Param("monthEnd")String monthEnd);


    public Integer findMemberCountBeforeDate(String date);
    public Integer findMemberCountByDate(String date);
    public Integer findMemberCountAfterDate(String date);
    public Integer findMemberTotalCount();

}