package com.vmall.filter;

import com.alibaba.fastjson.JSON;
import com.vmall.controller.utils.R;
import com.vmall.domain.LoginUser;
import com.vmall.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.management.RuntimeMBeanException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            filterChain.doFilter(request, response);

            return;
        }
        String id;
        try {
            id = jwtUtil.parseToken(token).getSubject();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("抛出一个token过期异常");
            throw new RuntimeException("token非法");
        }
        LoginUser loginUser;
        try {

            loginUser = JSON.parseObject(redisTemplate.opsForValue().get("login:" + id), LoginUser.class);
        }catch (RuntimeException e) {
            throw new RuntimeException("用户登录过期");
        }
        if (Objects.isNull(loginUser)) {
            System.out.println("抛出一个未登录异常");
            throw new RuntimeException("用户未登录");
        }
        //TODO 获取权限信息封装到AuthenticationToken中
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser, null, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
