package com.itwh.a0520erp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itwh.a0520erp.domain.Role;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 翁煌
 * @since 2020-05-20
 */
public interface RoleService extends IService<Role> {
    /**
     * 根据角色ID查询当前角色拥有的所有的权限或菜单ID
     * @param roleId
     * @return
     */
    List<Integer> queryRolePermissionIdsByRid(Integer roleId);

    void saveRolePermission(Integer rid, Integer[] ids);

    List<Integer> queryUserRoleIdsByUid(Integer id);
}

