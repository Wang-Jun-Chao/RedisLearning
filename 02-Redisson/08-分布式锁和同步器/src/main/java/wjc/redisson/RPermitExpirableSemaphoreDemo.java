package wjc.redisson;

import org.redisson.Redisson;
import org.redisson.api.RPermitExpirableSemaphore;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

/**
 * Author: 王俊超
 * Date: 2017-12-04 16-04
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class RPermitExpirableSemaphoreDemo {
    public static void main(String[] args) throws InterruptedException {
        // connects to 127.0.0.1:6379 by default
        RedissonClient redisson = Redisson.create();

        redisson.getKeys().flushall();

        RPermitExpirableSemaphore s = redisson.getPermitExpirableSemaphore("test");
        s.trySetPermits(1);

        System.out.println("main thread try to acquire 2 semaphore. available is " + s.availablePermits());
        String permitId = s.tryAcquire(100, 2, TimeUnit.SECONDS);
        System.out.println("main thread acquired 2 semaphore, permit id is " + permitId + ", available is " + s.availablePermits());

        Thread t = new Thread("sub") {
            public void run() {
                RPermitExpirableSemaphore s = redisson.getPermitExpirableSemaphore("test");
                try {
                    System.out.println("sub thread try to acquire 1 semaphore. available is " + s.availablePermits());
                    String permitId = s.acquire();
                    System.out.println("sub thread acquired 1 semaphore, permit id is " + permitId + ", available is " + s.availablePermits());
                    if (permitId != null) {
                        s.release(permitId);
                    }
                    System.out.println("sub thread release 1 semaphore" + ", available is " + s.availablePermits());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        t.start();
        t.join();


        if (permitId != null) {
            s.tryRelease(permitId);
        }
        System.out.println("main thread release 1 semaphore" + ", available is " + s.availablePermits());

        redisson.shutdown();
    }
}
