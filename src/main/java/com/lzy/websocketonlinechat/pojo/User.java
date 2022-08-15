package com.lzy.websocketonlinechat.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private Integer uid;
    private String uname;
    private Integer psw;
    private List<String> friends;
}
