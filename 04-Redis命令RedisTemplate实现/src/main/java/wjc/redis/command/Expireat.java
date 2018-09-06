package wjc.redis.command;

import org.junit.Test;
import wjc.redis.Command;

/**
 * <pre>
 *
 * </pre>
 * Author: 王俊超
 * Date: 2018-09-06 22:33
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class Expireat extends Command<String, String> {

    @Test
    public void test() {
        System.err.println("RedisTemplate dose not has expireat command, use expire");
    }
}
