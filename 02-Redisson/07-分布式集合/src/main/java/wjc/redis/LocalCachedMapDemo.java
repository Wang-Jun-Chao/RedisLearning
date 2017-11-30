package wjc.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.redisson.Redisson;
import org.redisson.api.LocalCachedMapOptions;
import org.redisson.api.RLocalCachedMap;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

/**
 * Author: 王俊超
 * Date: 2017-11-30 22:01
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class LocalCachedMapDemo {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();

        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redisson = Redisson.create(config);

        redisson.getKeys().flushall();

        LocalCachedMapOptions options = LocalCachedMapOptions.defaults()
                // 淘汰机制有LFU, LRU和NONE这几种算法策略可供选择
                .evictionPolicy(LocalCachedMapOptions.EvictionPolicy.LFU)
                .cacheSize(1000)
                // 如果该值是`真(true)`时，在该实例执行更新和删除操作的同时，将向其他所有的相同实例发
                // 送针对该元素的淘汰消息。其他相同实例在收到该消息以后，会同时删除自身的缓存。下次读取
                // 该元素时会从Redis服务器获取。
                .invalidateEntryOnChange(false)
                // 每个Map本地缓存里元素的有效时间，默认毫秒为单位
                .timeToLive(10000)
                // 或者
                .timeToLive(10, TimeUnit.SECONDS)
                // 每个Map本地缓存里元素的最长闲置时间，默认毫秒为单位
                .maxIdle(10000)
                // 或者
                .maxIdle(10, TimeUnit.SECONDS);

        RLocalCachedMap<String, Integer> map = redisson.getLocalCachedMap("test", options);

        map.put("1", 1);
        map.put("2", 2);

        map.fastPut("3", 4);

//        redisson.getKeys().flushall();
        redisson.shutdown();
    }
}
