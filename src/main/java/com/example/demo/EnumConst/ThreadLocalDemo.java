package com.example.demo.EnumConst;

import java.util.ArrayList;
import java.util.List;

/** ThreadLocal 应用举例
 * Created by Administrator on 2020/1/7.
 */
public class ThreadLocalDemo {
    private static ThreadLocal<List<String>> threadLocal = new ThreadLocal<>();

    public void setThreadLocal(List<String> value) {
        threadLocal.set(value);
    }

    public void getThreadLocal() {
        threadLocal.get().forEach(name-> System.out.println(Thread.currentThread().getName()+" - "+name));
    }

    public static void main(String[] args) {
       final ThreadLocalDemo demo = new ThreadLocalDemo();

       new Thread(()->{
           List<String> list = new ArrayList<>();
           list.add("1");
           list.add("2");
           list.add("3");
           demo.setThreadLocal(list);
           demo.getThreadLocal();
       },"t1").start();

        new Thread(()->{
            List<String> list = new ArrayList<>();
            list.add("a");
            list.add("b");
            list.add("c");
            demo.setThreadLocal(list);
            demo.getThreadLocal();
        },"t2").start();

    }

}
