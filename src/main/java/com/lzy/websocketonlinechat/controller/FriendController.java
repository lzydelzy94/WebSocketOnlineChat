package com.lzy.websocketonlinechat.controller;

import com.lzy.websocketonlinechat.service.IFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lzy
 * @className FriendController
 * @date 2022/8/22 3:00
 */

@RestController
@RequestMapping("/friend")
public class FriendController {
    @Autowired
    IFriendService iFriendService;

    @DeleteMapping("/{uid}")
    public boolean deleteFriend(@PathVariable("uid") int uid,@PathVariable("friendId") int friendId) {
        int success = iFriendService.deleteFriend(uid, friendId);
        return success == 0 ? false : true;
    }
}
