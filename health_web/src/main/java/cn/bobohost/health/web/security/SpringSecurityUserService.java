package cn.bobohost.health.web.security;

import cn.bobohost.health.service.UserService;
import cn.bobohost.pojo.Permission;
import cn.bobohost.pojo.Role;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class SpringSecurityUserService implements UserDetailsService {
    @Reference //注意：此处要通过dubbo远程调用用户服务
    private UserService userService;
  
    //根据用户名查询用户信息
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      	//远程调用用户服务，根据用户名查询用户信息
        cn.bobohost.pojo.User user = userService.findByUsername(username);
        if(user == null){
              //用户名不存在
              return null;
        }

        //授权列表
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        Set<Role> roles = user.getRoles();
        for(Role role : roles){
            Set<Permission> permissions = role.getPermissions();
            //
            for(Permission permission : permissions){
              	//授权
                grantedAuthorityList.add(new SimpleGrantedAuthority(permission.getKeyword()));
            }
        }
        
        UserDetails userDetails = new User(username,user.getPassword(),grantedAuthorityList);
        return userDetails;
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder bpe=new BCryptPasswordEncoder();
        String password = bpe.encode("123456");
        System.out.println(password);
    }
}