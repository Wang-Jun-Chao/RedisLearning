package wjc.redis.command;

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
public class PExpire extends Command<String, String> {
    private final static Logger logger = LoggerFactory.getLogger(PExpire.class);

    @Test
    public void test() {
        template.opsForValue().set("mykey", "Hello");
        RedisSerializer<String> serializer = (RedisSerializer<String>) template.getKeySerializer();
        template.getConnectionFactory().getConnection().keyCommands().pExpire(
                serializer.serialize("mykey"), 1400);

        // 四舍五入
        Long ttl = template.getConnectionFactory().getConnection().keyCommands().ttl(
                serializer.serialize("mykey"));
        System.out.println(ttl);
        Assert.assertEquals(Long.valueOf(1), ttl);

        ttl = template.getConnectionFactory().getConnection().keyCommands().pTtl(
                serializer.serialize("mykey"));
        System.out.println(ttl);
        Assert.assertTrue(Long.valueOf(1400).compareTo(ttl) > 0);
    }
}
