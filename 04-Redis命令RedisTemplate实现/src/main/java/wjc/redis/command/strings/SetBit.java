package wjc.redis.command.strings;

import org.junit.Assert;
import org.junit.Test;
import wjc.redis.Command;

import java.util.Arrays;

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
public class SetBit extends Command<String, String> {

    @Test
    @Override
    public void testTemplate() {

        template.opsForValue().setBit("mykey", 7, true);
        Boolean value = template.opsForValue().getBit("mykey", 7);
        System.out.println(value);
        Assert.assertTrue(value);

        template.opsForValue().setBit("mykey", 7, false);
        value = template.opsForValue().getBit("mykey", 7);
        System.out.println(value);
        Assert.assertFalse(value);
    }

    @Test
    @Override
    public void testConnection() {

        connection.setBit(keySerializer.serialize("mykey"), 7, true);
        byte[] value = connection.get(keySerializer.serialize("mykey"));
        System.out.println(Arrays.toString(value));
        Assert.assertEquals(1, value[0]);

    }
}
