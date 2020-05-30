package com.itwh.a0520erp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("sys")
public class SystemController {

    /*
    跳转到登录页面
     */
    //打开的welcome页面--index.html后跳转
    @RequestMapping("toLogin")
    public String toLogin() {
        return "system/index/login";
    }

    /**
     * 跳转到首页
     */
    //login.html70行登录成功的跳转页面
    @RequestMapping("index")
    public String index() {
        return "system/index/index";
    }

    /**
     * 跳转到工作台
     */
    //在index.html的63行
    @RequestMapping("toDeskManager")
    public String toDeskManager() {
        return "system/index/deskManager";
    }

    /**
     * 跳转到日志管理
     */
    //在数据表sys_permission里的登录日志的href
    @RequestMapping("toLoginfoManager")
    public String toLoginfoManager() {
        return "system/loginfo/loginfoManager";
    }

    /**
     * 跳转到日志管理
     */
    //在sys_permission里的登录日志的href
    @RequestMapping("toNoticeManager")
    public String toNoticeManager() {
        return "system/notice/noticeManager";
    }

    /**
     * 跳转到部门管理
     *
     */
    //在sys_permission里的登录日志的href
    @RequestMapping("toDeptManager")
    public String toDeptManager() {
        return "system/dept/deptManager";
    }

    /**
     * 跳转到部门管理-left
     *
     */
    //在deptManager.html的8行请求加载左边页面
    @RequestMapping("toDeptLeft")
    public String toDeptLeft() {
        return "system/dept/deptLeft";
    }


    /**
     * 跳转到部门管理--right
     *
     */
    //在deptManager.html的9行请求加载右边页面
    @RequestMapping("toDeptRight")
    public String toDeptRight() {
        return "system/dept/deptRight";
    }

    /**
     * 跳转到菜单管理
     *
     */
    @RequestMapping("toMenuManager")
    public String toMenuManager() {
        return "system/menu/menuManager";
    }

    /**
     * 跳转到菜单管理-left
     *
     */
    @RequestMapping("toMenuLeft")
    public String toMenuLeft() {
        return "system/menu/menuLeft";
    }


    /**
     * 跳转到菜单管理--right
     *
     */
    @RequestMapping("toMenuRight")
    public String toMenuRight() {
        return "system/menu/menuRight";
    }

    /**
     * 跳转到权限管理
     *
     */
    @RequestMapping("toPermissionManager")
    public String toPermissionManager() {
        return "system/permission/permissionManager";
    }

    /**
     * 跳转到权限管理-left
     *
     */
    @RequestMapping("toPermissionLeft")
    public String toPermissionLeft() {
        return "system/permission/permissionLeft";
    }


    /**
     * 跳转到权限管理--right
     *
     */
    @RequestMapping("toPermissionRight")
    public String toPermissionRight() {
        return "system/permission/permissionRight";
    }

    /**
     * 跳转到角色管理
     *
     */
    @RequestMapping("toRoleManager")
    public String toRoleManager() {
        return "system/role/roleManager";
    }
    /**
     * 跳转到用户管理
     *
     */
    @RequestMapping("toUserManager")
    public String toUserManager() {
        return "system/user/userManager";
    }
}