package com.ffcs.orderdinner.controller;

import com.alibaba.fastjson.JSONObject;
import com.ffcs.orderdinner.model.Order;
import com.ffcs.orderdinner.service.OrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/list/{id}/{start}", method = RequestMethod.GET)
    public String findByUserId(@PathVariable(value = "start") int start, @RequestParam(value = "length",defaultValue = "5") int length,@PathVariable int id){
        PageHelper.startPage(start, length);
        List<Order> list = orderService.findByUserId(id);
        PageInfo<Order> pageInfo = new PageInfo<>(list);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pageInfo",JSONObject.toJSON(pageInfo));
        return jsonObject.toJSONString();
    }

    @RequestMapping(value = "/list/{id}", method = RequestMethod.GET)
    public String findByUserId(@PathVariable int id){
        List<Order> list = orderService.findByUserId(id);
        return JSONObject.toJSONString(list);
    }

    /*@RequestMapping(value = "/list/{start}", method = RequestMethod.GET)
    public String findAll(@PathVariable(value = "start") int start, @RequestParam(value = "length",defaultValue = "5") int length){
        PageHelper.startPage(start, length);
        List<Order> list = orderService.findAll();
        PageInfo<Order> pageInfo = new PageInfo<>(list);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pageInfo",JSONObject.toJSON(pageInfo));
        return jsonObject.toJSONString();
    }*/

    @RequestMapping(value = "/listbytime/{time}/{start}", method = RequestMethod.GET)
    public String findByTime(@PathVariable String time,@PathVariable(value = "start") int start, @RequestParam(value = "length",defaultValue = "5") int length){
        PageHelper.startPage(start, length);
        List<Order> list = orderService.findByTime(time);
        PageInfo<Order> pageInfo = new PageInfo<>(list);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pageInfo",JSONObject.toJSON(pageInfo));
        return jsonObject.toJSONString();
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String get(@PathVariable int id){
        Order order = orderService.get(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("order",JSONObject.toJSON(order));
        return jsonObject.toJSONString();
    }


    @RequestMapping(value = "/" , method = RequestMethod.POST)
    public String insert (@RequestBody Order order){
        JSONObject jsonObject = new JSONObject();
        if (orderService.insert(order)){
            jsonObject.put("msg",JSONObject.toJSON(true));
        } else {
            jsonObject.put("msg",JSONObject.toJSON(false));
        }
        return jsonObject.toJSONString();
    }


    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public String delete(@PathVariable int id){
        JSONObject jsonObject = new JSONObject();
        if (orderService.delete(id)){
            jsonObject.put("msg",JSONObject.toJSON(true));
        } else {
            jsonObject.put("msg",JSONObject.toJSON(false));
        }
        return jsonObject.toJSONString();
    }

    @RequestMapping(value = "/{id}/{status}",method = RequestMethod.PUT)
    public String update(@PathVariable int id,@PathVariable int status){
        Order order = new Order();
        order.setId(id);
        switch (status){
            case 0:
                order.setStatus("已取消");
                break;
            case 1:
                order.setStatus("待支付");
                break;
            case 2:
                order.setStatus("已支付");
                break;
                default:
                    order.setStatus("待支付");
        }
        JSONObject jsonObject = new JSONObject();
        if(orderService.update(order)){
            jsonObject.put("msg",JSONObject.toJSON(true));
        } else {
            jsonObject.put("msg",JSONObject.toJSON(false));
        }
        return jsonObject.toJSONString();
    }



}
