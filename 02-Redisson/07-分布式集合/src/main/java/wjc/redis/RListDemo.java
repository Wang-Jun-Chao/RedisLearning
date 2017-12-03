package wjc.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.redisson.Redisson;
import org.redisson.api.RList;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * Author: 王俊超
 * Date: 2017-12-03 07:22
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class RListDemo {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redisson = Redisson.create(config);

        redisson.getKeys().flushall();


        RList<SomeObject> list = redisson.getList("anyList");
        list.add(new SomeObject("a"));

        System.out.println(list);

        SomeObject someObject = list.get(0);
        System.out.println(someObject);
        System.out.println(list);

        boolean a = list.remove(new SomeObject("a"));
        System.out.println(list);

        redisson.getKeys().flushall();
        redisson.shutdown();
    }
}
