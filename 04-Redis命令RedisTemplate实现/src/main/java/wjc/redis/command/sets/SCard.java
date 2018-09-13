package wjc.redis.command.sets;

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
public class SCard extends Command<String, String> {
    @Test
    @Override
    public void testTemplate() {
        template.opsForSet().add("myset", "Hello", "World", "World");
        Long size = template.opsForSet().size("myset");
        System.out.println(size);
        Assert.assertEquals(Long.valueOf(2), size);
    }

    @Test
    @Override
    public void testConnection() {
        template.opsForSet().add("myset", "Hello", "World", "World");
        Long size = connection.sCard(keySerializer.serialize("myset"));
        System.out.println(size);
        Assert.assertEquals(Long.valueOf(2), size);
    }
}
