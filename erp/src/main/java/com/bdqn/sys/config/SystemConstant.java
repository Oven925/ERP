package com.bdqn.sys.config;

import com.bdqn.sys.utils.JSONResult;

public interface SystemConstant {

    /**
     * 当前登录用户的key
     */
    String LOGINUSER = "loginUser";

    /**
     * 成功
     */
    Boolean OK = true;

    /**
     * 失败
     */
    Boolean ERROR = false;


    /**
     * 登录成功
     */
    JSONResult LOGIN_SUCCESS = new JSONResult(SystemConstant.OK,"登录成功");
    /**
     * 登录失败，用户名或密码错误
     */
    JSONResult LOGIN_ERROR_PASS = new JSONResult(SystemConstant.ERROR,"登录失败,用户名或密码错误");


    /**
     * 类型为菜单：用于首页左侧导航栏
     */
    String TYPE_MENU = "menu";

    /**
     * 类型为权限
     */
    String TYPE_PERMISSION ="permission" ;

    /**
     * 菜单是否展开，1展开
     */
    Integer OPEN_TRUE = 1;

    /**
     * 菜单是否展开，0不展开
     */
    Integer OPEN_FALSE = 0;

    /**
     * 角色为超级管理员
     */
    Integer SUPERUSER = 0;

    String LOGIN_ACTION = "登录操作";



}
