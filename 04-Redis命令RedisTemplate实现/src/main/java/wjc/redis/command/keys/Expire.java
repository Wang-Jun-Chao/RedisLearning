package wjc.redis.command.keys;

import org.junit.Assert;
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


    @Override
    public void testTemplate() {
        template.opsForValue().set("mykey", "Hello");
        template.expire("mykey", 10, TimeUnit.SECONDS);

        Long expire = template.getExpire("mykey");
        System.out.println(expire);

        template.opsForValue().set("mykey", "Hello World");

        try {
            TimeUnit.SECONDS.sleep(11);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        expire = template.getExpire("mykey");
        System.out.println(expire);

        Assert.assertEquals(Long.valueOf(-1), expire);
    }

    @Override
    public void testConnection() {
        template.opsForValue().set("mykey", "Hello");
        connection.expire(keySerializer.serialize("mykey"), 10);

        Boolean exists = connection.exists(keySerializer.serialize("mykey"));
        Assert.assertTrue(exists);

        Long ttl = connection.ttl(keySerializer.serialize("mykey"));
        System.out.println(ttl);

        template.opsForValue().set("mykey", "Hello World");

        try {
            TimeUnit.SECONDS.sleep(11);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ttl = connection.ttl(keySerializer.serialize("mykey"));
        Assert.assertEquals(Long.valueOf(-1), ttl);
    }
}
