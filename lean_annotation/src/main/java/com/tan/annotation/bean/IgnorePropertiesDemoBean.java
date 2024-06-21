package com.tan.annotation.bean;

/**
 * @Description TODO
 * @Author zhengqiang.tan
 * @Date 2024/6/21 23:38
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"userId", "gender"})
public class IgnorePropertiesDemoBean {
    public long userId = 0;
    public String name = "James Clark";
    public String gender = null;

    @Override
    public String toString() {
        return "IgnorePropertiesDemoBean{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}

