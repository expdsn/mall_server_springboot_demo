package com.vmall.controller;

import com.vmall.controller.utils.R;
import com.vmall.domain.LoginUser;
import com.vmall.domain.User;
import com.vmall.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/user")
public class LoginController {
    private final LoginService loginService;
    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public R login(@RequestBody User u) {
        return loginService.login(u);
    }

    @GetMapping("/logout")
    public R logout() {
        return loginService.logout();
    }


}
