package com.tan.annotation.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @Description TODO
 * @Author zhengqiang.tan
 * @Date 2024/6/21 23:23
 */
public class IgnoreDemoBean {
    @JsonIgnore
    public long personId = 0;
    public String name = "James Clark";

    @Override
    public String toString() {
        return "IgnoreDemoBean{" +
                "personId=" + personId +
                ", name='" + name + '\'' +
                '}';
    }
}