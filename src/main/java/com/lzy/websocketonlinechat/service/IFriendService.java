package com.lzy.websocketonlinechat.service;

import java.util.List;

public interface IFriendService {

    public int createFriend(int uid,int friendId);

    public List<String> getFriendsByUid(int uid);

    public int deleteFriend(int uid, int friendId);
}
