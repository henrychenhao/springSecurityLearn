package com.henry.basicauth.service.impl;

import com.henry.basicauth.bean.UserBean;
import com.henry.basicauth.service.AuthService;
import com.henry.basicauth.util.TestData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author hao.chen
 * @date 2021/9/15 3:55 下午
 */
@Service
public class AuthServiceImpl implements AuthService {
        private final String demoUserName = "admin";
        private final String demoUserPass = "admin";

        @Resource
        private TestData testData;

        @Override
        public UserBean userLogin(UserBean user){
            UserBean queryUser = testData.queryUser(user);
            if(null != queryUser){
                queryUser.setUserId(UUID.randomUUID().toString());
            }
            return queryUser;
        }
}
