package com.henry.security01.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.henry.security01.model.User;

/**
 * @author hao.chen
 * @date 2021/9/14 4:34 下午
 */
public interface UserMapper extends BaseMapper<User> {
    User getUserInfoByName(String username);
}
