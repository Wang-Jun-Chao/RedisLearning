package wjc.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.redisson.Redisson;
import org.redisson.api.RListMultimap;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Author: 王俊超
 * Date: 2017-12-01 07:54
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class RListMultimapDemo {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();

        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redisson = Redisson.create(config);

        redisson.getKeys().flushall();

        RListMultimap<SimpleKey, SimpleValue> map = redisson.getListMultimap("test1");
        map.put(new SimpleKey("0"), new SimpleValue("1"));
        map.put(new SimpleKey("0"), new SimpleValue("2"));
        map.put(new SimpleKey("0"), new SimpleValue("1"));
        map.put(new SimpleKey("3"), new SimpleValue("4"));
        System.out.println(map.entries());

        List<SimpleValue> allValues = map.get(new SimpleKey("0"));
        System.out.println("allValues: " + allValues);

        Collection<SimpleValue> newValues = Arrays.asList(new SimpleValue("7"),
                new SimpleValue("6"), new SimpleValue("5"));
        List<SimpleValue> oldValues = map.replaceValues(new SimpleKey("0"), newValues);
        System.out.println(map.entries());
        System.out.println("oldValues: " + oldValues);

        List<SimpleValue> removedValues = map.removeAll(new SimpleKey("0"));
        System.out.println("removedValues: " + removedValues);
        System.out.println(map.entries());

        redisson.getKeys().flushall();
        redisson.shutdown();
    }
}
