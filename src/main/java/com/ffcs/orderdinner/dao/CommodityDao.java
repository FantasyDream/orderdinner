package com.ffcs.orderdinner.dao;

import com.ffcs.orderdinner.model.Commodity;

import java.util.List;

/**
 * @author FantasyDream
 */
public interface CommodityDao {

    List<Commodity> findAll();

    List<Commodity> findByNameLike(String name);

    int insert(Commodity commodity);

    int delete(int id);

    Commodity get(int id);

    int update(Commodity commodity);

    List<Commodity> findByPriceBetween(double low, double high);

    List<Commodity> findByNameLikeAndPriceBetween(String name,double low,double high);
}
