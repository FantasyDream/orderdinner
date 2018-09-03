package com.ffcs.orderdinner.service.impl;

import com.ffcs.orderdinner.dao.UserDao;
import com.ffcs.orderdinner.model.User;
import com.ffcs.orderdinner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

/**
 * @author FantasyDream
 */
@Service
@WebService
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public boolean insert(User user) {
        if (userDao.insert(user)==0){
            return false;
        } else {
            return true;
        }
    }

    @Override
    public User get(User user) {
        return userDao.get(user);
    }

    @Override
    public User findByPhoneNumber(String phoneNumber) {
        return userDao.findByPhoneNumber(phoneNumber);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public List<User> findByNameLike(String userName) {
        return userDao.findByUserNameLike(userName);
    }

    @Override
    public boolean delete(int id) {
        if(userDao.delete(id)==0){
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean register(User user) {
        if (exist(user.getUserName())) {
            return false;
        }
        if (insert(user)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean login(User user) {
        if (get(user) != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean exist(String phoneNumber) {
        if (findByPhoneNumber(phoneNumber) != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean update(User user) {
        if (userDao.update(user) == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public User getById(int id){
        return userDao.getById(id);
    }

}
