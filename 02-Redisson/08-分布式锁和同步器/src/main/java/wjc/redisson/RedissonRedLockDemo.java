package wjc.redisson;

import org.redisson.Redisson;
import org.redisson.RedissonMultiLock;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Author: 王俊超
 * Date: 2017-12-04 15-36
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class RedissonRedLockDemo {
    public static void main(String[] args) throws Exception {
        RedissonClient client1 = Redisson.create();
        RedissonClient client2 = Redisson.create();

        RLock lock1 = client1.getLock("lock1");
        RLock lock2 = client1.getLock("lock2");
        RLock lock3 = client2.getLock("lock3");

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");


        Thread t1 = new Thread() {
            public void run() {
                lock3.lock();
                System.out.println(format.format(new Date()) + ", t1 thread gets lock.");
            }

            ;
        };
        t1.start();
        t1.join();

        Thread t = new Thread() {
            public void run() {
                RedissonMultiLock lock = new RedissonRedLock(lock1, lock2, lock3);
                lock.lock();

                try {
                    System.out.println(format.format(new Date()) + ", t thread get red lock.");
                    System.out.println("t thread does work");
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.unlock();
                System.out.println(format.format(new Date()) + ", t thread release red lock.");
            }
        };
        t.start();
        t.join(1000);

        lock3.delete();

        RedissonMultiLock lock = new RedissonRedLock(lock1, lock2, lock3);
        lock.lock();
        System.out.println(format.format(new Date()) + "main thread get red lock.");
        System.out.println("main thread does work");
        lock.unlock();
        System.out.println(format.format(new Date()) + ", main thread release red lock.");


        client1.shutdown();
        client2.shutdown();
    }
}
