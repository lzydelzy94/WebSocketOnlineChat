package com.lzy.websocketonlinechat.service.impl;

import com.lzy.websocketonlinechat.dao.UserDao;
import com.lzy.websocketonlinechat.pojo.User;
import com.lzy.websocketonlinechat.service.IFriendService;
import com.lzy.websocketonlinechat.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private IFriendService friendService;

    @Override
    public User verifyUser(String uname, int psw) {
        User user = this.getUserByName(uname);
        if(null!=user && psw == user.getPsw()){
            return user;
        }
        return null;
    }

    @Override
    public User getUserByName(String uname) {
        User user = userDao.getUserByName(uname);
        if(null!=user){
            user.setFriends(friendService.getFriendsByUid(user.getUid()));
        }
        return user;
    }

    @Override
    public int updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public boolean creatUser(User user) {
        userDao.createUser(user);
        return user.getUid() > 0;
    }


    @Override
    public User getUserByUid(int uid) {
        User user = userDao.getUserByUid(uid);
        if(null!=user){
            user.setFriends(friendService.getFriendsByUid(uid));
        }
        return user;
    }
}
