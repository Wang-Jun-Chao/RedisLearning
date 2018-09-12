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
public class LTrim extends Command<String, String> {
    private final static Logger logger = LoggerFactory.getLogger(LTrim.class);

    @Test
    @Override
    public void testTemplate() {
        template.opsForList().rightPushAll("mylist", "one", "two", "three");
        template.opsForList().trim("mylist", 1, -1);

        List<String> range = template.opsForList().range("mylist", 0, -1);
        System.out.println(range);
        List<String> expected = Lists.newArrayList("two", "three");
        Assert.assertEquals(expected, range);

    }

    @Test
    @Override
    public void testConnection() {
        template.opsForList().rightPushAll("mylist", "one", "two", "three");

        connection.lTrim(keySerializer.serialize("mylist"), 1, -1);

        List<String> range = template.opsForList().range("mylist", 0, -1);
        System.out.println(range);
        List<String> expected = Lists.newArrayList("two", "three");
        Assert.assertEquals(expected, range);

    }


}
