package wjc.redis.command.strings;

import org.junit.Assert;
import org.junit.Test;
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
public class Set extends Command<String, String> {

    @Test
    @Override
    public void testTemplate() {

        template.opsForValue().set("mykey", "Hello");
        String value = template.opsForValue().get("mykey");
        System.out.println(value);
        Assert.assertEquals("Hello", value);
    }

    @Test
    @Override
    public void testConnection() {


        connection.set(keySerializer.serialize("key"), valueSerializer.serialize("Hello"));
        byte[] value = connection.get(keySerializer.serialize("key"));
        System.out.println(valueSerializer.deserialize(value));
        Assert.assertEquals("Hello", valueSerializer.deserialize(value));
    }
}
