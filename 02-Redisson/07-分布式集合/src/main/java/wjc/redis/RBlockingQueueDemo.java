package wjc.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.redisson.Redisson;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

/**
 * Author: 王俊超
 * Date: 2017-12-04 11-19
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class RBlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        ObjectMapper mapper = new ObjectMapper();

        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redisson = Redisson.create(config);

        redisson.getKeys().flushall();

        RBlockingQueue<SomeObject> queue = redisson.getBlockingQueue("anyQueue");
        queue.offer(new SomeObject());
        System.out.println("queue: " + queue);
        System.out.println("=====");

        SomeObject obj = queue.peek();
        System.out.println("obj: " + obj);
        System.out.println("queue: " + queue);
        System.out.println("=====");

        SomeObject someObj = queue.poll();
        System.out.println("someObj: " + someObj);
        System.out.println("queue: " + queue);
        System.out.println("=====");

        SomeObject ob = queue.poll(5, TimeUnit.SECONDS);
        System.out.println("ob: " + ob);
        System.out.println("queue: " + ob);


        redisson.getKeys().flushall();
        redisson.shutdown();
    }
}
