package com.lzy.websocketonlinechat.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author lzy
 * @className ResultMessage
 * @date 2022/8/7 21:00
 * @description Controller层返回结果封装
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ResultMessage extends Message {
    private int code;  //结果代码，保存在ResultMessageType中
}
