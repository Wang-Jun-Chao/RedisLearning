package wjc.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.redisson.Redisson;
import org.redisson.api.RPriorityQueue;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * Author: 王俊超
 * Date: 2017-12-04 11-19
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class RPriorityQueueDemo {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redisson = Redisson.create(config);

        redisson.getKeys().flushall();

        RPriorityQueue<Integer> queue = redisson.getPriorityQueue("anyQueue");
        queue.trySetComparator(new MyComparator()); // 指定对象比较器
        queue.add(3);
        queue.add(1);
        queue.add(2);

        System.out.println("queue: " + queue);
        System.out.println("=====");

        queue.remove(1);
        System.out.println("queue: " + queue);
        System.out.println("=====");

        queue.add(5);
        System.out.println("queue: " + queue);
        System.out.println("=====");

        queue.poll();

        redisson.getKeys().flushall();
        redisson.shutdown();
    }
}
