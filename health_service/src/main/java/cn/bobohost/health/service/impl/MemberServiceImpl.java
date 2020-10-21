package cn.bobohost.health.service.impl;

import cn.bobohost.health.dao.MemberDao;
import cn.bobohost.health.service.MemberService;
import cn.bobohost.pojo.Member;
import cn.bobohost.utils.MD5Utils;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

@Service(interfaceClass = MemberService.class)
@Transactional
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;
    //根据手机号查询会员
    public Member findByTelephone(String telephone) {
        return memberDao.findByTelephone(telephone);
    }



    //新增会员
    public void add(Member member) {
        if(member.getPassword() != null){
            member.setPassword(MD5Utils.md5(member.getPassword()));
        }
        memberDao.add(member);
    }

    @Override
    //根据月份统计会员数量
    public List<Integer> findMemberCountByMonth(List<String> month) {
        List<Integer> list = new ArrayList<>();
        for(String m : month){
            m = m + ".31";//格式：2019.04.31
            Integer count = memberDao.findMemberCountBeforeDate(m);
            list.add(count);
        }
        return list;
    }


    /**
     * 统计过去12个月的会员数量
     * @return
     */
    @Override
    public Map getMemberReport() {
        //获取日历对象
        Calendar calendar = Calendar.getInstance();
        //设置日历往前推12个月
        calendar.add(Calendar.MONTH,-12);

        //定义一个list集合，存放过去12个月每个月的月份
        List<String> months = new ArrayList<>();

        //定义一个list集合，存放过去12个月每个月的会员数量
        List<Integer> memberCounts = new ArrayList<>();

        //遍历过去12个月的每一个月
        for (int i = 0; i < 12; i++) {

            //获取每个月的时间
            Date time = calendar.getTime();
            //获取每个月的月份
            String month = new SimpleDateFormat("yyyy-MM").format(time);

            //定义每个月开始日期
            String monthBegin = month+"-1";
            //定义每个月结束时间
            String monthEnd = month+"-31";

            //统计每个月人数
            int count = memberDao.findMemberCountByMonth(monthBegin,monthEnd);

            //添加每一个月
            months.add(month);

            //添加每一个月的会员数量
            memberCounts.add(count);

            //每次循环都在日历的月份上+1，如-11月,-10月，-9月，依次类推
            calendar.add(Calendar.MONTH,+1);

        }

        //创建一个map，将月份的集合以及每个月会员数量的集合存入其中
        Map<String, List> map = new HashMap<>();
        map.put("months",months);
        map.put("memberCounts",memberCounts);

        return map;
    }

}
