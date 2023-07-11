package com.ershouserver.ershou_server.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("good")
public class Good {
    @TableId
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;//id标识
    private String name;//名字
    private String description;//描述
    private Integer owner;//货品主人，对应user的id
    private String type;//货品类型
    private String state;//当前状态
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Double price;//价格
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Double oldprice;//原价
    private String media;//图片
    private Date addtime;//添加日期
    private String contactway;//联系方式，初期由于user无法完成了，先凑合着
    private String address;//地址
}
