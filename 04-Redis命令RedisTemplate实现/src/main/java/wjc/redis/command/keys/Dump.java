package wjc.redis.command.keys;

import org.junit.Test;
import wjc.redis.Command;

import java.util.Arrays;

/**
 * <pre>
 *
 * </pre>
 * Author: 王俊超
 * Date: 2018-09-06 22:02
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class Dump extends Command<String, Integer> {

    @Test
    @Override
    public void testTemplate() {
        template.opsForValue().set("mykey", 10);

        byte[] dump = template.dump("mykey");
        System.out.println(Arrays.toString(dump));
    }

    @Test
    @Override
    public void testConnection() {
        template.opsForValue().set("mykey", 10);

        byte[] dump = connection.dump(keySerializer.serialize("mykey"));
        System.out.println(Arrays.toString(dump));
    }
}
