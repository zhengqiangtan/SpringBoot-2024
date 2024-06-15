package com.tan.kafka.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Description 消息实体
 * @Author zhengqiang.tan
 * @Date 2024/6/15 16:20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Event {
    private String id;
    private String message;
}

