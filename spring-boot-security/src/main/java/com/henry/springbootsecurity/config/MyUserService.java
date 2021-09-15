package com.henry.springbootsecurity.config;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author hao.chen
 * @date 2021/9/15 7:13 下午
 */
public class MyUserService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if("admin1".equals(username)){
            return User.withUsername("admin1").password("123").authorities("mobile","salary").build();
        }
        return null;
    }
}
