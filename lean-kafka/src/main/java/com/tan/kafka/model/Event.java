package com.tan.kafka.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("event")
public class Event {
    @TableId(type = IdType.AUTO, value = "id")
    private Long id;
    private String msgId;
    private String message;
}

