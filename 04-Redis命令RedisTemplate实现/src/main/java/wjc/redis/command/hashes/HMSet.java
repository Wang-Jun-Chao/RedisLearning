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
public class HMSet extends Command<String, String> {
    @Test
    @Override
    public void testTemplate() {
        Map<String, String> map = Maps.newHashMap();
        map.put("field1", "Hello");
        map.put("field2", "World");
        template.opsForHash().putAll("myhash", map);

        Object value = template.opsForHash().get("myhash", "field1");
        System.out.println(value);
        Assert.assertEquals("Hello", value);

        value = template.opsForHash().get("myhash", "field2");
        System.out.println(value);
        Assert.assertEquals("World", value);
    }

    @Test
    @Override
    public void testConnection() {
        Map<byte[], byte[]> map = Maps.newHashMap();
        map.put(hashKeySerializer.serialize("field1"), hashValueSerializer.serialize("Hello"));
        map.put(hashKeySerializer.serialize("field2"), hashValueSerializer.serialize("World"));
        connection.hMSet(keySerializer.serialize("myhash"), map);

        Object value = template.opsForHash().get("myhash", "field1");
        System.out.println(value);
        Assert.assertEquals("Hello", value);

        value = template.opsForHash().get("myhash", "field2");
        System.out.println(value);
        Assert.assertEquals("World", value);
    }
}
