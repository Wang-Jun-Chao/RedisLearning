package wjc.redisson;

import org.redisson.Redisson;
import org.redisson.api.RCountDownLatch;
import org.redisson.api.RedissonClient;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Author: 王俊超
 * Date: 2017-12-04 16-22
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class RCountDownLatchDemo {
    public static void main(String[] args) throws Exception {
        // connects to 127.0.0.1:6379 by default
        RedissonClient redisson = Redisson.create();
        redisson.getKeys().flushall();

        ExecutorService executor = Executors.newFixedThreadPool(2);

        final RCountDownLatch latch = redisson.getCountDownLatch("latch1");
        latch.trySetCount(1);
        System.out.println("main thread latch count : " + latch.getCount());

        executor.execute(new Runnable() {

            @Override
            public void run() {
                latch.countDown();
                System.out.println("s1 thread latch count : " + latch.getCount());
            }

        });

        executor.execute(new Runnable() {

            @Override
            public void run() {
                try {
                    System.out.println("s2 thread latch count : " + latch.getCount());
                    latch.await(550, TimeUnit.MILLISECONDS);
                    System.out.println("s2 thread latch count : " + latch.getCount());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);

        redisson.shutdown();
    }
}
