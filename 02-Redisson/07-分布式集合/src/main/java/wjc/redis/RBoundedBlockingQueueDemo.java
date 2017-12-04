package wjc.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.redisson.Redisson;
import org.redisson.api.RBoundedBlockingQueue;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Author: 王俊超
 * Date: 2017-12-04 11-19
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class RBoundedBlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        ObjectMapper mapper = new ObjectMapper();

        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redisson = Redisson.create(config);

        redisson.getKeys().flushall();

        RBoundedBlockingQueue<SomeObject> queue = redisson.getBoundedBlockingQueue("anyQueue");
        // 如果初始容量（边界）设定成功则返回`真（true）`，
        // 如果初始容量（边界）已近存在则返回`假（false）`。
        boolean b = queue.trySetCapacity(2);
        System.out.println(b);
        System.out.println("=====");

        queue.offer(new SomeObject("1"));
        queue.offer(new SomeObject("2"));

        System.out.println("1 queue: " + queue);
        System.out.println("=====");

        // 10秒后执行任务
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        scheduledThreadPool.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("2 queue: " + queue);
                System.out.println("=====");

                SomeObject obj = queue.peek();
                System.out.println("obj: " + obj);
                System.out.println("queue: " + queue);
                System.out.println("=====");


                SomeObject someObj = queue.poll();
                System.out.println("someObj: " + someObj);
                System.out.println("queue: " + queue);
                System.out.println("=====");

                SomeObject ob = null;
                try {
                    ob = queue.poll(3, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("someObj: " + ob);
                System.out.println("queue: " + queue);
                System.out.println("=====");

                redisson.getKeys().flushall();
                redisson.shutdown();

                scheduledThreadPool.shutdown();
            }
        }, 10, TimeUnit.SECONDS);


        // 此时容量已满，下面代码将会被阻塞，直到有空闲为止。
        queue.put(new SomeObject());


    }
}
