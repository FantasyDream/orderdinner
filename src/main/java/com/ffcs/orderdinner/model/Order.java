package com.ffcs.orderdinner.model;


import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class Order implements Serializable{

    private int id;
    private int userId;
    private double totalPrice;
    private String status;
    private java.sql.Timestamp createTime;
    private List<OrderList> orderLists;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public List<OrderList> getOrderLists() {
        return orderLists;
    }

    public void setOrderLists(List<OrderList> orderLists) {
        this.orderLists = orderLists;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }


    public java.sql.Timestamp getCreateTime() {
        return (Timestamp) createTime.clone();
    }

    public void setCreateTime(String time) {
        this.createTime = Timestamp.valueOf(time);
    }

}
