package com.ffcs.orderdinner.service.impl;

import com.ffcs.orderdinner.dao.CommodityDao;
import com.ffcs.orderdinner.model.Commodity;
import com.ffcs.orderdinner.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommodityServiceImpl implements CommodityService {

    @Autowired
    CommodityDao commodityDao;

    @Override
    public List<Commodity> findAll() {
        return commodityDao.findAll();
    }

    @Override
    public List<Commodity> findByNameLike(String name) {
        return commodityDao.findByNameLike(name);
    }

    @Override
    public boolean insert(Commodity commodity) {
        if (commodityDao.insert(commodity)==0){
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean delete(int id) {
        if (commodityDao.delete(id)==0){
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Commodity get(int id) {
        return commodityDao.get(id);
    }

    @Override
    public boolean update(Commodity commodity) {
        if (commodityDao.update(commodity)==0){
            return false;
        } else {
            return true;
        }
    }

    @Override
    public List<Commodity> findByPriceBetween(double low, double high) {
        return commodityDao.findByPriceBetween(low,high);
    }

    @Override
    public List<Commodity> findByNameLikeAndPriceBetween(String name, double low, double high){
        return commodityDao.findByNameLikeAndPriceBetween(name,low,high);
    }
}
