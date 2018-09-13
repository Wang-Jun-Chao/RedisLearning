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
public class SDiffStore extends Command<String, String> {
    @Test
    @Override
    public void testTemplate() {
        template.opsForSet().add("key1", "a", "b", "c");
        template.opsForSet().add("key2", "c", "d", "e");

        Long diff = template.opsForSet().differenceAndStore("key1", "key2", "key");
        System.out.println(diff);
        Assert.assertEquals(Long.valueOf(2), diff);

        Set<String> members = template.opsForSet().members("key");
        Set<String> values = Sets.newHashSet("a", "b");
        System.out.println(values);
        Assert.assertTrue(members.containsAll(values));
        Assert.assertTrue(values.containsAll(members));
    }

    @Test
    @Override
    public void testConnection() {
        template.opsForSet().add("key1", "a", "b", "c");
        template.opsForSet().add("key2", "c", "d", "e");

        Long diff = connection.sDiffStore(
                keySerializer.serialize("key"),
                keySerializer.serialize("key1"),
                keySerializer.serialize("key2"));
        System.out.println(diff);
        Assert.assertEquals(Long.valueOf(2), diff);

        Set<String> members = template.opsForSet().members("key");
        Set<String> values = Sets.newHashSet("a", "b");
        System.out.println(values);
        Assert.assertTrue(members.containsAll(values));
        Assert.assertTrue(values.containsAll(members));
    }
}
