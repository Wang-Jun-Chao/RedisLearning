package wjc.redis.command.strings;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
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
public class BitPos extends Command<String, String> {

    @Test
    @Override
    public void testTemplate() {
        RedisTemplate<String, String> template = template(
                new GenericToStringSerializer<>(String.class),
                new GenericToStringSerializer<>(String.class));

        template.opsForValue().set("mykey", "\\xff\\xf0\\x00");


        Assert.fail("not finish");
    }

    @Test
    @Override
    public void testConnection() {
        RedisTemplate<String, String> template = template(
                new GenericToStringSerializer<>(String.class),
                new GenericToStringSerializer<>(String.class));

        template.opsForValue().set("mykey", "\\xff\\xf0\\x00");


        connection = connection(template);
        keySerializer = keySerializer(template);
        valueSerializer = valueSerializer(template);


        Assert.fail("not finish");
    }
}
