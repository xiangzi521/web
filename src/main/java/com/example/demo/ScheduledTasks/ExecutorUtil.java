package com.example.demo.ScheduledTasks;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Administrator on 2020/3/28.
 */
public class ExecutorUtil {

    public static final ScheduledExecutorService COMMON_EXECUTOR = Executors.newScheduledThreadPool(4, new ThreadFactory() {

        AtomicInteger count = new AtomicInteger(0);
        @Override
        public Thread newThread(Runnable r) {
            int i = count.incrementAndGet();
            return new Thread(r,"公共业务线程池 - " + i);
        }
    });

    /**
     *  固定的频率执行线程，不管任务有没有完成
     * @param r             执行的内容，是一个线程
     * @param initialDelay  延迟时间
     * @param period        间隔 默认单位是毫秒
     * @return
     */

    public static ScheduledFuture<?> scheduleAtFixedRAte(Runnable r,long initialDelay,long period){
        return COMMON_EXECUTOR.scheduleAtFixedRate(r,initialDelay,period, TimeUnit.MILLISECONDS);
    }

    /**
     *  任务完成后在延迟固定的时间执行下一次
     * @param command       执行的内容，是一个线程
     * @param initialDelay  延迟时间
     * @param period         间隔 默认单位是毫秒
     * @return
     */
    public static ScheduledFuture<?> scheduleWithFixedDelay(Runnable command, long initialDelay, long period) {
        return COMMON_EXECUTOR.scheduleWithFixedDelay(command, initialDelay, period, TimeUnit.MILLISECONDS);
    }

}
