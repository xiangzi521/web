package com.example.demo.EnumConst;

import java.util.concurrent.atomic.AtomicInteger;

/** 枚举用法举例
 * Created by Administrator on 2020/1/6.
 */
public enum  ExceptionEnum {

    RED("红色",1),GREEN("绿色",2), YELLO("黄色",3);

    private String msg;
    private int index;


    ExceptionEnum(String msg, int index) {
        this.msg = msg;
        this.index = index;
    }

    public static String getMsg(int index){
        for (ExceptionEnum exceptionEnum : ExceptionEnum.values()) {
            if (exceptionEnum.getIndex() == index){
                return exceptionEnum.getMsg();
            }
        }
        return null;
    }

    public String getMsg() {
        return msg;
    }

    public void setName(String name) {
        this.msg = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.compareAndSet(0,1);
        atomicInteger.compareAndSet(2,1);
        atomicInteger.compareAndSet(1,3);
        atomicInteger.compareAndSet(2,4);
        System.out.println(atomicInteger.get());
    }
}
