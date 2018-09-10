package wjc.redis.command.keys;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wjc.redis.Command;

/**
 * @author: wangjunchao(王俊超)
 * @time: 2018-09-07 18:51
 **/
public class PExpireAt extends Command<String, String> {
    private final static Logger logger = LoggerFactory.getLogger(PExpireAt.class);

    @Test
    @Override
    public void testTemplate() {
        System.out.println("use RedisTemplate.expire command");
    }

    @Test
    @Override
    public void testConnection() {
        template.opsForValue().set("mykey", "Hello");
        connection.pExpireAt(keySerializer.serialize("mykey"), 1555555555005L);

        // 四舍五入
        Long ttl = connection.ttl(keySerializer.serialize("mykey"));
        System.out.println(ttl);
//        Assert.assertEquals(Long.valueOf(1), ttl);

        ttl = connection.pTtl(keySerializer.serialize("mykey"));
        System.out.println(ttl);
//        Assert.assertTrue(Long.valueOf(1400).compareTo(ttl) > 0);
    }
}
