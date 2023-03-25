package com.vmall.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

@Data
@TableName("_goods")
public class Goods {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    @TableField("_gName")
    private String gName;
    @TableField("_price")
    private String price;
    @TableField("_desc")
    private String desc;
    @TableField("_type")
    private int type;
    @TableField("_pic")
    private String pic;
}
