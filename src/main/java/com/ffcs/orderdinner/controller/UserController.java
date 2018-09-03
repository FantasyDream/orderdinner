package com.ffcs.orderdinner.controller;

import com.alibaba.fastjson.JSONObject;
import com.ffcs.orderdinner.model.Order;
import com.ffcs.orderdinner.model.User;
import com.ffcs.orderdinner.service.UserService;
import com.ffcs.orderdinner.utils.AppMD5Util;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * @author FantasyDream
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody User user, HttpSession session){
        user.setPassword(AppMD5Util.MD5(user.getPassword()));
        JSONObject jsonObject = new JSONObject();
        if (userService.login(user)){
            session.setAttribute("user",userService.get(user));
            jsonObject.put("msg",JSONObject.toJSON(true));
            jsonObject.put("id",JSONObject.toJSON(userService.get(user).getId()));

        } else {
            jsonObject.put("msg",JSONObject.toJSON(false));
        }
        return jsonObject.toJSONString();
    }

    @ResponseBody
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String register(@RequestBody User user){
        user.setPassword(AppMD5Util.MD5(user.getPassword()));
        JSONObject jsonObject = new JSONObject();
        if (userService.register(user)){
            jsonObject.put("msg",JSONObject.toJSON(true));
            return jsonObject.toJSONString();
        } else {
            jsonObject.put("msg",JSONObject.toJSON(false));
            return jsonObject.toJSONString();
        }
    }

    @ResponseBody
    @RequestMapping(value="/exist/{phoneNumber}",method= RequestMethod.GET)
    public String exist(@PathVariable String phoneNumber){
        JSONObject jsonObject = new JSONObject();
        if (userService.exist(phoneNumber)){
            jsonObject.put("msg",JSONObject.toJSON(true));
            return jsonObject.toJSONString();
        } else {
            jsonObject.put("msg",JSONObject.toJSON(false));
            return jsonObject.toJSONString();
        }
    }

    @ResponseBody
    @RequestMapping(value = "/",method = RequestMethod.PUT)
    public String update(@RequestBody User user){
        JSONObject jsonObject = new JSONObject();
        if (userService.update(user)){
            jsonObject.put("msg",JSONObject.toJSON(true));
            return jsonObject.toJSONString();
        } else {
            jsonObject.put("msg",JSONObject.toJSON(false));
            return jsonObject.toJSONString();
        }
    }

    @ResponseBody
    @RequestMapping(value="/{id}",method= RequestMethod.DELETE)
    public String delete(@PathVariable int id){
        JSONObject jsonObject = new JSONObject();
        if (userService.delete(id)){
            jsonObject.put("msg",JSONObject.toJSON(true));
            return jsonObject.toJSONString();
        } else {
            jsonObject.put("msg",JSONObject.toJSON(false));
            return jsonObject.toJSONString();
        }
    }

    @ResponseBody
    @RequestMapping(value="/{id}",method= RequestMethod.GET)
    public String get(@PathVariable int id){
        User user = new User();
        user.setId(id);
        user = userService.get(user);
        if (user!=null){
            return JSONObject.toJSONString(user);
        } else {
            return JSONObject.toJSONString(null);
        }
    }

    @ResponseBody
    @RequestMapping(value="/list/{start}/{userName}",method= RequestMethod.GET)
    public String findByUserNameLike(@PathVariable(value = "start") int start,@PathVariable String userName,@RequestParam(value = "length",defaultValue = "10") int length){
        PageHelper.startPage(start,length);
        List<User> list = userService.findByNameLike(userName);
        PageInfo<User> pageInfo = new PageInfo<>(list);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pageInfo",JSONObject.toJSON(pageInfo));
        return JSONObject.toJSONString(pageInfo);
    }

    @ResponseBody
    @RequestMapping(value="/list",method= RequestMethod.GET)
    public String findAll(){
        List<User> userList = userService.findAll();
        return JSONObject.toJSONString(userList);
    }

    @ResponseBody
    @RequestMapping(value = "/list/{start}", method = RequestMethod.GET)
    public String findAll(@PathVariable(value = "start") int start, @RequestParam(value = "length",defaultValue = "10") int length){
        PageHelper.startPage(start,length);
        List<User> list = userService.findAll();
        PageInfo<User> pageInfo = new PageInfo<>(list);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pageInfo",JSONObject.toJSON(pageInfo));
        return JSONObject.toJSONString(pageInfo);
    }
}
