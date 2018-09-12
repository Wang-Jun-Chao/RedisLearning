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
public class RPop extends Command<String, String> {
    private final static Logger logger = LoggerFactory.getLogger(RPop.class);

    @Test
    @Override
    public void testTemplate() {
        template.opsForList().rightPushAll("mylist", "one", "two", "three");

        String value = template.opsForList().rightPop("mylist");
        System.out.println(value);
        Assert.assertEquals("three", value);

        List<String> range = template.opsForList().range("mylist", 0, -1);
        List<String> expected = Lists.newArrayList("one", "two");
        System.out.println(range);
        Assert.assertEquals(expected, range);

    }

    @Test
    @Override
    public void testConnection() {
        template.opsForList().rightPushAll("mylist", "one", "two", "three");


        String value = valueSerializer.deserialize(connection.rPop(keySerializer.serialize("mylist")));
        System.out.println(value);
        Assert.assertEquals("three", value);

        List<String> range = template.opsForList().range("mylist", 0, -1);
        List<String> expected = Lists.newArrayList("one", "two");
        System.out.println(range);
        Assert.assertEquals(expected, range);

    }


}
