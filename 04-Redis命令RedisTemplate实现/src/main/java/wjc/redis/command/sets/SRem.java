package wjc.redis.command.sets;

import com.google.common.collect.Sets;
import org.junit.Assert;
import org.junit.Test;
import wjc.redis.Command;

import java.util.Set;

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
public class SRem extends Command<String, String> {
    @Test
    @Override
    public void testTemplate() {
        template.opsForSet().add("myset", "one", "two", "three");

        template.opsForSet().remove("myset", "one", "four");

        Set<String> members = template.opsForSet().members("myset");

        Set<String> values = Sets.newHashSet("two", "three");
        System.out.println(values);
        Assert.assertTrue(members.containsAll(values));
        Assert.assertTrue(values.containsAll(members));
    }

    @Test
    @Override
    public void testConnection() {
        template.opsForSet().add("myset", "one", "two", "three");

        connection.sRem(keySerializer.serialize("myset"),
                valueSerializer.serialize("one"),
                valueSerializer.serialize("four"));

        Set<String> members = template.opsForSet().members("myset");

        Set<String> values = Sets.newHashSet("two", "three");
        System.out.println(values);
        Assert.assertTrue(members.containsAll(values));
        Assert.assertTrue(values.containsAll(members));
    }
}
