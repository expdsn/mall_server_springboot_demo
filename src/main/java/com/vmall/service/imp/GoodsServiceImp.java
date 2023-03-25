package com.vmall.service.imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vmall.dao.GoodsDao;
import com.vmall.domain.Goods;
import com.vmall.service.IGoodsService;
import org.springframework.stereotype.Service;

@Service
public class GoodsServiceImp extends ServiceImpl<GoodsDao, Goods> implements IGoodsService {
}
