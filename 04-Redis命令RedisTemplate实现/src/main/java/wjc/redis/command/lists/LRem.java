package wjc.redis.command.lists;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.serializer.RedisSerializer;
import wjc.redis.Command;

import java.util.List;
import java.util.Optional;

/**
 * @author: wangjunchao(王俊超)
 * @time: 2018-09-12 13:50
 **/
public class LRem extends Command<String, String> {
    private final static Logger logger = LoggerFactory.getLogger(LRem.class);

    @Test
    @Override
    public void testTemplate() {
        template.opsForList().rightPushAll("mylist", "hello", "hello", "foo", "hello");
        template.opsForList().remove("mylist", -2, "hello");

        List<String> range = template.opsForList().range("mylist", 0, -1);
        System.out.println(range);
        List<String> expected = Lists.newArrayList("hello", "foo");
        Assert.assertEquals(expected, range);

    }

    @Test
    @Override
    public void testConnection() {
        template.opsForList().rightPushAll("mylist", "hello", "hello", "foo", "hello");

        connection.lRem(keySerializer.serialize("mylist"), -2, valueSerializer.serialize("hello"));

        List<String> range = template.opsForList().range("mylist", 0, -1);
        System.out.println(range);
        List<String> expected = Lists.newArrayList("hello", "foo");
        Assert.assertEquals(expected, range);

    }


}
