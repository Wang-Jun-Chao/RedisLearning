package wjc.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.redisson.Redisson;
import org.redisson.api.RList;
import org.redisson.api.RMap;
import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;

/**
 * Author: 王俊超
 * Date: 2017-12-06 08-09
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class ComplexObjectDemo {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        // connects to 127.0.0.1:6379 by default
        RedissonClient redisson = Redisson.create();
        redisson.getKeys().flushall();

        RMap<RSet<RList>, RList<RMap>> map = redisson.getMap("myMap");
        RSet<RList> set = redisson.getSet("mySet");
        RList<RMap> list = redisson.getList("myList");

        map.put(set, list);
        // 在特殊引用对象的帮助下，我们甚至可以构建一个循环引用，这是通过普通序列化方式实现不了的。
        set.add(list);
        list.add(map);

        redisson.shutdown();
    }
}
