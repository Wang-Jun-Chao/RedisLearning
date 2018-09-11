package wjc.redis.command.strings;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import wjc.redis.Command;

import java.util.Arrays;

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
public class Get extends Command<String, String> {

    @Test
    @Override
    public void testTemplate() {
        String s = template.opsForValue().get("noexisting");
        System.out.println(s);
        Assert.assertNull(s);

        template.opsForValue().set("mykey", "Hello");
        String mykey = template.opsForValue().get("mykey");
        System.out.println(mykey);
        Assert.assertEquals("Hello", mykey);


    }

    @Test
    @Override
    public void testConnection() {
        byte[] bytes = connection.get(keySerializer.serialize("nonexisting"));
        System.out.println(Arrays.toString(bytes));
        Assert.assertNull(bytes);

        connection.set(keySerializer.serialize("mykey"), valueSerializer.serialize("Hello"));
        bytes = connection.get(keySerializer.serialize("mykey"));

        System.out.println(valueSerializer.deserialize(bytes));
        Assert.assertEquals("Hello", valueSerializer.deserialize(bytes));
    }
}
