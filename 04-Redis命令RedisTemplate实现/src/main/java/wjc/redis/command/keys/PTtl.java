package wjc.redis.command.keys;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.serializer.RedisSerializer;
import wjc.redis.Command;

/**
 * @author: wangjunchao(王俊超)
 * @time: 2018-09-07 18:51
 **/
public class PTtl extends Command<String, String> {
    private final static Logger logger = LoggerFactory.getLogger(PTtl.class);

    @Test
    public void test() {
        template.opsForValue().set("mykey", "Hello");
        RedisSerializer<String> serializer = (RedisSerializer<String>) template.getKeySerializer();
        connection.keyCommands().expire(
                serializer.serialize("mykey"), 1);

        Long pttl = connection.pTtl(serializer.serialize("mykey"));
        System.out.println(pttl);
        Assert.assertEquals(Long.valueOf(999), pttl);
    }
}
