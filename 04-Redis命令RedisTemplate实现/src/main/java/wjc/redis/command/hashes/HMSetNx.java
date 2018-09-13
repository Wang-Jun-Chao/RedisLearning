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
public class HMSetNx extends Command<String, String> {
    @Test
    @Override
    public void testTemplate() {
        template.opsForHash().putIfAbsent("myhash", "field", "Hello");
        template.opsForHash().putIfAbsent("myhash", "field", "World");

        Object value = template.opsForHash().get("myhash", "field");
        System.out.println(value);
        Assert.assertEquals("Hello", value);
    }

    @Test
    @Override
    public void testConnection() {
        connection.hSetNX(keySerializer.serialize("myhash"),
                hashKeySerializer.serialize("field"),
                hashValueSerializer.serialize("Hello"));
        connection.hSetNX(keySerializer.serialize("myhash"),
                hashKeySerializer.serialize("field"),
                hashValueSerializer.serialize("World"));

        Object value = template.opsForHash().get("myhash", "field");
        System.out.println(value);
        Assert.assertEquals("Hello", value);
    }
}
