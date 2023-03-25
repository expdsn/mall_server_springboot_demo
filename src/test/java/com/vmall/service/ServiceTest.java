package com.vmall.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vmall.dao.UserDao;
import com.vmall.domain.Order;
import com.vmall.domain.User;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ServiceTest {
    IUserService userService;
    UserDao userDao;
    private final IOrderService orderService;
    @Autowired
    public ServiceTest(IUserService userService, UserDao userDao, IOrderService orderService) {
        this.userService = userService;
        this.userDao = userDao;
        this.orderService = orderService;
    }

    @Test
    public void userServiceTest() {
//        User user = new User();
//        user.set_account("123");
//        user.set_passwd("58662");
//        user.set_avatar("null");
//        user.set_role(0);
//        user.set_uname("小黑子");
//        userDao.insert(user);
        System.out.println("保存成功");
    }


    @Test
    public void testOrder() {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        System.out.println(orderService.getAllOrder());
    }
    @Test
    public void userGetAll() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "_uname");
        System.out.println(userService.list(queryWrapper));
    }
}
