package com.henry.security01.service.impl;

import com.henry.security01.mapper.PermissionMapper;
import com.henry.security01.mapper.UserMapper;
import com.henry.security01.model.Permission;
import com.henry.security01.model.User;
import com.henry.security01.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hao.chen
 * @date 2021/9/14 4:28 下午
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    PermissionMapper permissionMapper;


    @Override
    public User getUserInfoByName(String username) {
        return userMapper.getUserInfoByName(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user=getUserInfoByName(username);
        List<GrantedAuthority> authorities=new ArrayList<>();
        if(user!=null){
            List<Permission> permissions= permissionMapper.selectByUserId(user.getId());
            //设置权限
            permissions.forEach(permission->{
                if(permission != null && !StringUtils.isEmpty(permission.getEnname())){
                    GrantedAuthority grantedAuthority=new SimpleGrantedAuthority(permission.getEnname());
                    authorities.add(grantedAuthority);
                }
            });
            //封装成UserDetails的实现类
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),user.getPassword(),authorities);
        }else{
            throw new UsernameNotFoundException("用户名不存在");
        }
    }
}
