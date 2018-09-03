package com.ffcs.orderdinner.dao;

import com.ffcs.orderdinner.model.Order;

import java.util.List;

public interface OrderDao {

    Order get(int id);

    List<Order> findByUserId(int id);

    int insert(Order order);

    int update(Order order);

    int delete(int id);

    List<Order> findAll();

    List<Order> findByTime(String time);
}
