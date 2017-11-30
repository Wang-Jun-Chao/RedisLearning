package wjc.redis;

import org.redisson.Redisson;
import org.redisson.api.LocalCachedMapOptions;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

/**
 * Author: 王俊超
 * Date: 2017-11-30 22:14
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class RClusteredLocalCachedMapDemo {
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

        LocalCachedMapOptions options = LocalCachedMapOptions.defaults()
                // LFU, LRU and NONE policies are available
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

        // 以下功能只在pro版本中有
//        RClusteredLocalCachedMap<String, Integer> map = redisson.getClusteredLocalCachedMap("test", options);
//
//        map.put("1", 1);
//        map.put("2", 2);
//
//        map.fastPut("3", 4);

        redisson.getKeys().flushall();
        redisson.shutdown();
    }
}
