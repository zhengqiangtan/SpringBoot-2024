package com.tan.annotation.service;


import com.tan.annotation.anno.BankAPI;
import com.tan.annotation.anno.BankAPIField;
import com.tan.annotation.entity.AbstractAPI;
import com.tan.annotation.entity.CreateUserAPI;
import com.tan.annotation.entity.PayAPI;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @Description 代码重构示例:用注解和反射！
 */
@Slf4j
public class BankService {
    //创建用户方法
    public static String createUser(String name, String identity, String mobile, int age) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        //字符串靠左，多余的地方填充_
        stringBuilder.append(String.format("%-10s", name).replace(' ', '_'));
        //字符串靠左，多余的地方填充_
        stringBuilder.append(String.format("%-18s", identity).replace(' ', '_'));
        //数字靠右，多余的地方用0填充
        stringBuilder.append(String.format("%05d", age));
        //字符串靠左，多余的地方用_填充
        stringBuilder.append(String.format("%-11s", mobile).replace(' ', '_'));
        //最后加上MD5作为签名
        return stringBuilder.toString();
//        stringBuilder.append(DigestUtils.md2Hex(stringBuilder.toString()));
//        return Request.Post("http://localhost:45678/reflection/bank/createUser")
//                .bodyString(stringBuilder.toString(), ContentType.APPLICATION_JSON)
//                .execute().returnContent().asString();
    }

    //支付方法
    public static String pay(long userId, BigDecimal amount) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        //数字靠右，多余的地方用0填充
        stringBuilder.append(String.format("%020d", userId));
        //金额向下舍入2位到分，以分为单位，作为数字靠右，多余的地方用0填充
        stringBuilder.append(String.format("%010d", amount.setScale(2, RoundingMode.DOWN).multiply(new BigDecimal("100")).longValue()));
        //最后加上MD5作为签名
        return stringBuilder.toString();

//        stringBuilder.append(DigestUtils.md2Hex(stringBuilder.toString()));
//        return Request.Post("http://localhost:45678/reflection/bank/pay")
//                .bodyString(stringBuilder.toString(), ContentType.APPLICATION_JSON)
//                .execute().returnContent().asString();
    }

    // TODO： 改造优化 反射配合注解实现动态的接口参数组装
    private static String remoteCall(AbstractAPI api) throws IOException {
        //从BankAPI注解获取请求地址
        BankAPI bankAPI = api.getClass().getAnnotation(BankAPI.class);
        bankAPI.url();
        StringBuilder stringBuilder = new StringBuilder();
        Arrays.stream(api.getClass().getDeclaredFields()) //获得所有字段
                .filter(field -> field.isAnnotationPresent(BankAPIField.class)) //查找标记了注解的字段
                .sorted(Comparator.comparingInt(a -> a.getAnnotation(BankAPIField.class).order())) //根据注解中的order对字段排序
                .peek(field -> field.setAccessible(true)) //设置可以访问私有字段
                .forEach(field -> {
                    //获得注解
                    BankAPIField bankAPIField = field.getAnnotation(BankAPIField.class);
                    Object value = "";
                    try {
                        //反射获取字段值
                        value = field.get(api);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    //根据字段类型以正确的填充方式格式化字符串
                    switch (bankAPIField.type()) {
                        case "S": {
                            stringBuilder.append(String.format("%-" + bankAPIField.length() + "s", value.toString()).replace(' ', '_'));
                            break;
                        }
                        case "N": {
                            stringBuilder.append(String.format("%" + bankAPIField.length() + "s", value.toString()).replace(' ', '0'));
                            break;
                        }
                        case "M": {
                            if (!(value instanceof BigDecimal))
                                throw new RuntimeException(String.format("{} 的 {} 必须是BigDecimal", api, field));
                            stringBuilder.append(String.format("%0" + bankAPIField.length() + "d", ((BigDecimal) value).setScale(2, RoundingMode.DOWN).multiply(new BigDecimal("100")).longValue()));
                            break;
                        }
                        default:
                            break;
                    }
                });
//        //签名逻辑
//        stringBuilder.append(DigestUtils.md2Hex(stringBuilder.toString()));
//        String param = stringBuilder.toString();
//        long begin = System.currentTimeMillis();
//        //发请求
//        String result = Request.Post("http://localhost:45678/reflection" + bankAPI.url())
//                .bodyString(param, ContentType.APPLICATION_JSON)
//                .execute().returnContent().asString();
//        log.info("调用银行API {} url:{} 参数:{} 耗时:{}ms", bankAPI.desc(), bankAPI.url(), param, System.currentTimeMillis() - begin);
        return "result";
    }


    // 反射给予了我们在不知晓类结构的时候，按照固定的逻辑处理类的成员；
    // 而注解给了我们为这些成员补充元数据的能力，使得我们利用反射实现通用逻辑的时候，可以从外部获得更多我们关心的数据。
    //创建用户方法v2
    public static String createUserV2(String name, String identity, String mobile, int age) throws IOException {
        CreateUserAPI createUserAPI = new CreateUserAPI();
        createUserAPI.setName(name);
        createUserAPI.setIdentity(identity);
        createUserAPI.setAge(age);
        createUserAPI.setMobile(mobile);
        return remoteCall(createUserAPI);
    }

    //支付方法V2
    public static String payV2(long userId, BigDecimal amount) throws IOException {
        PayAPI payAPI = new PayAPI();
        payAPI.setUserId(userId);
        payAPI.setAmount(amount);
        return remoteCall(payAPI);
    }


}