package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/** 返回视图
 * Created by Administrator on 2019/12/11.
 */
@Api(tags = "thymeleaf-demo")
@CrossOrigin //跨域
@org.springframework.stereotype.Controller
@RequestMapping("demo")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public  String user(Model model){
//        User user = userService.getUser(1000);
        List<User> users = userService.getAllUser();
        model.addAttribute("users",users);

        return "user/user";
    }

    @RequestMapping(value = "/show",method = RequestMethod.GET)
    public @ResponseBody String showJsp(){
        return "success";
    }
}
