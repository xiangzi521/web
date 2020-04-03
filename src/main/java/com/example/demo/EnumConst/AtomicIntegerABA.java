package com.example.demo.EnumConst;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Administrator on 2020/1/7.
 */
public class AtomicIntegerABA {

    private static AtomicInteger atomicInteger = new AtomicInteger(100);

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        executorService.execute(()->{
            System.out.println(Thread.currentThread().getName() + " - "+ atomicInteger.get());
            atomicInteger.compareAndSet(100,50);
            System.out.println(Thread.currentThread().getName() + " - "+ atomicInteger.get());
        });

        executorService.execute(()->{
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " - "+ atomicInteger.get());
            atomicInteger.compareAndSet(50,100);
            System.out.println(Thread.currentThread().getName() + " - "+ atomicInteger.get());
        });

        executorService.execute(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " - "+ atomicInteger.get());
            atomicInteger.compareAndSet(100,50);
            System.out.println(Thread.currentThread().getName() + " - "+ atomicInteger.get());
        });

        executorService.shutdown();

    }
}
