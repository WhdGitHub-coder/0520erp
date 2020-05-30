package com.itwh.a0520erp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itwh.a0520erp.domain.Role;
import com.itwh.a0520erp.mapper.RoleMapper;
import com.itwh.a0520erp.service.RoleService;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 翁煌
 * @since 2020-05-20
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Override
    public boolean removeById(Serializable id) {
        /**
         * 根据角色ID删除sys_role_permission
         * @param id
         */
        this.getBaseMapper().deleteRolePermissionByRid(id);
        /**
         * 根据角色ID删除sys_role_user
         * @param id
         */
        this.getBaseMapper().deleteRoleUserByRid(id);
        return super.removeById(id);
    }
    /**
     * 根据角色ID查询当前角色拥有的所有的权限或菜单ID
     */
    @Override
    public List<Integer> queryRolePermissionIdsByRid(Integer roleId) {
        return this.getBaseMapper().queryRolePermissionIdsByRid(roleId);
    }

    @Override
    public void saveRolePermission(Integer rid, Integer[] ids) {
        RoleMapper roleMapper = this.getBaseMapper();
        //根据rid删除sys_role_permission
        roleMapper.deleteRolePermissionByRid(rid);
        if(ids!=null&&ids.length>0) {
            for (Integer pid : ids) {
                roleMapper.saveRolePermission(rid,pid);
            }
        }
    }

    /**
     * 查询当前用户拥有的角色ID集合
     */
    @Override
    public List<Integer> queryUserRoleIdsByUid(Integer id) {
        return this.getBaseMapper().queryUserRoleIdsByUid(id);
    }
}
