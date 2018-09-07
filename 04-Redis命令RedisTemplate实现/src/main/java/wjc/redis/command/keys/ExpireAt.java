package wjc.redis.command.keys;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.data.redis.serializer.RedisSerializer;
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
public class ExpireAt extends Command<String, String> {

    @Test
    public void test() {
        template.opsForValue().set("mykey", "Hello");
        RedisSerializer<String> serializer = (RedisSerializer<String>) template.getKeySerializer();

        boolean exist = connection.exists(serializer.serialize("mykey"));
        System.out.println(exist);
        Assert.assertTrue(exist);

        connection.keyCommands().expireAt(serializer.serialize("mykey"), 1293840000);
        exist = connection.exists(serializer.serialize("mykey"));
        System.out.println(exist);
        Assert.assertFalse(exist);
    }
}
