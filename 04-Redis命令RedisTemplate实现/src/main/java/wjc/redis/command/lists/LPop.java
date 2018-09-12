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
public class LPop extends Command<String, String> {
    private final static Logger logger = LoggerFactory.getLogger(LPop.class);

    @Test
    @Override
    public void testTemplate() {
        template.opsForList().rightPush("mylist", "one");
        template.opsForList().rightPush("mylist", "two");
        template.opsForList().rightPush("mylist", "three");

        String s = template.opsForList().leftPop("mylist");
        System.out.println(s);
        Assert.assertEquals("one", s);

        List<String> range = template.opsForList().range("mylist", 0, -1);
        System.out.println(range);
        List<String> expect = Lists.newArrayList("two", "three");
        Assert.assertTrue(expect.containsAll(range) && range.containsAll(expect));
    }

    @Test
    @Override
    public void testConnection() {
        template.opsForList().rightPush("mylist", "one");
        template.opsForList().rightPush("mylist", "two");
        template.opsForList().rightPush("mylist", "three");

        byte[] s = connection.lPop(keySerializer.serialize("mylist"));
        System.out.println(valueSerializer.deserialize(s));
        Assert.assertEquals("one", valueSerializer.deserialize(s));

        List<String> range = template.opsForList().range("mylist", 0, -1);
        System.out.println(range);
        List<String> expect = Lists.newArrayList("two", "three");
        Assert.assertTrue(expect.containsAll(range) && range.containsAll(expect));
    }
}
