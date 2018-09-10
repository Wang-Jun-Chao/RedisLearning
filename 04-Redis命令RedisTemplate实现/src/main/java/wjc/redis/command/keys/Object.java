package wjc.redis.command.keys;

import org.junit.Assert;
import org.junit.Test;
import wjc.redis.Command;

/**
 * <pre>
 *
 * </pre>
 * Author: 王俊超
 * Date: 2018-09-07 07:30
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class Object extends Command<String, String> {
    @Test
    @Override
    public void testTemplate() {
        System.out.println("RedisTemplate dose not have command");

    }

    @Test
    @Override
    public void testConnection() {
        System.out.println("RedisConnection dose not have command");
    }
}
