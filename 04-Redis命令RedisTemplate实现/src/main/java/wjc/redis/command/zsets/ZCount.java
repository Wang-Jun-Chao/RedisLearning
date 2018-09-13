package wjc.redis.command.zsets;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.data.redis.connection.RedisZSetCommands;
import wjc.redis.Command;

/**
 * <pre>
 *
 * </pre>
 * Author: 王俊超
 * Date: 2018-09-14 06:50
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class ZCount extends Command<String, String> {
    @Test
    @Override
    public void testTemplate() {
        template.opsForZSet().add("myzset", "one", 1);
        template.opsForZSet().add("myzset", "two", 2);
        template.opsForZSet().add("myzset", "three", 3);

        Long myzset = template.opsForZSet().count("myzset",
                Double.MIN_VALUE, Double.MAX_VALUE);

        System.out.println(myzset);
        Assert.assertEquals(Long.valueOf(3), myzset);

    }

    @Test
    @Override
    public void testConnection() {
        template.opsForZSet().add("myzset", "one", 1);
        template.opsForZSet().add("myzset", "two", 2);
        template.opsForZSet().add("myzset", "three", 3);

        Long myzset = connection.zCount(keySerializer.serialize("myzset"),
                Double.MIN_VALUE, Double.MAX_VALUE);
        System.out.println(myzset);
        Assert.assertEquals(Long.valueOf(3), myzset);

        myzset = connection.zCount(keySerializer.serialize("myzset"),
                RedisZSetCommands.Range.range().gt(1).lte(3));
        System.out.println(myzset);
        Assert.assertEquals(Long.valueOf(2), myzset);
    }
}
