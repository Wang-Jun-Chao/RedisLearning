package wjc.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.redisson.Redisson;
import org.redisson.api.RFuture;
import org.redisson.api.RSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * Author: 王俊超
 * Date: 2017-12-01 08:13
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class SortedSetDemo {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redisson = Redisson.create(config);

        redisson.getKeys().flushall();

        RSortedSet<Integer> set = redisson.getSortedSet("anySet");
        set.trySetComparator(new MyComparator()); // 配置元素比较器
        set.add(3);
        set.add(1);
        set.add(2);

        System.out.println(mapper.writeValueAsString(set));

        RFuture<Boolean> future = set.removeAsync(0);

        if (future.get()) {
            System.out.println("remove true: " + mapper.writeValueAsString(set));
        } else {
            System.out.println("remove false: " + mapper.writeValueAsString(set));

        }

        future = set.addAsync(5);
        if (future.get()) {
            System.out.println("add true: " + mapper.writeValueAsString(set));
        } else {
            System.out.println("add false: " + mapper.writeValueAsString(set));
        }

        redisson.getKeys().flushall();
        redisson.shutdown();
    }
}
