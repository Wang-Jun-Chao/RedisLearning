package wjc.redis.command;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wjc.redis.Command;

/**
 * @author: wangjunchao(王俊超)
 * @time: 2018-09-07 18:51
 **/
public class Rename extends Command<String, String> {
    private final static Logger logger = LoggerFactory.getLogger(Rename.class);

    @Test
    public void test() {
        template.opsForValue().set("mykey", "Hello");
        template.rename("mykey",  "myotherkey");
        String value = template.opsForValue().get("myotherkey");
        System.out.println(value);
        Assert.assertNotNull("Hello", value);
    }
}
