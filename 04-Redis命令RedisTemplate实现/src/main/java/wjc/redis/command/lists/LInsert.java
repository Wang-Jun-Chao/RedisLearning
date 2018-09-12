package wjc.redis.command.lists;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.RedisListCommands;
import wjc.redis.Command;

import java.util.List;

/**
 * @author: wangjunchao(王俊超)
 * @time: 2018-09-12 13:50
 **/
public class LInsert extends Command<String, String> {
    private final static Logger logger = LoggerFactory.getLogger(LInsert.class);

    @Test
    @Override
    public void testTemplate() {
        Assert.fail("RedisTemplate dose not have command linsert");
    }

    @Test
    @Override
    public void testConnection() {
        template.opsForList().rightPush("mylist", "Hello");
        template.opsForList().rightPush("mylist", "World");

        Long insert = connection.lInsert(
                keySerializer.serialize("mylist"),
                RedisListCommands.Position.BEFORE,
                valueSerializer.serialize("World"),
                valueSerializer.serialize("There"));

        List<String> value = template.opsForList().range("mylist", 0, -1);
        System.out.println(value);
        List<String> expect = Lists.newArrayList("Hello", "There", "World");
        Assert.assertTrue(expect.containsAll(value) && value.containsAll(expect));
    }
}
