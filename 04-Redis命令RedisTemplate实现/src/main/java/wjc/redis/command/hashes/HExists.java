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
public class HExists extends Command<String, String> {
    @Test
    @Override
    public void testTemplate() {
        template.opsForHash().put("myhash", "field1", "foo");
        Boolean hasKey = template.opsForHash().hasKey("myhash", "field1");
        System.out.println(hasKey);
        Assert.assertTrue(hasKey);

        hasKey = template.opsForHash().hasKey("myhash", "field2");
        System.out.println(hasKey);
        Assert.assertFalse(hasKey);
    }

    @Test
    @Override
    public void testConnection() {
        template.opsForHash().put("myhash", "field1", "foo");

        Boolean hasKey = connection.hExists(hashKeySerializer.serialize("myhash"),
                hashValueSerializer.serialize("field1"));
        System.out.println(hasKey);
        Assert.assertTrue(hasKey);

        hasKey = connection.hExists(hashKeySerializer.serialize("myhash"),
                hashValueSerializer.serialize("field2"));
        System.out.println(hasKey);
        Assert.assertFalse(hasKey);
    }
}
