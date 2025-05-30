package com.tan.annotation.entity;

import com.tan.annotation.anno.BankAPI;
import com.tan.annotation.anno.BankAPIField;
import lombok.Data;

/**
 * 接口参数定义
 */
@BankAPI(url = "/bank/createUser", desc = "创建用户接口")
@Data
public class CreateUserAPI extends AbstractAPI {
    @BankAPIField(order = 1, type = "S", length = 10)
    private String name;

    @BankAPIField(order = 2, type = "S", length = 18)
    private String identity;

    @BankAPIField(order = 4, type = "S", length = 11) //注意这里的order需要按照API表格中的顺序
    private String mobile;

    @BankAPIField(order = 3, type = "N", length = 5)
    private int age;
}