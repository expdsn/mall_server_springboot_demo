package com.vmall.controller.utils;


import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.security.auth.login.LoginException;

@RestControllerAdvice
public class ProjectExceptionAdvice {
    @ExceptionHandler
    public R doException(Exception e) {
        e.printStackTrace();

        return new R(250, "服务器故障！",null);
    }

}
