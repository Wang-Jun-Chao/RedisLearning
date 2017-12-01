package wjc.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.redisson.Redisson;
import org.redisson.api.RSetMultimapCache;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

/**
 * Author: 王俊超
 * Date: 2017-12-01 07:58
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class RSetMultimapCacheDemo {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redisson = Redisson.create(config);

        redisson.getKeys().flushall();

        RSetMultimapCache<String, String> multimap = redisson.getSetMultimapCache("myMultimap");
        multimap.put("1", "a");
        multimap.put("1", "b");
        multimap.put("1", "c");
        multimap.put("1", "c");

        multimap.put("2", "e");
        multimap.put("2", "f");
        multimap.put("2", "f");

        System.out.println(multimap.entries());

        multimap.expireKey("2", 5, TimeUnit.SECONDS);

        Thread.sleep(6000);

        System.out.println(multimap.entries());

        redisson.getKeys().flushall();
        redisson.shutdown();
    }
}
