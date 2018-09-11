package wjc.redis.command.strings;

import org.junit.Assert;
import org.junit.Test;
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
public class StrLen extends Command<String, String> {

    @Test
    @Override
    public void testTemplate() {

        template = template(new GenericToStringSerializer<>(String.class),
                new GenericToStringSerializer<>(String.class));

        template.opsForValue().set("mykey", "Hello World");
        Long size = template.opsForValue().size("mykey");
        System.out.println(size);
        Assert.assertEquals(Long.valueOf(11), size);
    }

    @Test
    @Override
    public void testConnection() {

        template = template(new GenericToStringSerializer<>(String.class),
                new GenericToStringSerializer<>(String.class));
        connection = connection(template);
        keySerializer = keySerializer(template);
        valueSerializer = valueSerializer(template);
        connection.set(keySerializer.serialize("mykey"), valueSerializer.serialize("Hello World"));
        Long len = connection.strLen(keySerializer.serialize("mykey"));
        System.out.println(len);
        Assert.assertEquals(Long.valueOf(11), len);

    }
}
