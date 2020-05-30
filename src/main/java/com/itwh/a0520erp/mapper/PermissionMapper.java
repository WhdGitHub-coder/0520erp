package com.itwh.a0520erp.mapper;

import com.itwh.a0520erp.domain.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 翁煌
 */
public interface PermissionMapper extends BaseMapper<Permission> {
    void deleteRolePermissionByPid(@Param("id")Serializable id);
}
