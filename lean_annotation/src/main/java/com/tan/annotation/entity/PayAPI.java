package com.tan.annotation.entity;

import com.tan.annotation.anno.BankAPI;
import com.tan.annotation.anno.BankAPIField;
import lombok.Data;

import java.math.BigDecimal;

@BankAPI(url = "/bank/pay", desc = "支付接口")
@Data
public class PayAPI extends AbstractAPI {
    @BankAPIField(order = 1, type = "N", length = 20)
    private long userId;
    @BankAPIField(order = 2, type = "M", length = 10)
    private BigDecimal amount;
}