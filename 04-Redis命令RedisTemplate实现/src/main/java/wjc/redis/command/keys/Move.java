package wjc.redis.command.keys;

import org.junit.Test;
import wjc.redis.Command;

/**
 * <pre>
 *
 * </pre>
 * Author: 王俊超
 * Date: 2018-09-07 07:25
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class Move extends Command<String, String> {

    @Test
    public void test() {
        template.opsForValue().set("mykey", "Hello World");
        template.move("mykey", 1);
    }
}
