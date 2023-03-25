package com.vmall.service;

import com.vmall.domain.Order;
import com.vmall.domain.OrderRes;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.omg.CORBA.IRObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class OrderTest {
    private final IOrderService orderService;
    @Autowired
    public OrderTest(IOrderService orderService) {
        this.orderService = orderService;
    }
    @Test
    public void  testGetAll() {
        List<Order> allOrder = orderService.list();
//        JSONArray jsonArray = new JSONArray();
//        for (Map<String, Object> entry : allOrder) {
//            JSONObject jsonObject = new JSONObject(entry);
//            jsonArray.put(jsonObject);
//        }

        System.out.println(allOrder);
//        System.out.println(new JSONObject(allOrder.get(0)));
    }
    @Test
    public void getOrderById() {

        Order orderById = orderService.getById(1638467774568169474L);
        System.out.println(orderById);
    }
    @Test
    public void addOrder() {
        Order order = new Order();
        order.setOrderTime(new Date());
        order.setUId(1639294018448744449L);
        order.setGId(1637814503499350018L);
        orderService.save(order);
    }
}
