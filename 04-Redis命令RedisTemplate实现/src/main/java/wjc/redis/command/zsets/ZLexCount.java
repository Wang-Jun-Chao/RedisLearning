package wjc.redis.command.zsets;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.ZSetOperations;
import wjc.redis.Command;

import java.util.Set;

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
public class ZLexCount extends Command<String, String> {
    @Test
    @Override
    public void testTemplate() {
      Assert.fail("RedisTemplate dose not have zlexcount command");
    }

    @Test
    @Override
    public void testConnection() {
        Assert.fail("RedisConnection dose not have zlexcount command");
    }

}
