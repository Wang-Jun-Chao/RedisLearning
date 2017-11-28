package wjc.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * Author: 王俊超
 * Date: 2017-11-28 21:47
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class RBloomFilterDemo {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redisson = Redisson.create(config);

        redisson.getKeys().flushall();

        RBloomFilter<BloomObject> bloomFilter = redisson.getBloomFilter("sample");
        // 初始化布隆过滤器，预计统计元素数量为55000000，期望误差率为0.03
        bloomFilter.tryInit(55000000L, 0.03);
        bloomFilter.add(new BloomObject("field1Value", "field2Value"));
        bloomFilter.add(new BloomObject("field5Value", "field8Value"));
        boolean r = bloomFilter.contains(new BloomObject("field1Value", "field8Value"));
        System.out.println(r);

        redisson.getKeys().flushall();
        redisson.shutdown();
    }
}
