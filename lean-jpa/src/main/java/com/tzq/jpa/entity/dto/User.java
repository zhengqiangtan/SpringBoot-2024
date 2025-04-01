package com.tzq.jpa.entity.dto;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description: 用户类
 * @BelongsProject: Jmccms
 * @BelongsPackage: com.jmccms.entity
 * @Author: ChenYongJia
 * @CreateTime: 2019-05-02 15:32
 * @Email chen87647213@163.com
 */
@Getter
@Setter
@AllArgsConstructor // 自动所有参数的构造方法方法
@NoArgsConstructor // 自动无参的构造方法方法
@Builder
@Entity
@Table(name = "jpa_user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @OrderBy
    @Column(columnDefinition = "bigint(19) unsigned  COMMENT '用户id'")
    private Long userId;

    @Column(columnDefinition = "varchar(64) NOT NULL COMMENT '用户名称'  ")
    private String userName;

//    @Column(columnDefinition = "varchar(100) NOT NULL COMMENT '用户密码'  ")
//    private String userPassWord;
//
//    @Column(columnDefinition = "datetime COMMENT '用户创建时间' ")
//    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")//日期格式化为中国的时区 东8区
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")//接受::字符串日期需要格式化为日期类型
//    private Date userCreateTime;
//
//    @Column(columnDefinition = "datetime COMMENT '用户最后一次登录时间' ")
//    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")//日期格式化为中国的时区 东8区
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")//接受::字符串日期需要格式化为日期类型
//    private Date userLastLoginTime;
//
//    @Column(columnDefinition = "timestamp COMMENT '最后一次修改时间'", nullable = false, updatable = false, insertable = false)
//    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")//日期格式化为中国的时区 东8区
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")//接受::字符串日期需要格式化为日期类型
//    private Timestamp userLastUpdateTime;
//
//    @Column(columnDefinition = "varchar(64) NOT NULL COMMENT '创建人'  ")
//    private String createBy;
//
//    @Column(columnDefinition = "varchar(64) COMMENT '修改人'  ")
//    private String updateBy;



}