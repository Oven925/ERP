package com.bdqn.sys.controller;


import com.bdqn.sys.config.SystemConstant;
import com.bdqn.sys.entity.Log;
import com.bdqn.sys.service.LogService;
import com.bdqn.sys.utils.JSONResult;
import com.bdqn.sys.vo.LoginUserVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.annotation.Resources;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author KazuGin
 * @since 2020-06-14
 */
@RestController
@RequestMapping("/sys/user")
public class UserController {


    @Resource
    private LogService logService;

    @PostMapping("/login")
    public JSONResult login(String loginname, String pwd, HttpSession session, HttpServletRequest request){
        try {
            //获取当前登录主体对象
            Subject subject = SecurityUtils.getSubject();
            //创建令牌对象
            UsernamePasswordToken token = new UsernamePasswordToken(loginname,pwd);
            //登录
            subject.login(token);
            //获取当前登录对象
            LoginUserVo userVo =(LoginUserVo) subject.getPrincipal();
            //保存session
            session.setAttribute(SystemConstant.LOGINUSER,userVo.getUser());

            //保存日志
            Log log = new Log("用户登录",SystemConstant.LOGIN_ACTION,userVo.getUser().getLoginname()+"-"+userVo.getUser().getName(),userVo.getUser().getId(), request.getRemoteUser(),new Date());
            logService.save(log);

            //登录成功
            return SystemConstant.LOGIN_SUCCESS;
        } catch (AuthenticationException e) {
            e.printStackTrace();
            //登录失败，用户名或密码错误
            return SystemConstant.LOGIN_ERROR_PASS;
        }
    }

}

