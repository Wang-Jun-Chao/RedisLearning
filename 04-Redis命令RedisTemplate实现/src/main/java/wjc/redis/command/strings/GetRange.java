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
public class GetRange extends Command<String, String> {

    @Test
    @Override
    public void testTemplate() {
        template.opsForValue().set("mykey", "This is a string");
        Assert.fail("RedisTemplate dose not have getrange command");
    }

    @Test
    @Override
    public void testConnection() {
        RedisTemplate<String, String> template = template(
                new GenericToStringSerializer<>(String.class),
                new GenericToStringSerializer<>(String.class));
        RedisSerializer keySerializer = template.getKeySerializer();
        RedisSerializer valueSerializer = template.getValueSerializer();
        template.opsForValue().set("mykey", "This is a string");
        byte[] b = connection.getRange(keySerializer.serialize("mykey"), 0, 3);
        System.out.println(valueSerializer.deserialize(b));
        Assert.assertEquals("This", valueSerializer.deserialize(b));


        b = connection.getRange(keySerializer.serialize("mykey"), -3, -1);
        System.out.println(valueSerializer.deserialize(b));
        Assert.assertEquals("ing", valueSerializer.deserialize(b));

        b = connection.getRange(keySerializer.serialize("mykey"), 0, -1);
        System.out.println(valueSerializer.deserialize(b));
        Assert.assertEquals("This is a string", valueSerializer.deserialize(b));

        b = connection.getRange(keySerializer.serialize("mykey"), 10, 100);
        System.out.println(valueSerializer.deserialize(b));
        Assert.assertEquals("string", valueSerializer.deserialize(b));

        // 完全超出就返回空
        b = connection.getRange(keySerializer.serialize("mykey"), 100, 1000);
        System.out.println(valueSerializer.deserialize(b));
        Assert.assertEquals("", valueSerializer.deserialize(b));
    }
}
