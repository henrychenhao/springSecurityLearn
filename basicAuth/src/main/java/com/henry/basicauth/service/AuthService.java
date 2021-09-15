package com.henry.basicauth.service;

import com.henry.basicauth.bean.UserBean;

/**
 * @author hao.chen
 * @date 2021/9/15 3:54 下午
 */
public interface AuthService {
    /**
     * 获取登陆用户
     * @param user
     * @return
     */
    UserBean userLogin(UserBean user);
}
