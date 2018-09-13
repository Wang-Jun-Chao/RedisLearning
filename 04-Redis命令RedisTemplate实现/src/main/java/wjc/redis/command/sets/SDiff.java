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
public class SDiff extends Command<String, String> {
    @Test
    @Override
    public void testTemplate() {
        template.opsForSet().add("key1", "a", "b", "c");
        template.opsForSet().add("key2", "c", "d", "e");
        Set<String> diff = template.opsForSet().difference("key1", "key2");
        System.out.println(diff);
        Set<String> expected = Sets.newHashSet("a", "b");
        Assert.assertTrue(diff.containsAll(expected));
        Assert.assertTrue(expected.containsAll(diff));
    }

    @Test
    @Override
    public void testConnection() {
        template.opsForSet().add("key1", "a", "b", "c");
        template.opsForSet().add("key2", "c", "d", "e");
        Set<byte[]> byteDiff = connection.sDiff(keySerializer.serialize("key1"),
                keySerializer.serialize("key2"));
        Set<String> diff = Sets.newHashSet();
        byteDiff.forEach(item -> diff.add(valueSerializer.deserialize(item)));
        System.out.println(diff);
        Set<String> expected = Sets.newHashSet("a", "b");
        Assert.assertTrue(diff.containsAll(expected));
        Assert.assertTrue(expected.containsAll(diff));
    }
}
