package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2019/11/27.
 */
@Api(tags = "User")
@RestController("/user")
public class Controller {

    @Autowired
    UserService userService;

    private static final Logger log = LoggerFactory.getLogger(Controller.class);

    @PutMapping("/add")
    @ApiOperation(value = "新增人员信息", notes = "主键自增")
    public int login(@RequestBody User user) {
        return userService.add(user);
    }

    @ApiOperation("获取所有人员信息")
    @GetMapping("/getAllUser")
    public List<User> getAllUser() {
        log.info("获取所有人员信息...");
        return userService.getAllUser();
    }

    @ApiOperation("修改人员信息")
    @PutMapping("/updateUser")
    public String updateUser(@RequestBody User user) {
         userService.updateUser(user);
         return "success";
    }

    @ApiOperation("删除人员信息")
    @DeleteMapping("/deleteUser")
    public int deleteUser(int id){
        return userService.deleteUser(id);
    }

}
