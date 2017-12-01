package wjc.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.redisson.Redisson;
import org.redisson.api.RSetCache;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

/**
 * Author: 王俊超
 * Date: 2017-12-01 08:07
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class RSetCacheDemo {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redisson = Redisson.create(config);

        redisson.getKeys().flushall();

        RSetCache<SomeObject> set = redisson.getSetCache("anySet");
        // ttl = 5 seconds
        set.add(new SomeObject(), 5, TimeUnit.SECONDS);
        System.out.println(mapper.writeValueAsString(set));

        Thread.sleep(8000);
        System.out.println(mapper.writeValueAsString(set));


        redisson.getKeys().flushall();
        redisson.shutdown();
    }
}
