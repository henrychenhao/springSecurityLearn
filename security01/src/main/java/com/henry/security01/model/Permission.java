package com.henry.security01.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author hao.chen
 * @date 2021/9/14 5:00 下午
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_permission")
@ApiModel(value="permission对象", description="permission对象")
public class Permission implements Serializable {
    private Long id;
    private Long parentId;
    private String name;
    private String enname;
    private String url;
    private String description;
}
