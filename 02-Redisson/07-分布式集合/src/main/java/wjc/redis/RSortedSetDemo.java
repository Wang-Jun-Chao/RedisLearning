package wjc.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * Author: 王俊超
 * Date: 2017-12-03 07:22
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class RSortedSetDemo {
        public static void main(String[] args) {
            ObjectMapper mapper = new ObjectMapper();

            Config config = new Config();
            config.useSingleServer().setAddress("redis://127.0.0.1:6379");
            RedissonClient redisson = Redisson.create(config);

            redisson.getKeys().flushall();




            redisson.getKeys().flushall();
            redisson.shutdown();
    }
}
