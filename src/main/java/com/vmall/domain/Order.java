package com.vmall.domain;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("_order")
public class Order {
    private Long id;
    @TableField("_gId")
    private Long gId;
    @TableField("_uId")
    private Long uId;
    @TableField("_orderTime")
    private Date orderTime;
}
