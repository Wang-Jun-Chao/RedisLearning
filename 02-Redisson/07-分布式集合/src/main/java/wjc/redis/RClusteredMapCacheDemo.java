package wjc.redis;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

/**
 * Author: 王俊超
 * Date: 2017-11-30 22:18
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class RClusteredMapCacheDemo {
    public static void main(String[] args) {
        Config config = new Config();
        config.setUseLinuxNativeEpoll(true);
        config.useClusterServers()
                .addNodeAddress("redis://192.168.241.150:7110")
                .addNodeAddress("redis://192.168.241.150:7111")
                .addNodeAddress("redis://192.168.241.150:7112")
                .addNodeAddress("redis://192.168.241.150:7113")
                .addNodeAddress("redis://192.168.241.150:7114");
        RedissonClient redisson = Redisson.create(config);

        redisson.getKeys().flushall();

        // 下在功能只在pro版本中有
//        RClusteredMapCache<String, SomeObject> map = redisson.getClusteredMapCache("anyMap");
//        // 有效时间 ttl = 10分钟
//        map.put("key1", new SomeObject(), 10, TimeUnit.MINUTES);
//        // 有效时间 ttl = 10分钟, 最长闲置时间 maxIdleTime = 10秒钟
//        map.put("key1", new SomeObject(), 10, TimeUnit.MINUTES, 10, TimeUnit.SECONDS);
//
//        // 有效时间 = 3 秒钟
//        map.putIfAbsent("key2", new SomeObject(), 3, TimeUnit.SECONDS);
//        // 有效时间 ttl = 40秒钟, 最长闲置时间 maxIdleTime = 10秒钟
//        map.putIfAbsent("key2", new SomeObject(), 40, TimeUnit.SECONDS, 10, TimeUnit.SECONDS);

        redisson.getKeys().flushall();
        redisson.shutdown();
    }
}
