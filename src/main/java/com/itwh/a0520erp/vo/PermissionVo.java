package com.itwh.a0520erp.vo;

import com.itwh.a0520erp.domain.Permission;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 */
@Data
@EqualsAndHashCode(callSuper = false) //不使用父类的方法
public class PermissionVo extends Permission {
    private static final long serialVersionUID=1L;

    //当前页
    private Integer page=1;
    //一页几条
    private Integer limit=10;
}
