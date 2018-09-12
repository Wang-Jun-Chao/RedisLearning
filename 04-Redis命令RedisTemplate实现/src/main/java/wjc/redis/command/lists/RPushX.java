package wjc.redis.command.lists;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wjc.redis.Command;

import java.util.List;

/**
 * @author: wangjunchao(王俊超)
 * @time: 2018-09-12 13:50
 **/
public class RPushX extends Command<String, String> {
    private final static Logger logger = LoggerFactory.getLogger(RPushX.class);

    @Test
    @Override
    public void testTemplate() {
        template.opsForList().rightPush("mylist", "Hello");
        template.opsForList().rightPush("mylist", "World");

        template.opsForList().rightPushIfPresent("myotherlist", "Hello");

        List<String> range = template.opsForList().range("mylist", 0, -1);
        System.out.println(range);
        List<String> expect = Lists.newArrayList("Hello", "World");
        Assert.assertTrue(expect.containsAll(range) && range.containsAll(expect));

        List<String> list = template.opsForList().range("myotherlist", 0, -1);
        System.out.println(list);
        Assert.assertTrue(list.isEmpty());
    }

    @Test
    @Override
    public void testConnection() {
        connection.rPush(
                keySerializer.serialize("mylist"),
                valueSerializer.serialize("Hello"),
                valueSerializer.serialize("World"));

        connection.rPushX(keySerializer.serialize("myotherlist"), valueSerializer.serialize("Hello"));

        List<String> range = template.opsForList().range("mylist", 0, -1);
        System.out.println(range);
        List<String> expect = Lists.newArrayList("Hello", "World");
        Assert.assertTrue(expect.containsAll(range) && range.containsAll(expect));

        List<String> list = template.opsForList().range("myotherlist", 0, -1);
        System.out.println(list);
        Assert.assertTrue(list.isEmpty());
    }
}
