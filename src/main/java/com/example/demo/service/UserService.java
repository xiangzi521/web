package com.example.demo.service;

import com.example.demo.model.User;

import java.util.List;

/**
 * Created by Administrator on 2019/11/27.
 */
public interface UserService {

    int add(User user);

    List<User> getAllUser();

    void say();
}
