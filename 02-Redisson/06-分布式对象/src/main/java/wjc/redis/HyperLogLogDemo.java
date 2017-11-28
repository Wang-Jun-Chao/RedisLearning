package wjc.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.redisson.Redisson;
import org.redisson.api.RHyperLogLog;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * Author: 王俊超
 * Date: 2017-11-28 21:54
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class HyperLogLogDemo {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redisson = Redisson.create(config);

        redisson.getKeys().flushall();

        RHyperLogLog<Integer> log = redisson.getHyperLogLog("log");
        log.add(1);
        log.add(2);
        log.add(3);

        long a = log.count();
        System.out.println(a);

        redisson.getKeys().flushall();
        redisson.shutdown();
    }
}
