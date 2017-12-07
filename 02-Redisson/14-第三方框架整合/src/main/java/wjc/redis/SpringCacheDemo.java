package wjc.redis;

import org.redisson.api.RedissonClient;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Author: 王俊超
 * Date: 2017-12-07 07-48
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class SpringCacheDemo {
    public static void main(String[] args) throws IOException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("application2.xml");
        RedissonClient client = ctx.getBean(RedissonClient.class);
        System.out.println(client.getConfig().toYAML());

        CacheManager manager = ctx.getBean(CacheManager.class);
        System.out.println(manager);

        client.shutdown();
    }
}
