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
public class HLen extends Command<String, Integer> {
    @Test
    @Override
    public void testTemplate() {
        Map<String, String> map = Maps.newHashMap();
        map.put("field1", "Hello");
        map.put("field2", "World");
        template.opsForHash().putAll("myhash", map);

        Long size = template.opsForHash().size("myhash");
        System.out.println(size);
        Assert.assertEquals(Long.valueOf(2), size);
    }

    @Test
    @Override
    public void testConnection() {
        Map<String, String> map = Maps.newHashMap();
        map.put("field1", "Hello");
        map.put("field2", "World");
        template.opsForHash().putAll("myhash", map);

        Long size = connection.hLen(keySerializer.serialize("myhash"));
        System.out.println(size);
        Assert.assertEquals(Long.valueOf(2), size);
    }
}
