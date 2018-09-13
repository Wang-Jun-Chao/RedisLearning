package wjc.redis.command.hashes;


import org.junit.Assert;
import org.junit.Test;
import wjc.redis.Command;

/**
 * <pre>
 *
 * </pre>
 * Author: 王俊超
 * Date: 2018-09-13 07:23
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class HIncrByFload extends Command<String, Integer> {
    @Test
    @Override
    public void testTemplate() {

        template.opsForHash().put("myhash", "field", 10.50);
        Double value = template.opsForHash().increment("myhash", "field", 0.1);
        System.out.println(value);
        Assert.assertEquals(Double.valueOf(10.6), value);


        template.opsForHash().put("myhash", "field", 5.0000e3);
        value = template.opsForHash().increment("myhash", "field", 2.000e2);
        System.out.println(value);
        Assert.assertEquals(Double.valueOf(5200), value);

    }

    @Test
    @Override
    public void testConnection() {
        template.opsForHash().put("myhash", "field", 10.50);

        byte[] myhash = keySerializer.serialize("myhash");
        byte[] field = hashKeySerializer.serialize("field");
        Double value = connection.hIncrBy(myhash, field, 0.1);
        System.out.println(value);
        Assert.assertEquals(Double.valueOf(10.6), value);

        template.opsForHash().put("myhash", "field", 5.0000e3);
        value = connection.hIncrBy(myhash, field, 2.000e2);
        System.out.println(value);
        Assert.assertEquals(Double.valueOf(5200), value);
    }
}
