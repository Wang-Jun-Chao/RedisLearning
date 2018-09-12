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
public class BRPopLPush extends Command<String, String> {
    private final static Logger logger = LoggerFactory.getLogger(BRPopLPush.class);

    @Test
    @Override
    public void testTemplate() {
        template.delete(Lists.newArrayList("job", "command", "request"));
        template.opsForList().leftPush("command", "update system...");
        template.opsForList().leftPush("request", "visit page");

        Assert.fail("RedisTemplate dose not have brpoplpush command");
    }

    @Test
    @Override
    public void testConnection() {
        template.opsForList().rightPush("msg", "hello moto");


        byte[] value = connection.bRPopLPush(3, keySerializer.serialize("msg"),
                keySerializer.serialize("receiver"));
        System.out.println(valueSerializer.deserialize(value));
        Assert.assertEquals("hello moto", valueSerializer.deserialize(value));

        Long size = template.opsForList().size("receiver");
        System.out.println(size);
        Assert.assertEquals(Long.valueOf(1), size);

        List<byte[]> bytes = connection.lRange(keySerializer.serialize("receiver"), 0, 0);
        String s = RedisUtils.toString(bytes, valueSerializer);
        System.out.println(s);
    }

    @Test
    public void testConnection2() {
        byte[] value = connection.bRPopLPush(3, keySerializer.serialize("msg"),
                keySerializer.serialize("receiver"));
        System.out.println(valueSerializer.deserialize(value));
        Assert.assertEquals(null, valueSerializer.deserialize(value));

        List<String> list = template.opsForList().range("receiver", 0, 0);
        System.out.println(list);
    }
}
