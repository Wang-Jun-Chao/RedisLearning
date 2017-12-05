package wjc.redis.executorservice;

import org.redisson.Redisson;
import org.redisson.RedissonNode;
import org.redisson.api.RExecutorFuture;
import org.redisson.api.RExecutorService;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.RedissonNodeConfig;

import java.util.Collections;

/**
 * Author: 王俊超
 * Date: 2017-12-05 21-35
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class ExecutorServiceDemo {
    public static void main(String[] args) throws Exception {
        Config config = new Config();
        config.useClusterServers().addNodeAddress(
                "redis://192.168.241.150:7110", "redis://192.168.241.150:7111",
                "redis://192.168.241.150:7112", "redis://192.168.241.150:7113",
                "redis://192.168.241.150:7114");

        RedissonClient redisson = Redisson.create(config);

        redisson.getKeys().flushall();

        RedissonNodeConfig nodeConfig = new RedissonNodeConfig(config);
        nodeConfig.setExecutorServiceWorkers(Collections.singletonMap("myExecutor", 1));
        RedissonNode node = RedissonNode.create(nodeConfig);
        node.start();

        RExecutorService e = redisson.getExecutorService("myExecutor");
        e.execute(new RunnableTask());
        RExecutorFuture<String> future = e.submit(new CallableTask());
        System.out.println("future: " + future.get());

        e.shutdown();
        node.shutdown();
        redisson.shutdown();
    }
}
