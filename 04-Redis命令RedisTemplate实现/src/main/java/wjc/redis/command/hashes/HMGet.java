package wjc.redis.command.hashes;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Assert;
import org.junit.Test;
import wjc.redis.Command;

import java.util.List;
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
public class HMGet extends Command<String, String> {
    @Test
    @Override
    public void testTemplate() {
        Map<String, String> map = Maps.newHashMap();
        map.put("field1", "Hello");
        map.put("field2", "World");
        template.opsForHash().putAll("myhash", map);

        List<Object> keys = Lists.newArrayList();
        keys.addAll(map.keySet());
        keys.add("field3");

        List<String> v = Lists.newArrayList();
        v.addAll(map.values());
        v.add(null);

        List<Object> values = template.opsForHash().multiGet("myhash", keys);
        System.out.println(values);
        Assert.assertTrue(v.containsAll(values));
        Assert.assertTrue(values.containsAll(v));
    }

    @Test
    @Override
    public void testConnection() {
        Map<String, String> map = Maps.newHashMap();
        map.put("field1", "Hello");
        map.put("field2", "World");
        template.opsForHash().putAll("myhash", map);

        byte[][] keys = new byte[3][];

        keys[0] = hashKeySerializer.serialize("field1");
        keys[1] = hashKeySerializer.serialize("field2");
        keys[2] = hashKeySerializer.serialize("field3");


        List<String> v = Lists.newArrayList();
        v.addAll(map.values());
        v.add(null);

        List<byte[]> byteValues = connection.hMGet(keySerializer.serialize("myhash"), keys);
        List<String> values = Lists.newArrayList();
        byteValues.forEach(item -> values.add(hashValueSerializer.deserialize(item)));
        System.out.println(values);
        Assert.assertTrue(v.containsAll(values));
        Assert.assertTrue(values.containsAll(v));
    }
}
