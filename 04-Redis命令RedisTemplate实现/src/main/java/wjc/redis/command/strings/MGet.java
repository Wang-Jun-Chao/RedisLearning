package wjc.redis.command.strings;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;
import wjc.redis.Command;

import java.util.List;

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
public class MGet extends Command<String, String> {

    @Test
    @Override
    public void testTemplate() {
        template.opsForValue().set("key1", "Hello");
        template.opsForValue().set("key2", "World");

        List<String> values = template.opsForValue().multiGet(Lists.newArrayList("key1", "key2"));
        System.out.println(values);
        Assert.assertEquals(values, Lists.newArrayList("Hello", "World"));
    }

    @Test
    @Override
    public void testConnection() {

        connection.set(keySerializer.serialize("key1"), valueSerializer.serialize("Hello"));
        connection.set(keySerializer.serialize("key2"), valueSerializer.serialize("World"));
        byte[][] keyArray = Lists.newArrayList(keySerializer.serialize("key1"),
                keySerializer.serialize("key2")).toArray(new byte[0][0]);
        List<byte[]> bytes = connection.mGet(keyArray);
        List<String> value = Lists.newArrayList();
        bytes.forEach(item -> value.add(valueSerializer.deserialize(item)));

        System.out.println(value);
        Assert.assertEquals(value, Lists.newArrayList("Hello", "World"));
    }
}
