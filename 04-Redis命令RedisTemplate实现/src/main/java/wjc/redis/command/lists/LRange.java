package wjc.redis.command.lists;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.serializer.RedisSerializer;
import wjc.redis.Command;
import wjc.redis.util.RedisUtils;

import java.util.List;
import java.util.Optional;

/**
 * @author: wangjunchao(王俊超)
 * @time: 2018-09-12 13:50
 **/
public class LRange extends Command<String, String> {
    private final static Logger logger = LoggerFactory.getLogger(LRange.class);

    @Test
    @Override
    public void testTemplate() {
        template.opsForList().rightPushAll("mylist", "one", "two", "three");
        List<String> range = template.opsForList().range("mylist", 0, 0);
        System.out.println(range);
        List<String> expected = Lists.newArrayList("one");
        Assert.assertEquals(expected.containsAll(range), range.containsAll(expected));

        range = template.opsForList().range("mylist", -3, 2);
        System.out.println(range);
        expected = Lists.newArrayList("one", "two", "three");
        Assert.assertEquals(expected.containsAll(range), range.containsAll(expected));

        range = template.opsForList().range("mylist", -100, 100);
        System.out.println(range);
        expected = Lists.newArrayList("one", "two", "three");
        Assert.assertEquals(expected.containsAll(range), range.containsAll(expected));

        range = template.opsForList().range("mylist", 5, 10);
        System.out.println(range);
        expected = Lists.newArrayList();
        Assert.assertEquals(expected.containsAll(range), range.containsAll(expected));
    }

    @Test
    @Override
    public void testConnection() {
        template.opsForList().rightPushAll("mylist", "one", "two", "three");

        List<String> range = RedisUtils.toStringList(
                connection.lRange(keySerializer.serialize("mylist"), 0, 0),
                valueSerializer);
        System.out.println(range);
        List<String> expected = Lists.newArrayList("one");
        Assert.assertEquals(expected.containsAll(range), range.containsAll(expected));

       range = RedisUtils.toStringList(
                connection.lRange(keySerializer.serialize("mylist"), -3, 2),
                valueSerializer);
        System.out.println(range);
        expected = Lists.newArrayList("one", "two", "three");
        Assert.assertEquals(expected.containsAll(range), range.containsAll(expected));

        range = RedisUtils.toStringList(
                connection.lRange(keySerializer.serialize("mylist"), -100, 100),
                valueSerializer);
        System.out.println(range);
        expected = Lists.newArrayList("one", "two", "three");
        Assert.assertEquals(expected.containsAll(range), range.containsAll(expected));

        range = RedisUtils.toStringList(
                connection.lRange(keySerializer.serialize("mylist"), 5, 10),
                valueSerializer);
        System.out.println(range);
        expected = Lists.newArrayList();
        Assert.assertEquals(expected.containsAll(range), range.containsAll(expected));

    }

}
