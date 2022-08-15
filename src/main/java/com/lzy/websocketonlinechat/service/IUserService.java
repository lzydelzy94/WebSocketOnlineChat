package com.lzy.websocketonlinechat.service;

import com.lzy.websocketonlinechat.pojo.User;


public interface IUserService {

    public User verifyUser(String uname, int psw);

    public boolean creatUser(User user);

    public User getUserByUid(int uid);

    public User getUserByName(String uname);

    public int updateUser(User user);

}
