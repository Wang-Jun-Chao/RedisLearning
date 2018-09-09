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

        connection.set(keySerializer.serialize("key1"), valueSerializer.serialize("value"));
        connection.lPush(keySerializer.serialize("key2"), valueSerializer.serialize("value"));
        connection.sAdd(keySerializer.serialize("key3"), valueSerializer.serialize("value"));


        DataType type = connection.type(keySerializer.serialize("key1"));
        System.out.println(type);
        Assert.assertEquals(DataType.STRING, type);

        type = connection.type(keySerializer.serialize("key2"));
        System.out.println(type);
        Assert.assertEquals(DataType.LIST, type);

        type = connection.type(keySerializer.serialize("key3"));
        System.out.println(type);
        Assert.assertEquals(DataType.SET, type);
    }
}
