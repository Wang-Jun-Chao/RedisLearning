package wjc.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.redisson.Redisson;
import org.redisson.api.RSetMultimap;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Author: 王俊超
 * Date: 2017-11-30 22:35
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class RSetMultimapDemo {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();

        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redisson = Redisson.create(config);

        redisson.getKeys().flushall();

        RSetMultimap<SimpleKey, SimpleValue> map = redisson.getSetMultimap("myMultimap");
        map.put(new SimpleKey("0"), new SimpleValue("1"));
        map.put(new SimpleKey("0"), new SimpleValue("2"));
        map.put(new SimpleKey("3"), new SimpleValue("4"));
        System.out.println(map.entries());

        Set<SimpleValue> allValues = map.get(new SimpleKey("0"));
        System.out.println("allValues: " + allValues);

        List<SimpleValue> newValues = Arrays.asList(new SimpleValue("7"),
                new SimpleValue("6"), new SimpleValue("5"));
        Set<SimpleValue> oldValues = map.replaceValues(new SimpleKey("0"), newValues);
        System.out.println(map.entries());
        System.out.println("oldValues: " + oldValues);

        Set<SimpleValue> removedValues = map.removeAll(new SimpleKey("0"));
        System.out.println("removedValues: " + removedValues);

        redisson.getKeys().flushall();
        redisson.shutdown();
    }
}
