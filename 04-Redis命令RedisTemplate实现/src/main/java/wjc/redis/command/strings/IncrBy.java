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
public class IncrBy extends Command<String, Integer> {

    @Test
    @Override
    public void testTemplate() {
        String key = "mykey";
        template.opsForValue().set(key, 10);
        template.opsForValue().increment(key, 5);

        Integer mycounter = template.opsForValue().get(key);
        System.out.println(mycounter);
        Assert.assertEquals(Integer.valueOf(15), mycounter);

    }

    @Test
    @Override
    public void testConnection() {
        byte[] key = keySerializer.serialize("mykey");
        connection.set(key, valueSerializer.serialize(10));
        connection.incrBy(key, 5L);
        byte[] value = connection.get(key);
        System.out.println(valueSerializer.deserialize(value));
        Assert.assertEquals(Integer.valueOf(15), valueSerializer.deserialize(value));
    }
}
