package com.ffcs.orderdinner.service;

import com.ffcs.orderdinner.model.Commodity;

import java.util.List;

public interface CommodityService {

    List<Commodity> findAll();

    List<Commodity> findByNameLike(String name);

    boolean insert(Commodity commodity);

    boolean delete(int id);

    Commodity get(int id);

    boolean update(Commodity commodity);

    List<Commodity> findByPriceBetween(double low, double high);

    List<Commodity> findByNameLikeAndPriceBetween(String name,double low,double high);
}
