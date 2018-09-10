package wjc.redis.command.keys;

import com.google.common.collect.Sets;
import com.sun.org.apache.bcel.internal.generic.RET;
import org.junit.Assert;
import org.junit.Test;
import wjc.redis.Command;

import java.util.HashMap;
import java.util.HashSet;
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
    @Override
    public void testTemplate() {
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

    @Test
    @Override
    public void testConnection() {
        Map<byte[], byte[]> map = new HashMap<>();
        map.put(keySerializer.serialize("one"), valueSerializer.serialize(1));
        map.put(keySerializer.serialize("two"), valueSerializer.serialize(2));
        map.put(keySerializer.serialize("three"), valueSerializer.serialize(3));
        map.put(keySerializer.serialize("four"), valueSerializer.serialize(4));
        connection.mSet(map);

        Set<byte[]> keys = connection.keys(keySerializer.serialize("*o*"));
        keys.forEach(item -> System.out.println(keySerializer.deserialize(item)));

        Set<String> set = Sets.newHashSet("one", "four", "two");
        Assert.assertEquals(keys.size(), set.size());
        for (byte[] key : keys) {
            Assert.assertTrue(set.contains(keySerializer.deserialize(key)));
        }

        keys = connection.keys(keySerializer.serialize("t??"));
        Set<String> stringKeys = toStringKyes(keys);
        System.out.println(stringKeys);
        Assert.assertEquals(1, stringKeys.size());
        Assert.assertTrue(stringKeys.contains("two"));

        keys = connection.keys(keySerializer.serialize("*"));
        stringKeys = toStringKyes(keys);
        Set<String> orgineKeys = toStringKyes(map.keySet());

        System.out.println(stringKeys);
        Assert.assertTrue(stringKeys.containsAll(orgineKeys));
        Assert.assertTrue(orgineKeys.containsAll(stringKeys));
    }

    private Set<String> toStringKyes(Set<byte[]> keys) {
        Set<String> set = new HashSet<>();

        keys.forEach(item-> set.add(keySerializer.deserialize(item)));

        return set;
    }
}
