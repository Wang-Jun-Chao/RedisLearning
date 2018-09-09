package wjc.redis.command.keys;

import org.junit.Assert;
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

    @Override
    public void testTemplate() {
        template.opsForValue().set("key1", "Hello");

        Boolean hasKey = template.hasKey("key1");
        Assert.assertEquals(true, hasKey);

        hasKey = template.hasKey("key2");
        Assert.assertEquals(false, hasKey);
    }

    @Override
    public void testConnection() {

        template.opsForValue().set("key1", "Hello");

        Boolean exists = connection.exists(keySerializer.serialize("key1"));
        Assert.assertEquals(true, exists);

        exists = template.hasKey("key2");
        Assert.assertEquals(false, exists);

    }
}
