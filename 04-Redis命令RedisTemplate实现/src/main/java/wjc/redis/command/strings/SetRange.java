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
public class SetRange extends Command<String, String> {

    @Test
    @Override
    public void testTemplate() {

        template = template(new GenericToStringSerializer<>(String.class),
                new GenericToStringSerializer<>(String.class));

        template.opsForValue().set("mykey", "Hello World");
        template.opsForValue().set("mykey", "Redis", 6);
        String value = template.opsForValue().get("mykey");
        System.out.println(value);
        Assert.assertEquals("Hello Redis", value);
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
        connection.setRange(keySerializer.serialize("mykey"), valueSerializer.serialize("Redis"), 6);
        byte[] value = connection.get(keySerializer.serialize("mykey"));
        System.out.println(valueSerializer.deserialize(value));
        Assert.assertEquals("Hello Redis", valueSerializer.deserialize(value));

    }
}
