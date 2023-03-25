package com.vmall.controller.utils;

import lombok.Data;

@Data
public class R {
    private Object data;
    private int code;
    private String msg;

    public R() {

    }
    public R(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
    }

    public R(int code, String msg,Object data) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }
}
