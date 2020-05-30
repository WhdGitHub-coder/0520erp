package com.itwh.a0520erp.vo;

import com.itwh.a0520erp.domain.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author 翁煌
 * @Description:
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false) //不使用父类的方法
public class RoleVo extends Role {
    private static final long serialVersionUID=1L;

    //当前页
    private Integer page=1;
    //一页几条
    private Integer limit=10;
}
