package wjc.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.redisson.Redisson;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * Author: 王俊超
 * Date: 2017-12-01 08:19
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class RScoredSortedSetDemo {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redisson = Redisson.create(config);

        redisson.getKeys().flushall();

        RScoredSortedSet<SomeObject2> set = redisson.getScoredSortedSet("simple");

        set.add(0.13, new SomeObject2("a", "b"));
        set.addAsync(0.251, new SomeObject2("c", "d"));
        set.add(0.302, new SomeObject2("g", "d"));

        System.out.println(mapper.writeValueAsString(set.toArray()));

        set.pollFirst();
        System.out.println(set);

        set.pollLast();
        System.out.println(set);


        int index = set.rank(new SomeObject2("g", "d")); // 获取元素在集合中的位置
        System.out.println(index);

        Double score = set.getScore(new SomeObject2("g", "d")); // 获取元素的评分
        System.out.println(score);

        redisson.getKeys().flushall();
        redisson.shutdown();
    }
}
