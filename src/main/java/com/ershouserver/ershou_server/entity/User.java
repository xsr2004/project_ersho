package com.ershouserver.ershou_server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@TableName("user")
public class User {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;//id标识

    private String name;//名字
    private String login;//登录账号
    private String password;//登录密码
    private String address;//地址
    private String contactway;//联系方式

}
