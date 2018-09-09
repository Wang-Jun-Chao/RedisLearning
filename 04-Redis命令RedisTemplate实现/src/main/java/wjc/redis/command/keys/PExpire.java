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
public class PExpire extends Command<String, String> {
    private final static Logger logger = LoggerFactory.getLogger(PExpire.class);


    @Test
    @Override
    public void testTemplate() {
        template.opsForValue().set("mykey", "Hello");
        template.expire("key", 1400, TimeUnit.MILLISECONDS);
        Long expire = template.getExpire("key", TimeUnit.MICROSECONDS);
        Assert.assertTrue(Long.valueOf(1400).compareTo(expire) > 0);
    }

    @Test
    @Override
    public void testConnection() {
        template.opsForValue().set("mykey", "Hello");
        connection.pExpire(keySerializer.serialize("mykey"), 1400);

        // 四舍五入
        Long ttl = connection.ttl(keySerializer.serialize("mykey"));
        System.out.println(ttl);
        Assert.assertEquals(Long.valueOf(1), ttl);

        ttl = connection.pTtl(keySerializer.serialize("mykey"));
        System.out.println(ttl);
        Assert.assertTrue(Long.valueOf(1400).compareTo(ttl) > 0);
    }
}
