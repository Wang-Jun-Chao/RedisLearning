package wjc.redis.command.strings;

import org.junit.Assert;
import org.junit.Test;
import wjc.redis.Command;

import java.util.concurrent.TimeUnit;

/**
 * <pre>
 *
 * </pre>
 * Author: 王俊超
 * Date: 2018-09-11 07:09
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class SetEx extends Command<String, String> {

    @Test
    @Override
    public void testTemplate() {

        template.opsForValue().set("mykey", "value", 2, TimeUnit.SECONDS);
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Boolean value = template.hasKey("mykey");
        System.out.println(value);
        Assert.assertFalse(value);
    }

    @Test
    @Override
    public void testConnection() {

        connection.setEx(keySerializer.serialize("mykey"), 2,
                valueSerializer.serialize("value"));
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Boolean value = connection.exists(keySerializer.serialize("mykey"));
        System.out.println(value);
        Assert.assertFalse(value);

    }
}
