package com.vmall.controller;

import com.vmall.controller.utils.R;
import com.vmall.domain.Order;
import com.vmall.domain.OrderRes;
import com.vmall.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/order")
public class OrderController {
    IOrderService orderService;
    @Autowired
    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }
    @GetMapping
    public R getAll() {
        List<Order> orderList = orderService.list();

        if (orderList != null)
            return new R(200, "ok", orderList);
        else {
            return new R(201, "failed");
        }
    }
    @GetMapping("/{id}")
    public R getById(@PathVariable Long id) {
        JSONObject jsonObject = null;
        Order order = orderService.getById(id);
        if (Objects.isNull(order)) {
            return new R(201, "can not find");
        }
        return new R(200, "ok", order);
    }

    @PostMapping
    public R save(@RequestBody Order order) {
        if (orderService.save(order)) {
            return new R(200, "ok");
        }else {
            return new R(201, "failed");

        }
    }
    @PutMapping
    public R update(@RequestBody Order order) {
        if (orderService.updateById(order)) {
            return new R(200, "ok");
        }else {
            return new R(201, "failed");
        }
    }
    @DeleteMapping("/{id}")
    public R delete(@PathVariable Long id) {
        if (orderService.removeById(id)) {
            return new R(200, "ok");
        }else {
            return new R(201, "failed");
        }
    }
}
