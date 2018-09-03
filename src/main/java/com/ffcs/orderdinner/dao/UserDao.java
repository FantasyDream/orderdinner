package com.ffcs.orderdinner.dao;

import com.ffcs.orderdinner.model.User;

import java.util.List;

public interface UserDao {

    int insert(User user);

    User get(User user);

    User getById(int id);

    User findByPhoneNumber(String phoneNumber);

    List<User> findByUserNameLike(String userName);

    List<User> findAll();

    int delete(int id);

    int update(User user);

}
