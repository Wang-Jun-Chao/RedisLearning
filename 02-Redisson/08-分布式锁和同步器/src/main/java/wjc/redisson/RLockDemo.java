package wjc.redisson;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

/**
 * Author: 王俊超
 * Date: 2017-12-04 15-24
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class RLockDemo {
    public static void main(String[] args) throws InterruptedException {
        // connects to 127.0.0.1:6379 by default
        RedissonClient redisson = Redisson.create();
        redisson.getKeys().flushall();

        RLock lock = redisson.getLock("lock");

        lock.lock(5, TimeUnit.SECONDS);
        long time = System.currentTimeMillis();

        Thread t = new Thread() {
            public void run() {
                RLock lock1 = redisson.getLock("lock");
                lock1.lock();
                System.out.println("sub thread get lock after "
                        + (System.currentTimeMillis() - time) + " ms");
                lock1.unlock();
            }

            ;
        };

        t.start();
        t.join();

        lock.unlock();

        redisson.getKeys().flushall();
        redisson.shutdown();
    }
}
