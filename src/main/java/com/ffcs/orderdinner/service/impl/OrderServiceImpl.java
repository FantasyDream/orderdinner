package com.ffcs.orderdinner.service.impl;

import com.ffcs.orderdinner.dao.OrderDao;
import com.ffcs.orderdinner.dao.OrderListDao;
import com.ffcs.orderdinner.model.Order;
import com.ffcs.orderdinner.model.OrderList;
import com.ffcs.orderdinner.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderListDao orderListDao;

    @Autowired
    OrderDao orderDao;


    @Override
    public Order get(int id) {
        return orderDao.get(id);
    }

    @Override
    public List<Order> findByUserId(int id) {
        return orderDao.findByUserId(id);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED,rollbackForClassName="Exception")
    public boolean insert(Order order) {
        try{
            orderDao.insert(order);
            List<OrderList> orderLists = order.getOrderLists();
            for (OrderList orderList:orderLists){
                orderList.setOrderId(order.getId());
                orderListDao.insert(orderList);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Order order) {
        if(orderDao.update(order)==0){
            return false;
        }else {
            return true;
        }
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED,rollbackForClassName="Exception")
    public boolean delete(int id) {
        try{
            orderListDao.deleteByOrderId(id);
            orderDao.delete(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Order> findAll() {
        return orderDao.findAll();
    }

    @Override
    public List<Order> findByTime(String time) {
        return orderDao.findByTime(time);
    }

    @Override
    public boolean deleteByOrderId(int id) {
        if(orderListDao.deleteByOrderId(id)!=0){
            return true;
        }else {
            return false;
        }
    }
}
