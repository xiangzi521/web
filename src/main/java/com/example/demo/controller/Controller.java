package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** 返回json数据
 * Created by Administrator on 2019/11/27.
 */
@Api(tags = "User")
@RestController
@RequestMapping("/user")
@SuppressWarnings("all")
public class Controller {

    @Autowired
    UserService userService;

    @Autowired
    private RedisUtils redisUtils;

    private static final Logger log = LoggerFactory.getLogger(Controller.class);

    @RequestMapping(value = "/add",method = RequestMethod.PUT)
    @ApiOperation(value = "新增人员信息", notes = "主键自增")
    public int login(@RequestBody User user) {
        boolean set = redisUtils.set("name", "zhangsan");
        System.out.println("redisUtils.set(name, zhangsan) : " + set);
        return userService.add(user);
    }

    @ApiOperation("获取所有人员信息")
    @RequestMapping(value = "/getAllUser",method = RequestMethod.GET)
    public List<User> getAllUser() {
        log.info("获取所有人员信息...");
        Object bool = redisUtils.get("name");
        System.out.println("redisUtils.get(name) : " + bool);
        return userService.getAllUser();
    }

    @ApiOperation("获取人员信息")
    @RequestMapping(value = "/getUser",method = RequestMethod.GET)
    public User getUser(int id) {
        log.info("获取人员信息...");
        return userService.getUser(id);
    }

    @ApiOperation("修改人员信息")
    @RequestMapping(value = "/updateUser",method = RequestMethod.PUT)
    public String updateUser(@RequestBody User user) {
         userService.updateUser(user);
         return "success";
    }

    @ApiOperation("删除人员信息")
    @RequestMapping(value = "/deleteUser",method = RequestMethod.DELETE)
    public int deleteUser(int id){
        return userService.deleteUser(id);
    }

}
