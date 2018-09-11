package wjc.redis.command.strings;

import com.google.common.collect.Maps;
import org.junit.Assert;
import org.junit.Test;
import wjc.redis.Command;

import java.util.Map;
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
public class PSetEx extends Command<String, String> {

    @Test
    @Override
    public void testTemplate() {

        template.opsForValue().set("mykey", "Hello", 1000, TimeUnit.MILLISECONDS);

        Long expire = template.getExpire("mykey", TimeUnit.MILLISECONDS);
        System.out.println(expire);
        try {
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(1000 > expire);
    }

    @Test
    @Override
    public void testConnection() {

        Map<byte[], byte[]> map = Maps.newHashMap();
        map.put(keySerializer.serialize("key1"), valueSerializer.serialize("Hello"));
        map.put(keySerializer.serialize("key2"), valueSerializer.serialize("there"));

        Map<byte[], byte[]> map2 = Maps.newHashMap();
        map2.put(keySerializer.serialize("key2"), valueSerializer.serialize("there"));
        map2.put(keySerializer.serialize("key3"), valueSerializer.serialize("World"));

        connection.pSetEx(keySerializer.serialize("key"), 1000, valueSerializer.serialize("Hello"));

        Long pTtl = connection.pTtl(keySerializer.serialize("key"));

        try {
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(pTtl);
        Assert.assertTrue(1000 > pTtl);
    }
}
