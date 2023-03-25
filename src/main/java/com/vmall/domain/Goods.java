package com.vmall.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("_goods")
public class Goods {
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
