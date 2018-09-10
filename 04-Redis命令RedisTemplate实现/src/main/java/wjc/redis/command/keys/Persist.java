package wjc.redis.command.keys;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wjc.redis.Command;

import java.util.concurrent.TimeUnit;

/**
 * @author: wangjunchao(王俊超)
 * @time: 2018-09-07 18:51
 **/
public class Persist extends Command<String, String> {
    private final static Logger logger = LoggerFactory.getLogger(Persist.class);

    @Test
    @Override
    public void testTemplate() {
        template.opsForValue().set("mykey", "Hello");
        template.expire("mykey", 10, TimeUnit.SECONDS);
        Long ttl = connection.ttl(keySerializer.serialize("mykey"));
        System.out.println(ttl);
        Assert.assertEquals(Long.valueOf(10), ttl);

        template.persist("mykey");
        ttl = connection.ttl(keySerializer.serialize("mykey"));
        System.out.println(ttl);

        Assert.assertEquals(Long.valueOf(-1), ttl);
    }

    @Test
    @Override
    public void testConnection() {
        template.opsForValue().set("mykey", "Hello");
        template.expire("mykey", 10, TimeUnit.SECONDS);
        Long ttl = connection.ttl(keySerializer.serialize("mykey"));
        System.out.println(ttl);
        Assert.assertEquals(Long.valueOf(10), ttl);

        connection.persist(keySerializer.serialize("mykey"));
        ttl = connection.ttl(keySerializer.serialize("mykey"));
        System.out.println(ttl);

        Assert.assertEquals(Long.valueOf(-1), ttl);
    }
}
