package com.tan.annotation.bean;

/**
 * @Description TODO
 * @Author zhengqiang.tan
 * @Date 2024/6/21 23:42
 */

import com.fasterxml.jackson.annotation.JsonIgnoreType;

public class IgnoreTypeDemoBean {
    @JsonIgnoreType
    public static class Address {
        public String doorNumber = null;
        public String streetName = null;
        public String pinCode = null;
        public String city = null;

        @Override
        public String toString() {
            return "Address{" +
                    "doorNumber='" + doorNumber + '\'' +
                    ", streetName='" + streetName + '\'' +
                    ", pinCode='" + pinCode + '\'' +
                    ", city='" + city + '\'' +
                    '}';
        }
    }

    public long personId = 0;
    public String name = "James Clark";
    public Address address = new Address();

    @Override
    public String toString() {
        return "IgnoreTypeDemoBean{" +
                "personId=" + personId +
                ", name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}
