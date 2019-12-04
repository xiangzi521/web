package com.example.demo.ScheduledTasks;

import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2019/11/30.
 */
@Component
public class ScheduledTasks {

    @Autowired
    UserService userService;

    /**
     * 每秒钟执行一次
     */
    @Scheduled(cron = "1  * * * * ?")
    public void taskSay(){
        userService.say();
    }
}
