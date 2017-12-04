package wjc.redisson;

import org.redisson.Redisson;
import org.redisson.api.RSemaphore;
import org.redisson.api.RedissonClient;

/**
 * Author: 王俊超
 * Date: 2017-12-04 15-57
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class RSemaphoreDemo {
    public static void main(String[] args) throws InterruptedException {
        // connects to 127.0.0.1:6379 by default
        RedissonClient redisson = Redisson.create();

        RSemaphore s = redisson.getSemaphore("test");
        // 设置信号量的数目为5
        s.trySetPermits(5);

        // 取3个信号量
        s.acquire(3);
        System.out.println("main thread acquire 3 semaphore. now available is "
                + s.availablePermits());

        Thread t = new Thread("sub") {
            @Override
            public void run() {
                RSemaphore s = redisson.getSemaphore("test");
                s.release();
                s.release();
                System.out.println("sub thread release 2 semaphore. now available is "
                        + s.availablePermits());

            }
        };

        t.start();

        // 请求四个信号量
        s.acquire(4);
        System.out.println("main thread acquire 4 semaphore. now available is "
                + s.availablePermits());
        redisson.shutdown();
    }
}
