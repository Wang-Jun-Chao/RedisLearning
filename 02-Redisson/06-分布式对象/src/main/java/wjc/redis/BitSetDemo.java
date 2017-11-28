package wjc.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.redisson.Redisson;
import org.redisson.api.RBitSet;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * Author: 王俊超
 * Date: 2017-11-28 21:24
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class BitSetDemo {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redisson = Redisson.create(config);

        redisson.getKeys().flushall();

        RBitSet set = redisson.getBitSet("simpleBitset");
        set.set(0, true);
        System.out.println(set);

        set.set(1812, true);
        System.out.println(set);

        set.set(1812, false);
        System.out.println(set);

        set.clear(0);
        System.out.println(set);

        set.andAsync("e");
        System.out.println(set);

        set.xor("anotherBitset");
        System.out.println(set);

        redisson.getKeys().flushall();
        redisson.shutdown();
    }
}
