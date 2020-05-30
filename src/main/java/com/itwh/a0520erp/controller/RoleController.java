package com.itwh.a0520erp.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itwh.a0520erp.common.Constast;
import com.itwh.a0520erp.common.DataGridView;
import com.itwh.a0520erp.common.ResultObj;
import com.itwh.a0520erp.common.TreeNode;
import com.itwh.a0520erp.domain.Permission;
import com.itwh.a0520erp.domain.Role;
import com.itwh.a0520erp.service.PermissionService;
import com.itwh.a0520erp.service.RoleService;
import com.itwh.a0520erp.vo.RoleVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 翁煌
 * @since 2020-05-20
 */
@RestController
@RequestMapping("role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;
    /*
    查询公告
     */
    //roleManager.html145行加载公告内容
    @RequestMapping("loadAllRole")
    public DataGridView loadAllRole(RoleVo roleVo){
        IPage<Role> page =new Page<>(roleVo.getPage(),roleVo.getLimit());
        QueryWrapper<Role> quearyWrapper = new QueryWrapper<>();
        quearyWrapper.like(StringUtils.isNotBlank(roleVo.getName()),"name",roleVo.getName());
        quearyWrapper.like(StringUtils.isNotBlank(roleVo.getRemark()),"remark",roleVo.getRemark());
        quearyWrapper.eq(roleVo.getAvailable()!=null,"available",roleVo.getAvailable());
        quearyWrapper.orderByDesc("createtime");
        IPage<Role> page1 = this.roleService.page(page, quearyWrapper);
        return  new DataGridView(page1.getTotal(),page1.getRecords());
    }

    /*
    新增公告
     */
    //roleManager211行，新增公告请求
    @RequestMapping("addRole")
    public ResultObj addRole(RoleVo roleVo){
        try {
            roleVo.setCreatetime(new Date());
            this.roleService.save(roleVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /*
   修改公告
    */
    //roleManager228行，修改公告请求
    @RequestMapping("updateRole")
    public ResultObj updateRole(RoleVo roleVo){
        try {
            this.roleService.updateById(roleVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }
    /*
    删除公告
     */
    @RequestMapping("deleteRole")
    public ResultObj deleteRole(Integer id){
        try {
            this.roleService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
    /**
     * 根据角色ID加载菜单和权限的树的json串
     */
    @RequestMapping("initPermissionByRoleId")
    public DataGridView initPermissionByRoleId(Integer roleId) {
        //查询所有可用的菜单和权限
        QueryWrapper<Permission> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("available", Constast.AVAILABLE_TRUE);
        List<Permission> allPermissions = permissionService.list(queryWrapper);
        /**
         * 1,根据角色ID查询当前角色拥有的所有的权限或菜单ID
         * 2,根据查询出来的菜单ID查询权限和菜单数据
         */
        List<Integer> currentRolePermissions=this.roleService.queryRolePermissionIdsByRid(roleId);
        List<Permission> carrentPermissions=null;
        if(currentRolePermissions.size()>0) { //如果有ID就去查询
            queryWrapper.in("id", currentRolePermissions);
            carrentPermissions = permissionService.list(queryWrapper);
        }else {
            carrentPermissions=new ArrayList<>();
        }
        //构造 List<TreeNode>
        List<TreeNode> nodes=new ArrayList<>();
        for (Permission p1 : allPermissions) {
            String checkArr="0";
            for (Permission p2 : carrentPermissions) {
                if(p1.getId()==p2.getId()) {
                    checkArr="1";
                    break;
                }
            }
            Boolean spread=(p1.getOpen()==null||p1.getOpen()==1)?true:false;
            nodes.add(new TreeNode(p1.getId(), p1.getPid(), p1.getTitle(), spread, checkArr));
        }
        return new DataGridView(nodes);
    }

    /**
     * 保存角色和菜单权限之间的关系
     */
    @RequestMapping("saveRolePermission")
    public ResultObj saveRolePermission(Integer rid,Integer[] ids) {
        try {
            this.roleService.saveRolePermission(rid,ids);
            return ResultObj.DISPATCH_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DISPATCH_ERROR;
        }
    }

}

