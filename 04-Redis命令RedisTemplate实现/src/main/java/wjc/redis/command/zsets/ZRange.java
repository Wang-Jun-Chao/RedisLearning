package wjc.redis.command.zsets;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wjc.redis.Command;
import wjc.redis.util.RedisUtils;

import java.util.List;
import java.util.Set;

/**
 * @author: wangjunchao(王俊超)
 * @time: 2018-09-12 13:50
 **/
public class ZRange extends Command<String, String> {
    private final static Logger logger = LoggerFactory.getLogger(ZRange.class);

    @Test
    @Override
    public void testTemplate() {
        template.opsForZSet().add("myzset", "one", 1);
        template.opsForZSet().add("myzset", "two", 2);
        template.opsForZSet().add("myzset", "three", 3);

        Set<String> range = template.opsForZSet().range("myzset", 0, -1);
        System.out.println(range);
        Set<String> expected = Sets.newHashSet("one", "two", "three");
        Assert.assertEquals(expected.containsAll(range), range.containsAll(expected));

        range = template.opsForZSet().range("myzset", 2, 3);
        System.out.println(range);
        expected = Sets.newHashSet( "three");
        Assert.assertEquals(expected.containsAll(range), range.containsAll(expected));

        range = template.opsForZSet().range("myzset", -2, -1);
        System.out.println(range);
        expected = Sets.newHashSet("two", "three");
        Assert.assertEquals(expected.containsAll(range), range.containsAll(expected));
    }

    @Test
    @Override
    public void testConnection() {
        template.opsForZSet().add("myzset", "one", 1);
        template.opsForZSet().add("myzset", "two", 2);
        template.opsForZSet().add("myzset", "three", 3);

        final Set<String> range = Sets.newHashSet();
        connection.zRange(keySerializer.serialize("myzset"), 0, -1)
            .forEach(item->range.add(valueSerializer.deserialize(item)));
        System.out.println(range);
        Set<String> expected = Sets.newHashSet("one", "two", "three");
        Assert.assertEquals(expected.containsAll(range), range.containsAll(expected));

        range.clear();
        connection.zRange(keySerializer.serialize("myzset"), 2, 3)
                .forEach(item->range.add(valueSerializer.deserialize(item)));
        System.out.println(range);
        expected = Sets.newHashSet( "three");
        Assert.assertEquals(expected.containsAll(range), range.containsAll(expected));

        range.clear();
        connection.zRange(keySerializer.serialize("myzset"), -2, -1)
                .forEach(item->range.add(valueSerializer.deserialize(item)));
        System.out.println(range);
        expected = Sets.newHashSet("two", "three");
        Assert.assertEquals(expected.containsAll(range), range.containsAll(expected));
    }

}
