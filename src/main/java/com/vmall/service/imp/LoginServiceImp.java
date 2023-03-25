package com.vmall.service.imp;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vmall.controller.utils.R;
import com.vmall.dao.UserDao;
import com.vmall.domain.LoginUser;
import com.vmall.domain.User;
import com.vmall.service.IUserService;
import com.vmall.service.LoginService;
import com.vmall.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class LoginServiceImp  implements LoginService {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private IUserService userService;
    private static final long serialVersionUID = -1;
    @Override
    public R login(User u) {
        jwtUtil.createToken(u.getAccount());
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(u.getAccount(), u.getPasswd());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("登录失败");
        }
        LoginUser loginUser= (LoginUser)authenticate.getPrincipal();
        String id = loginUser.getUser().getId().toString();
        String token = jwtUtil.createToken(id);
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("uuid", id);
        redisTemplate.opsForValue().set("login:"+id, JSON.toJSONString(loginUser));
        return new R(200, "登录成功", map);

    }

    @Override
    public R logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userid = loginUser.getUser().getId();
        redisTemplate.delete("login:"+userid);
        return new R(200, "ok");
    }


}
