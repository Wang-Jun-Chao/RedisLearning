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
 * Date: 2018-09-09 22:45
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class Ttl extends Command<String, String> {

    @Test
    @Override
    public void testTemplate() {
        template.opsForValue().set("mykey", "Hello");
        template.expire("mykey", 10, TimeUnit.SECONDS);
        Long expire = template.getExpire("mykey");
        System.out.println(expire);
        Assert.assertEquals(Long.valueOf(10), expire);
    }

    @Test
    @Override
    public void testConnection() {
        byte[] mykey = keySerializer.serialize("mykey");
        connection.set(mykey, valueSerializer.serialize("Hello"));
        connection.expire(mykey, 10);
        Long ttl = connection.ttl(mykey);

        System.out.println(ttl);
        Assert.assertEquals(Long.valueOf(10), ttl);
    }
}
