package wjc.redis.command.keys;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;
import wjc.redis.Command;

import java.util.Collection;

/**
 * <pre>
 *
 * </pre>
 * Author: 王俊超
 * Date: 2018-09-06 21:36
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class Del extends Command<String, String> {

    @Test
    @Override
    public void testTemplate() {
        template.opsForValue().set("key1", "Hello");
        template.opsForValue().set("key2", "World");

        Collection<String> list = Lists.newArrayList("key1", "key2", "key3");
        Long delete = template.delete(list);

        Assert.assertEquals(Long.valueOf(2), delete);
    }

    @Test
    @Override
    public void testConnection() {
        template.opsForValue().set("key1", "Hello");
        template.opsForValue().set("key2", "World");

        byte[][] keys = new byte[][]{
                keySerializer.serialize("key1"),
                keySerializer.serialize("key2"),
                keySerializer.serialize("key3")};
        Long delete = connection.del(keys);
        System.out.println(delete);
        Assert.assertEquals(Long.valueOf(2), delete);
    }
}
