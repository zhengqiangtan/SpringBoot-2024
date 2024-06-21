package com.tan.annotation.bean;

/**
 * @Description TODO
 * @Author zhengqiang.tan
 * @Date 2024/6/21 23:43
 */

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class AutoDetectDemoBean {
    private long personId = 123L;
    private String name = "James Clark";

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "IgnoreDemoBean{" +
                "personId=" + personId +
                ", name='" + name + '\'' +
                '}';
    }
}