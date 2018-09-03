package com.ffcs.orderdinner.dao;

import com.ffcs.orderdinner.model.OrderList;

import java.util.List;

public interface OrderListDao{
    OrderList get(int id);

    int insert(OrderList orderList);

    List<OrderList> findByOrderId(int orderId);

    int update(OrderList orderList);

    int delete(int id);

    int deleteByOrderId(int id);


}
