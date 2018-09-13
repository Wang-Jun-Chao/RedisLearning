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
public class HIncrBy extends Command<String, Integer> {
    @Test
    @Override
    public void testTemplate() {

        template.opsForHash().put("myhash", "field", 5);
        Long value = template.opsForHash().increment("myhash", "field", 1);
        System.out.println(value);
        Assert.assertEquals(Long.valueOf(6), value);


        value = template.opsForHash().increment("myhash", "field", -1);
        System.out.println(value);
        Assert.assertEquals(Long.valueOf(5), value);

        value = template.opsForHash().increment("myhash", "field", -10);
        System.out.println(value);
        Assert.assertEquals(Long.valueOf(-5), value);
    }

    @Test
    @Override
    public void testConnection() {
        template.opsForHash().put("myhash", "field", 5);

        byte[] myhash = keySerializer.serialize("myhash");
        byte[] field = hashKeySerializer.serialize("field");
        Long value = connection.hIncrBy(myhash, field, 1);
        System.out.println(value);
        Assert.assertEquals(Long.valueOf(6), value);


        value = connection.hIncrBy(myhash, field, -1);
        System.out.println(value);
        Assert.assertEquals(Long.valueOf(5), value);

        value = connection.hIncrBy(myhash, field, -10);
        System.out.println(value);
        Assert.assertEquals(Long.valueOf(-5), value);
    }
}
