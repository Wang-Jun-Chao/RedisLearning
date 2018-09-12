package wjc.redis.command.lists;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wjc.redis.Command;
import wjc.redis.util.StringUtils;

import java.util.List;

/**
 * @author: wangjunchao(王俊超)
 * @time: 2018-09-12 13:50
 **/
public class LIndex extends Command<String, String> {
    private final static Logger logger = LoggerFactory.getLogger(LIndex.class);

    @Test
    @Override
    public void testTemplate() {
        template.opsForList().leftPush("mylist", "World");
        template.opsForList().leftPush("mylist", "Hello");
        String value = template.opsForList().index("mylist", 0);
        System.out.println(value);
        Assert.assertEquals("Hello", value);

        value = template.opsForList().index("mylist", -1);
        System.out.println(value);
        Assert.assertEquals("World", value);

        value = template.opsForList().index("mylist", -3);
        System.out.println(value);
        Assert.assertNull(value);
    }

    @Test
    @Override
    public void testConnection() {
        template.opsForList().leftPush("mylist", "World");
        template.opsForList().leftPush("mylist", "Hello");

        byte[] list = keySerializer.serialize("mylist");
        byte[] value = connection.lIndex(list, 0);
        System.out.println(valueSerializer.deserialize(value));
        Assert.assertEquals("Hello", valueSerializer.deserialize(value));

        value = connection.lIndex(list, -1);
        System.out.println(valueSerializer.deserialize(value));
        Assert.assertEquals("World", valueSerializer.deserialize(value));

        value = connection.lIndex(list, -3);
        System.out.println(valueSerializer.deserialize(value));
        Assert.assertEquals(null, valueSerializer.deserialize(value));
    }
}
