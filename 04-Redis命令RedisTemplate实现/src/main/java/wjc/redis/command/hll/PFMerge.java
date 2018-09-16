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
public class PFMerge extends Command<String, String> {
    @Test
    @Override
    public void testTemplate() {
        template.opsForHyperLogLog().add("hll1", "foo", "bar", "zip", "a");
        template.opsForHyperLogLog().add("hll2", "a", "b", "c", "foo");


        Long size = template.opsForHyperLogLog().union("hll3", "hll1", "hll2");
        System.out.println(size);
        Assert.assertEquals(Long.valueOf(6), size);
    }

    @Test
    @Override
    public void testConnection() {
        template.opsForHyperLogLog().add("hll1", "foo", "bar", "zip", "a");
        template.opsForHyperLogLog().add("hll2", "a", "b", "c", "foo");


        connection.pfMerge(
                keySerializer.serialize("hll3"),
                keySerializer.serialize("hll1"),
                keySerializer.serialize("hll2"));
        Long size = template.opsForHyperLogLog().size("hll3");
        System.out.println(size);
        Assert.assertEquals(Long.valueOf(6), size);
    }
}
