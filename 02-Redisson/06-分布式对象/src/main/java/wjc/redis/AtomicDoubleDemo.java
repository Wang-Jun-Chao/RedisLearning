package wjc.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.redisson.Redisson;
import org.redisson.api.RAtomicDouble;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * Author: 王俊超
 * Date: 2017-11-28 21:31
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class AtomicDoubleDemo {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redisson = Redisson.create(config);

        RAtomicDouble atomicDouble = redisson.getAtomicDouble("myAtomicDouble");
        System.out.println(atomicDouble);

        atomicDouble.set(2.81);
        System.out.println(atomicDouble);

        atomicDouble.addAndGet(4.11);
        System.out.println(atomicDouble);

        atomicDouble.get();
        System.out.println(atomicDouble);

        redisson.getKeys().flushall();
        redisson.shutdown();
    }
}
