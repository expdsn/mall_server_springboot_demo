package com.vmall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vmall.domain.Order;
import com.vmall.domain.OrderRes;

import java.util.List;
import java.util.Map;

public interface IOrderService extends IService<Order> {
    public List<OrderRes> getAllOrder();

    public OrderRes getOrderById(Long id);
}
