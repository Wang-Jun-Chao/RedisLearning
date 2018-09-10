package wjc.redis.command.strings;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import wjc.redis.Command;

/**
 * <pre>
 *
 * </pre>
 * Author: 王俊超
 * Date: 2018-09-10 21:42
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class Append extends Command<String, String> {
    @Test
    @Override
    public void testTemplate() {
        RedisTemplate<String, String> template = template(
                new StringRedisSerializer(),
                new StringRedisSerializer());
        Boolean mykey = template.hasKey("mykey");
        Assert.assertFalse(mykey);

        template.opsForValue().append("mykey", "Hello");
        template.opsForValue().append("mykey", " World");

        Object value = template.opsForValue().get("mykey");
        System.out.println(value);
        Assert.assertEquals(value, "Hello World");
    }


    @Test
    @Override
    public void testConnection() {
        RedisTemplate<String, String> template = template(
                new StringRedisSerializer(),
                new StringRedisSerializer());
        RedisConnection connection = connection(template);

        RedisSerializer keySerializer = template.getKeySerializer();
        RedisSerializer valueSerializer = template.getValueSerializer();

        Boolean exists = connection.exists(keySerializer.serialize("mykey"));
        Assert.assertFalse(exists);

        connection.append(keySerializer.serialize("mykey"),
                valueSerializer.serialize("Hello"));
        connection.append(keySerializer.serialize("mykey"),
                valueSerializer.serialize(" World"));

        byte[] value = connection.get(keySerializer.serialize("mykey"));
        System.out.println(valueSerializer.deserialize(value));
        Assert.assertEquals("Hello World", valueSerializer.deserialize(value));

    }
}
