package wjc.redis.command.keys;

import org.junit.Assert;
import org.junit.Test;
import wjc.redis.Command;

/**
 * <pre>
 *
 * </pre>
 * Author: 王俊超
 * Date: 2018-09-09 22:45
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class Wait extends Command<String, String> {

    @Test
    @Override
    public void testTemplate() {
        System.out.println("RedisTemplate dose not has wait command");
        Assert.fail();
    }

    @Test
    @Override
    public void testConnection() {
        System.out.println("RedisConnection dose not has wait command");
        Assert.fail();
    }
}
