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
public class RenameNx extends Command<String, String> {
    private final static Logger logger = LoggerFactory.getLogger(RenameNx.class);

    @Test
    public void testTemplate() {
        template.opsForValue().set("mykey", "Hello");
        template.opsForValue().set("myotherkey", "World");
        Boolean result = template.renameIfAbsent("mykey", "myotherkey");
        Assert.assertFalse(result);

        String value = template.opsForValue().get("myotherkey");
        System.out.println(value);
        Assert.assertNotNull("World", value);
    }

    @Test
    public void testConnection() {

        template.opsForValue().set("mykey", "Hello");
        template.opsForValue().set("myotherkey", "World");

        String value = template.opsForValue().get("myotherkey");
        Boolean result = connection.renameNX(keySerializer.serialize("mykey"),
                keySerializer.serialize("myotherkey"));
        System.out.println(value);
        Assert.assertFalse(result);

        value = template.opsForValue().get("myotherkey");
        System.out.println(value);
        Assert.assertNotNull("World", value);
    }
}
