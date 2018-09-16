package wjc.redis.command.hll;

import org.junit.Assert;
import org.junit.Test;
import wjc.redis.Command;

/**
 * <pre>
 *
 * </pre>
 * Author: 王俊超
 * Date: 2018-09-16 08:51
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class PFCount extends Command<String, String> {
    @Test
    @Override
    public void testTemplate() {
        Long add = template.opsForHyperLogLog().add("hll", "a", "b", "c", "d", "e", "f", "g");
        System.out.println(add);
        Assert.assertEquals(Long.valueOf(1), add);

        Long size = template.opsForHyperLogLog().size("hll");
        System.out.println(size);
        Assert.assertEquals(Long.valueOf(7), size);
    }

    @Test
    @Override
    public void testConnection() {
        Long add = template.opsForHyperLogLog().add("hll", "a", "b", "c", "d", "e", "f", "g");

        System.out.println(add);
        Assert.assertEquals(Long.valueOf(1), add);

        Long size = connection.pfCount(keySerializer.serialize("hll"));
        System.out.println(size);
        Assert.assertEquals(Long.valueOf(7), size);
    }
}
