package wjc.redisson;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;

/**
 * Author: 王俊超
 * Date: 2017-12-04 15-52
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class RReadWriteLockDemo {
    public static void main(String[] args) throws InterruptedException {
        // connects to 127.0.0.1:6379 by default
        RedissonClient redisson = Redisson.create();

        final RReadWriteLock lock = redisson.getReadWriteLock("lock");

        lock.writeLock().tryLock();
        System.out.println("main thread gets write lock");

        Thread t = new Thread("sub") {
            public void run() {
                RLock r = lock.readLock();
                r.lock();
                System.out.println("sub thread get read lock");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                r.unlock();
            }

            ;
        };

        t.start();
        t.join();

        lock.writeLock().unlock();

        redisson.shutdown();
    }
}
