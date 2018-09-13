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
public class HStrLen extends Command<String, Object> {
    @Test
    @Override
    public void testTemplate() {
        Assert.fail("RedisTemplate dose not have hstrlen command");
    }

    @Test
    @Override
    public void testConnection() {
        Assert.fail("RedisConnection dose not have hstrlen command");
    }
}
