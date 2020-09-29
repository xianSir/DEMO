import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;

/**
 * @author xks
 * @date 2020-09-27
 */
public class tets {
    public static void main(String[] args) throws InterruptedException {
        allOfExample();
    }

    /**
     * 当所有的阶段完成，新建一个完成阶段
     */
    static void allOfExample() {
        StringBuilder result = new StringBuilder();
        List<String> messages = Arrays.asList("a", "b", "c");
        List<CompletableFuture> futures = messages.stream()
                .map(msg -> CompletableFuture.completedFuture(msg).thenApply(s -> {
                            System.out.println("1 " + s);
                            return s.toLowerCase();
                        }
                ))
                .collect(Collectors.toList());
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()])).whenComplete((v, th) -> {
            futures.forEach(cf -> {
                System.out.println(cf.getNow("未完成"));
            });
            result.append("done");

        });
        System.out.println(result);
    }


    /**
     * 当多个阶段中有有何一个完成，即新建一个完成阶段
     */
    static void anyOfExample() {
        StringBuilder result = new StringBuilder();
        List<String> messages = Arrays.asList("a", "b", "c");
        List<CompletableFuture<String>> futures = messages.stream()
                .map(msg -> CompletableFuture.completedFuture(msg).thenApply(s -> {
                    System.out.println("1 " + s);
                    return s.toLowerCase();
                }))
                .collect(Collectors.toList());
        CompletableFuture.anyOf(futures.toArray(new CompletableFuture[futures.size()])).whenComplete((res, th) -> {
            if(th == null) {
                System.out.println("这个先完成：" + res);
                result.append(res);
            }
        });
        System.out.println(result);
    }

    static void thenComposeExample() {
        String original = "Message";
        CompletableFuture cf = CompletableFuture.completedFuture(original).thenApply(s -> {
            System.out.println("1 " + s);
            return s.toLowerCase();
        }).thenCompose(upper -> CompletableFuture.completedFuture(original)
                .thenApply(s -> {
                    System.out.println("2 " + s);
                    return s.toLowerCase();
                }).thenApply(s -> upper + s));
        System.out.println(cf.join());
    }

    /**
     * 将Bifunction同时作用于两个阶段的结果
     */
    static void thenCombineExample() {
        String original = "Message";
        CompletableFuture cf = CompletableFuture.completedFuture(original).thenApply(s -> {
            System.out.println(s);
            return s.toUpperCase();
        }).thenCombine(CompletableFuture.completedFuture(original).thenApply(s -> {
                    System.out.println(s);
                    return s.toLowerCase();
                }),
                (s1, s2) -> s1 + s2);
        System.out.println(cf.getNow(null));
    }

    /**
     * 用Biconsumer接收两个stage的结果
     */
    static void thenAcceptBothExample() {
        String original = "Message";
        StringBuilder result = new StringBuilder();
        CompletableFuture.completedFuture(original)
                .thenApply(String::toUpperCase)
                .thenAcceptBoth(
                        CompletableFuture.completedFuture(original).thenApply(s -> {
                            System.out.println(s);
                            return s.toLowerCase();
                        }),
                        (s1, s2) -> result.append(s1 + s2)
                );
        System.out.println(result.toString());
    }

    static ExecutorService executor = Executors.newFixedThreadPool(3, new ThreadFactory() {
        int count = 1;

        @Override
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "custom-executor-" + count++);
        }
    });

    /**
     * 使用一个自定义的Executor来异步执行该方法
     *
     * @throws InterruptedException
     */
    static void thenApplyAsyncWithExecutorExample() throws InterruptedException {
        CompletableFuture cf = CompletableFuture.completedFuture("message").thenApplyAsync(s -> {
            System.out.println(Thread.currentThread().getName() + "  " + Thread.currentThread().isDaemon());
            return s.toUpperCase();
        }, executor);
        Thread.sleep(100);
        System.out.println(cf.getNow(null));
        System.out.println(cf.join());
        System.exit(1);
    }

    /**
     * 将Function作用于两个已完成Stage的结果之一
     */
    static void applyToEitherExample() {
        String original = "Message";
        CompletableFuture cf1 = CompletableFuture.completedFuture(original)
                .thenApplyAsync(s -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(s.toUpperCase());
                    return s.toUpperCase();
                });
        CompletableFuture<String> cf2 = cf1.applyToEither(
                CompletableFuture.completedFuture(original).thenApplyAsync(s -> {
                    System.out.println(s.toLowerCase());
                    return s.toLowerCase();
                }),
                s -> {
                    System.out.println(s + " from applyToEither");
                    return s + " from applyToEither";
                });
        System.out.println(cf2.join().endsWith(" from applyToEither"));
    }
}
