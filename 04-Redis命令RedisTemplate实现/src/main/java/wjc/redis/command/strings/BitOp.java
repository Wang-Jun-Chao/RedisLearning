package wjc.redis.command.strings;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
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
public class BitOp extends Command<String, String> {

    @Test
    @Override
    public void testTemplate() {
        RedisTemplate<String, String> template = template(
                new GenericToStringSerializer<>(String.class),
                new GenericToStringSerializer<>(String.class));

        template.opsForValue().set("key1", "foobar");
        template.opsForValue().set("key2", "abcdef");

        Assert.fail("not finish");
    }

    @Test
    @Override
    public void testConnection() {
        RedisTemplate<String, String> template = template(
                new StringRedisSerializer(),
                new StringRedisSerializer());
        template.opsForValue().set("key1", "foobar");
        template.opsForValue().set("key2", "abcdef");

        connection = connection(template);
        keySerializer = keySerializer(template);
        valueSerializer = valueSerializer(template);
        connection.bitOp(RedisStringCommands.BitOperation.AND, keySerializer.serialize("dest"),
                keySerializer.serialize("key1"), keySerializer.serialize("key2"));

        String dest = template.opsForValue().get("dest");
        System.out.println(dest);

        Assert.assertNotNull(dest);
    }
}
