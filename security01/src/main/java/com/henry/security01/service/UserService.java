package com.henry.security01.service;

import com.henry.security01.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author hao.chen
 * @date 2021/9/14 4:27 下午
 */
public interface UserService extends UserDetailsService {
    User getUserInfoByName(String username);

    UserDetails loadUserByUsername(String username);
}
