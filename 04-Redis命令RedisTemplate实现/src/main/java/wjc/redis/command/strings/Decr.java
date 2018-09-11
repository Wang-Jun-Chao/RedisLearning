package wjc.redis.command.strings;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.data.redis.RedisSystemException;
import org.springframework.data.redis.connection.RedisConnection;
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
public class Decr extends Command<String, Long> {

    @Test
    @Override
    public void testTemplate() {
        template.opsForValue().set("mykey", 10L);


        Assert.fail("not finish");
    }

    @Test
    @Override
    public void testConnection() {
        RedisTemplate<String, String> template = template(
                new GenericToStringSerializer<>(String.class),
                new GenericToStringSerializer<>(String.class));
        RedisSerializer<String> keySerializer = keySerializer(template);
        template.opsForValue().set("mykey", "10");
        RedisConnection connection = connection(template);

        Long mykey = connection.decr(keySerializer.serialize("mykey"));
        System.out.println(mykey);
        Assert.assertEquals(Long.valueOf(9L), mykey);
    }

    @Test(expected = RedisSystemException.class)
    public void testConnectionException() {
        RedisTemplate<String, String> template = template(
                new GenericToStringSerializer<>(String.class),
                new GenericToStringSerializer<>(String.class));
        RedisSerializer<String> keySerializer = keySerializer(template);

        template.opsForValue().set("mykey", "234293482390480948029348230948");
        Long mykey = connection.decr(keySerializer.serialize("mykey"));
        System.out.println(mykey);
        Assert.assertEquals(Long.valueOf(9L), mykey);
    }
}
