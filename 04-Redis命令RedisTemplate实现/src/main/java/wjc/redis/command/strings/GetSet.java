package wjc.redis.command.strings;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import wjc.redis.Command;

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
public class GetSet extends Command<String, Integer> {

    @Test
    @Override
    public void testTemplate() {
        template.opsForValue().increment("mycounter", 1);
        Integer mycounter = template.opsForValue().getAndSet("mycounter", 0);
        System.out.println(mycounter);
        Assert.assertEquals(Integer.valueOf(1), mycounter);

        mycounter = template.opsForValue().get("mycounter");
        System.out.println(mycounter);
        Assert.assertEquals(Integer.valueOf(0), mycounter);

    }

    @Test
    @Override
    public void testConnection() {
        byte[] mycounter = keySerializer.serialize("mycounter");
        connection.incr(mycounter);
        byte[] value = connection.getSet(mycounter, valueSerializer.serialize(0));
        System.out.println(valueSerializer.deserialize(value));
        Assert.assertEquals(Integer.valueOf(1), valueSerializer.deserialize(value));

        value = connection.get(mycounter);
        System.out.println(valueSerializer.deserialize(value));
        Assert.assertEquals(Integer.valueOf(0), valueSerializer.deserialize(value));
    }
}
