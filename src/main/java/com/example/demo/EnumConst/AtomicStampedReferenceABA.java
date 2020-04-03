package com.example.demo.EnumConst;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * Created by Administrator on 2020/1/7.
 */
public class AtomicStampedReferenceABA {
    private static AtomicStampedReference<Integer> atomicInteger = new AtomicStampedReference<>(100, 0);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        int[] res = new int[1];
        executorService.execute(() -> {
            System.out.println(Thread.currentThread().getName() + " - " + atomicInteger.get(res));
            atomicInteger.compareAndSet(100, 50, 0, 1);
            System.out.println(Thread.currentThread().getName() + " - " + atomicInteger.get(res));
        });

        executorService.execute(() -> {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " - " + atomicInteger.get(res));
            atomicInteger.compareAndSet(50, 100, 1, 2);
            System.out.println(Thread.currentThread().getName() + " - " + atomicInteger.get(res));
        });

        executorService.execute(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " - " + atomicInteger.get(res));
            atomicInteger.compareAndSet(100, 50, 0, 1);
            System.out.println(Thread.currentThread().getName() + " - " + atomicInteger.get(res));
        });
        executorService.shutdown();
    }
}
