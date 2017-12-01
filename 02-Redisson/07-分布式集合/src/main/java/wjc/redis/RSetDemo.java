package wjc.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.redisson.Redisson;
import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * Author: 王俊超
 * Date: 2017-12-01 08:04
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class RSetDemo {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();

        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redisson = Redisson.create(config);

        redisson.getKeys().flushall();

        RSet<SomeObject> set = redisson.getSet("anySet");
        set.add(new SomeObject("1"));
        System.out.println(set);

        set.remove(new SomeObject("1"));
        System.out.println(set);

        redisson.getKeys().flushall();
        redisson.shutdown();
    }
}
