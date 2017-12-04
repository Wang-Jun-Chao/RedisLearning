package wjc.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.redisson.Redisson;
import org.redisson.api.RBlockingDeque;
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
public class RBlockingDequeDemo {
    public static void main(String[] args) throws InterruptedException {
        ObjectMapper mapper = new ObjectMapper();

        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redisson = Redisson.create(config);

        redisson.getKeys().flushall();

        RBlockingDeque<Integer> deque = redisson.getBlockingDeque("anyDeque");
        deque.putFirst(1);
        deque.putLast(2);

        System.out.println("deque " + deque);
        System.out.println("=====");

        Integer firstValue = deque.takeFirst();
        System.out.println("firstValue 1 :" + firstValue);
        System.out.println("deque " + deque);
        System.out.println("=====");


        Integer lastValue = deque.takeLast();
        System.out.println("lastValue 1 :" + lastValue);
        System.out.println("deque " + deque);
        System.out.println("=====");

        firstValue = deque.pollFirst(3, TimeUnit.SECONDS);
        System.out.println("firstValue 2 :" + firstValue);
        System.out.println("deque " + deque);
        System.out.println("=====");


        lastValue = deque.pollLast(2, TimeUnit.SECONDS);
        System.out.println("lastValue 2 :" + lastValue);
        System.out.println("deque " + deque);
        System.out.println("=====");


        redisson.getKeys().flushall();
        redisson.shutdown();
    }
}
