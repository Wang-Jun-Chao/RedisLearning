package wjc.redis.command.keys;

import org.junit.Assert;
import org.junit.Test;
import wjc.redis.Command;

import java.util.concurrent.TimeUnit;

/**
 * <pre>
 *
 * </pre>
 * Author: 王俊超
 * Date: 2018-09-06 22:25
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class Expire extends Command<String, String> {

    @Test
    public void test() throws InterruptedException {
        template.opsForValue().set("mykey", "Hello");
        template.expire("mykey", 10, TimeUnit.SECONDS);

        Long expire = template.getExpire("mykey");
        System.out.println(expire);

        template.opsForValue().set("mykey", "Hello World");

        TimeUnit.SECONDS.sleep(11);
        expire = template.getExpire("mykey");
        System.out.println(expire);

        Assert.assertEquals(Long.valueOf(-1), expire);
    }
}
