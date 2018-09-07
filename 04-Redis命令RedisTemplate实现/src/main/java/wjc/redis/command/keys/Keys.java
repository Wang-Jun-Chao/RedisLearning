package wjc.redis.command.keys;

import com.google.common.collect.Sets;
import org.junit.Assert;
import org.junit.Test;
import wjc.redis.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * <pre>
 *
 * </pre>
 * Author: 王俊超
 * Date: 2018-09-06 22:37
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class Keys extends Command<String, Integer> {

    @Test
    public void test() {
        Map<String, Integer> map = new HashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        map.put("four", 4);
        template.opsForValue().multiSet(map);

        Set<String> keys = template.keys("*o*");
        System.out.println(keys);

        Set<String> set = Sets.newHashSet("one", "four", "two");
        Assert.assertEquals(keys.size(), set.size());
        for (String key : keys) {
            Assert.assertTrue(set.contains(key));
        }

        keys = template.keys("t??");
        System.out.println(keys);
        Assert.assertEquals(1, keys.size());
        Assert.assertTrue(keys.contains("two"));

        keys = template.keys("*");
        System.out.println(keys);
        Assert.assertTrue(keys.containsAll(map.keySet()));
        Assert.assertTrue(map.keySet().containsAll(keys));

    }
}
