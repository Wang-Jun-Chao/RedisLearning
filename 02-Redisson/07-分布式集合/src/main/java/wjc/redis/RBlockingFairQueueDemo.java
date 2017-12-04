package wjc.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * Author: 王俊超
 * Date: 2017-12-04 11-19
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class RBlockingFairQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        ObjectMapper mapper = new ObjectMapper();

        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redisson = Redisson.create(config);

        redisson.getKeys().flushall();

        // 此功能在开源版本中未找到
//        RBlockingFairQueue queue = redisson.getBlockingFairQueue("myQueue");
//        queue.offer(new SomeObject());
//
//        SomeObject obj = queue.peek();
//        SomeObject someObj = queue.poll();
//        SomeObject ob = queue.poll(10, TimeUnit.MINUTES);


        redisson.getKeys().flushall();
        redisson.shutdown();
    }
}
