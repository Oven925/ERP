package com.bdqn.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sys")
public class SystemController {
    /**
     * 去到后台首页
     * @return
     */
    @RequestMapping("/index")
    public String toIndex(){
        return "system/home/index";
    }

    /**
     * 去到首页工作台
     * @return
     */
    @RequestMapping("/toDestopManager")
    public String todesktopManager(){
        return "system/home/desktopManager";
    }

    /**
     * 日志管理页面
     * @return
     */
    @RequestMapping("/toLogManager")
    public String toLogManager() {
        return "system/log/logManager";
    }


    /**
     * 去到公告管理页面
     * @return
     */
    @RequestMapping("/toNoticeManager")
    public String toNoticeManager() {
        return "system/notice/noticeManager";
    }

}
