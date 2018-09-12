package wjc.redis.command.lists;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wjc.redis.Command;
import wjc.redis.util.RedisUtils;

import java.util.List;

/**
 * @author: wangjunchao(王俊超)
 * @time: 2018-09-12 13:50
 **/
public class RPopLPush extends Command<String, String> {
    private final static Logger logger = LoggerFactory.getLogger(RPopLPush.class);

    @Test
    @Override
    public void testTemplate() {
        template.opsForList().rightPushAll("mylist", "one", "two", "three");
        String s = template.opsForList().rightPopAndLeftPush("mylist", "myotherlist");
        System.out.println(s);
        Assert.assertEquals("three", s);

        List<String> list = template.opsForList().range("mylist", 0, -1);
        System.out.println(list);
        List<String> expected = Lists.newArrayList("one", "two");
        Assert.assertEquals(expected.containsAll(list), list.containsAll(expected));

        list = template.opsForList().range("myotherlist", 0, -1);
        System.out.println(list);
        expected = Lists.newArrayList("three");
        Assert.assertEquals(expected.containsAll(list), list.containsAll(expected));
    }

    @Test
    @Override
    public void testConnection() {
        template.opsForList().rightPushAll("mylist", "one", "two", "three");
        String s = valueSerializer.deserialize(connection.rPopLPush(
                keySerializer.serialize("mylist"),
                keySerializer.serialize("myotherlist")));

        System.out.println(s);
        Assert.assertEquals("three", s);

        List<String> list = template.opsForList().range("mylist", 0, -1);
        System.out.println(list);
        List<String> expected = Lists.newArrayList("one", "two");
        Assert.assertEquals(expected.containsAll(list), list.containsAll(expected));

        list = template.opsForList().range("myotherlist", 0, -1);
        System.out.println(list);
        expected = Lists.newArrayList("three");
        Assert.assertEquals(expected.containsAll(list), list.containsAll(expected));
    }


}
