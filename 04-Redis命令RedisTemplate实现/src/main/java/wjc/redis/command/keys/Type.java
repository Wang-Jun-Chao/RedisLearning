package wjc.redis.command.keys;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.data.redis.connection.DataType;
import wjc.redis.Command;

/**
 * <pre>
 *
 * </pre>
 * Author: 王俊超
 * Date: 2018-09-09 22:45
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class Type extends Command<String, String> {

    @Test
    @Override
    public void testTemplate() {
        template.opsForValue().set("key1", "value");
        template.opsForList().rightPush("key2", "value");
        template.opsForSet().add("key3", "value");

        DataType type = template.type("key1");
        System.out.println(type);
        Assert.assertEquals(DataType.STRING, type);

        type = template.type("key2");
        System.out.println(type);
        Assert.assertEquals(DataType.LIST, type);

        type = template.type("key3");
        System.out.println(type);
        Assert.assertEquals(DataType.SET, type);
    }

    @Test
    @Override
    public void testConnection() {
        byte[] mykey = keySerializer.serialize("mykey");
        connection.set(mykey, valueSerializer.serialize("Hello"));
        connection.expire(mykey, 10);
        Long ttl = connection.ttl(mykey);

        System.out.println(ttl);
        Assert.assertEquals(Long.valueOf(10), ttl);
    }
}
