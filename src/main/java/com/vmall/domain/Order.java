package com.vmall.domain;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;

@Data
@TableName("_order")
public class Order {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)
    @TableField("_gId")
    private Long gId;
    @JsonSerialize(using = ToStringSerializer.class)
    @TableField("_uId")
    private Long uId;
    @TableField("_orderTime")
    private Date orderTime;
}
