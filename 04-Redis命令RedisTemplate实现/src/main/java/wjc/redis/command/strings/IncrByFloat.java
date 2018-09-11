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
public class IncrByFloat extends Command<String, Double> {

    @Test
    @Override
    public void testTemplate() {
        String key = "mykey";
        template.opsForValue().set(key, 10.50);
        template.opsForValue().increment(key, 0.1);

        Double mycounter = template.opsForValue().get(key);
        System.out.println(mycounter);
        Assert.assertEquals(Double.valueOf(10.60), mycounter);

        template.opsForValue().set(key, 5.0E3);
        template.opsForValue().increment(key, 2.0E2);

        mycounter = template.opsForValue().get(key);
        System.out.println(mycounter);
        Assert.assertEquals(5200.0, mycounter, 0.000001);
    }

    @Test
    @Override
    public void testConnection() {
        byte[] key = keySerializer.serialize("mykey");
        connection.set(key, valueSerializer.serialize(10.50));
        connection.incrBy(key, 0.1);
        byte[] value = connection.get(key);
        System.out.println(valueSerializer.deserialize(value));
        Assert.assertEquals(Double.valueOf(10.60), valueSerializer.deserialize(value));

        connection.set(key, valueSerializer.serialize(5.0001E3));
        connection.incrBy(key, 2.001E2);
        value = connection.get(key);
        System.out.println(valueSerializer.deserialize(value));
        Assert.assertEquals(5200.2, valueSerializer.deserialize(value), 0.000001);
    }
}
