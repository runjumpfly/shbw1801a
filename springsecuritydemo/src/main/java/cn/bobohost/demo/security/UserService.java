package cn.bobohost.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    //模拟数据库中的用户数据
    public static  Map<String, cn.bobohost.pojo.User> map = new HashMap<>();
//    static {
    public void initData(){
        cn.bobohost.pojo.User user1 = new cn.bobohost.pojo.User();
        user1.setUsername("admin");
//        user1.setPassword("admin");
        user1.setPassword(passwordEncoder.encode("admin"));

        cn.bobohost.pojo.User user2 = new cn.bobohost.pojo.User();
        user2.setUsername("xiaoming");
//        user2.setPassword("1234");
        user2.setPassword(passwordEncoder.encode("123456"));

        map.put(user1.getUsername(),user1);
        map.put(user2.getUsername(),user2);
    }

     /**
     * 根据用户名加载用户信息
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        initData();
        cn.bobohost.pojo.User userInDb = map.get(username);//模拟根据用户名查询数据库
        if(userInDb == null){
            //根据用户名没有查询到用户
            return null;
        }

        //模拟数据库中的密码，后期需要查询数据库
        //注意：使用springsecurity5，需要加上{noop}指定使用NoOpPasswordEncoder给DelegatingPasswordEncoder去校验密码
        //，同时需要知道NoOpPasswordEncoder已经过时了，这里仅是为了方便
//        String passwordInDb = "{noop}" + userInDb.getPassword();
        String passwordInDb = userInDb.getPassword();
        System.out.println("username:" + username);
        System.out.println("password:"+ passwordInDb);

        List<GrantedAuthority> list = new ArrayList<>();
        //授权，后期需要改为查询数据库动态获得用户拥有的权限和角色
        list.add(new SimpleGrantedAuthority("add"));
        list.add(new SimpleGrantedAuthority("delete"));
        list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        //注意这里的User是SpringSecurity提供的
        UserDetails user = new User(username,passwordInDb,list);
        return user;
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder bpe=new BCryptPasswordEncoder();
        String password = bpe.encode("admin");
        System.out.println(password);
    }
}