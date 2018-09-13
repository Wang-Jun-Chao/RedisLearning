package wjc.redis.command.hashes;


import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.junit.Assert;
import org.junit.Test;
import wjc.redis.Command;

import java.util.Map;
import java.util.Set;

/**
 * <pre>
 *
 * </pre>
 * Author: 王俊超
 * Date: 2018-09-13 07:23
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class HKeys extends Command<String, Integer> {
    @Test
    @Override
    public void testTemplate() {
        Map<String, String> map = Maps.newHashMap();
        map.put("field1", "Hello");
        map.put("field2", "World");
        template.opsForHash().putAll("myhash", map);

        Set<Object> keys = template.opsForHash().keys("myhash");
        System.out.println(keys);
        Assert.assertTrue(keys.containsAll(map.keySet()));
        Assert.assertTrue(map.keySet().containsAll(keys));
    }

    @Test
    @Override
    public void testConnection() {
        Map<String, String> map = Maps.newHashMap();
        map.put("field1", "Hello");
        map.put("field2", "World");
        template.opsForHash().putAll("myhash", map);

        Set<byte[]> byteKeys = connection.hKeys(keySerializer.serialize("myhash"));
        Set<String> keys = Sets.newHashSet();
        byteKeys.forEach(item -> keys.add(hashKeySerializer.deserialize(item)));
        System.out.println(keys);
        Assert.assertTrue(keys.containsAll(map.keySet()));
        Assert.assertTrue(map.keySet().containsAll(keys));
    }
}
