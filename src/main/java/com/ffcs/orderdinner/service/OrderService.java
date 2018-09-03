package com.ffcs.orderdinner.service;

import com.ffcs.orderdinner.model.Order;

import java.util.List;

public interface OrderService {
    Order get(int id);

    List<Order> findByUserId(int id);

    boolean insert(Order order);

    boolean update(Order order);

    boolean delete(int id);

    List<Order> findAll();

    List<Order> findByTime(String time);

    boolean deleteByOrderId(int id);
}
