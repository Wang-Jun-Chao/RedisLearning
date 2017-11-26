package wjc.redis.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Author: 王俊超
 * Date: 2017-11-26 21:14
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class Main5 {
    public static void main(String[] args) throws IOException {
        Config config = new Config();
        config.useReplicatedServers()
                .setScanInterval(2000) // 主节点变化扫描间隔时间
                //可以用"rediss://"来启用SSL连接
                .addNodeAddress("redis://192.168.241.150:7110");

        RedissonClient redisson = Redisson.create(config);
        System.out.println(redisson.getConfig().toYAML());
        redisson.shutdown();
    }
}
