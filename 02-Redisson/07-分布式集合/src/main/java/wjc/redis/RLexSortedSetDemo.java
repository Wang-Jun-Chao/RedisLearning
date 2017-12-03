package wjc.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.redisson.Redisson;
import org.redisson.api.RLexSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.Collection;

/**
 * Author: 王俊超
 * Date: 2017-12-03 07:22
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class RLexSortedSetDemo {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redisson = Redisson.create(config);

        redisson.getKeys().flushall();


        RLexSortedSet set = redisson.getLexSortedSet("simple");
        set.add("d");
        set.addAsync("e");
        set.add("f");

        System.out.println(mapper.writeValueAsString(set));


        Collection<String> elements = set.rangeHead("d", false);
        System.out.println("rangeHead(\"d\", false): " + elements);

        int e = set.countHead("e", true);
        System.out.println("countHead(\"e\", true): " + e);


        elements = set.range("d", true, "z", false);
        System.out.println("range(\"d\", true, \"z\", false): " + elements);

        redisson.getKeys().flushall();
        redisson.shutdown();
    }
}
