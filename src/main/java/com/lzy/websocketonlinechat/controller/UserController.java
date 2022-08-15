package com.lzy.websocketonlinechat.controller;

import com.lzy.websocketonlinechat.pojo.User;
import com.lzy.websocketonlinechat.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
//todo 返回结果封装为ResultMessage
public class UserController {

    @Autowired
    private IUserService userService;

    //登录校验，数据合法性由前台保证，成功则将用户信息放入session

    //检验用户名和密码可以在后续修改用户信息等地方用到，不止是登录时
    @GetMapping("/{uname}/{psw}")
    public boolean login(@PathVariable String uname, @PathVariable int psw,
                         HttpSession session){
        User user = userService.verifyUser(uname, psw);
        if(null!=user){
            session.setAttribute("user",user);
            return true;
        }
        return false;
    }

    //获取当前用户信息,解决html静态页面跨域问题
    //todo 改用vue后取消该方法
    @GetMapping
    public User getUserName(HttpSession session){
        User user = (User)session.getAttribute("user");
        return user;
    }


    @PostMapping
    public String register(User user){
        User tempUser = userService.getUserByName(user.getUname());
        if(null != tempUser){
            return "用户名重复";
        }
        userService.creatUser(user);
        return "注册成功";
    }

    @PutMapping
    public String updateUser(User user,User newUser,HttpSession session){
        User tempUser = userService.verifyUser(user.getUname(), user.getPsw());
        if(null != tempUser){
            if(userService.updateUser(newUser) > 0){
                session.setAttribute("user",newUser);
                return "修改成功";
            }
            return "修改失败";
        }else{
            return "用户名密码错误";
        }
    }

}
