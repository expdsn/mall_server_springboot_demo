package com.vmall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vmall.controller.utils.R;
import com.vmall.domain.User;
import com.vmall.service.IUserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    IUserService userService;
    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public R getAll() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        List<User> userList = userService.list();
        if (userList != null)
            return new R(200, "ok", userList);
        else {
            return new R(201, "failed");
        }
    }
    @GetMapping("/{id}")
    public R getById(@PathVariable Long id) {
        User user = userService.getById(id);
        if (user != null) {
            return new R(200, "ok", user);
        }else {
            return new R(201, "failed");
        }
    }
    @PostMapping
    public R save(@RequestBody User user) {
        return getR(user);
    }
    @PutMapping
    public R update(@RequestBody User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode(user.getPasswd());
        user.setPasswd(encode);
        if (userService.updateById(user)) {
            return new R(200, "ok");
        }else {
            return new R(201, "failed");
        }
    }
    @DeleteMapping("/{id}")
    public R delete(@PathVariable Long id) {
        if (userService.removeById(id)) {
            return new R(200, "ok");
        }else {
            return new R(201, "failed");
        }
    }
    @PostMapping("/register")
    public R register(@RequestBody User user) {
        return getR(user);
    }

    private R getR(@RequestBody User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode(user.getPasswd());
        user.setPasswd(encode);
        if (userService.save(user)) {
            return new R(200, "ok");
        }else {
            return new R(201, "failed");

        }
    }
}
