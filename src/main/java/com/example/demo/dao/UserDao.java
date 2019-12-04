package com.example.demo.dao;

import com.example.demo.model.User;

import java.util.List;

/**
 * Created by Administrator on 2019/11/27.
 */
public interface UserDao {

    List<User> getAllUser();

    int insert(User user);
}
