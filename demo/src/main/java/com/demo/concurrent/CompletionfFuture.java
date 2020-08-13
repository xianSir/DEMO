package com.demo.concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author xks
 * @date 2020-08-12
 */
public class CompletionfFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> task = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000*5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("异步线程开始处理");
            return "6666";
        });
        Thread.sleep(1000*3);
        System.out.println(task.complete("异步任务未完"));
        CompletableFuture<String> complete = task.whenComplete((s, throwable) -> {
            System.out.println("结束是执行");
        });
        System.out.println("获取到的结果：" + task.get());
        Thread.sleep(1000*3);
        String s1 = task.get();
        System.out.println(s1);
        String s2 = CompletableFuture.completedFuture("nothing").get();
        System.out.println("s2: "+s2);

    }

}
