package wjc.redis.command.keys;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wjc.redis.Command;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @author: wangjunchao(王俊超)
 * @time: 2018-09-07 18:51
 **/
public class Restore extends Command<String, String> {
    private final static Logger logger = LoggerFactory.getLogger(Restore.class);

    @Test
    public void testTemplate() {
        template.opsForValue().set("mykey", "Hello");
        byte[] dump = template.dump("mykey");
        System.out.println(Arrays.toString(dump));

        template.delete("mykey");
        template.restore("mykey", dump, 0, TimeUnit.SECONDS);

        String value = template.opsForValue().get("mykey");
        System.out.println(value);
        Assert.assertEquals("Hello", value);
    }

    @Test
    public void testConnection() {
        template.opsForValue().set("mykey", "Hello");
        byte[] dump = template.dump("mykey");
        System.out.println(Arrays.toString(dump));

        template.delete("mykey");
        connection.restore(keySerializer.serialize("mykey"), 0, dump);

        String value = template.opsForValue().get("mykey");
        System.out.println(value);
        Assert.assertEquals("Hello", value);
    }
}
