package wjc.redis.command.sets;

import org.junit.Assert;
import org.junit.Test;
import wjc.redis.Command;

/**
 * <pre>
 *
 * </pre>
 * Author: 王俊超
 * Date: 2018-09-13 22:51
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class SIsMember extends Command<String, String> {
    @Test
    @Override
    public void testTemplate() {
        template.opsForSet().add("myset", "one");

        Boolean member = template.opsForSet().isMember("myset", "one");
        System.out.println(member);
        Assert.assertTrue(member);

        member = template.opsForSet().isMember("myset", "two");
        System.out.println(member);
        Assert.assertFalse(member);
    }

    @Test
    @Override
    public void testConnection() {
        template.opsForSet().add("myset", "one");

        Boolean member = connection.sIsMember(keySerializer.serialize("myset"),
                valueSerializer.serialize("one"));
        System.out.println(member);
        Assert.assertTrue(member);

        member = connection.sIsMember(keySerializer.serialize("myset"),
                valueSerializer.serialize("two"));
        System.out.println(member);
        Assert.assertFalse(member);
    }
}
