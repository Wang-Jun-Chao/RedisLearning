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
public class BitCount extends Command<String, String> {
    @Test
    @Override
    public void testTemplate() {
        RedisTemplate<String, String> template = template(
                new StringRedisSerializer(),
                new StringRedisSerializer());

        template.opsForValue().set("mykey", "foobar");

        Assert.fail();

    }


    @Test
    @Override
    public void testConnection() {
        RedisTemplate<String, String> template = template(
                new StringRedisSerializer(),
                new StringRedisSerializer());
        RedisSerializer keySerializer = template.getKeySerializer();

        template.opsForValue().set("mykey", "foobar");
        RedisConnection connection = connection(template);

        char[] chars = "foobar".toCharArray();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            builder.append("-").append(Integer.toBinaryString(chars[i]));
        }
        System.out.println(builder.substring(1));

        Long count = connection.bitCount(keySerializer.serialize("mykey"));
        System.out.println(count);
        Assert.assertEquals(Long.valueOf(26), count);

        count = connection.bitCount(keySerializer.serialize("mykey"), 0, 0);
        System.out.println(count);
        Assert.assertEquals(Long.valueOf(4), count);

        count = connection.bitCount(keySerializer.serialize("mykey"), 1, 1);
        System.out.println(count);
        Assert.assertEquals(Long.valueOf(6), count);
    }
}
