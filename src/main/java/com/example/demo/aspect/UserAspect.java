package com.example.demo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2019/11/30.
 */
@Aspect
@Component
public class UserAspect {

    /**
     * 切面表达式
     *  execution():表达式主题
     *  第一个*号:表示返回值类型
     *  com.example.demo.controller 表示包及子孙包所有类的方法
     *  第二个*号 表示类名，*号表示所有的类
     *  *(..)表示*号内所有的方法，括号里面表示方法的参数，..表示任何参数
     */
    @Before(value = "execution(* com.example.demo.controller..*.*(..))")
    public void before(){
        System.out.println("UserAspect 执行了 。。。");
    }
}
