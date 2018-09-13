package wjc.redis.command.zsets;

import org.junit.Assert;
import org.junit.Test;
import wjc.redis.Command;

/**
 * <pre>
 *
 * </pre>
 * Author: 王俊超
 * Date: 2018-09-13 22:51
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class ZCard extends Command<String, String> {
    @Test
    @Override
    public void testTemplate() {
        template.opsForZSet().add("myzset", "one", 1);
        template.opsForZSet().add("myzset", "two", 2);

        Long size = template.opsForZSet().size("myzset");
        System.out.println(size);
        Assert.assertEquals(Long.valueOf(2), size);
    }

    @Test
    @Override
    public void testConnection() {
        template.opsForZSet().add("myzset", "one", 1);
        template.opsForZSet().add("myzset", "two", 2);

        Long size = connection.zCard(keySerializer.serialize("myzset"));
        System.out.println(size);
        Assert.assertEquals(Long.valueOf(2), size);
    }
}
