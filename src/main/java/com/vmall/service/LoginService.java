package com.vmall.service;

import com.vmall.controller.utils.R;
import com.vmall.domain.LoginUser;
import com.vmall.domain.User;

public interface LoginService {

    public R login(User user);

    public R logout();
}
