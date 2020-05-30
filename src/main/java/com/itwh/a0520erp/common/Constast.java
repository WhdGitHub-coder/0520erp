package com.itwh.a0520erp.common;

/**
 * @Author 翁煌
 * @Description:
 * @Version 1.0
 */
public class Constast {
    //定义常量状态码
    public static final Integer OK=200;
    public static final Integer ERROR=-1;

    /*
    菜单权限类型
     */
    public static final String TYPE_MENU="menu";
    public static final String TYPE_PERMISSION="permission";

    /*
    可用状态
     */
    public static final Object AVAILABLE_TRUE=1;
    public static final Object AVAILABLE_FALSE=0;

    /*
    用户类型 超级管理员和普通用户
     */
    public static final Integer USER_TYPE_SUPER=0;
    public static final Integer USER_TYPE_NORMAL=1;

    public static final Integer OPEN_TRUE =1;
    public static final Integer OPEN_FALSE =0;

    public static final Object USER_DEFAULT_PWD ="123456" ;

    /**
     * 商品默认图片
     */
    public static final String IMAGES_DEFAULTGOODSIMG_PNG = "images/defaultgoodsimg.png";
}
