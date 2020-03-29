package com.zhaoyang.tankbattle.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 昭阳
 * @date 2020/3/26 13:58
 */
public class ThreadFactory {

    private static ExecutorService service = Executors.newFixedThreadPool(10);

    private static List<Thread> threads = Collections.synchronizedList(new ArrayList<>());

    public static void execute(Thread thread) {
        service.submit(thread);
        service.execute(thread);
    }

    public static void stopAll() {
        threads.forEach(Thread::interrupt);
    }

    public static void setThreads(Thread threads) {
        ThreadFactory.threads.add(threads);
    }
}
