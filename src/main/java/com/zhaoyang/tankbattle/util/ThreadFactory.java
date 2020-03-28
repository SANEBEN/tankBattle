package com.zhaoyang.tankbattle.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 昭阳
 * @date 2020/3/26 13:58
 */
public class ThreadFactory {

    private static ExecutorService service = Executors.newFixedThreadPool(10);

    public static void execute(Thread thread) {
        service.submit(thread);
        service.execute(thread);
    }
}
