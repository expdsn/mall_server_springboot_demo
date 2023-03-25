package com.vmall.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

@Data
@TableName("_user")
public class User {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    @TableField("_uname")
    private String uname;
    @TableField("_passwd")
    private String passwd;
    @TableField("_account")
    private String account;
    @TableField("_avatar")
    private String avatar;
    @TableField("_role")
    private int role;

}
