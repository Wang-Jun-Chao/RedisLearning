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
public class SMove extends Command<String, String> {
    @Test
    @Override
    public void testTemplate() {
        template.opsForSet().add("myset", "one", "two");
        template.opsForSet().add("myotherset", "three");

        template.opsForSet().move("myset", "two", "myotherset");

        Set<String> members = template.opsForSet().members("myset");
        Set<String> values = Sets.newHashSet("one");
        System.out.println(values);
        Assert.assertTrue(members.containsAll(values));
        Assert.assertTrue(values.containsAll(members));

        members = template.opsForSet().members("myotherset");
        values = Sets.newHashSet("two", "three");
        System.out.println(values);
        Assert.assertTrue(members.containsAll(values));
        Assert.assertTrue(values.containsAll(members));
    }

    @Test
    @Override
    public void testConnection() {
        template.opsForSet().add("myset", "one", "two");
        template.opsForSet().add("myotherset", "three");

        connection.sMove(keySerializer.serialize("myset"),
                keySerializer.serialize("myotherset"),
                valueSerializer.serialize("two"));

        Set<String> members = template.opsForSet().members("myset");
        Set<String> values = Sets.newHashSet("one");
        System.out.println(values);
        Assert.assertTrue(members.containsAll(values));
        Assert.assertTrue(values.containsAll(members));

        members = template.opsForSet().members("myotherset");
        values = Sets.newHashSet("two", "three");
        System.out.println(values);
        Assert.assertTrue(members.containsAll(values));
        Assert.assertTrue(values.containsAll(members));
    }
}
