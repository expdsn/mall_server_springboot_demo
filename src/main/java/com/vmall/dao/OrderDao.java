package com.vmall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vmall.domain.Order;
import com.vmall.domain.OrderRes;
import com.vmall.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface OrderDao extends BaseMapper<Order> {
//    @Select("select a.id,_orderTime, b._uname,_uid,_gid, c._gname from _order a join _user b on a._uid=b.id join _goods c on a._gid=c.id")
//    List<Order> getAllOrder();
//    @Select("select a.id,_orderTime, b._uname, _uid,_gid,c._gname from _order a join _user b on a._uid=b.id join _goods c on a._gid=c.id where a.id=#{id}")
//    Order getOrderById(Long id);
}
