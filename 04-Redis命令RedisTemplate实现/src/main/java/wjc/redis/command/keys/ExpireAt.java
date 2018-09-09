package wjc.redis.command.keys;

import org.junit.Assert;
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

    @Override
    public void testTemplate() {
        System.out.println("Use RedisTemplate.expire");
        Assert.fail();
    }

    @Override
    public void testConnection() {
        template.opsForValue().set("mykey", "Hello");

        boolean exist = connection.exists(keySerializer.serialize("mykey"));
        System.out.println(exist);
        Assert.assertTrue(exist);

        connection.keyCommands().expireAt(keySerializer.serialize("mykey"), 1293840000);
        exist = connection.exists(keySerializer.serialize("mykey"));
        System.out.println(exist);
        Assert.assertFalse(exist);


    }
}
