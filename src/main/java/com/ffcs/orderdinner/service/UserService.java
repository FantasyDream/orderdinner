package com.ffcs.orderdinner.service;

import com.ffcs.orderdinner.model.User;

import javax.jws.WebService;
import java.util.List;

/**
 * @author FantasyDream
 */
public interface UserService {

    /**
     * 向数据库添加一条user信息
     * @param user
     * @return int
     */
    boolean insert(User user);

    /**
     * 根据传入的User中的信息从数据库中得到User实例
     * @param user
     * @return
     */
    User get(User user);

    /**
     * 通过传入的电话号码从数据库中找到相应的user信息并返回User实例
     * @param phoneNumber
     * @return User
     */
    User findByPhoneNumber(String phoneNumber);

    /**
     * 查询所有用户
     * @return List
     */
    List<User> findAll();

    List<User> findByNameLike(String userName);

    boolean delete(int id);

    boolean register(User user);

    boolean login(User user);

    boolean exist(String phoneNumber);

    boolean update(User user);

    User getById(int id);

}
