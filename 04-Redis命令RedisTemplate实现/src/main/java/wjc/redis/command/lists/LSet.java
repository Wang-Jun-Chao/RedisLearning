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
public class LSet extends Command<String, String> {
    private final static Logger logger = LoggerFactory.getLogger(LSet.class);

    @Test
    @Override
    public void testTemplate() {
        template.opsForList().rightPushAll("mylist", "one", "two", "three");
        template.opsForList().set("mylist", 0, "four");
        template.opsForList().set("mylist", -2, "five");

        List<String> range = template.opsForList().range("mylist", 0, -1);
        System.out.println(range);
        List<String> expected = Lists.newArrayList("four", "five", "three");
        Assert.assertEquals(expected, range);

    }

    @Test
    @Override
    public void testConnection() {
        template.opsForList().rightPushAll("mylist", "one", "two", "three");

        connection.lSet(keySerializer.serialize("mylist"), 0, valueSerializer.serialize("four"));
        connection.lSet(keySerializer.serialize("mylist"), -2, valueSerializer.serialize("five"));

        List<String> range = template.opsForList().range("mylist", 0, -1);
        System.out.println(range);
        List<String> expected = Lists.newArrayList("four", "five", "three");
        Assert.assertEquals(expected, range);

    }


}
