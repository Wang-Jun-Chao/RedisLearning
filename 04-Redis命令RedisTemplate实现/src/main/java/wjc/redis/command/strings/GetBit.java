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
public class GetBit extends Command<String, String> {

    @Test
    @Override
    public void testTemplate() {
        boolean b = template.opsForValue().setBit("mykey", 7, true);
        System.out.println(b);
        Assert.assertFalse(b);

        b = template.opsForValue().getBit("mykey", 0);
        System.out.println(b);
        Assert.assertFalse(b);

        b = template.opsForValue().getBit("mykey", 7);
        System.out.println(b);
        Assert.assertTrue(b);

        b = template.opsForValue().getBit("mykey", 100);
        System.out.println(b);
        Assert.assertFalse(b);
    }

    @Test
    @Override
    public void testConnection() {
        boolean b = connection.setBit(keySerializer.serialize("mykey"), 7, true);
        System.out.println(b);
        Assert.assertFalse(b);

        b = connection.getBit(keySerializer.serialize("mykey"), 0);
        System.out.println(b);
        Assert.assertFalse(b);

        b = connection.getBit(keySerializer.serialize("mykey"), 7);
        System.out.println(b);
        Assert.assertTrue(b);

        b = connection.getBit(keySerializer.serialize("mykey"), 100);
        System.out.println(b);
        Assert.assertFalse(b);
    }
}
