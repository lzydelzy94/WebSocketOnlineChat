package com.lzy.websocketonlinechat.service.impl;

import com.lzy.websocketonlinechat.dao.FriendDao;
import com.lzy.websocketonlinechat.service.IFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lzy
 * @className FriendServiceImpl
 * @date 2022/8/7 1:23
 * @description TODO
 */

@Service
public class FriendServiceImpl implements IFriendService {
    @Autowired
    private FriendDao friendDao;

    @Override
    public int createFriend(int uid, int friendId) {
        //互相创建关系，都创建成功时才算成功
        //todo 事务
        int a = friendDao.createFriend(uid, friendId);
        int b = friendDao.createFriend(friendId, uid);
        return a&b;
    }

    public List<String> getFriendsByUid(int uid){
        return friendDao.getFriendsByUid(uid);
    }
}
