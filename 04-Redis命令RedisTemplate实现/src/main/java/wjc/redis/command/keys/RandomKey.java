package wjc.redis.command.keys;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wjc.redis.Command;

/**
 * @author: wangjunchao(王俊超)
 * @time: 2018-09-07 18:51
 **/
public class RandomKey extends Command<String, String> {
    private final static Logger logger = LoggerFactory.getLogger(RandomKey.class);

    @Test
    public void test() {

        String randomKey = template.randomKey();
        System.out.println(randomKey);
        Assert.assertNull(randomKey);

        template.opsForValue().set("mykey", "Hello");
        randomKey = template.randomKey();
        System.out.println(randomKey);
        Assert.assertNotNull(randomKey);
    }
}
