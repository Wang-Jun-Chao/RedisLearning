package wjc.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.redisson.Redisson;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * Author: 王俊超
 * Date: 2017-11-28 21:30
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class AtomicLongDemo {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redisson = Redisson.create(config);

        redisson.getKeys().flushall();

        RAtomicLong atomicLong = redisson.getAtomicLong("myAtomicLong");
        System.out.println(atomicLong);

        atomicLong.set(3);
        System.out.println(atomicLong);

        atomicLong.incrementAndGet();
        System.out.println(atomicLong);

        atomicLong.get();
        System.out.println(atomicLong);

        redisson.getKeys().flushall();
        redisson.shutdown();
    }
}
