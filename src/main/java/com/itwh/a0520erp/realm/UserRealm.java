package com.itwh.a0520erp.realm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itwh.a0520erp.common.ActiverUser;
import com.itwh.a0520erp.common.Constast;
import com.itwh.a0520erp.domain.Permission;
import com.itwh.a0520erp.domain.User;
import com.itwh.a0520erp.service.PermissionService;
import com.itwh.a0520erp.service.RoleService;
import com.itwh.a0520erp.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    @Lazy
    private PermissionService permissionService;

    @Autowired
    @Lazy
    private RoleService roleService;

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }



    /*
    授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
        ActiverUser activerUser=(ActiverUser) principalCollection.getPrimaryPrincipal();
        User user=activerUser.getUser();
        List<String> permissions = activerUser.getPermissions();
        if(user.getType()==Constast.USER_TYPE_SUPER) {
            authorizationInfo.addStringPermission("*:*");
        }else {
            if(null!=permissions&&permissions.size()>0) {
                authorizationInfo.addStringPermissions(permissions);
            }
        }
        return authorizationInfo;
    }

    /*
    认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //wrapper是包装
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("loginname",authenticationToken.getPrincipal());//把authenticationToken里的姓名传到sql语句
        //获取登录名为某某的user对象，先判断是否有这个人，如果有，在进行密码的匹配
        User user = userService.getOne(queryWrapper);
        if(null!=user){
            ActiverUser activerUser=new ActiverUser();

            activerUser.setUser(user);

            //根据用户ID查询percode
            //查询所有菜单
            QueryWrapper<Permission> qw=new QueryWrapper<>();
            //设置只能查询菜单
            qw.eq("type", Constast.TYPE_PERMISSION);
            qw.eq("available", Constast.AVAILABLE_TRUE);

            //根据用户ID+角色+权限去查询
            Integer userId=user.getId();
            //根据用户ID查询角色
            List<Integer> currentUserRoleIds = roleService.queryUserRoleIdsByUid(userId);
            //根据角色ID取到权限和菜单ID
            Set<Integer> pids=new HashSet<>();
            for (Integer rid : currentUserRoleIds) {
                List<Integer> permissionIds = roleService.queryRolePermissionIdsByRid(rid);
                pids.addAll(permissionIds);
            }
            List<Permission> list=new ArrayList<>();
            //根据角色ID查询权限
            if(pids.size()>0) {
                qw.in("id", pids);
                list=permissionService.list(qw);
            }
            List<String> percodes=new ArrayList<>();
            for (Permission permission : list) {
                percodes.add(permission.getPercode());
            }
            //放到
            activerUser.setPermissions(percodes);

            //加盐
            ByteSource credentialSalt =ByteSource.Util.bytes(user.getSalt());
            //封装信息，返回Realm中，然后shiro进行验证的时候，会从realm中获取这个info
            //进行密码匹配，先进入一个assertCredentialsMatch(token, info)方法
            //该方法首先会获取一个凭证匹配器
            SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(activerUser,user.getPwd(), credentialSalt,this.getName());
            return  info;
        }
        return null;
    }
}
