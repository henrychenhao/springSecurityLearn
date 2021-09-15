package com.henry.security01.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.henry.security01.model.Permission;

import java.util.List;

/**
 * @author hao.chen
 * @date 2021/9/14 5:02 下午
 */
public interface PermissionMapper extends BaseMapper<Permission> {
    List<Permission> selectByUserId(Long id);
}
