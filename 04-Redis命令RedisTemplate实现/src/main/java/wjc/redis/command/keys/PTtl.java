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
public class PTtl extends Command<String, String> {
    private final static Logger logger = LoggerFactory.getLogger(PTtl.class);

    @Test
    @Override
    public void testTemplate() {
        template.opsForValue().set("mykey", "Hello");
        template.expire("mykey", 1, TimeUnit.SECONDS);
        Long expire = template.getExpire("mykey", TimeUnit.MILLISECONDS);
        System.out.println(expire);
    }

    @Test
    @Override
    public void testConnection() {
        template.opsForValue().set("mykey", "Hello");
        connection.expire(keySerializer.serialize("mykey"), 1);

        Long pttl = connection.pTtl(keySerializer.serialize("mykey"));
        System.out.println(pttl);
        try {
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(Long.valueOf(999), pttl);
    }
}
