package com.demo.concurrent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * fork/join  多线程分割任务
 * @author xks
 * @date 2020-06-04
 */
public class ForkJoinPoolDemo {
    class SendMsgTask extends RecursiveAction {

        private final int THRESHOLD = 10;

        private int start;
        private int end;
        private List<String> list;

        public SendMsgTask(int start, int end, List<String> list) {
            this.start = start;
            this.end = end;
            this.list = list;
        }

        @Override
        protected void compute() {

            if((end - start) <= THRESHOLD) {
                for (int i = start; i < end; i++) {
                    System.out.println(Thread.currentThread().getName() + ": " + list.get(i));
                }
            } else {
                int middle = (start + end) / 2;
                invokeAll(new SendMsgTask(start, middle, list), new SendMsgTask(middle, end, list));
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 123; i++) {
            list.add(String.valueOf(i + 1));
        }

        ForkJoinPool pool = new ForkJoinPool();
        pool.submit(new ForkJoinPoolDemo().new SendMsgTask(0, list.size(), list));
        pool.awaitTermination(10, TimeUnit.SECONDS);
        pool.shutdown();
    }

}

class ForkJoinTaskDemo1 {

    private class SumTask extends RecursiveTask<Integer> {

        private static final int THRESHOLD = 20;

        private int arr[];
        private int start;
        private int end;

        public SumTask(int[] arr, int start, int end) {
            this.arr = arr;
            this.start = start;
            this.end = end;
        }

        /**
         * 小计
         */
        private Integer subtotal() {
            Integer sum = 0;
            for (int i = start; i < end; i++) {
                sum += arr[i];
            }
            System.out.println(Thread.currentThread().getName() + ": ∑(" + start + "~" + end + ")=" + sum);
            return sum;
        }

        @Override
        protected Integer compute() {

            if((end - start) <= THRESHOLD) {
                return subtotal();
            } else {
                int middle = (start + end) / 2;
                SumTask left = new SumTask(arr, start, middle);
                SumTask right = new SumTask(arr, middle, end);
                left.fork();
                right.fork();

                return left.join() + right.join();
            }
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }

        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Integer> result = pool.submit(new ForkJoinTaskDemo1().new SumTask(arr, 0, arr.length));
        System.out.println("最终计算结果: " + result.invoke());
        pool.shutdown();
    }

}

class RecursiveActionDemo {

    private static class SortTask extends RecursiveAction {

        static final int THRESHOLD = 100;

        final long[] array;
        final int lo, hi;

        public SortTask(long[] array, int lo, int hi) {
            this.array = array;
            this.lo = lo;
            this.hi = hi;
        }

        public SortTask(long[] array) {
            this(array, 0, array.length);
        }

        public void sortSequentially(int lo, int hi) {
            Arrays.sort(array, lo, hi);
        }

        public void merge(int lo, int mid, int hi) {
            long[] buf = Arrays.copyOfRange(array, lo, mid);
            for (int i = 0, j = lo, k = mid; i < buf.length; j++) {
                array[j] = (k == hi || buf[i] < array[k]) ? buf[i++] : array[k++];
            }
        }

        @Override
        protected void compute() {
            if(hi - lo < THRESHOLD) {
                sortSequentially(lo, hi);
            } else {
                int mid = (lo + hi) >>> 1;
                invokeAll(new SortTask(array, lo, mid), new SortTask(array, mid, hi));
                merge(lo, mid, hi);
            }
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long[] array = new long[120];
        for (int i = 0; i < array.length; i++) {
            array[i] = (long) (Math.random() * 1000);
        }
        System.out.println(Arrays.toString(array));

        ForkJoinPool pool = new ForkJoinPool();
        pool.submit(new SortTask(array));
        pool.awaitTermination(5, TimeUnit.SECONDS);
        pool.shutdown();

    }
}

class RecursiveTaskDemo1 {

    private static class Fibonacci extends RecursiveTask<Integer> {

        final int n;

        public Fibonacci(int n) {
            this.n = n;
        }

        @Override
        protected Integer compute() {
            if (n <= 1) {
                return n;
            }else {
                Fibonacci f1 = new Fibonacci(n - 1);
                f1.fork();
                Fibonacci f2 = new Fibonacci(n - 1);
                return f2.compute() + f1.join();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ForkJoinPool pool = new ForkJoinPool();
        Future<Integer> future = pool.submit(new Fibonacci(10));
        System.out.println(future.get());
        pool.shutdown();
    }

}