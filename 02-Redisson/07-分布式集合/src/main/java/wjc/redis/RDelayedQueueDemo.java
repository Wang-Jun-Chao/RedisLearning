package wjc.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.redisson.Redisson;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RQueue;
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
public class RDelayedQueueDemo {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redisson = Redisson.create(config);

        redisson.getKeys().flushall();

        RQueue<String> distinationQueue = redisson.getQueue("myQueue");
        RDelayedQueue<String> delayedQueue = redisson.getDelayedQueue(distinationQueue);
        // 10秒钟以后将消息发送到指定列队
        delayedQueue.offer("msg1", 5, TimeUnit.SECONDS);
        System.out.println("distinationQueue: " + mapper.writeValueAsString(distinationQueue));
        System.out.println("=====");
        Thread.sleep(6000);
        System.out.println("distinationQueue: " + mapper.writeValueAsString(distinationQueue));
        System.out.println("=====");

        // 一分钟以后将消息发送到指定列队
        delayedQueue.offer("msg2", 8, TimeUnit.SECONDS);
        System.out.println("distinationQueue: " + mapper.writeValueAsString(distinationQueue));
        System.out.println("=====");
        Thread.sleep(10000);
        System.out.println("distinationQueue: " + mapper.writeValueAsString(distinationQueue));
        System.out.println("=====");

        redisson.getKeys().flushall();
        redisson.shutdown();
    }
}
