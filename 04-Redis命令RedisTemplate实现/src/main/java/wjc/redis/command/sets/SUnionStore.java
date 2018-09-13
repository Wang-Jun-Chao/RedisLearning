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
public class SUnionStore extends Command<String, String> {
    @Test
    @Override
    public void testTemplate() {
        template.opsForSet().add("key1", "a", "b", "c");
        template.opsForSet().add("key2", "c", "d", "e");

        Long union = template.opsForSet().unionAndStore("key1", "key2", "key");
        System.out.println(union);
        Assert.assertEquals(Long.valueOf(5), union);

        Set<String> members = template.opsForSet().members("key");
        Set<String> expected = Sets.newHashSet("a", "b", "c", "d", "e");
        System.out.println(expected);
        Assert.assertTrue(members.containsAll(expected));
        Assert.assertTrue(expected.containsAll(members));
    }

    @Test
    @Override
    public void testConnection() {
        template.opsForSet().add("key1", "a", "b", "c");
        template.opsForSet().add("key2", "c", "d", "e");

        Long union = connection.sUnionStore(
                keySerializer.serialize("key"),
                keySerializer.serialize("key1"),
                keySerializer.serialize("key2"));
        System.out.println(union);
        Assert.assertEquals(Long.valueOf(5), union);

        Set<String> members = template.opsForSet().members("key");
        Set<String> expected = Sets.newHashSet("a", "b", "c", "d", "e");
        System.out.println(expected);
        Assert.assertTrue(members.containsAll(expected));
        Assert.assertTrue(expected.containsAll(members));
    }
}
