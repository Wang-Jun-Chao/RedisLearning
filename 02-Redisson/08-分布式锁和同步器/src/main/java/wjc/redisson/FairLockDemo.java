package wjc.redisson;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: 王俊超
 * Date: 2017-12-04 15-24
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class FairLockDemo {
    public static void main(String[] args) throws InterruptedException {
        // connects to 127.0.0.1:6379 by default
        RedissonClient redisson = Redisson.create();
        redisson.getKeys().flushall();

        RLock lock = redisson.getFairLock("test");

        int size = 10;
        List<Thread> threads = new ArrayList<Thread>();
        for (int i = 0; i < size; i++) {
            final int j = i;
            Thread t = new Thread() {
                public void run() {
                    lock.lock();
                    System.out.println("j = " + j);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lock.unlock();
                }

                ;
            };

            threads.add(t);
        }


        for (Thread thread : threads) {
            thread.start();
            // 确保先启动的线程先请求锁
            thread.join(10);
        }

        for (Thread thread : threads) {
            thread.join();
        }

        redisson.getKeys().flushall();
        redisson.shutdown();
    }
}
