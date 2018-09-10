package wjc.redis.command.strings;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.data.redis.core.RedisTemplate;
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
public class BitField extends Command<String, String> {

    @Test
    @Override
    public void testTemplate() {
        RedisTemplate<String, String> template = template(
                new StringRedisSerializer(),
                new StringRedisSerializer());

        template.opsForValue().set("mykey", "foobar");

        Assert.fail("not finish");
    }

    @Test
    @Override
    public void testConnection() {
        RedisTemplate<String, String> template = template(
                new StringRedisSerializer(),
                new StringRedisSerializer());
        connection = connection(template);

        Assert.fail("not finish");
    }
}
