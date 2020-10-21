package cn.bobohost.health.service;

import cn.bobohost.pojo.Member;

import java.util.List;
import java.util.Map;

public interface MemberService {
    public void add(Member member);
    public Member findByTelephone(String telephone);

    List<Integer> findMemberCountByMonth(List<String> list);

    //获取过去12个月每月会员数量
    Map getMemberReport();

}
