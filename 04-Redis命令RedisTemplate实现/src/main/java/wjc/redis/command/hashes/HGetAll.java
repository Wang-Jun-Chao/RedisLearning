package wjc.redis.command.hashes;


import com.google.common.collect.Maps;
import org.junit.Assert;
import org.junit.Test;
import wjc.redis.Command;

import java.util.Map;

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
public class HGetAll extends Command<String, String> {
    @Test
    @Override
    public void testTemplate() {
        Map<String, String> map = Maps.newHashMap();
        map.put("field1", "Hello");
        map.put("field2", "World");
        template.opsForHash().putAll("myhash", map);

        Map<Object, Object> value = template.opsForHash().entries("myhash");
        System.out.println(value);
        Assert.assertEquals(2, value.size());

        Assert.assertTrue(map.keySet().containsAll(value.keySet()));
        Assert.assertTrue(map.values().containsAll(value.values()));
        Assert.assertTrue(value.keySet().containsAll(map.keySet()));
        Assert.assertTrue(value.values().containsAll(map.values()));

    }

    @Test
    @Override
    public void testConnection() {
        Map<String, String> map = Maps.newHashMap();
        map.put("field1", "Hello");
        map.put("field2", "World");
        template.opsForHash().putAll("myhash", map);

        Map<byte[], byte[]> byteValue = connection.hGetAll(keySerializer.serialize("myhash"));
        Map<String, String> value = Maps.newHashMap();
        byteValue.forEach((k, v) -> value.put(hashKeySerializer.deserialize(k), hashValueSerializer.deserialize(v)));

        System.out.println(value);
        Assert.assertEquals(2, value.size());

        Assert.assertTrue(map.keySet().containsAll(value.keySet()));
        Assert.assertTrue(map.values().containsAll(value.values()));
        Assert.assertTrue(value.keySet().containsAll(map.keySet()));
        Assert.assertTrue(value.values().containsAll(map.values()));
    }
}
