package com.vmall.service.imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vmall.dao.OrderDao;
import com.vmall.domain.Order;
import com.vmall.domain.OrderRes;
import com.vmall.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImp extends ServiceImpl<OrderDao, Order> implements IOrderService {
    private final OrderDao orderDao;
    @Autowired
    public OrderServiceImp(OrderDao orderDao){
        this.orderDao = orderDao;
    }
    @Override
    public List<OrderRes> getAllOrder() {
        return orderDao.getAllOrder();
    }

    @Override
    public OrderRes getOrderById(Long id) {
        return  orderDao.getOrderById(id);
    }
}
