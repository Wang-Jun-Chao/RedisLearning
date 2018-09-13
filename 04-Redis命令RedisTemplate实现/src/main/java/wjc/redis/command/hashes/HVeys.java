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
public class HVeys extends Command<String, Integer> {
    @Test
    @Override
    public void testTemplate() {
        Map<String, String> map = Maps.newHashMap();
        map.put("field1", "Hello");
        map.put("field2", "World");
        template.opsForHash().putAll("myhash", map);

        List<Object> values = template.opsForHash().values("myhash");
        System.out.println(values);
        Assert.assertTrue(values.containsAll(map.values()));
        Assert.assertTrue(map.values().containsAll(values));
    }

    @Test
    @Override
    public void testConnection() {
        Map<String, String> map = Maps.newHashMap();
        map.put("field1", "Hello");
        map.put("field2", "World");
        template.opsForHash().putAll("myhash", map);

        List<byte[]> byteValues = connection.hVals(keySerializer.serialize("myhash"));
        List<String> values = Lists.newArrayList();
        byteValues.forEach(item -> values.add(hashKeySerializer.deserialize(item)));
        System.out.println(values);
        Assert.assertTrue(values.containsAll(map.values()));
        Assert.assertTrue(map.values().containsAll(values));
    }
}
