package com.vmall.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.vmall.dao.UserDao;
import com.vmall.domain.LoginUser;
import com.vmall.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserDetailServiceImp implements UserDetailsService {
    @Autowired
    private UserDao userDao;
    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getAccount, account);
        User user = userDao.selectOne(lambdaQueryWrapper);
        System.out.println(account);
        if (Objects.isNull(user)) {
            throw new RuntimeException("用户名或者密码错误");

        }
        //把数据封装成UserDetails
        return new LoginUser(user);
    }
}
