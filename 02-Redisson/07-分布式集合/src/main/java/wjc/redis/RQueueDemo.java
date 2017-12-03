package wjc.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.redisson.Redisson;
import org.redisson.api.RQueue;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * Author: 王俊超
 * Date: 2017-12-03 07:22
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class RQueueDemo {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redisson = Redisson.create(config);

        redisson.getKeys().flushall();

        RQueue<SomeObject> queue = redisson.getQueue("anyQueue");
        queue.add(new SomeObject("a"));
        System.out.println(queue);


        SomeObject obj = queue.peek();
        System.out.println(obj);
        System.out.println(queue);

        SomeObject someObj = queue.poll();
        System.out.println(someObj);
        System.out.println(queue);


        redisson.getKeys().flushall();
        redisson.shutdown();
    }
}
