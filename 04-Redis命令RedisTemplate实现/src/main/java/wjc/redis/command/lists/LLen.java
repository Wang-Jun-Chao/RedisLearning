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
public class LLen extends Command<String, String> {
    private final static Logger logger = LoggerFactory.getLogger(LLen.class);

    @Test
    @Override
    public void testTemplate() {
        template.opsForList().leftPush("mylist", "World");
        template.opsForList().leftPush("mylist", "Hello");
        Long size = template.opsForList().size("mylist");
        System.out.println(size);
        Assert.assertEquals(Long.valueOf(2), size);
    }

    @Test
    @Override
    public void testConnection() {
        template.opsForList().leftPush("mylist", "World");
        template.opsForList().leftPush("mylist", "Hello");

        Long size = connection.lLen(keySerializer.serialize("mylist"));
        System.out.println(size);
        Assert.assertEquals(Long.valueOf(2), size);
    }
}
