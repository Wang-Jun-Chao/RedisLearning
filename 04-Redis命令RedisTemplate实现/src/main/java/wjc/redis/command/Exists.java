package wjc.redis.command;

import org.junit.Assert;
import org.junit.Test;
import wjc.redis.Command;

/**
 * <pre>
 *
 * </pre>
 * Author: 王俊超
 * Date: 2018-09-06 22:14
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class Exists extends Command<String, String> {

    @Test
    public void test() {
        template.opsForValue().set("key1", "Hello");

        Boolean hasKey = template.hasKey("key1");
        Assert.assertEquals(true, hasKey);

        hasKey = template.hasKey("key2");
        Assert.assertEquals(false, hasKey);
    }
}
