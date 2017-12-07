package wjc.redis;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: 王俊超
 * Date: 2017-12-07 07-58
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
@Configuration
@ComponentScan
@EnableCaching
public class Application {

    @Bean(destroyMethod = "shutdown")
    RedissonClient redisson() throws IOException {
        // 集群方式
//        Config config = new Config();
//        config.useClusterServers()
//                .addNodeAddress("127.0.0.1:7004", "127.0.0.1:7001");
//        return Redisson.create(config);
        // connects to 127.0.0.1:6379 by default
        return Redisson.create();
    }

    @Bean
    CacheManager cacheManager(RedissonClient redissonClient) {
        Map<String, CacheConfig> config = new HashMap<String, CacheConfig>();
        // 创建一个名称为"testMap"的缓存，过期时间ttl为24秒钟，同时最长空闲时maxIdleTime为12秒钟。
        config.put("testMap", new CacheConfig(24 * 60 * 1000, 12 * 60 * 1000));
        return new RedissonSpringCacheManager(redissonClient, config);
    }

}