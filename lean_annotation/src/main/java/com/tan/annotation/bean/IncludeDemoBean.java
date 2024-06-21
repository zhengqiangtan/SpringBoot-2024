package com.tan.annotation.bean;

/**
 * @Description TODO
 * @Author zhengqiang.tan
 * @Date 2024/6/21 23:48
 */

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class IncludeDemoBean {
    public long personId = 123L;
    public String name = null;

    @Override
    public String toString() {
        return "IncludeDemoBean{" +
                "personId=" + personId +
                ", name='" + name + '\'' +
                '}';
    }
}
