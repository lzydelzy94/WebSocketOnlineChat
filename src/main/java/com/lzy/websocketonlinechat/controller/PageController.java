package com.lzy.websocketonlinechat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author lzy
 * @className PageController
 * @description 页面跳转
 */

@Controller
public class PageController {
    @RequestMapping("/")
    public String login(){
        return "login";
    }
    @RequestMapping("/main")
    public String main(){
        return "main";
    }
    @RequestMapping("/loginerror")
    public String longinError(){
        return "loginerror";
    }
}
