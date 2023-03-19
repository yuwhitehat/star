package com.liuziyu.star.test.thead;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * desc
 *
 * @author LiuZiyu
 * @date 2022/12/18 15:54
 */
public class ThreadMain {

    public static void main(String[] args) throws InterruptedException {
        // 创建有界阻塞队列
        BlockingQueue<Runnable> workQueue =
                new LinkedBlockingQueue<>(2);
        // 创建线程池
        MyThreadPool pool = new MyThreadPool(
                10, workQueue);
        // 提交任务
        pool.execute(()-> {
            System.out.println("hello");
        });
    }
}
