package com.example.demo.controller;

/**
 * Created by Administrator on 2020/4/18.
 */
/*@Api(tags = "Shiro")
@org.springframework.stereotype.Controller
@RequestMapping("/shiro")
public class ShiroController {

    @RequestMapping(value = "/defaultLogin",method = RequestMethod.GET)
    @ResponseBody
    public String defaultLogin(){
        return "首页";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestParam("username") String username,@RequestParam("password") String password){
        // 获取一个subject
        Subject subject = SecurityUtils.getSubject();
        // 获取令牌
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
        }catch (Exception e){
            e.printStackTrace();
        }

        if (subject.isAuthenticated()){
            return "登录成功";
        }else {
            token.clear();
            return "登录失败";
        }
    }

    @RequestMapping("/showUser")
    @ResponseBody
    @RequiresPermissions("user:list")
    public String showUser(){
        return "学生信息";
    }

}*/
