package wjc.redis.command.strings;

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
 * Date: 2018-09-11 07:09
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class MSet extends Command<String, String> {

    @Test
    @Override
    public void testTemplate() {
        Map<String, String> map = Maps.newHashMap();
        map.put("key1", "Hello");
        map.put("key2", "World");
        template.opsForValue().multiSet(map);


        List<String> values = template.opsForValue().multiGet(Lists.newArrayList("key1", "key2", "nonexisting"));
        System.out.println(values);
        Assert.assertEquals(values, Lists.newArrayList("Hello", "World", null));
    }

    @Test
    @Override
    public void testConnection() {

        Map<byte[], byte[]> map = Maps.newHashMap();
        map.put(keySerializer.serialize("key1"), valueSerializer.serialize("Hello"));
        map.put(keySerializer.serialize("key2"), valueSerializer.serialize("World"));
        connection.mSet(map);

        byte[][] keyArray = Lists.newArrayList(keySerializer.serialize("key1"),
                keySerializer.serialize("key2"), keySerializer.serialize("nonexisting")).toArray(new byte[0][0]);
        List<byte[]> bytes = connection.mGet(keyArray);
        List<String> value = Lists.newArrayList();
        bytes.forEach(item -> value.add(valueSerializer.deserialize(item)));

        System.out.println(value);
        Assert.assertEquals(value, Lists.newArrayList("Hello", "World", null));
    }
}
