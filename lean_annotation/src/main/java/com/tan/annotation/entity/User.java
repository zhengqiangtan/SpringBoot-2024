package com.tan.annotation.entity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @time 21:00
 * @discription
 **/
@Data
public class User implements Serializable {
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 创建时间
     */
    private Date createTime;

}