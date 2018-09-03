package com.ffcs.orderdinner.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ffcs.orderdinner.model.Commodity;
import com.ffcs.orderdinner.service.CommodityService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("commodity")
public class CommodityController {

    @Autowired
    CommodityService commodityService;

    @ResponseBody
    @RequestMapping(value = "/list/{start}", method = RequestMethod.GET)
    public String findAll(@PathVariable(value = "start") int start,@RequestParam(value = "length",defaultValue = "5") int length){
        PageHelper.startPage(start, length);
        List<Commodity> list = commodityService.findAll();
        PageInfo<Commodity> pageInfo = new PageInfo<>(list);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pageInfo",JSONObject.toJSON(pageInfo));
        return jsonObject.toJSONString();
    }

    @ResponseBody
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String findAll(){
        List<Commodity> list = commodityService.findAll();
        return JSONObject.toJSONString(list);
    }


    @ResponseBody
    @RequestMapping(value = "/list/{start}/{name}",method = RequestMethod.GET)
    public String findByNameLike(@PathVariable("start") int start,@PathVariable("name") String name,
                                 @RequestParam(value = "length",defaultValue = "5") int length){
        PageHelper.startPage(start,length);
        List<Commodity> list = commodityService.findByNameLike(name);
        PageInfo<Commodity> pageInfo = new PageInfo<>(list);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pageInfo",JSONObject.toJSON(pageInfo));
        return jsonObject.toJSONString();
    }

    @ResponseBody
    @RequestMapping(value = "/list/{start}/{low}/{high}",method = RequestMethod.GET)
    public String findByPriceBetween(@PathVariable int start,@PathVariable double low,@PathVariable double high,
                                     @RequestParam(value = "length",defaultValue = "5") int length){
        PageHelper.startPage(start,length);
        List<Commodity> list = commodityService.findByPriceBetween(low,high);
        PageInfo<Commodity> pageInfo = new PageInfo<>(list);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pageInfo",JSONObject.toJSON(pageInfo));
        return jsonObject.toJSONString();
    }

    @ResponseBody
    @RequestMapping(value = "/list/{start}/{low}/{high}/{name}",method = RequestMethod.GET)
    public String findByNameLikeAndPriceBetween(@PathVariable int start,@PathVariable("name") String name,@PathVariable double low,
                                                @PathVariable double high, @RequestParam(value = "length",defaultValue = "5") int length){
        PageHelper.startPage(start,length);
        List<Commodity> list = commodityService.findByNameLikeAndPriceBetween(name,low,high);
        PageInfo<Commodity> pageInfo = new PageInfo<>(list);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pageInfo",JSONObject.toJSON(pageInfo));
        return jsonObject.toJSONString();
    }

    @ResponseBody
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public String insertCommodity(@RequestBody Commodity commodity){
        JSONObject jsonObject = new JSONObject();
        if(commodityService.insert(commodity)){
            jsonObject.put("msg",JSONObject.toJSON(true));
        } else {
            jsonObject.put("msg",JSONObject.toJSON(false));
        }
        return jsonObject.toJSONString();
    }

    @ResponseBody
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public String deleteCommodity(@PathVariable int id){
        JSONObject jsonObject = new JSONObject();
        if(commodityService.delete(id)){
            jsonObject.put("msg",JSONObject.toJSON(true));
        } else {
            jsonObject.put("msg",JSONObject.toJSON(false));
        }
        return jsonObject.toJSONString();
    }

    @ResponseBody
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public String getCommodity(@PathVariable int id){
        JSONObject jsonObject = new JSONObject();
        Commodity commodity=commodityService.get(id);
        if(commodity!=null){
            jsonObject.put("msg",JSONObject.toJSON(commodity));
        } else {
            jsonObject.put("msg",JSONObject.toJSON(false));
        }
        return jsonObject.toJSONString();
    }

    @ResponseBody
    @RequestMapping(value = "/",method = RequestMethod.PUT)
    public String update(@RequestBody Commodity commodity){
        JSONObject jsonObject = new JSONObject();
        if(commodityService.update(commodity)){
            jsonObject.put("msg",JSONObject.toJSON(true));
        } else {
            jsonObject.put("msg",JSONObject.toJSON(false));
        }
        return jsonObject.toJSONString();
    }

    @ResponseBody
    @RequestMapping(value = "/insertMany",method = RequestMethod.POST)
    public String insertMany(@RequestBody List<Commodity> commodities){
        JSONObject jsonObject = new JSONObject();
        for (Commodity commodity:commodities){
            if(commodityService.insert(commodity)==false){
                jsonObject.put("msg",JSONObject.toJSON(false));
            }
        }
        jsonObject.put("msg",JSONObject.toJSON(true));
        return jsonObject.toJSONString();
    }
}
