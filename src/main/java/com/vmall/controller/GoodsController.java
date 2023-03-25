package com.vmall.controller;

import com.vmall.controller.utils.R;
import com.vmall.domain.Goods;
import com.vmall.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goods")
public class GoodsController {
    IGoodsService goodsService;
    @Autowired
    public GoodsController(IGoodsService goodsService) {
        this.goodsService = goodsService;
    }
    @GetMapping
    public R getAll() {
        List<Goods> goodList = goodsService.list();
        if (goodList != null)
            return new R(200, "ok", goodList);
        else {
            return new R(201, "failed");
        }
    }
    @GetMapping("/{id}")
    public R getById(@PathVariable Long id) {
        Goods goods = goodsService.getById(id);
        if (goods != null) {
            return new R(200, "ok", goods);
        }else {
            return new R(201, "failed");
        }
    }
    @PostMapping
    public R save(@RequestBody Goods goods) {
        if (goodsService.save(goods)) {
            return new R(200, "ok");
        }else {
            return new R(201, "failed");

        }
    }
    @PutMapping
    public R update(@RequestBody Goods goods) {
        if (goodsService.updateById(goods)) {
            return new R(200, "ok");
        }else {
            return new R(201, "failed");
        }
    }
    @DeleteMapping("/{id}")
    public R delete(@PathVariable Long id) {
        if (goodsService.removeById(id)) {
            return new R(200, "ok");
        }else {
            return new R(201, "failed");
        }
    }
}
