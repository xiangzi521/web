package com.example.demo.service.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2019/11/27.
 */
@Service
@SuppressWarnings("all")
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public int add(User user) {
        return userDao.insert(user) ;
    }

    @Override
    public List<User> getAllUser() {
        return userDao.getAllUser();
    }

    @Override
    public User getUser(int id) {
        return userDao.getUser(id);
    }

    @Override
    public void updateUser(User user) {
         userDao.updateUser(user);
    }

    @Override
    public int deleteUser(int id) {
         return userDao.deleteUser(id);
    }

    @Override
    public void login(int userName, int password) {
        User user = userDao.getUser(userName);
        if (user == null){
            System.out.println("user is null ");
            return;
        }

        if (password != user.getPassword()){
            System.out.println("请输入真确的密码");
            return;
        }
    }

    @Override
    public void say() {
        System.out.println("定时任务执行");
    }
}
