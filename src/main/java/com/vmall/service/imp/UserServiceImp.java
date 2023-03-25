package com.vmall.service.imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vmall.dao.UserDao;
import com.vmall.domain.User;
import com.vmall.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp extends ServiceImpl<UserDao, User> implements IUserService {
}
